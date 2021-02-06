package com.freeman.samplekotlin.http

interface OnApiFinish<T> {
    fun onApiFinish(msg:Int,dataObj:T)
}