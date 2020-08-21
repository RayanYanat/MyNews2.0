package com.example.mynews;

import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateFormatTest {

    @Test
    public void checkEndDate() {
        String expectedEndDate = "17/08/2020";

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2020);
        calendar.set(Calendar.MONTH, 07);
        calendar.set(Calendar.DAY_OF_MONTH, 17);
        String mFormat = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(mFormat, Locale.FRANCE);
        String actualEndDate = simpleDateFormat.format(calendar.getTime());

        Assert.assertEquals(expectedEndDate, actualEndDate);
    }

    @Test
    public void checkBeginDate() {
        String expectedBeginDate = "30/07/2020";

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2020);
        calendar.set(Calendar.MONTH, 06);
        calendar.set(Calendar.DAY_OF_MONTH, 30);
        String mFormat = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(mFormat, Locale.FRANCE);
        String actualBeginDate = simpleDateFormat.format(calendar.getTime());

        Assert.assertEquals(expectedBeginDate, actualBeginDate);
    }
}
