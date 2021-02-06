package com.freeman.samplekotlin.util

import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

object Util_Date {

    /**
     * calendar轉換為文字
     */
    @Synchronized
    fun calendarToString(calendar: Calendar?, type: Int): String {
        if(calendar==null){
            return ""
        }
        var simpleDateFormat: SimpleDateFormat? = null
        when(type){
            0 -> simpleDateFormat = SimpleDateFormat("yyyy/MM/dd")
            1 -> simpleDateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
            2 -> simpleDateFormat = SimpleDateFormat("yyyyMMdd")
            3 -> simpleDateFormat = SimpleDateFormat("yyyyMMddHHmmss")
            4 -> simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
            5 -> simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            else -> simpleDateFormat = SimpleDateFormat("yyyy/MM/dd")
        }
        try{
            return simpleDateFormat.format(calendar.time)
        } catch (e:Exception){
            return ""
        }
    }

    /**
     * 文字轉換為calendar
     */
    @Synchronized
    fun strToCalendar(str: String?, type: Int): Calendar {
        var calendar = Calendar.getInstance()
        if(str==null ||str!!.isEmpty()){
            return calendar
        }
        var simpleDateFormat: SimpleDateFormat? = null
        when(type){
            0 -> simpleDateFormat = SimpleDateFormat("yyyy/MM/dd")
            1 -> simpleDateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
            2 -> simpleDateFormat = SimpleDateFormat("yyyyMMdd")
            3 -> simpleDateFormat = SimpleDateFormat("yyyyMMddHHmmss")
            4 -> simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
            5 -> simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            else -> simpleDateFormat = SimpleDateFormat("yyyy/MM/dd")
        }
        try {
            calendar.time = simpleDateFormat.parse(str)
        } catch (e:Exception){
            calendar = Calendar.getInstance()
        }
        return calendar
    }

}