package com.healthyfoody.api.location;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

import javax.transaction.Transactional;

interface AddressRepository extends JpaRepository<Address, UUID> {

	@Query("from Address a where a.customer.id = :id")
	List<Address> findByCustomerId(UUID id);

	@Transactional
	@Query("update Address a set a.defaultAddress = false where a.customer.id = :id")
	Boolean removeDefaultAddress(UUID id);
}
