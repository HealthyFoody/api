package com.healthyfoody.api.product.repository;

import java.time.LocalTime;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.healthyfoody.api.product.entity.Product;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Query("select distinct p from Product p join p.categories cat where cat.id = :categoryId")
    Page<Product> findAllFromCategory(UUID categoryId, Pageable pageable);

    @Query("select s.inStock from Stock s where s.product.id = :productId and s.store.id = :storeId")
    Boolean isThereStockAtStore(UUID productId, UUID storeId);

    @Query("select case when count(p) > 0 then true else false end from Product p where p.id = :productId and p.saleTimeSpan.endingHour > :hour")
    Boolean isOnSaleSpan(UUID productId, LocalTime hour);
}
