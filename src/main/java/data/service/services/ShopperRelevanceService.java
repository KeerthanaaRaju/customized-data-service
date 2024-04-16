package data.service.services;

import data.service.entities.ShopperProductRelevance;
import data.service.exceptions.DataServiceException;

import java.util.List;

public interface ShopperRelevanceService
{
    String saveShopperRelevance(List<ShopperProductRelevance> productRelevances) throws DataServiceException;
}