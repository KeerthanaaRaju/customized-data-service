package com.nielseniq.data.service.services;

import com.nielseniq.data.service.constants.Constants;
import com.nielseniq.data.service.entities.Shopper;
import com.nielseniq.data.service.exceptions.DataServiceException;
import com.nielseniq.data.service.repositories.ShopperRepository;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringJUnit4ClassRunner.class)
public class ShopperServiceTest
{
    @Mock
    private ShopperRepository shopperRepository;
    @InjectMocks
    private ShopperServiceImpl shopperService;

    Shopper shopper;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        shopper = new Shopper();
        shopper.setShopperId("s1");
        shopper.setName("name");
    }

    @Test
    public void ShopperService_CreateShopper_ReturnsSuccess()
    {
        Mockito.when(shopperRepository.save(ArgumentMatchers.any())).thenReturn(shopper);
        String result="";
        try
        {
            result  = shopperService.saveShopper(new Shopper());
        }
        catch (DataServiceException e)
        {
            Assert.fail();
        }
        Assertions.assertThat(result).isEqualTo(Constants.SHOPPER_SAVED);

    }

    @Test
    public void ShopperService_CreateShopper_ShouldThrowException() throws DataServiceException
    {
        Mockito.when(shopperRepository.save(ArgumentMatchers.any())).thenReturn(null);
        assertThrows(DataServiceException.class, () -> {
            shopperService.saveShopper(new Shopper());
        });

    }

    @Test
    public void ShopperService_FindByIdShopper_ReturnsSuccess()
    {
        Mockito.when(shopperRepository.findById(ArgumentMatchers.anyString())).thenReturn(Optional.of(shopper));
        Shopper result=null;
        try
        {
            result  = shopperService.findShopperById(shopper.getShopperId());
        }
        catch (EntityNotFoundException e)
        {
            Assert.fail();
        }
        Assertions.assertThat(result.getShopperId()).isEqualTo(shopper.getShopperId());

    }

    @Test
    public void ShopperService_FindByIdShopper__ShouldThrowException()
    {
        Mockito.when(shopperRepository.findById(ArgumentMatchers.anyString())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> {
            shopperService.findShopperById("abc");
        });
    }

}