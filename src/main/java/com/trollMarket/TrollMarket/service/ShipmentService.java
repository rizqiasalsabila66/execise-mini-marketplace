package com.trollMarket.TrollMarket.service;

import com.trollMarket.TrollMarket.dao.ShipmentRepository;
import com.trollMarket.TrollMarket.dao.TransactionRepository;
import com.trollMarket.TrollMarket.dto.product.ProductUpsertDTO;
import com.trollMarket.TrollMarket.dto.shipment.ShipmentRowDTO;
import com.trollMarket.TrollMarket.dto.shipment.ShipmentUpsertDTO;
import com.trollMarket.TrollMarket.entity.Product;
import com.trollMarket.TrollMarket.entity.Shipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ShipmentService {

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public Page<ShipmentRowDTO> getTransactionRows(int page) {
        var pageable = PageRequest.of(page-1,10, Sort.by("id"));
        var rows = shipmentRepository.getRow(pageable);
        return rows;
    }

    public void save(ShipmentUpsertDTO dto) {
        var entity = new Shipment();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        if(dto.getService()==null){
            entity.setService(true);
        }else {
            entity.setService(dto.getService());
        }
        shipmentRepository.save(entity);

    }

    public ShipmentUpsertDTO findOne(Long id){
        var entity = shipmentRepository.findById(id).get();
        var dto = new ShipmentUpsertDTO(
                entity.getId(), entity.getName(),entity.getPrice(),entity.getService()
        );
        return dto;
    }

    public Long depandencies(Long id){
        return transactionRepository.getTransactionByShipment(id);
    }

    public void delete(Long id){
        shipmentRepository.deleteById(id);
    }

    public void stopService(Long id){
        var shipment = shipmentRepository.findById(id).get();
        shipment.setService(false);
        shipmentRepository.save(shipment);
    }
}
