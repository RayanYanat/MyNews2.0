package com.example.mynews;

import org.junit.Assert;
import org.junit.Test;


import java.util.Calendar;
import java.util.Date;

import utils.GetDate;

public class DateFormatTest {

    @Test
    public void checkEndDate() {
        String expectedEndDate = "17/08/2020";

        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, 2020);
        calendar.set(Calendar.MONTH, 7);
        calendar.set(Calendar.DAY_OF_MONTH, 17);
        Date actualEndDate = GetDate.getDateParse(expectedEndDate);

        Assert.assertEquals(calendar.getTimeInMillis(), actualEndDate.getTime());
    }

    @Test
    public void checkBeginDate() {
        String expectedBeginDate = "30/07/2020";

        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, 2020);
        calendar.set(Calendar.MONTH, 6);
        calendar.set(Calendar.DAY_OF_MONTH, 30);
        Date actualBeginDate = GetDate.getDateParse(expectedBeginDate);

        Assert.assertEquals(calendar.getTimeInMillis(), actualBeginDate.getTime());
    }
}
