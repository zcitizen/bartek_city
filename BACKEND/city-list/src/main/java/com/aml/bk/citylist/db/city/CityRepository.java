package com.aml.bk.citylist.db.city;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

    City findFirstByNameStartsWith(String name);


    /**
     * We assume that ids start from 1 and are going up by 1 as in the input file
     *
     * @param from
     * @param to
     * @return
     */
    List<City> getCityByIdBetween(Integer from, Integer to);


}
