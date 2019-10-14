package com.czh.pojo;

import lombok.Data;

import java.util.Map;

/***
 * {
 *   "status": 0,
 *   "data": {
 *     "id": 1000,
 *     "card": "*2 *3 *4 *5 *6 *7 *8 *9 *10 *J *Q *K *A"
 *   }
 * }
 * 请求参数
 */

@Data
public class Rqst {
    private String status;
    private Integer id;
    private String card;

}
