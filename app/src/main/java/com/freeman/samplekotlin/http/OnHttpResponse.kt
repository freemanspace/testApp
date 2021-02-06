package com.freeman.samplekotlin.http

interface OnHttpResponse {
    fun onHttpRequestFinish(httpResponseData: HttpResponseData) //httpresponse呼叫此

}