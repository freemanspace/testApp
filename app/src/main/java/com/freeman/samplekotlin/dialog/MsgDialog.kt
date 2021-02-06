package com.freeman.samplekotlin.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.freeman.samplekotlin.R



class MsgDialog(context:Context,private var title:String?,private var msg:String?,private var str_ok:String?,private var onDialogClickListener: OnDialogClickListener?,private var obj:Any?) : Dialog(context,R.style.dialogNobackground) {

    companion object {
        val Action_OK = 0
    }

    private var tv_title: TextView? = null
    private var tv_msg:TextView? = null
    private var btn_ok: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setCancelable(false)
        setCanceledOnTouchOutside(false)
        //view
        setContentView(LayoutInflater.from(context).inflate(R.layout.dialog_msg,null) as LinearLayout)
        tv_title = findViewById(R.id.tv_title)
        if(title==null || title!!.isEmpty()){
           tv_title?.visibility = View.GONE
        } else {
            tv_title?.setText(title)
        }
        tv_msg = findViewById(R.id.tv_msg)
        tv_msg?.setText(msg)
        btn_ok = findViewById(R.id.btn_ok)
        btn_ok?.setText(str_ok)
        btn_ok?.setOnClickListener(onClickListener)
        //
        var lp:WindowManager.LayoutParams = window?.attributes as WindowManager.LayoutParams
        lp.x = 0
        lp.y = 0
        onWindowAttributesChanged(lp)
    }


    private val onClickListener = View.OnClickListener {
        if (onDialogClickListener != null) {
            onDialogClickListener!!.onDialogClick(Action_OK, obj)
        }
        dismiss()
    }

}