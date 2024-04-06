package com.nielseniq.data.service.repositories;

import com.nielseniq.data.service.TestDataCreator;
import com.nielseniq.data.service.entities.Product;
import com.nielseniq.data.service.exceptions.DataServiceException;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest
{
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ShopperRepository shopperRepository;

    @Autowired
    private ShopperRelevanceRepository relevanceRepository;

    @Test
    public void ProductRepository_Save_ReturnSavedProduct()
    {
        //Arrange
        Product product = TestDataCreator.getProductObject("Test_Prod_Id","Nike","Shoes");
        //Act
        Product savedProduct = productRepository.save(product);

        //Assert
        Assertions.assertThat(savedProduct).isNotNull();
        Assertions.assertThat(savedProduct.getProductId()).isEqualTo("Test_Prod_Id");
        Assertions.assertThat(savedProduct.getCategory()).isEqualTo("Shoes");
    }

    @Test
    public void ProductRepository_Test_FilterQueryWorks() throws DataServiceException
    {
        populateData();
        String shopperId = "S1";
        String category = "Electronics";
        String brand = "Samsung";
        Pageable pageable = Pageable.ofSize( 100);

        // Act
        List<Product> productsResult = productRepository.findByShopperIdCategoryAndBrand(shopperId, category, brand, pageable);

        //Assert
        Assertions.assertThat(productsResult).isNotNull();
    }

    @Test
    public void ProductRepository_Test_FilterQueryWithNullValues() throws DataServiceException
    {
        populateData();
        String category = "Electronics";
        Pageable pageable = Pageable.ofSize( 100);

        // Act
        List<Product> productsResult = productRepository.findByShopperIdCategoryAndBrand(null, category, null, pageable);

        //Assert
        Assertions.assertThat(productsResult.size()).isEqualTo(3);
    }

    @Test
    public void ProductRepository_Test_FilterQueryWithPagination() throws DataServiceException
    {
        populateData();
        String category = "Electronics";
        Pageable pageable = Pageable.ofSize( 2);

        // Act
        List<Product> productsResult = productRepository.findByShopperIdCategoryAndBrand(null, category, null, pageable);

        //Assert
        Assertions.assertThat(productsResult.size()).isEqualTo(2);
    }

    @Test
    public void ProductRepository_Test_Duplicate_ShouldThrowException() throws DataServiceException
    {
        //Arrange
        populateData();
        //Assert
        assertThrows(DataIntegrityViolationException.class, () -> {
            productRepository.save(TestDataCreator.getTestProductsList().get(0));
        });
    }

    private void populateData()
    {
        productRepository.saveAll(TestDataCreator.getTestProductsList());
        shopperRepository.saveAll(TestDataCreator.getTestShoppersList());
        relevanceRepository.saveAll(TestDataCreator.getTestRelevanceList());
    }





}