package com.coocaa.demo.util;

import java.text.DecimalFormat;
import java.util.List;

public class MathUtil {
    public static String division(int x,int y){
    if(y<=0)return"0";
    DecimalFormat df=new DecimalFormat("0.00");
    String res = df.format((float)x*100000/y);
    return res;
    }
    public static int sum(List<Integer> list){
        int sum = 0;
        for (int i = 0; i <list.size() ; i++) {
            sum += list.get(i);
        }
        return sum;
    }

    public static int sumArray(int a[]){
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum+=a[i];
        }
        return  sum;
    }

}
