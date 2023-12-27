package com.trollMarket.TrollMarket.service;

import com.trollMarket.TrollMarket.dao.ProductRepository;
import com.trollMarket.TrollMarket.dao.TransactionRepository;
import com.trollMarket.TrollMarket.dto.product.ProductDetailDTO;
import com.trollMarket.TrollMarket.dto.product.ProductMerchandiseRowDTO;
import com.trollMarket.TrollMarket.dto.product.ProductRowDTO;
import com.trollMarket.TrollMarket.dto.product.ProductUpsertDTO;
import com.trollMarket.TrollMarket.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public Page<ProductRowDTO> getProductShopRow(int page, String name, String category, String description){
        var pageable = PageRequest.of(page-1,10, Sort.by("id"));
        var rows = productRepository.getRowShop(name,category,description,pageable);
        return rows;
    }

    public ProductDetailDTO getDetailProduct(Long id){
        var dto = productRepository.getDetailProduct(id);
        return dto;
    }

    public Page<ProductMerchandiseRowDTO> getProductMerchandiseRow(String username,int page){
        var pageable = PageRequest.of(page-1,10, Sort.by("id"));
        var rows = productRepository.getRowMerchandise(username,pageable);
        return rows;
    }

    public void save(ProductUpsertDTO dto) {
        var entity = new Product();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setCategory(dto.getCategory());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setSellerId(dto.getSellerId());
        entity.setDiscontinued(dto.getDiscontinued());
        productRepository.save(entity);
    }

    public ProductUpsertDTO findOne(Long id){
        var entity = productRepository.findById(id).get();
        var dto = new ProductUpsertDTO(
                entity.getId(), entity.getSellerId(), entity.getName(), entity.getCategory(),
                entity.getDescription(),entity.getPrice(),entity.getDiscontinued()
        );
        return dto;
    }

    public Long depandencies(Long id){
        return transactionRepository.getTransactionByProduct(id);
    }

    public void delete(Long id){
        productRepository.deleteById(id);
    }

    public void discontinued(Long id){
        var product = productRepository.findById(id).get();
        product.setDiscontinued(true);
        productRepository.save(product);
    }
}
