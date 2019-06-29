package com.josancamon19.unittesting2cdm.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Random;

import static com.josancamon19.unittesting2cdm.util.DateUtil.GET_MONTH_ERROR;
import static com.josancamon19.unittesting2cdm.util.DateUtil.monthNumbers;
import static com.josancamon19.unittesting2cdm.util.DateUtil.months;
import static org.junit.jupiter.api.Assertions.*;

public class DateUtilTest {

    public static final String TODAY = "06-2019";

    @Test
    public void testGetCurrentTime_returnedTimestamp() throws Exception {
        // Arrange
        assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                // Assert
                assertEquals(TODAY, DateUtil.getCurrentTimeStamp());
            }
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11})
    public void getMonthFromNumber_returnSuccess(int number) throws Exception {
        assertEquals(months[number], DateUtil.getMonthFromNumber(monthNumbers[number]));
        System.out.println(monthNumbers[number] + " : " + months[number]);
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5,6,7,8,9,10,11})
    public void testGetMonthFromNumber_returnError(int number) throws Exception{
        int randomInt = new Random().nextInt(90) + 13;
        System.out.println(randomInt);
        assertEquals(DateUtil.getMonthFromNumber(String.valueOf(number * randomInt)),GET_MONTH_ERROR);
        System.out.println(monthNumbers[number] + " : " + GET_MONTH_ERROR);
    }
}
