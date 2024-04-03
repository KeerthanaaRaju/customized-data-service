package com.nielseniq.data.service.services;

import com.nielseniq.data.service.exceptions.DataServiceException;
import com.nielseniq.data.service.payload.ShopperRelevancePayload;
import org.springframework.stereotype.Service;

public interface ShopperRelevanceService
{
    String saveShopperRelevance(ShopperRelevancePayload relevancePayload) throws DataServiceException;
}