package data.service.services;

import data.service.entities.Product;
import data.service.exceptions.DataServiceException;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface ProductService
{
    String saveProduct(Product product) throws DataServiceException;
    Product findProductById(String id) throws EntityNotFoundException;
    List<Product> findByShopperIdCategoryAndBrand(String shopperId, String category, String brand, Integer limit) throws DataServiceException;
}