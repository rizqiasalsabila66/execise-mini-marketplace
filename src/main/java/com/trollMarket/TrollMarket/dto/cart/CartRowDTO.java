package com.trollMarket.TrollMarket.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter //getter setter semua properti di class ini
@AllArgsConstructor //konstraktor semua atribut
@NoArgsConstructor //konstraktor kosong
public class CartRowDTO {
    private Long id;
    private String product;
    private Long quantity;
    private String shipment;
    private String seller;
    private Double totalPrice;

}
