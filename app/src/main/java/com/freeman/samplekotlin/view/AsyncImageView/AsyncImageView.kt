package com.freeman.samplekotlin.view.AsyncImageView

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.freeman.samplekotlin.R
import com.freeman.samplekotlin.application.GlideApp
import java.io.File

class AsyncImageView : AppCompatImageView {

    private var defaultPic: Int = R.drawable.ic_launcher_background //設定default image
    private var errorPic: Int = R.drawable.ic_launcher_background //設定error image
    private var v_width = 100
    private var v_height = 100
    private var isAutoHeight = false    //是否自動計算高度
    private var transType = 0 //形狀轉換的方法,0:不變 1:cycle 2:圓角
    private var borderWidth = 1 //編筐寬度
    private var roundRate = 30.0f   //倒角角度
    private var borderColor = Color.RED //編筐顏色

    constructor(context: Context) : super(context) {
    }

    constructor(context: Context, attrs: AttributeSet?):super(context, attrs) {
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int):super(
        context,
        attrs,
        defStyle
    ) {
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (isAutoHeight) {    //是否自動計算高度
            val d = drawable
            if (d != null) {
                val w = MeasureSpec.getSize(widthMeasureSpec)
                val h = w * d.intrinsicHeight / d.intrinsicWidth
                setMeasuredDimension(w, h)
            } else {
                super.onMeasure(widthMeasureSpec, heightMeasureSpec)
            }
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
    }

    /**
     * 設定起始圖片
     * @param imageRes
     */
    fun setDefaultImage(imageRes: Int) {
        defaultPic = imageRes
    }

    /**
     * 設定失敗的圖片
     * @param imageRes
     */
    fun setErrorImage(imageRes: Int) {
        errorPic = imageRes
    }

    /**
     * 設定圖片轉化型態 0不變,1圓形,2圓角
     * @param transType
     */
    fun setTransType(transType: Int) {
        this.transType = transType
    }

    /**
     * 設定編筐寬度
     */
    fun setBorderWidth(borderWidth: Int) {
        this.borderWidth = borderWidth
    }

    /**
     * 設定編筐顏色
     */
    fun setBorderColor(borderColor: Int) {
        this.borderColor = borderColor
    }

    /**
     * 設定倒角角度
     */
    fun setRoundRate(roundRate: Float) {
        this.roundRate = roundRate
    }

    /**
     * 指定image view size
     */
    fun setSize(width: Int, height: Int, isAutoHeight: Boolean) {
        //是否自動高度
        this.isAutoHeight = isAutoHeight
        //設定大小
        v_width = width
        v_height = height
        var lp1 = this.layoutParams
        if (lp1 == null) {
            if (isAutoHeight) {
                lp1 = LinearLayout.LayoutParams(v_width, ViewGroup.LayoutParams.WRAP_CONTENT)
            } else {
                lp1 = LinearLayout.LayoutParams(v_width, v_height)
            }
        } else {
            lp1.width = v_width
            if (isAutoHeight) {
                lp1.height = ViewGroup.LayoutParams.WRAP_CONTENT
            } else {
                lp1.height = v_height
            }
        }
        this@AsyncImageView.layoutParams = lp1
    }

    //查看類型做圖片切割
    private fun addOptions(options: RequestOptions) {
        when(transType){
            1 -> {  //圓形
                options.apply(
                    RequestOptions.bitmapTransform(
                        CircleCrop()    //glide
                    )
                )
            }
            2 -> {  //圓形 自定義，會比較模糊
                options.apply(
                    RequestOptions.bitmapTransform(
                        CycleTransform(borderWidth, borderColor)
                    )
                )
            }
            3 -> {   //導角
                options.apply(
                    RequestOptions.bitmapTransform(
                        RoundTransform(roundRate, borderWidth, borderColor)
                    )
                )
            }
            4 -> {   //導角 自定義
                options.apply(
                    RequestOptions.bitmapTransform(
                        RoundedCorners(roundRate.toInt())
                    )
                )
            }

        }
    }


    /**
     * 設定url的圖片
     * @param imageUrl
     * @param width
     * @param height
     */
    fun setImageUrl(imageUrl: String?, width: Int, height: Int, isAutoHeight: Boolean) {
        if (width != 0 && height != 0) {    //如果都給0不變動size
            setSize(width, height, isAutoHeight)
        }
        val options: RequestOptions = RequestOptions()
            .placeholder(defaultPic)
            .error(errorPic)
            .fitCenter()
        //.centerCrop()	截取中间的显示
        addOptions(options) //做變形
        if (transType != 0) {
            GlideApp.with(context)
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.NONE) //不要file cacahe
                .skipMemoryCache(true) //不要memory cache
                .apply(options)
                .into(this@AsyncImageView)
        } else {
            GlideApp.with(context)
                .load(imageUrl)
                .apply(options)
                .into(this@AsyncImageView)
        }
    }

    /**
     * 設定本地的圖片
     * @param imageFilePath
     * @param width
     * @param height
     */
    fun setImageFilePath(imageFilePath: String?, width: Int, height: Int, isAutoHeight: Boolean) {
        if (width != 0 && height != 0) {    //如果都給0不變動size
            setSize(width, height, isAutoHeight)
        }
        val options: RequestOptions = RequestOptions()
            .placeholder(defaultPic)
            .error(errorPic)
            .fitCenter()
        //.centerCrop()	截取中间的显示
        addOptions(options) //做變形
        if (transType != 0) {
            GlideApp.with(context)
                .load(File(imageFilePath))
                .diskCacheStrategy(DiskCacheStrategy.NONE) //不要file cacahe
                .skipMemoryCache(true) //不要memory cache
                .apply(options)
                .into(this@AsyncImageView)
        } else {
            GlideApp.with(context)
                .load(File(imageFilePath))
                .apply(options)
                .into(this@AsyncImageView)
        }
    }

    /**
     * 設定resource的圖片
     * @param imageFilePath
     * @param width
     * @param height
     */
    fun setImageRes(drawableres: Int, width: Int, height: Int, isAutoHeight: Boolean) {
        if (width != 0 && height != 0) {    //如果都給0不變動size
            setSize(width, height, isAutoHeight)
        }
        val options: RequestOptions = RequestOptions()
            .placeholder(defaultPic)
            .error(errorPic)
            .fitCenter()
        addOptions(options) //做變形
        if (transType != 0) {
            GlideApp.with(context)
                .load(drawableres)
                .diskCacheStrategy(DiskCacheStrategy.NONE) //不要file cacahe
                .skipMemoryCache(true) //不要memory cache
                .apply(options)
                .into(this@AsyncImageView)
        } else {
            GlideApp.with(context)
                .load(drawableres)
                .apply(options)
                .into(this@AsyncImageView)
        }
    }

}