package com.trollMarket.TrollMarket.dto.product;

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
public class ProductUpsertDTO {

    private Long id;
    private Long sellerId;

    @NotBlank(message = "name wajib diisi")
    private String name;

    @NotBlank(message = "category wajib diisi")
    private String category;

    private String description;

    @NotNull(message = "name wajib diisi")
    private Double price;

    private Boolean discontinued;

}
