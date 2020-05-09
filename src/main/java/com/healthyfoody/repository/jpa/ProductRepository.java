package com.healthyfoody.repository.jpa;

import com.healthyfoody.entity.MealGroup;
import com.healthyfoody.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalTime;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Query("select distinct p from Product p join p.categories cat where cat.id = :categoryId and p.listed = true ")
    Page<Product> findAllFromCategory(UUID categoryId, Pageable pageable);

    @Query("select s.inStock from Stock s where s.product.id = :productId and s.store.id = :storeId and s.product.listed = true ")
    Boolean isThereStockAtStore(UUID productId, UUID storeId);

    @Query("select case when count(p) > 0 then true else false end from Product p where p.id = :productId and p.saleTimeSpan.endingHour > :hour and p.listed = true ")
    Boolean isOnSaleSpan(UUID productId, LocalTime hour);

    @Query("select g from MealGroup g join g.meals m where m.id = :mealId and g.combo.id = :comboId ")
    Optional<MealGroup> findGroup(UUID mealId, UUID comboId);
}
