

package gui.Pubudu.modler;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class GetCurrentTime {
    
    public static void main(String args[]){
        DateFormat dateFormat = new SimpleDateFormat("YYYY/MM/dd");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        
        Calendar cal = Calendar.getInstance();
        System.out.println(dateFormat.format(date));
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        
        calendar.add(Calendar.DAY_OF_MONTH,-30);
        
        Date Previousmonthdate = calendar.getTime();
        String result = dateFormat.format(Previousmonthdate);
        System.out.println("...............");
        System.out.println(result);
    }
}
