package com.freeman.samplekotlin.view.MyEditText

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.freeman.samplekotlin.R
import com.google.android.material.textfield.TextInputEditText

/**
 * 透過icon left新增左方icon
 * 透過icon right新增右方icon，右方做清空的動作
 */
class MyEditText : TextInputEditText{

    var leftDrable: Drawable? = null
    var rightDrable: Drawable? = null

    constructor(context: Context) : super(context) {
        initView(context,null)
    }

    constructor(context: Context, attrs: AttributeSet?):super(context, attrs) {
        initView(context,attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int):super(context, attrs, defStyle) {
        initView(context,attrs)
    }


    private fun initView(context: Context, attrs: AttributeSet?){
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyEditText)
        leftDrable = typedArray.getDrawable(R.styleable.MyEditText_iconLeft)
        rightDrable = typedArray.getDrawable(R.styleable.MyEditText_iconRight)
        val iconSize = context.resources.getDimensionPixelOffset(R.dimen.myicon_size)

        leftDrable?.setBounds(0, 0, iconSize, iconSize)
        rightDrable?.setBounds(0, 0, iconSize, iconSize)

        setClearIconVisible(false)

        setOnTouchListener(onTouchListener)
        addTextChangedListener(textWatcher)
    }

    private fun setClearIconVisible(visible: Boolean) {
        rightDrable?.setVisible(visible, false)
        if(visible) {
            setCompoundDrawables(leftDrable, null, rightDrable, null)
        } else{
            setCompoundDrawables(leftDrable, null, null, null)
        }
    }


    private val onTouchListener = object : View.OnTouchListener {
        override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
            var x = p1?.x?.toInt()
            if(rightDrable!=null && x!=null) {
                if (rightDrable!!.isVisible && (x>getWidth()-paddingRight-rightDrable!!.intrinsicWidth) ){
                    if (p1?.getAction() == MotionEvent.ACTION_UP) {
                        clearText()
                    }
                    return true
                }
            }
            return false
        }
    }

    private var textWatcher = object :TextWatcher{
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            if(p0!=null && isFocused){
                setClearIconVisible(p0!!.length>0)
            }
        }

        override fun afterTextChanged(p0: Editable?) {
        }
    }

    /**
     * 清空資料
     */
    public fun clearText(){
        setText("")
        setCompoundDrawables(leftDrable, null, null, null)
    }




}