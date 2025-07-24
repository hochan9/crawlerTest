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
public class CpLinkVO {
  private String cpId;
  private String mobileArticleLinkTypeCode;
  private String mobileBmsInspectPassYn;
  private boolean pcArticleLinkUseAtArticleTitle;
  private boolean pcArticleLinkUseAtCpName;
  private boolean mobileArticleLinkUseAtArticleTitle;
  private boolean mobileArticleLinkUseAtCpName;
}
