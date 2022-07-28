package Scratch;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateExperiments {
    
    public static void main(String[] args) throws ParseException {
        Date today = new Date();
        
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

        String dateInString = "7-Jun-2013";
        
        Date exactDate = formatter.parse(dateInString);
        
        long difference = (- today.getTime() + exactDate.getTime())/ (1000 * 60 * 60 * 24);
        

        System.out.println(Math.abs(difference));
        
        
     
    }
}
