package com.freeman.samplekotlin.util

import android.util.Log

object MyLog {

    @Synchronized
    fun log(tag: String?, msg: String?){
        if(tag==null){
            Log.e("test", msg!!)
        } else{
            Log.e(tag, msg!!)
        }
    }

    @Synchronized
    fun log(msg: String?) {
        //系統log
        Log.e("test", msg!!)
    }
}