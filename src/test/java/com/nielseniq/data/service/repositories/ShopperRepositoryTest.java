package com.nielseniq.data.service.repositories;

import com.nielseniq.data.service.TestDataCreator;
import com.nielseniq.data.service.entities.Shopper;
import com.nielseniq.data.service.exceptions.DataServiceException;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@RunWith(SpringRunner.class)
@DataJpaTest
public class ShopperRepositoryTest
{
    @Autowired
    private ShopperRepository shopperRepository;

    @Test
    public void ShopperRepository_SaveAll_ReturnSavedShopper() {

        //Arrange
        Shopper shopper = TestDataCreator.getShopperObject("Test_Shopper_Id","Shopper_name");
        shopper.setShopperId("Test_Shopper_Id");

        //Act
        Shopper savedShopper = shopperRepository.save(shopper);

        //Assert
        Assertions.assertThat(savedShopper).isNotNull();
        Assertions.assertThat(savedShopper.getShopperId()).isEqualTo("Test_Shopper_Id");
        Assertions.assertThat(savedShopper.getName()).isEqualTo("Shopper_name");
    }

    @Test
    public void ShopperRepository_Test_Duplicate_ShouldThrowException() throws DataServiceException
    {
        //Arrange
        shopperRepository.saveAll(TestDataCreator.getTestShoppersList());
        //Assert
        assertThrows(DataIntegrityViolationException.class, () -> {
            shopperRepository.save(TestDataCreator.getTestShoppersList().get(0));
        });
    }
}