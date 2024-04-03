package com.nielseniq.data.service.services;

import com.nielseniq.data.service.constants.Constants;
import com.nielseniq.data.service.entities.Product;
import com.nielseniq.data.service.exceptions.DataServiceException;
import com.nielseniq.data.service.repositories.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProductServiceTest
{
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductServiceImpl productService;

    Product product;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        product = new Product();
        product.setProductId("p1");
        product.setCategory("category");
        product.setBrand("brand");
    }

    @Test
    public void ProductService_CreateProduct_ReturnsSuccess()
    {
        Mockito.when(productRepository.save(ArgumentMatchers.any())).thenReturn(product);
        String result="";
        try
        {
           result  = productService.saveProduct(new Product());
        }
        catch (DataServiceException e)
        {
            Assert.fail();
        }
        Assertions.assertThat(result).isEqualTo(Constants.PROD_SAVED);

    }

    @Test
    public void ProductService_CreateProduct_ShouldThrowException() throws DataServiceException
    {
        Mockito.when(productRepository.save(ArgumentMatchers.any())).thenReturn(null);
        assertThrows(DataServiceException.class, () -> {
            productService.saveProduct(new Product());
        });
    }

    @Test
    public void ProductService_FindByIdProduct_ReturnsSuccess()
    {
        Mockito.when(productRepository.findById(ArgumentMatchers.anyString())).thenReturn(Optional.of(product));
        Product result=null;
        try
        {
            result  = productService.findProductById(product.getProductId());
        }
        catch (EntityNotFoundException e)
        {
            Assert.fail();
        }
        Assertions.assertThat(result.getProductId()).isEqualTo(product.getProductId());

    }

    @Test
    public void ProductService_FindByIdProduct__ShouldThrowException()
    {
        Mockito.when(productRepository.findById(ArgumentMatchers.anyString())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> {
            productService.findProductById("abc");
        });
    }

    @Test
    public void ProductService_LimitLessThanZero_Default()
    {
        int result = ProductServiceImpl.getRoundedLimit(0);
        Assertions.assertThat(result).isEqualTo(Constants.DEFAULT_LIMIT);
    }

    @Test
    public void ProductService_LimitGreaterThanHundred_Default()
    {
        int result = ProductServiceImpl.getRoundedLimit(1000);
        Assertions.assertThat(result).isEqualTo(Constants.DEFAULT_LIMIT);
    }

    @Test
    public void ProductService_LimitBWOneAndHundred_Default()
    {
        int result = ProductServiceImpl.getRoundedLimit(5);
        Assertions.assertThat(result).isEqualTo(5);
    }

}