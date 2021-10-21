package com.aml.bk.citylist;

import com.aml.bk.citylist.db.city.City;
import com.aml.bk.citylist.db.city.CityRepository;
import com.aml.bk.citylist.rest.dto.CityDto;
import com.aml.bk.citylist.rest.service.CityService;
import com.aml.bk.citylist.rest.service.CityServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class CityListApplicationTests {

    @TestConfiguration
    static class CityServiceImplConfigurationContext {
        @Bean
        public CityService cityService(CityRepository cityRepository) {
            return new CityServiceImpl((cityRepository));
        }
    }

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CityService cityService;

    @Test
    public void testRepository() {
        List<City> all = cityRepository.findAll();
        assertEquals(1000, all.size());
    }

    @Test
    public void testGetCityByStartWith() {
        City tokyo = cityRepository.findFirstByNameStartsWith("Tok");
        assertNotNull(tokyo);
        assertEquals("Tokyo", tokyo.getName());
    }

    @Test
    public void testIfCityStartsWithIsNull() {
        CityDto nullCity = cityService.findFirstByNameStartsWith("123123123");
        assertNull(nullCity);
    }

    @Test
    public void testCityServicePagination() {
        List<CityDto> cities = cityService.getCities(0, 10);
        Integer maxId = cities.stream().max(Comparator.comparingInt(CityDto::getId)).orElseGet(CityDto::new).getId();
        assertNotNull(maxId);
        assertEquals(maxId, 10);
        assertEquals(10, cities.size());
    }

    @Test
    public void updateCityWithNonExistingIdTest() {
        CityDto toUpdate = new CityDto();
        toUpdate.setId(-100000);
        toUpdate.setName("not existing");
        toUpdate.setPhoto("not existing");
        boolean updateResult = cityService.updateCity(toUpdate, "Update_user");
        assertFalse(updateResult);
    }

    @Test
    public void updateCityTest() {
        String updateValue = "NEW VALUE";
        Optional<City> byId = cityRepository.findById(1);
        CityDto toUpdate = new CityDto();
        if (byId.isPresent()) {
            City city = byId.get();
            toUpdate.setId(city.getId());
            toUpdate.setName(updateValue);
            toUpdate.setPhoto(updateValue);
        }
        boolean updateResult = cityService.updateCity(toUpdate, "Update_user");
        CityDto updated = cityService.findFirstByNameStartsWith("NEW");
        assertTrue(updateResult);
        assertEquals(updateValue, updated.getName());
        assertEquals(updateValue, updated.getPhoto());
    }

}
