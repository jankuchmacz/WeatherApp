package pl.kuchmaczpogoda.controller;


import org.junit.jupiter.api.Test;
import java.text.ParseException;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class WeatherControllerTest {

    Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance

    @Test
    public void shouldReturnCorrectTimeInTimeZoneAsDateObject() throws ParseException {
        //given
        Clock fixedClock = Clock.fixed(Instant.parse("2020-10-14T08:15:30.00Z"), ZoneId.of("Europe/Warsaw"));
        WeatherController weatherController = new WeatherController(fixedClock);
        String exampleTimeZone = "Europe/Warsaw";
        Date date = prepareFixedDate();
        //when
        Date currentDate = weatherController.getTimeInZone(exampleTimeZone);
        //then
        assertEquals(date.getTime(), currentDate.getTime());
    }
    @Test
    public void shouldTrimDateCorrectly(){
        //given
        Date date = prepareFixedDate();
        //when
        Date dateAfterTrimOperation = WeatherController.trim(date);
        calendar.setTime(dateAfterTrimOperation);
        //then
        assertAll("Group assertion for TrimDateFunction",
                () ->  assertEquals(calendar.get(Calendar.HOUR_OF_DAY), 12),
                () ->  assertEquals(calendar.get(Calendar.MINUTE), 0),
                () ->  assertEquals(calendar.get(Calendar.SECOND), 0),
                () ->  assertEquals(calendar.get(Calendar.MILLISECOND), 0));
    }
   @Test
    public static Date prepareFixedDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 30);
        calendar.set(Calendar.MINUTE, 15);
        calendar.set(Calendar.HOUR_OF_DAY,10);
        calendar.set(Calendar.DAY_OF_MONTH, 14);
        calendar.set(Calendar.MONTH, 9);
        calendar.set(Calendar.YEAR, 2020);
        return calendar.getTime();
    }
}
