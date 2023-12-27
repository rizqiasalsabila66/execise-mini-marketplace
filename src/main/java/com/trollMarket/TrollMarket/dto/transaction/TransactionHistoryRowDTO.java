package com.trollMarket.TrollMarket.dto.transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter //getter setter semua properti di class ini
@AllArgsConstructor //konstraktor semua atribut
@NoArgsConstructor //konstraktor kosong
public class TransactionHistoryRowDTO {

    private Long id;
    private LocalDate date;
    private String seller;
    private String buyer;
    private String product;
    private Long quantity;
    private String shipment;
    private Double totalPrice;
}
