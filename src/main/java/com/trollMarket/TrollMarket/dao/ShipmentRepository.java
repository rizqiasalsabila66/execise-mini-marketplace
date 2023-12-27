package com.trollMarket.TrollMarket.dao;

import com.trollMarket.TrollMarket.dto.shipment.ShipmentRowDTO;
import com.trollMarket.TrollMarket.dto.utility.DropdownDTO;
import com.trollMarket.TrollMarket.entity.Shipment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment,Long> {

    @Query("""
            SELECT new com.trollMarket.TrollMarket.dto.shipment.ShipmentRowDTO(shi.id,shi.name,shi.price,shi.service)
            FROM Shipment AS shi
            """)
    public Page<ShipmentRowDTO> getRow(Pageable pageable);

    @Query("""
            SELECT new com.trollMarket.TrollMarket.dto.utility.DropdownDTO(shi.id, shi.name)
            FROM Shipment AS shi
            WHERE shi.service = true
            """)
    public List<DropdownDTO> getDropdownShipment();
}
