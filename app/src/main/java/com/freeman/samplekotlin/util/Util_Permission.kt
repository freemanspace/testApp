package com.freeman.samplekotlin.util

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build

object Util_Permission {

    const val PermissionRequestCode = 800

    /**
     * 檢核permission是否全部通過
     * @param activity
     * @param PermissionList
     * @return
     */
    @Synchronized
    fun checkPermission(activity: Activity, permissionList: Array<String>): Boolean {
        var permission = true
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (i in permissionList.indices) {
                if (activity.checkSelfPermission(permissionList[i]) == PackageManager.PERMISSION_DENIED) {
                    permission = false
                }
            }
        }
        return permission
    }

    /**
     * 要求權限
     * @param activity
     * @param PermissionList
     */
    @Synchronized
    fun requestPermission(activity: Activity, permissionList: Array<String>) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.requestPermissions(permissionList, PermissionRequestCode)
        }
    }

    /**
     * 檢核是否全部權限都同意
     * @param requestCode
     * @param grantResults
     * @return
     */
    @Synchronized
    fun handlePermissionResult(requestCode: Int, grantResults: IntArray, onPermissionHandle: OnPermissionHandle) {
        if (requestCode == PermissionRequestCode) {
            var permission = true
            for (i in grantResults.indices) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    permission = false
                }
            }
            onPermissionHandle.onPermissionHandle(true, permission)
        } else {
            onPermissionHandle.onPermissionHandle(false, false)
        }
    }

    /**
     * handle permission listener
     */
    public interface OnPermissionHandle {
        fun onPermissionHandle(isRequestCode: Boolean, isPermissionpass: Boolean)
    }
}