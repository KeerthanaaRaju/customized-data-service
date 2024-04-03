package com.nielseniq.data.service.services;

import com.nielseniq.data.service.constants.Constants;
import com.nielseniq.data.service.entities.Shopper;
import com.nielseniq.data.service.exceptions.DataServiceException;
import com.nielseniq.data.service.repositories.ShopperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class ShopperServiceImpl implements ShopperService
{
    @Autowired
    private ShopperRepository shopperRepository;

    public String saveShopper(Shopper shopper) throws DataServiceException
    {
        Shopper result = shopperRepository.save(shopper);
        if(result == null)
        {
            throw new DataServiceException(Constants.SHOPPER_SAVE_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return Constants.SHOPPER_SAVED;
    }

    @Override
    public Shopper findShopperById(String id) throws EntityNotFoundException
    {
        return shopperRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Shopper with id: " + id + " not found"));
    }
}