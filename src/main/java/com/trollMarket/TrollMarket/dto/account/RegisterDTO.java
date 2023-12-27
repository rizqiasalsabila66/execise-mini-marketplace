package com.trollMarket.TrollMarket.dto.account;

import com.trollMarket.TrollMarket.validation.ComparePassword;
import com.trollMarket.TrollMarket.validation.UniqueUsername;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ComparePassword(message = "passowrd not match")
@Getter
@Setter //getter setter semua properti di class ini
@AllArgsConstructor //konstraktor semua atribut
@NoArgsConstructor //konstraktor kosong
public class RegisterDTO {

    @UniqueUsername(message = "username sudah ada")
    @NotBlank(message = "wajib diisi")
    @Size(max=20, message = "Maksimal 20 karakter")
    private String username;

    @NotBlank(message = "wajib diisi")
    @Size(max=20, message = "Maksimal 20 karakter")
    private String password;

    @NotBlank(message = "wajib diisi")
    @Size(max=20, message = "Maksimal 20 karakter")
    private String passwordConfirmation;

    private String role;
}
