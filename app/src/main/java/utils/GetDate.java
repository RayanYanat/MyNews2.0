package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetDate {

    public static Date getDateParse(String date) {
        try {
          return new  SimpleDateFormat("dd/MM/yyyy").parse(date);
        }
        catch (Exception e) {
        }

        return null;
    }
}