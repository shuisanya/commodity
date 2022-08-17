package com.mhy.commodity.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FetchBigDecimal {

    public static BigDecimal getBigDecimal(double nums,int digits){

        return BigDecimal.valueOf(nums).setScale(digits, RoundingMode.HALF_UP);

    }
}
