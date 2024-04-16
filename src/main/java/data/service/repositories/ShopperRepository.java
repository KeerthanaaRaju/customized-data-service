package data.service.repositories;

import data.service.entities.Shopper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopperRepository extends JpaRepository<Shopper,String>
{
    // no new customisations here
}