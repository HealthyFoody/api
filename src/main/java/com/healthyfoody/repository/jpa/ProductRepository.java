package com.healthyfoody.repository.jpa;

import java.time.LocalTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.healthyfoody.entity.MealGroup;
import com.healthyfoody.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Query("select distinct p from Product p join p.categories cat where cat.id = :categoryId and p.listed = true ")
    Page<Product> findAllFromCategory(@Param("categoryId") UUID categoryId, Pageable pageable);

    @Query("select s.inStock from Stock s where s.product.id = :productId and s.store.id = :storeId and s.product.listed = true ")
    Boolean isThereStockAtStore(@Param("productId") UUID productId, @Param("storeId") UUID storeId);

    @Query("select case when count(p) > 0 then true else false end from Product p where p.id = :productId and p.saleTimeSpan.endingHour > :hour and p.listed = true ")
    Boolean isOnSaleSpan(@Param("productId") UUID productId, @Param("hour") LocalTime hour);

    @Query("select g from MealGroup g join g.meals m where m.id = :mealId and g.combo.id = :comboId ")
    Optional<MealGroup> findGroup(@Param("mealId") UUID mealId, @Param("comboId") UUID comboId);
}
