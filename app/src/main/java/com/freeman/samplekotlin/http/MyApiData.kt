package com.freeman.samplekotlin.http

import androidx.lifecycle.MutableLiveData

class MyApiData<T>(public var msg:Int,public var dataObj:T) {

    public var isFinish:SingleLiveEvent<Boolean>?=null


    fun getisFinish(): MutableLiveData<Boolean?> {
        if (isFinish == null) {
            //isFinish = new MutableLiveData<Boolean>();
            isFinish = SingleLiveEvent()
        }
        return isFinish!!
    }

}