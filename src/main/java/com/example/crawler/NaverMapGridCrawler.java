
package com.example.crawler;

import com.example.crawler.dto.Article;
import com.example.crawler.dto.ArticleListResponse;
import com.example.service.KakaoGeocodingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NaverMapGridCrawler {

  private final WebClient webClient;
  private final KakaoGeocodingService geocodingService;


  /**
   * 병렬 크롤링
   */
  public Flux<Article> crawlAllGrids(Flux<GridArea> gridFlux) {
    return gridFlux
            .buffer(1) // 1개씩 묶음. 쓰레드 개수만큼 요청하기에 쓰레드당 1건씩 처리
            .concatMap(batch ->
                    Flux.fromIterable(batch)
                            .parallel()
                            .runOn(Schedulers.parallel())
                            .flatMap(this::crawlOneGrid)
                            .sequential()
                            .delaySubscription(Duration.ofSeconds(1)) // 1초 딜레이
            )
            .doOnNext(a -> log.info("Article: {}", a.getAtclNm()));
  }

  public Flux<Article> crawlByRegion(String region) {
    if (region != null && !region.isBlank()) {
      return geocodingService.reverseGeocode(region)
              .flatMapMany(doc -> {
                double lat = Double.parseDouble(doc.getY());
                double lon = Double.parseDouble(doc.getX());
                double top = lat + 0.1;
                double btm = lat;
                double lft = lon;
                double rgt = lon + 0.1;

                String depth3 = doc.getAddress().getRegion_3depth_h_name();
                double delta;
                double step;
                int z;

                if (depth3 != null && !depth3.isBlank()) {
                  // 동 단위일 경우: 좁게 크롤링
                  delta = 0.005;
                  step = 0.002;
                  z = 15;
                } else {
                  // 시/구 단위일 경우: 넓게 크롤링
                  delta = 0.05;
                  step = 0.02;
                  z = 11;
                }
                List<GridArea> grids = generateGrid(0.002, lat - delta, lat + delta, lon - delta, lon + delta, z);
                return Flux.fromIterable(grids);
              })
              .transform(this::crawlAllGrids);
    } else {
      List<GridArea> grids = generateNationwideGrid(0.1);
      return crawlAllGrids(Flux.fromIterable(grids));
    }
  }

  private Flux<Article> crawlOneGrid(GridArea area) {
    return webClient.get()
            .uri(uriBuilder -> uriBuilder
                    .path("/cluster/ajax/articleList")
                    .queryParam("z", area.z())
                    .queryParam("lat", area.lat())
                    .queryParam("lon", area.lon())
                    .queryParam("btm", area.btm())
                    .queryParam("lft", area.lft())
                    .queryParam("top", area.top())
                    .queryParam("rgt", area.rgt())
                    .build())
            .retrieve()
            .bodyToMono(ArticleListResponse.class)
            .flatMapMany(response -> {
              if (response.getBody() == null) {
                log.warn("Not Found");
                return Flux.empty();
              }
              return Flux.fromIterable(response.getBody());
            });
  }

  public List<GridArea> generateGrid(double step, double minLat, double maxLat, double minLon, double maxLon, int z) {
    List<GridArea> gridAreas = new ArrayList<>();

    for (double lat = minLat; lat < maxLat; lat += step) {
      for (double lon = minLon; lon < maxLon; lon += step) {
        double top = lat + step;
        double btm = lat;
        double lft = lon;
        double rgt = lon + step;

        double centerLat = (top + btm) / 2.0;
        double centerLon = (lft + rgt) / 2.0;

        gridAreas.add(new GridArea(centerLat, centerLon, lft, btm, rgt, top, z,
                String.format("(%.6f,%.6f)", centerLat, centerLon)));
      }
    }

    return gridAreas;
  }

  public List<GridArea> generateNationwideGrid(double step) {
    return generateGrid(step, 33.0, 38.0, 124.9, 131.0, 9);
  }
}
