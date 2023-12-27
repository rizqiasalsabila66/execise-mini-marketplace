package com.trollMarket.TrollMarket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "Seller") //nama asli table di database
@Entity
@Getter
@Setter //getter setter semua properti di class ini
@AllArgsConstructor //konstraktor semua atribut
@NoArgsConstructor
public class Seller {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="Name")
    private String name;

    @Column(name="Address")
    private String address;

    @Column(name="Balance")
    private Double balance;

    @Column(name="Username")
    private String username;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Username", referencedColumnName = "Username",insertable=false, updatable=false)
    private Account account;
}

