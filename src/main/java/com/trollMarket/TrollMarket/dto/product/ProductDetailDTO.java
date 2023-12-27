package com.trollMarket.TrollMarket.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter //getter setter semua properti di class ini
@AllArgsConstructor //konstraktor semua atribut
@NoArgsConstructor //konstraktor kosong
public class ProductDetailDTO {

    private Long id;
    private String name;
    private String category;
    private String description;
    private Double price;
    private String sellerName;
}
