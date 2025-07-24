/*
 * Created by Hochan Son on 2025. 7. 24.
 * As part of
 *
 * Copyright (C)  () - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Backend Team <hc.son9@google.com>, 2025. 7. 24.
 */

package com.example.dto;

import lombok.Data;

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
@Data
public class Address {
  private String address_name;
  private String b_code;
  private String h_code;
  private String main_address_no;
  private String mountain_yn;
  private String region_1depth_name;
  private String region_2depth_name;
  private String region_3depth_name;
  private String region_3depth_h_name;
  private String sub_address_no;
  private String x;
  private String y;
}