package com.nielseniq.data.service.repositories;

import com.nielseniq.data.service.entities.ShopperProductRelevance;
import com.nielseniq.data.service.entities.ShopperRelevanceKey;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@RunWith(SpringRunner.class)
@DataJpaTest
public class ShopperRelevanceRepositoryTest
{
    @Autowired
    ShopperRelevanceRepository relevanceRepository;

    @Test
    public void ShopperRelevanceRepository_Save()
    {
        //Arrange
        ShopperProductRelevance spr = new ShopperProductRelevance();
        ShopperRelevanceKey srk = new ShopperRelevanceKey();
        spr.setProductId("Test_Prod_Id");
        spr.setShopperId("Test_Shopper_Id");

        spr.setRelevance(40.00000);
        //Act
        ShopperProductRelevance savedRelevance = relevanceRepository.save(spr);

        //Assert
        Assertions.assertThat(savedRelevance).isNotNull();
        Assertions.assertThat(savedRelevance.getProductId()).isEqualTo("Test_Prod_Id");
    }

    @Test
    public void ShopperRelevanceRepository_SaveDuplicate()
    {
        //Arrange
        ShopperProductRelevance spr = new ShopperProductRelevance();
        spr.setProductId("Test_Prod_Id");
        spr.setShopperId("Test_Shopper_Id");

        spr.setRelevance(40.00000);
        //Act
        ShopperProductRelevance savedRelevance = relevanceRepository.save(spr);

        //Assert
        Assertions.assertThat(savedRelevance).isNotNull();
        Assertions.assertThat(savedRelevance.getProductId()).isEqualTo("Test_Prod_Id");
        Assertions.assertThat(savedRelevance.getRelevance()).isEqualTo(40.00000);

        spr.setProductId("Test_Prod_Id");
        spr.setShopperId("Test_Shopper_Id");

        spr.setRelevance(41.00000);

        savedRelevance = relevanceRepository.save(spr);
        Assertions.assertThat(savedRelevance).isNotNull();
        Assertions.assertThat(savedRelevance.getRelevance()).isEqualTo(41.00000);

    }

}