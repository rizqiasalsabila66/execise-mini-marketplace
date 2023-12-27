package com.trollMarket.TrollMarket.dao;

import com.trollMarket.TrollMarket.dto.product.ProductDetailDTO;
import com.trollMarket.TrollMarket.dto.product.ProductMerchandiseRowDTO;
import com.trollMarket.TrollMarket.dto.product.ProductRowDTO;
import com.trollMarket.TrollMarket.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("""
            SELECT new com.trollMarket.TrollMarket.dto.product.ProductRowDTO(pro.id, pro.name, pro.price)
            FROM Product AS pro
            WHERE pro.discontinued = false AND
            (:name IS NULL OR pro.name LIKE %:name%) AND
            (:category IS NULL OR pro.category LIKE %:category%) AND
            (:description IS NULL OR pro.description LIKE %:description%)
            """)
    public Page<ProductRowDTO> getRowShop(@Param("name") String name, @Param("category") String category,
                                      @Param("description") String description, Pageable pageable);

    @Query("""
            SELECT new com.trollMarket.TrollMarket.dto.product.ProductDetailDTO(pro.id, pro.name,
            pro.category, pro.description, pro.price, sel.name)
            FROM Product AS pro
            JOIN pro.seller AS sel
            WHERE pro.id = :id
            """)
    public ProductDetailDTO getDetailProduct (@Param("id")Long id);

    @Query("""
            SELECT new com.trollMarket.TrollMarket.dto.product.ProductMerchandiseRowDTO(pro.id, 
            pro.name,pro.category, pro.discontinued)
            FROM Product AS pro
            JOIN pro.seller AS sel
            WHERE sel.username = :username
            """)
    public Page<ProductMerchandiseRowDTO> getRowMerchandise(@Param("username")String username, Pageable pageable);

}
