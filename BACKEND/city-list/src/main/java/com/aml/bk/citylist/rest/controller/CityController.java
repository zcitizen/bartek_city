package com.aml.bk.citylist.rest.controller;

import com.aml.bk.citylist.rest.dto.CityDto;
import com.aml.bk.citylist.rest.dto.PageSizeResponse;
import com.aml.bk.citylist.rest.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/city")
@PreAuthorize("hasRole('ALLOW_VIEW')")
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST})
public class CityController {


    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping(path = "/get/startsWith")
    public ResponseEntity<CityDto> getCityByNameStartWith(@RequestParam String name) {
        CityDto dto = cityService.findFirstByNameStartsWith(name);
        return ResponseEntity.ok(dto);
    }


    @GetMapping(path = "/get/pagesCount")
    public ResponseEntity<PageSizeResponse> getPageCount(@RequestParam(defaultValue = "10") int size) {
        PageSizeResponse pageCountForPageSize = cityService.getPageCountForPageSize(size);
        return ResponseEntity.ok(pageCountForPageSize);
    }

    @GetMapping(path = "/get/pages")
    public ResponseEntity<List<CityDto>> getAllCities(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
        if (page <= 0) {
            page = 1;
        }
        if (size <= 0) {
            size = 1;
        }
        List<CityDto> cities = cityService.getCities(page - 1, size);

        return ResponseEntity.ok(cities);
    }

    @PostMapping(path = "/update")
    @PreAuthorize("hasRole('ALLOW_EDIT')")
    public ResponseEntity<CityDto> updateCity(@RequestBody CityDto cityDto, Principal principal) {
        boolean success = cityService.updateCity(cityDto, principal.getName());
        if (success) {
            return ResponseEntity.ok(cityDto);
        }
        return ResponseEntity.internalServerError().build();
    }

}
