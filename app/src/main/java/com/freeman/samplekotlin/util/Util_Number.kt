package com.freeman.samplekotlin.util

import java.math.BigDecimal

object Util_Number {

    /**
     * double四捨五入
     */
    @Synchronized
    fun getDouble(num:Double,scale:Int): Double {
        return BigDecimal(num).setScale(scale,BigDecimal.ROUND_HALF_UP).toDouble()
    }

    /**
     * float四捨五入
     */
    @Synchronized
    fun getFloat(num:Double,scale:Int): Float {
        return BigDecimal(num).setScale(scale,BigDecimal.ROUND_HALF_UP).toFloat()
    }

}