package com.coocaa.demo.util;

public class TimeUtil {
    //1week = 144.000s
    //1day = 28,800s
    //1hour = 3600s
    //1min = 60s
    public static String WeekDayHour(int s){
        int min = 0,hour = 0,day = 0,week = 0,temp = s;
        if(temp/144000 > 0){
            week = temp / 144000;
            temp = temp%144000;
        }if(temp/28800 > 0){
            day  = temp / 28800;
            temp = temp % 28800;
        }if(temp/3600 > 0){
            hour = temp / 3600;
            temp = temp % 3600;
        }if(temp/60 > 0){
            min = temp / 60;
            temp = temp % 60;
        }
        StringBuilder res = new StringBuilder("");
        if(week != 0)res = res.append(week).append("w");
        if(day != 0) res = res.append(day).append("d");
        if(hour != 0)res = res.append(hour).append("h");
        if(min != 0) res = res.append(min).append("m");
        return res.toString();
    }
}
