package data.service.services;

import data.service.entities.Shopper;
import data.service.exceptions.DataServiceException;

import javax.persistence.EntityNotFoundException;

public interface ShopperService
{
    String saveShopper(Shopper shopper) throws DataServiceException;
    Shopper findShopperById(String id) throws EntityNotFoundException;
}