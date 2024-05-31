package com.stat_tracker.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class StatsUtils {
    public static double calculatePercentage(int made, int attempted){
        if(attempted == 0) return 0.0;

        double percentage = (made * 100.0) / attempted;
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat df = new DecimalFormat("#.00", symbols);
        return Double.parseDouble(df.format(percentage));
    }
}
