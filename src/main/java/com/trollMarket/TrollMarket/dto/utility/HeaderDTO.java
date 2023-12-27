package com.trollMarket.TrollMarket.dto.utility;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter //getter setter semua properti di class ini
@AllArgsConstructor //konstraktor semua atribut
@NoArgsConstructor //konstraktor kosong
public class HeaderDTO {
    private Long id;
    private String name;
    private String role;
    private String address;
    private Double balance;
}
