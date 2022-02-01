package pl.kuchmaczpogoda.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import pl.kuchmaczpogoda.model.WeatherInfo;
import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.mock;

public class AddCityControllerTest {

    private static final String CITY_NAME = "Warsaw";
    private AddCityController addCityController = new AddCityController();

    @Mock
    WeatherInfo weatherInfoMock = mock(WeatherInfo.class);

    @Test
    public void shouldReturnTrueForCorrectCityName() throws MalformedURLException {
        //given
        willDoNothing().given(weatherInfoMock).connectToDatabase(CITY_NAME);
        given(weatherInfoMock.getResponseCode()).willReturn(200);
        //when
        boolean result = addCityController.isCityNameCorrect(CITY_NAME);
        //then
        assertTrue(result);
    }
}
