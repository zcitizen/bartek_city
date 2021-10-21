package com.aml.bk.citylist.rest.mappers;

import com.aml.bk.citylist.db.city.City;
import com.aml.bk.citylist.rest.dto.CityDto;

public final class CityMapper {

    private CityMapper() {

    }

    public static CityDto mapToDto(City city) {
        if (city == null) {
            return null;
        }
        CityDto dto = new CityDto();
        dto.setName(city.getName());
        dto.setId(city.getId());
        dto.setPhoto(city.getPhoto());
        return dto;
    }

}
