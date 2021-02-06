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

class MsgSelDialog(
    context: Context,
    private var title: String?,
    private var msg: String?,
    private var str_no: String?,
    private var str_ok: String?,
    private var onDialogClickListener: OnDialogClickListener?,
    private var obj: Any?
) : Dialog(context, R.style.dialogNobackground) {

    companion object {
        val Action_OK = 0
        val Action_NO = 1
    }

    private var tv_title: TextView? = null
    private var tv_msg:TextView? = null
    private var btn_no: Button? = null
    private var btn_ok: Button? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setCancelable(false)
        setCanceledOnTouchOutside(false)
        //view
        setContentView(
            LayoutInflater.from(context).inflate(R.layout.dialog_msgselect, null) as LinearLayout
        )
        tv_title = findViewById(R.id.tv_title)
        if(title==null || title!!.isEmpty()){
           tv_title?.visibility = View.GONE
        } else {
            tv_title?.setText(title)
        }
        tv_msg = findViewById(R.id.tv_msg)
        tv_msg?.setText(msg)
        btn_no = findViewById(R.id.btn_no)
        btn_no?.setText(str_no)
        btn_no?.setOnClickListener(onClickListener)
        btn_ok = findViewById(R.id.btn_ok)
        btn_ok?.setText(str_ok)
        btn_ok?.setOnClickListener(onClickListener)
        //
        var lp:WindowManager.LayoutParams = window?.attributes as WindowManager.LayoutParams
        lp.x = 0
        lp.y = 0
        onWindowAttributesChanged(lp)
    }


    private val onClickListener =
        View.OnClickListener { view ->
            val vid = view.id
            if (vid == btn_no?.id) {
                if (onDialogClickListener != null) {
                    onDialogClickListener?.onDialogClick(Action_NO,obj)
                }
            } else if (vid == btn_ok?.id) {
                if (onDialogClickListener != null) {
                    onDialogClickListener?.onDialogClick(Action_OK,obj)
                }
            }
            dismiss()
        }

}