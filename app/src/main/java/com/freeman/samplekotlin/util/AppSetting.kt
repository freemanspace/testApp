package com.freeman.samplekotlin.util

import android.app.Activity
import android.os.Build
import android.provider.Settings
import android.util.DisplayMetrics

object AppSetting {
    var DeviceWidth:Int=0   //寬
    var DeviceHeight:Int=0  //高
    var ScreenDensity = 1.0f   //手機密度
    var ScreenDPI = 0           //手機DPI

    var DeviceID = "" //手機device id
    var DeviceName = "" //手機device name
    var PACKAGENAME = ""    //package name

    var CAMERAPATH = "" //相簿位置(只可放入相面),用在相機應用
    var IMAGEPATH = "" //圖片存取位置
    var FilePATH = "" //file存取位置


    //手機密度
    fun init(activity: Activity){
        val outMetrics = DisplayMetrics()
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            val display = activity.display
            display?.getRealMetrics(outMetrics)
        } else {
            @Suppress("DEPRECATION")
            val display = activity.windowManager.defaultDisplay
            @Suppress("DEPRECATION")
            display.getMetrics(outMetrics)
        }
        DeviceWidth = outMetrics.widthPixels
        DeviceHeight = outMetrics.heightPixels
        ScreenDensity = outMetrics.density
        ScreenDPI = outMetrics.densityDpi


        //device id
        try {
            DeviceID = Build.SERIAL
        } catch (e: Exception) {
        }
        if (DeviceID == "") {
            try {
                DeviceID = Settings.Secure.getString(
                        activity.getContentResolver(),
                        Settings.Secure.ANDROID_ID
                )
            } catch (e: Exception) {
            }
        }
        //device name
        val manufactuer = Build.MANUFACTURER
        val model = Build.MODEL
        if (manufactuer != null) {
            if (model != null && model.startsWith(manufactuer)) {
                DeviceName = model
            } else {
                DeviceName = "$manufactuer $model"
            }
        } else {
            DeviceName = model!!
        }
        PACKAGENAME = activity.packageName

        //檔案位置
        val dirFile_file = activity.getExternalFilesDir(null) //建立檔案目錄
        FilePATH = dirFile_file!!.absolutePath+"/"
        if (!dirFile_file!!.exists()) {
            dirFile_file.mkdir()
        }

        val dirFile_camera = activity.getExternalFilesDir("Camera") //建立相片目錄
        CAMERAPATH = dirFile_camera!!.absolutePath+"/"
        if (!dirFile_camera!!.exists()) {
            dirFile_camera.mkdir()
        }

        val dirFile_img = activity.getExternalFilesDir("Image") //建立相片目錄
        IMAGEPATH = dirFile_img!!.absolutePath+"/"
        if (!dirFile_img!!.exists()) {
            dirFile_img.mkdir()
        }
    }


}