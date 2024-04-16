package data.service.services;

import data.service.constants.Constants;
import data.service.entities.ShopperProductRelevance;
import data.service.exceptions.DataServiceException;
import data.service.repositories.ShopperRelevanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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