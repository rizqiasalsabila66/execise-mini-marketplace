package com.trollMarket.TrollMarket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "Product") //nama asli table di database
@Entity
@Getter
@Setter //getter setter semua properti di class ini
@AllArgsConstructor //konstraktor semua atribut
@NoArgsConstructor
public class Product {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SellerId")
    private Long sellerId;

    @ManyToOne
    @JoinColumn(name = "SellerId",insertable = false, updatable = false)
    private Seller seller;

    @Column(name = "Name")
    private String name;

    @Column(name = "Price")
    private Double price;

    @Column(name = "Discontinued")
    private Boolean discontinued;

    @Column(name = "Description")
    private String description;

    @Column(name = "Category")
    private String category;
}
