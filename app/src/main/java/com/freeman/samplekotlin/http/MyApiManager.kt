package com.freeman.samplekotlin.http

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import com.freeman.samplekotlin.data.JsonParser
import com.freeman.samplekotlin.data.Test_Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import java.io.File
import java.lang.ref.WeakReference

object MyApiManager {

    @Synchronized
    fun getDemo(onApiFinish: OnApiFinish<Test_Json>){
        val onMyDataFinishWeakReference: WeakReference<OnApiFinish<Test_Json>> = WeakReference<OnApiFinish<Test_Json>>(onApiFinish)
        var request = Request.Builder()
                .url("https://reqres.in/api/users")
                .addHeader("Authorization", "")
                .get()
                .build()
        HttpHelper.httpRequest(request, object : OnHttpResponse {
            override fun onHttpRequestFinish(httpResponseData: HttpResponseData) {
                val test_Json = Test_Json()
                //parser data
                JsonParser().parser(test_Json,httpResponseData.response)
                //live data
                bindMyApiData(0,test_Json,onMyDataFinishWeakReference)
            }
        })
    }

    @Synchronized
    fun postJsonDemo(onApiFinish: OnApiFinish<Test_Json>){
        val onMyDataFinishWeakReference: WeakReference<OnApiFinish<Test_Json>> = WeakReference<OnApiFinish<Test_Json>>(onApiFinish)

        var jsonParam = JSONObject()
        jsonParam.put("test","test")
        var request = Request.Builder()
                .url("https://reqres.in/api/users")
                .addHeader("Authorization", "")
                .post(jsonParam.toString().toRequestBody("application/json;charset=utf-8".toMediaType()))
                .build()

        HttpHelper.httpRequest(request, object : OnHttpResponse {
            override fun onHttpRequestFinish(httpResponseData: HttpResponseData) {
                val test_Json = Test_Json()
                //parser data
                JsonParser().parser(test_Json,httpResponseData.response)
                //live data
                bindMyApiData(0,test_Json,onMyDataFinishWeakReference)
            }
        })
    }

    @Synchronized
    fun postFormDemo(onApiFinish: OnApiFinish<Test_Json>){
        val onMyDataFinishWeakReference: WeakReference<OnApiFinish<Test_Json>> = WeakReference<OnApiFinish<Test_Json>>(onApiFinish)

        var mapParam = HashMap<String,String>()
        mapParam.put("test","test")
        var request = Request.Builder()
                .url("https://reqres.in/api/users")
                .addHeader("Authorization", "")
                .post(mapParam.toString().toRequestBody("application/x-www-form-urlencoded;charset=utf-8".toMediaType()))
                .build()

        HttpHelper.httpRequest(request, object : OnHttpResponse {
            override fun onHttpRequestFinish(httpResponseData: HttpResponseData) {
                val test_Json = Test_Json()
                //parser data
                JsonParser().parser(test_Json,httpResponseData.response)
                //live data
                bindMyApiData(0,test_Json,onMyDataFinishWeakReference)
            }
        })
    }

    @Synchronized
    fun postFileDemo(onApiFinish: OnApiFinish<Test_Json>){
        val onMyDataFinishWeakReference: WeakReference<OnApiFinish<Test_Json>> = WeakReference<OnApiFinish<Test_Json>>(onApiFinish)

        var file = File("xxxx.png")
        val img = file.asRequestBody("image/png".toMediaType())

        var multipartBody = MultipartBody.Builder()
                .addFormDataPart("file","xxx.png",img)
                .addFormDataPart("test","test")
                .build()
        var request = Request.Builder()
                .url("https://reqres.in/api/users")
                .addHeader("Authorization", "")
                .post(multipartBody)
                .build()

        HttpHelper.httpRequest(request, object : OnHttpResponse {
            override fun onHttpRequestFinish(httpResponseData: HttpResponseData) {
                val test_Json = Test_Json()
                //parser data
                JsonParser().parser(test_Json,httpResponseData.response)
                //live data
                bindMyApiData(0,test_Json,onMyDataFinishWeakReference)
            }
        })
    }



    //bind live data----------------------------------------------------------------------------------------------
    private fun <T>bindMyApiData(msg:Int,dataObj:T,onMyDataFinishWeakReference:WeakReference<OnApiFinish<T>>){
        val myApiData = MyApiData(0, dataObj)
        bindLiveData(onMyDataFinishWeakReference.get(), myApiData)
    }


    @Synchronized
    private fun <T> bindLiveData(onMyDataFinish: OnApiFinish<T>?, myApiData: MyApiData<T>) {
        val onMyDataFinishWeakReference: WeakReference<OnApiFinish<T>> = WeakReference<OnApiFinish<T>>(
                onMyDataFinish
        )
        val observer: Observer<Boolean?> = Observer {
            if (onMyDataFinishWeakReference.get() != null) {
                onMyDataFinishWeakReference.get()!!.onApiFinish(
                        myApiData.msg!!,
                        myApiData.dataObj!!
                )
            }
        }
        myApiData.getisFinish().observe(ForeverStartLifecycleOwner.INSTANCE, observer)
        myApiData.getisFinish().setValue(true)
    }


    private enum class ForeverStartLifecycleOwner : LifecycleOwner {
        INSTANCE;

        private val mLifecycleRegistry: LifecycleRegistry

        init {
            mLifecycleRegistry = LifecycleRegistry(this)
            mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)
        }

        override fun getLifecycle(): Lifecycle {
            return mLifecycleRegistry
        }
    }

}