package com.nielseniq.data.service.services;

import com.nielseniq.data.service.constants.Constants;
import com.nielseniq.data.service.entities.ShopperProductRelevance;
import com.nielseniq.data.service.exceptions.DataServiceException;
import com.nielseniq.data.service.payload.ShelfItem;
import com.nielseniq.data.service.payload.ShopperRelevancePayload;
import com.nielseniq.data.service.repositories.ShopperRelevanceRepository;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringJUnit4ClassRunner.class)
public class ShopperRelevanceServiceTest
{
    @Mock
    private ShopperRelevanceRepository relevanceRepository;
    @InjectMocks
    private ShopperRelevanceServiceImpl relevanceService;

    private ShopperProductRelevance productRelevance;
    private ShopperRelevancePayload srp;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        productRelevance = new ShopperProductRelevance();
        productRelevance.setShopperId("s1");
        productRelevance.setProductId("p1");
        productRelevance.setRelevance(50.45236);

        srp = new ShopperRelevancePayload();
        srp.setShopperId("s1");
        List<ShelfItem> shelfItems = Collections.singletonList(new ShelfItem("p1",50.45236));
        srp.setShelfItemList(shelfItems);
    }

    @Test
    public void RelevanceService_CreateObjects_ReturnsSuccess()
    {
        Mockito.when(relevanceRepository.saveAll(ArgumentMatchers.any())).thenReturn(Collections.singletonList(productRelevance));
        String result="";
        try
        {
            result  = relevanceService.saveShopperRelevance(srp);
        }
        catch (DataServiceException e)
        {
            Assert.fail();
        }
        Assertions.assertThat(result).isEqualTo(Constants.RELEVANCE_SAVED);
    }

    @Test
    public void RelevanceService_CreateObjects_ShouldThrowException() throws DataServiceException
    {
        Mockito.when(relevanceRepository.saveAll(ArgumentMatchers.any())).thenReturn(null);
        assertThrows(DataServiceException.class, () -> {
            relevanceService.saveShopperRelevance(srp);
        });
    }

    @Test
    public void RelevanceService_CreateObjects_MissingPayload_ShouldThrowException() throws DataServiceException
    {
        Mockito.when(relevanceRepository.saveAll(ArgumentMatchers.any())).thenReturn(Collections.singletonList(productRelevance));
        assertThrows(DataServiceException.class, () -> {
            relevanceService.saveShopperRelevance(new ShopperRelevancePayload());
        });
    }

    @Test
    public void RelevanceService_EntityToPayloadConversion()
    {
        List<ShelfItem> shelfItems = new ArrayList<>();
        shelfItems.add(new ShelfItem("p1",55.8787));
        shelfItems.add(new ShelfItem("p2",52.8787));
        srp.setShelfItemList(shelfItems);

        List<ShopperProductRelevance> relevanceList = ShopperRelevanceServiceImpl.payloadToEntity(srp);
        Assertions.assertThat(relevanceList.size()).isEqualTo(srp.getShelfItemList().size());
    }
}