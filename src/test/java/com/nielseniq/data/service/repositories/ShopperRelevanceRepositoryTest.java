package com.nielseniq.data.service.repositories;

import com.nielseniq.data.service.entities.Product;
import com.nielseniq.data.service.entities.ShopperProductRelevance;
import com.nielseniq.data.service.entities.ShopperRelevanceKey;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

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
    }

}