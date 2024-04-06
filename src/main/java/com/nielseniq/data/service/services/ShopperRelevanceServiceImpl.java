package com.nielseniq.data.service.services;

import com.nielseniq.data.service.constants.Constants;
import com.nielseniq.data.service.entities.ShopperProductRelevance;
import com.nielseniq.data.service.exceptions.DataServiceException;
import com.nielseniq.data.service.payload.ShelfItem;
import com.nielseniq.data.service.payload.ShopperRelevancePayload;
import com.nielseniq.data.service.repositories.ShopperRelevanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Service
public class ShopperRelevanceServiceImpl implements ShopperRelevanceService
{
    @Autowired
    private ShopperRelevanceRepository relevanceRepository;

    @Transactional
    @Override
    public String saveShopperRelevance(List<ShopperProductRelevance> shopperProductRelevances) throws DataServiceException, DataIntegrityViolationException
    {
//        if(!StringUtils.hasText(relevancePayload.getShopperId()) ||  relevancePayload.getShelfItemList().isEmpty())
//        {
//            throw new DataServiceException(Constants.MISSING_ITEMS,HttpStatus.BAD_REQUEST);
//        }
//        List<ShopperProductRelevance> relevanceList = payloadToEntity(relevancePayload);
        List<ShopperProductRelevance> updatedList = relevanceRepository.saveAll(shopperProductRelevances);
        if(Objects.isNull(updatedList) || updatedList.size() != shopperProductRelevances.size())
        {
            throw new DataServiceException(Constants.RELEVANCE_SAVE_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return Constants.RELEVANCE_SAVED;
    }


}