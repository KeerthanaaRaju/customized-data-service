package com.nielseniq.data.service.helper;

import com.nielseniq.data.service.entities.ShopperProductRelevance;
import com.nielseniq.data.service.payload.ShelfItem;
import com.nielseniq.data.service.payload.ShopperRelevancePayload;

import java.util.LinkedList;
import java.util.List;

public class ShopperRelevanceConverter
{
    public static List<ShopperProductRelevance> payloadToEntity(ShopperRelevancePayload payload)
    {
        List<ShopperProductRelevance> relevanceList = new LinkedList<>();

        String shopperId = payload.getShopperId();
        for(ShelfItem shelf : payload.getShelfItemList())
        {
            ShopperProductRelevance relevance = new ShopperProductRelevance();
            relevance.setShopperId(shopperId);
            relevance.setProductId(shelf.getProductId());
            relevance.setRelevance(shelf.getRelevancyScore());

            relevanceList.add(relevance);
        }
        return relevanceList;
    }
}