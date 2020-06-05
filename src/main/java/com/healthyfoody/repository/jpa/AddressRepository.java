package com.healthyfoody.repository.jpa;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.healthyfoody.entity.Address;

public interface AddressRepository extends JpaRepository<Address, UUID> {

    @Query("from Address a where a.customer.id = :customerId")
    List<Address> findByCustomerId(@Param("customerId") UUID customerId);

    @Transactional
    @Query("update Address a set a.isDefault = false where a.customer.id = :customerId")
    Boolean removeDefaultAddress(@Param("customerId") UUID customerId);
}
