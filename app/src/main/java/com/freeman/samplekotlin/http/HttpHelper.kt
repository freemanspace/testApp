package com.freeman.samplekotlin.http


import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleEmitter
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.core.SingleOnSubscribe
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.*
import okio.Buffer
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.net.ssl.*
import javax.security.cert.CertificateException

object HttpHelper {

    @Synchronized
    public fun httpRequest(request: Request, onHttpResponse: OnHttpResponse){
        Single.create(
                SingleOnSubscribe<HttpResponseData> { emitter ->
                //call api
                callHttpRequest(request, emitter)
            }
        )
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object : SingleObserver<HttpResponseData>{
            override fun onSubscribe(d: Disposable?) {
            }

            override fun onError(e: Throwable?) {
            }

            override fun onSuccess(httpResponseData: HttpResponseData?) {
                onHttpResponse.onHttpRequestFinish(httpResponseData!!)
            }
        })
    }



    //使用okhttp呼叫api
    private fun callHttpRequest(request: Request, emitter: SingleEmitter<HttpResponseData>){

        //http client
        var (sslSocketFactory, x509TrustManager) = createSSLSocketFactory()  //create ssl unsafe pass
        val client: OkHttpClient = OkHttpClient.Builder()
            .sslSocketFactory(sslSocketFactory, x509TrustManager) //ssl驗證
            .hostnameVerifier(hostnameVerifier = HostnameVerifier { _, _ -> true })    //host check pass
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(90, TimeUnit.SECONDS).build()
        //call api
        val call = client.newCall(request)

        call.enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                //log
                logRequestError(call, e)
                //通知觀察者
                val httpResponseData = HttpResponseData()
                httpResponseData.httpCode = -1
                httpResponseData.session = ""
                httpResponseData.response = e.message
                emitter.onSuccess(httpResponseData)
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                //取出http Code
                val httpCode = response.code
                //取出http response
                val httpResponse = response.body!!.string()
                //取出http session
                var httpSession = ""
                val headers = response.headers
                val cookies = headers.values("Set-Cookie")
                if (cookies != null && cookies.size > 0) {
                    httpSession = cookies[0]
                    httpSession = httpSession.substring(0, httpSession.indexOf(";"))
                }
                //log
                logRequestResponse(call, httpResponse)

                //通知觀察者
                val httpResponseData = HttpResponseData()
                httpResponseData.response = httpResponse
                httpResponseData.httpCode = httpCode
                httpResponseData.session = httpSession
                emitter.onSuccess(httpResponseData)
            }
        })
    }

    @Synchronized
    private fun logRequestError(call: Call?, e: IOException) {
        try {
            Log.e("test", "request =>" + call!!.request().body.toString())
            Log.e("test", "headers =>" + call.request().headers.toString())
            val log = Buffer()
            if (call != null && call.request() != null && call.request().body != null) {
                call.request().body!!.writeTo(log)
            }
            Log.e("test", "params =>" + log.readUtf8())
        } catch (e1: Exception) {
            e1.printStackTrace()
        }
    }

    @Synchronized
    private fun logRequestResponse(call: Call?, response: String) {
        Log.e("test", "request =>" + call!!.request().toString())
        Log.e("test", "headers =>" + call.request().headers.toString())
        try {
            val log = Buffer()
            if (call != null && call.request() != null && call.request().body != null) {
                call.request().body!!.writeTo(log)
            }
            Log.e("test", "params =>" + log.readUtf8())
        } catch (e1: java.lang.Exception) {
            e1.printStackTrace()
        }
        try {
            Log.e("test", "response =>$response")
        } catch (e: java.lang.Exception) {
        }
    }

    //產生ssl socket factory
    private fun createSSLSocketFactory(): Pair<SSLSocketFactory, X509TrustManager> {
        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
            @Throws(CertificateException::class)
            override fun checkClientTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
            }

            @Throws(CertificateException::class)
            override fun checkServerTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
            }

            override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
                return arrayOf()
            }
        })

        var sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, trustAllCerts, java.security.SecureRandom())
        val sslSocketFactory = sslContext.socketFactory
        val x509TrustManager = trustAllCerts[0] as X509TrustManager
        return Pair(sslSocketFactory, x509TrustManager)
    }


}