package com.nielseniq.data.service.repositories;

import com.nielseniq.data.service.DataServiceApplication;
import com.nielseniq.data.service.entities.Product;
import com.nielseniq.data.service.entities.Shopper;
import com.nielseniq.data.service.entities.ShopperProductRelevance;
import com.nielseniq.data.service.entities.ShopperRelevanceKey;
import com.nielseniq.data.service.exceptions.DataServiceException;
import lombok.NoArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        Product product = new Product();
        product.setProductId("Test_Prod_Id");
        product.setBrand("Nike");
        product.setCategory("Shoes");
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
    public void ProductRepository_Test_Pagination_LimitExceeded() throws DataServiceException
    {
        populateData();
        String category = "Electronics";
        Pageable pageable = Pageable.ofSize( 200);

        // Act
        List<Product> productsResult = productRepository.findByShopperIdCategoryAndBrand(null, category, null, pageable);

        //Assert
        Assertions.assertThat(productsResult.size()).isEqualTo(3);
    }

    private void populateData()
    {
        List<Product> products = new LinkedList<>();

        //Arrange
        Product product1 = getProductObject("Prod1","Nike","Shoes");
        Product product2 = getProductObject("Prod2","Puma","Shoes");

        Product product3 = getProductObject("Prod3","Samsung","Electronics");
        Product product4 = getProductObject("Prod4","Lenovo","Electronics");

        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);

        productRepository.saveAll(products);
//-----------
        Shopper shopper1 = getShopperObject("S1","S1");
        Shopper shopper2 = getShopperObject("S2","S2");

        List<Shopper> shoppers = new ArrayList<>();
        shoppers.add(shopper1);
        shoppers.add(shopper2);

        shopperRepository.saveAll(shoppers);

        ShopperProductRelevance spr1 = getRelevanceObject("Prod1","S1",40.00000);
        ShopperProductRelevance spr2 = getRelevanceObject("Prod2","S1",40.00);
        ShopperProductRelevance spr3 = getRelevanceObject("Prod3","S1",40.00000);
        ShopperProductRelevance spr4 = getRelevanceObject("Prod1","S2",40.00);
        ShopperProductRelevance spr5 = getRelevanceObject("Prod3","S2",40.00000);
        ShopperProductRelevance spr6 = getRelevanceObject("Prod4","S2",40.00);


        List<ShopperProductRelevance> sprList = new LinkedList<>();
        sprList.add(spr1);
        sprList.add(spr2);
        sprList.add(spr3);
        sprList.add(spr4);
        sprList.add(spr5);
        sprList.add(spr6);
        List<ShopperProductRelevance> savedRelevance = relevanceRepository.saveAll(sprList);
    }

    private ShopperProductRelevance getRelevanceObject(String productId, String shopperId, Double relevance)
    {
        ShopperProductRelevance spr = new ShopperProductRelevance();
        spr.setProductId(productId);
        spr.setShopperId(shopperId);
        spr.setRelevance(relevance);
        return spr;
    }

    private Shopper getShopperObject( String shopperId, String name)
    {
        Shopper shopper = new Shopper();
        shopper.setName(name);
        shopper.setShopperId(shopperId);

        return shopper;
    }

    private Product getProductObject(String productId, String brand, String category)
    {
        Product prod = new Product();
        prod.setProductId(productId);
        prod.setBrand(brand);
        prod.setCategory(category);
        return prod;
    }
}