
package com.example.crawler;

import com.example.crawler.dto.Article;
import com.example.crawler.dto.ArticleListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CrawlerController {

    private final NaverMapGridCrawler crawler;

    @GetMapping("/crawl")
    public Flux<Article> crawl(@RequestParam(required = false) String region) {
        return crawler.crawlByRegion(region);
    }

}
