/*
 * Created by Hochan Son on 2025. 7. 24.
 * As part of
 *
 * Copyright (C)  () - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Backend Team <hc.son9@google.com>, 2025. 7. 24.
 */

package com.example.service;

import com.example.dto.Document;
import com.example.dto.KakaoAddressResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * create on 2025. 7. 24. create by IntelliJ IDEA.
 * create by IntelliJ IDEA.
 *
 * <p>클래스 설명. </p>
 * <p> {@link } and {@link }관련 클래스 </p>
 *
 * @author Hochan Son
 * @version 1.0
 * @see
 * @since 지원하는 자바버전 (ex : 5+ 5이상)
 */

@Slf4j
@Service
public class KakaoGeocodingService {

  private final WebClient webClient;

  public KakaoGeocodingService(@Value("${kakao.api.key}") String apiKey) {
    webClient = WebClient.builder()
            .baseUrl("https://dapi.kakao.com")
            .defaultHeader("Authorization", "KakaoAK " + apiKey)
            .build();
  }



  public Mono<Document> reverseGeocode(String query) {
    return webClient.get()
            .uri(uriBuilder -> uriBuilder
                    .path("/v2/local/search/address.json")
                    .queryParam("query", query)
                    .build())
            .retrieve()
            .bodyToMono(KakaoAddressResponse.class)
            .flatMap(response -> {
              if (response.getDocuments() == null || response.getDocuments().isEmpty()) {
                return Mono.empty();
              }
              log.info(response.getDocuments().get(0).toString());
              return Mono.just(response.getDocuments().get(0));
            });
  }


}
