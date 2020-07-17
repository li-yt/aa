package com.fh.product.bean.product;


import lombok.Data;

@Data
public class ProductVo {

    private Integer id;

    private String productName;

    private String classifyName;

    private String brandName;

    private Double price;

    private Integer stock;

    private String productImg;

    private String unit;

    private Double weight;

}
