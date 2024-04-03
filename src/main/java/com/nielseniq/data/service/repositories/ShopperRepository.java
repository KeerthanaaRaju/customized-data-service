package com.nielseniq.data.service.repositories;

import com.nielseniq.data.service.entities.Shopper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopperRepository extends JpaRepository<Shopper,String>
{
    // no new customisations here
}