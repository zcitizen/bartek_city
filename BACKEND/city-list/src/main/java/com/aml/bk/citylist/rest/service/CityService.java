package com.aml.bk.citylist.rest.service;

import com.aml.bk.citylist.rest.dto.CityDto;
import com.aml.bk.citylist.rest.dto.PageSizeResponse;

import java.util.List;

public interface CityService {


    List<CityDto> getCities(int pageNumber, int pageSize);

    boolean updateCity(CityDto cityDto, String updater);

    CityDto findFirstByNameStartsWith(String name);

    PageSizeResponse getPageCountForPageSize(int pageSize);

}
