package com.aml.bk.citylist.rest.service;

import com.aml.bk.citylist.db.city.City;
import com.aml.bk.citylist.db.city.CityRepository;
import com.aml.bk.citylist.rest.dto.CityDto;
import com.aml.bk.citylist.rest.dto.PageSizeResponse;
import com.aml.bk.citylist.rest.mappers.CityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CityServiceImpl.class);

    private final CityRepository cityRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<CityDto> getCities(int pageNumber, int pageSize) {
        int start = (pageNumber * pageSize) + 1;
        int end = start + pageSize - 1;
        List<City> cityByIdBetween = cityRepository.getCityByIdBetween(start, end);
        LOGGER.debug("City list fetched size: {}", cityByIdBetween.size());
        return cityByIdBetween.stream().map(CityMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public boolean updateCity(CityDto cityDto, String updater) {
        Optional<City> cityOptional = cityRepository.findById(cityDto.getId());
        if (cityOptional.isPresent()) {
            City city = cityOptional.get();
            LOGGER.info("City with id: {} updated by user {}. Changed name from {} to {} and photo URL from {} to {}",
                    city.getId(), updater, city.getName(), cityDto.getName(), city.getPhoto(), cityDto.getPhoto());
            city.setName(cityDto.getName());
            city.setPhoto(cityDto.getPhoto());
            cityRepository.saveAndFlush(city);
            return true;
        }
        LOGGER.warn("Tried to update city with not existing ID: {}", cityDto.getId());
        return false;
    }

    @Override
    public CityDto findFirstByNameStartsWith(String name) {
        City city = cityRepository.findFirstByNameStartsWith(name);
        return CityMapper.mapToDto(city);
    }

    @Override
    public PageSizeResponse getPageCountForPageSize(int pageSize) {
        long totalSize = cityRepository.count();
        long result = totalSize / pageSize;

        return new PageSizeResponse(pageSize, result);
    }
}
