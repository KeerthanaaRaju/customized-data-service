package com.nielseniq.data.service.repositories;

import com.nielseniq.data.service.exceptions.DataServiceException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.nielseniq.data.service.entities.Product;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<Product,String>
{
    @Query("SELECT p FROM Product p " +
            "INNER JOIN ShopperProductRelevance spr ON p.productId = spr.productId " +
            "WHERE (:shopperId IS NULL OR spr.shopperId = :shopperId) " +
            "AND (:category IS NULL OR p.category = :category) " +
            "AND (:brand IS NULL OR p.brand = :brand)")
    List<Product> findByShopperIdCategoryAndBrand(String shopperId, String category, String brand, Pageable limit) throws DataServiceException;
}