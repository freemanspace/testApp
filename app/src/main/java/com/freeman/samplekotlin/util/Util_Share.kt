package com.freeman.samplekotlin.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.core.content.FileProvider
import com.freeman.samplekotlin.BuildConfig
import java.io.File
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

object Util_Share {

    /**
     * 分享文字
     */
    @Synchronized
    fun shareMsg(activity: Activity,title:String,msg:String){
        Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, title)
            putExtra(Intent.EXTRA_TEXT, msg)
            activity.startActivity(Intent.createChooser(this, "Share"))
        }
    }

    /**
     * 分享圖片
     */
    @Synchronized
    fun shareImg(activity:Activity,title:String,msg:String,filePath:String?) {
        //val uri = saveCacheBitmap(bitmap, fileName)
        Intent(Intent.ACTION_SEND).apply {
            flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
            type = "image/*"
            putExtra(Intent.EXTRA_STREAM, getProviderUri(activity,filePath))
            putExtra(Intent.EXTRA_SUBJECT, title)
            putExtra(Intent.EXTRA_TEXT, msg)
            activity.startActivity(Intent.createChooser(this, "Share"))
        }
    }

    /**
     * 使用FileProvider取得分享檔案的位置
     */
    @Synchronized
    fun getProviderUri(context: Context,filePath: String?):Uri{
        val file = File(filePath)
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){   //6.0 23
            return FileProvider.getUriForFile(context,BuildConfig.APPLICATION_ID+".file_provider",file)
        } else{
            return Uri.fromFile(file)
        }
    }


}