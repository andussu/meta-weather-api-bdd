package com.metaweather.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    /**
     *
     * @param date argument can be relational (today/tomorrow) or specific date pre-formatted (yyyy/MM/dd)
     * @return regardless of entry, will return date as String of pattern yyyy/MM/dd
     */
    public String processDate(String date) {

        Calendar cal = Calendar.getInstance();

        switch (date.toLowerCase()) {
            case "yesterday":
                cal.add(Calendar.DATE, -1);
                break;
            case "today":
                //happy days, just return
                break;
            case "tomorrow":
                cal.add(Calendar.DATE, 1);
                break;
            default:
                return date;
        }
        return "" + cal.get(Calendar.YEAR) + '/' + cal.get(Calendar.MONTH) + '/' + cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     *
     * @param date This will arrive as yyyy/MM/dd format w/o 0's in the single digit situations
     * @return same date with yyyy-MM-dd format, including 0's where single digit date
     */
    public String swapDateFormat(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try{
            Date hyphenedReqDay = sdf.parse(date.replace('/','-'));
            return sdf.format(hyphenedReqDay);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return null;
    }
}
