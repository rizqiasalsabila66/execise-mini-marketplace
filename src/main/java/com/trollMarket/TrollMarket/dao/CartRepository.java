package com.trollMarket.TrollMarket.dao;

import com.trollMarket.TrollMarket.dto.cart.CartRowDTO;
import com.trollMarket.TrollMarket.dto.cart.PurchaseRowDTO;
import com.trollMarket.TrollMarket.entity.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Long> {

    @Query("""
            SELECT new com.trollMarket.TrollMarket.dto.cart.CartRowDTO(car.id, pro.name, car.quantity,
            shi.name, sel.name, (car.quantity*pro.price)+shi.price)
            FROM Cart AS car
            JOIN car.product AS pro
            JOIN car.shipment AS shi
            JOIN car.buyer AS buy
            JOIN pro.seller AS sel
            WHERE buy.username =:username
            """)
    public Page<CartRowDTO> getRow(@Param("username") String username, Pageable pageable);

    @Query("""
            SELECT new com.trollMarket.TrollMarket.dto.cart.PurchaseRowDTO(car.id, car.productId, car.quantity,
            car.shipmentId,car.buyerId,pro.price,shi.price)
            FROM Cart AS car
            JOIN car.product AS pro
            JOIN car.shipment AS shi
            JOIN car.buyer AS buy
            WHERE buy.username =:username
            """)
    public List<PurchaseRowDTO> getAllCart(@Param("username") String username);

    @Query("""
            SELECT SUM((car.quantity*pro.price)+shi.price)
            FROM Cart AS car
            JOIN car.product AS pro
            JOIN car.shipment AS shi
            JOIN car.buyer AS buy
            WHERE buy.username =:username
            """)
    public Double getTotal(@Param("username") String username);
}
