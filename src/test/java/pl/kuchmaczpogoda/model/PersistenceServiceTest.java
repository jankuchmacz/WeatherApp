package pl.kuchmaczpogoda.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersistenceServiceTest {
    @Test
    void saveToPersistenceMethodAndLoadFromPersistenceMethodShouldDealWithDataCorrectly(){
        //given
        PersistenceData persistenceData = preparePersistenceData();
        PersistenceService persistenceService = new PersistenceService("dataTestFile.ser");
        //when
        persistenceService.saveToPersistenceMethod(persistenceData);
        PersistenceData returnPersistentData = persistenceService.loadFromPersistenceMethod();
        //then
        assertAll(
                () -> assertEquals (returnPersistentData.getFisrtCityData().getCityName(), "Krakow"),
                () -> assertEquals (returnPersistentData.getFisrtCityData().getTimeZoneName(), "Europe/Warsaw"),
                () -> assertEquals (returnPersistentData.getSecondCityData().getCityName(), "Moscow"),
                () -> assertEquals (returnPersistentData.getSecondCityData().getTimeZoneName(), "Europe/Moscow"),
                () -> assertEquals(returnPersistentData.getCities().get(0).getCityName(), "Warszawa"),
                () -> assertEquals(returnPersistentData.getCities().get(1).getCityName(), "London")

        );
    }

    private PersistenceData preparePersistenceData() {
        PersistenceData persistenceData = new PersistenceData();
        SelectedCityData dataForCityOne = new SelectedCityData("Krakow", "Europe/Warsaw");
        persistenceData.setFisrtCityData(dataForCityOne);
        SelectedCityData dataForCityTwo = new SelectedCityData("Moscow", "Europe/Moscow");
        persistenceData.setSecondCityData(dataForCityTwo);
        List<City> cityList = Arrays.asList(new City("Warszawa"), new City("London"));
        persistenceData.setCities(cityList);
        return persistenceData;
    }
}
