package com.freeman.samplekotlin.activity.fragment.othger

import android.view.View
import com.freeman.samplekotlin.R
import com.freeman.samplekotlin.activity.BaseFragment
import com.freeman.samplekotlin.data.Test_Json
import com.freeman.samplekotlin.http.MyApiManager
import com.freeman.samplekotlin.http.OnApiFinish
import kotlinx.android.synthetic.main.fragment_demo_api.*

class DemoAPI_Fragment : BaseFragment() {

    override fun getLayoutRes(): Int {
        return R.layout.fragment_demo_api
    }

    override fun initView(){
        setHeaderTitle("Api Demo")
        btn_send.setOnClickListener(View.OnClickListener {
            showLoading()
            MyApiManager.getDemo(object :OnApiFinish<Test_Json>{
                override fun onApiFinish(msg: Int, dataObj: Test_Json) {
                    closeLoading()
                    tv_response.setText("data size is ${dataObj.datas.size}")

                }
            })
        })
    }


}