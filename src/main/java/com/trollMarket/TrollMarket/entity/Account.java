package com.trollMarket.TrollMarket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "Account") //nama asli table di database
@Entity //untuk memetakan entity/ table java dengan database, harus memasukan id
//lombok pengganti geter seter dan konstraktor
@Getter
@Setter //getter setter semua properti di class ini
@AllArgsConstructor //konstraktor semua atribut
@NoArgsConstructor
public class Account {

    @Id
    @Column(name = "Username")
    private String username;

    @Column(name = "Password")
    private  String password;

    @Column(name = "Role")
    private  String role;

    @OneToOne(mappedBy = "account")
    private Buyer buyer;

    @OneToOne(mappedBy = "account")
    private Seller seller;
}
