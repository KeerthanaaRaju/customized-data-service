package com.nielseniq.data.service.services;

import com.nielseniq.data.service.constants.Constants;
import com.nielseniq.data.service.entities.Product;
import com.nielseniq.data.service.exceptions.DataServiceException;
import com.nielseniq.data.service.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;


@Service
public class ProductServiceImpl implements ProductService
{
    @Autowired
    private ProductRepository productRepository;

    @Override
    public String saveProduct(Product product) throws DataServiceException, DataIntegrityViolationException
    {
        Product result = productRepository.save(product);
        if(result == null)
        {
            throw new DataServiceException(Constants.PROD_SAVE_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return Constants.PROD_SAVED;
    }

    @Override
    public Product findProductById(String id) throws EntityNotFoundException
    {
       return productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product with id: " + id + " not found"));
    }

    @Override
    public List<Product> findByShopperIdCategoryAndBrand(String shopperId, String category, String brand, Integer limit) throws DataServiceException
    {
        limit = getRoundedLimit(limit);
        return productRepository.findByShopperIdCategoryAndBrand(shopperId, category, brand, Pageable.ofSize(limit));
    }

    //exposed for testing
    protected static int getRoundedLimit(Integer limit)
    {
        if (limit == null || limit.intValue() <= 0 || limit.intValue() > 100)
        {
            limit = Constants.DEFAULT_LIMIT; // Default limit
        }
        return limit;
    }


}