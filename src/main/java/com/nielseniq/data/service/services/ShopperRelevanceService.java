package com.nielseniq.data.service.services;

import com.nielseniq.data.service.entities.ShopperProductRelevance;
import com.nielseniq.data.service.exceptions.DataServiceException;
import com.nielseniq.data.service.payload.ShopperRelevancePayload;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ShopperRelevanceService
{
    String saveShopperRelevance(List<ShopperProductRelevance> productRelevances) throws DataServiceException;
}