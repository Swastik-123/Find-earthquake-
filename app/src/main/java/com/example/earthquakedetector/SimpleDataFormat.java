


                                          //We dont need this








//Date Formatting
//        Thankfully, you don't have to handle date formatting yourself. There's a fantastic class called SimpleDateFormat that knows all about time zones and how dates are written in different parts of the world, and will handle all this complexities for you.
//
//        You supply a desired time format such as: "yyyy-MM-dd HH:mm:ss"
//
//        In the time format syntax, characters have special meaning, which is described in detail in this table.
//
//        “y” stands for year, “yyyy” stands for a 4-digit year like 2016.
//        “M” stands for month, “MM” stands for a 2-digit month like 03.
//        “d” stands for day, “dd” stands for a 2-digit day like 10.
//        “H” stands for hour.
//        “m” stands for minute in hour.
//        “s” stands for second in minute.
//        Any characters that are not listed in the table of special characters, are used directly in the output string. If the time format string contains a colon or dash or comma, for example, then the output string will also contain the same punctuation symbol in that direct location.
//
//        Here’s an example for the time 1463159138711 milliseconds. We create a SimpleDateFormat object with the format "yyyy-MM-dd HH:mm:ss a", and pass in a Date object initialized with the time 1463159138711 milliseconds, and then the date will be formatted as “2016-05-13 12:07:46 PM”.
//
//



//                                     Networking ,lesson,19,20



//package com.example.earthquakedetector;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//public class SimpleDataFormat {
//
//    long timeInMilliseconds = 1454124312220L;
//    Date dateObject = new Date(timeInMilliseconds);
//
//    SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM DD, yyyy");
//    String dateToDisplay = dateFormatter.format(dateObject);
//
//}
