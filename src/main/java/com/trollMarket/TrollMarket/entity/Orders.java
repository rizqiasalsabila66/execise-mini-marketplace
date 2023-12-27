package com.trollMarket.TrollMarket.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Table(name = "Orders") //nama asli table di database
@Entity
@Getter
@Setter //getter setter semua properti di class ini
@AllArgsConstructor //konstraktor semua atribut
@NoArgsConstructor
public class Orders {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ProductId")
    private Long productId;

    @ManyToOne
    @JoinColumn(name = "ProductId",insertable = false, updatable = false)
    private Product product;

    @Column(name = "BuyerId")
    private Long buyerId;

    @ManyToOne
    @JoinColumn(name = "BuyerId",insertable = false, updatable = false)
    private Buyer buyer;

    @Column(name = "ShipmentId")
    private Long shipmentId;

    @ManyToOne
    @JoinColumn(name = "ShipmentId",insertable = false, updatable = false)
    private Shipment shipment;

    @Column(name="Quantity")
    private Long quantity;

    @Column(name="Date")
    private LocalDate date;

    @Column(name = "ProductPrice")
    private Double productPrice;

    @Column(name = "ShipmentPrice")
    private Double shipmentPrice;
}
