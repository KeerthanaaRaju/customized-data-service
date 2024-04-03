package com.nielseniq.data.service.services;

import com.nielseniq.data.service.entities.Shopper;
import com.nielseniq.data.service.exceptions.DataServiceException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

public interface ShopperService
{
    String saveShopper(Shopper shopper) throws DataServiceException;
    Shopper findShopperById(String id) throws EntityNotFoundException;
}