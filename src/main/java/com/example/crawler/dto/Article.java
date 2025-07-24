/*
 * Created by Hochan Son on 2025. 7. 24.
 * As part of
 *
 * Copyright (C)  () - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Backend Team <hc.son9@google.com>, 2025. 7. 24.
 */

package com.example.crawler.dto;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

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
@Getter
public class Article {
  private String atclNo;
  private String cortarNo;
  private String atclNm;
  private String rletTpNm;
  private String tradTpNm;
  private String flrInfo;
  private int prc;
  private int rentPrc;
  private String hanPrc;
  private String spc1;
  private String spc2;
  private String direction;
  private String atclFetrDesc;
  private List<String> tagList;
  private double lat;
  private double lng;
  private String repImgUrl;
  private String rltrNm;
  private String cpNm;
  private String directTradYn;
  private CpLinkVO cpLinkVO;
}
