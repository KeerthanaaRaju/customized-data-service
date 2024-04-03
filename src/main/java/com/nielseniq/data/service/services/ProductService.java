package com.nielseniq.data.service.services;

import com.nielseniq.data.service.entities.Product;
import com.nielseniq.data.service.exceptions.DataServiceException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface ProductService
{
    String saveProduct(Product product) throws DataServiceException;
    Product findProductById(String id) throws EntityNotFoundException;
    List<Product> findByShopperIdCategoryAndBrand(String shopperId, String category, String brand, Integer limit) throws DataServiceException;
}