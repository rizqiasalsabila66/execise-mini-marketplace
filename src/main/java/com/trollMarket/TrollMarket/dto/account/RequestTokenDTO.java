package com.trollMarket.TrollMarket.dto.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter //getter setter semua properti di class ini
@AllArgsConstructor //konstraktor semua atribut
@NoArgsConstructor //konstraktor kosong
public class RequestTokenDTO {
    private String username;
    private String password;
    private String subject;
    private String audience;
    private String secretKey;
}
