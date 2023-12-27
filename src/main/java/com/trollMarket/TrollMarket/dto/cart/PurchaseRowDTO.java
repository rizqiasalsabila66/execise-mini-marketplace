package com.trollMarket.TrollMarket.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter //getter setter semua properti di class ini
@AllArgsConstructor //konstraktor semua atribut
@NoArgsConstructor //konstraktor kosong
public class PurchaseRowDTO {
    private Long id;
    private Long productId;
    private Long quantity;
    private Long shipmentId;
    private Long buyerId;
    private Double productPrice;
    private Double shipmentPrice;
}
