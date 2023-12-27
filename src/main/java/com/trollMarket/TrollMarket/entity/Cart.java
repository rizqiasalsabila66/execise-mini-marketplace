package com.trollMarket.TrollMarket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "Cart") //nama asli table di database
@Entity
@Getter
@Setter //getter setter semua properti di class ini
@AllArgsConstructor //konstraktor semua atribut
@NoArgsConstructor
public class Cart {

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
}
