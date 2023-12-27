package com.trollMarket.TrollMarket.dto.shipment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.internal.LoadingCache;

import java.time.LocalDate;

@Getter
@Setter //getter setter semua properti di class ini
@AllArgsConstructor //konstraktor semua atribut
@NoArgsConstructor //konstraktor kosong
public class ShipmentRowDTO {

    private Long id;
    private String name;
    private Double price;
    private Boolean service;

}
