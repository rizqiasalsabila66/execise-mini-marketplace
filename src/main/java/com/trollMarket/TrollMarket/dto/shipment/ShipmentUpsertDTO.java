package com.trollMarket.TrollMarket.dto.shipment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter //getter setter semua properti di class ini
@AllArgsConstructor //konstraktor semua atribut
@NoArgsConstructor //konstraktor kosong
public class ShipmentUpsertDTO {

    private Long id;

    @NotBlank(message = "name wajib diisi")
    private String name;

    @NotNull(message = "price wajib diisi")
    private Double price;

    private Boolean service;

}
