package com.trollMarket.TrollMarket.dto.shop;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter //getter setter semua properti di class ini
@AllArgsConstructor //konstraktor semua atribut
@NoArgsConstructor //konstraktor kosong
public class AddCartDTO {

    private Long productId;
    private Long quantity;
    private Long shipmentId;
    private String usernameBuyer;

}
