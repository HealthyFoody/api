package com.healthyfoody.repository.jpa;

import com.healthyfoody.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {

    @Query("from Address a where a.customer.id = :customerId")
    List<Address> findByCustomerId(UUID customerId);

    @Transactional
    @Query("update Address a set a.defaultAddress = false where a.customer.id = :customerId")
    Boolean removeDefaultAddress(UUID customerId);
}
