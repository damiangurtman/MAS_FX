package pl.Dams.MAS.Models;

import java.util.Date;

public class MyDate extends Date {

    public MyDate(int year, int month, int day) {
        super(year -1900, month -1, day);
    }

    public MyDate() {
        super();
    }
}
