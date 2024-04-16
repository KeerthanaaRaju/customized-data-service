package data.service.repositories;

import data.service.entities.ShopperProductRelevance;
import data.service.entities.ShopperRelevanceKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopperRelevanceRepository extends JpaRepository<ShopperProductRelevance, ShopperRelevanceKey>
{
    List<ShopperProductRelevance> findByShopperId(String shopperId);
    List<ShopperProductRelevance> findByProductId(String productId);
}