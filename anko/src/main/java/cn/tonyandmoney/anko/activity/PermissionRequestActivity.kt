package cn.tonyandmoney.anko.activity

import android.content.pm.PackageManager
import android.os.Build
import android.support.annotation.NonNull
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.util.SparseArray


/**
 * Created by niantuo on 2018/3/1.
 * 权限请求的Activity
 */
open class PermissionRequestActivity : AppCompatActivity() {

    private var requestList = SparseArray<PermissionRequest>()

    protected fun requirePermissions(requestCode: Int, @NonNull permissions: Array<String>,
                                     callbackEvent: PermissionEvent): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPermission(permissions, requestCode, callbackEvent)
        } else true
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private fun checkPermission(permissions: Array<String>, requestCode: Int,
                                callbackEvent: PermissionEvent): Boolean {
        var hasPermission = true
        val lackPermissions = mutableListOf<String>()
        for (permission in permissions) {
            val permissionState = checkSelfPermission(permission)
            if (permissionState != PackageManager.PERMISSION_GRANTED) {
                hasPermission = false
                lackPermissions.add(permission)
            }
        }
        if (!lackPermissions.isEmpty()) {
            val request = PermissionRequest()
            request.callbackEvent = callbackEvent
            request.permissions = lackPermissions.toTypedArray()
            requestList.put(requestCode, request)
            requestPermissions(request.permissions, requestCode)
        }
        return hasPermission
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        if (requestList.indexOfKey(requestCode) >= 0) {
            val request: PermissionRequest = requestList.get(requestCode)
            requestList.remove(requestCode)
            if (request.callbackEvent != null) {
                val deniedList = mutableListOf<String>()
                for (requestPermission in request.permissions.orEmpty()) {
                    var permissionIndex = -1
                    for (i in permissions.indices) {
                        val permission = permissions[i]
                        if (permission == requestPermission) {
                            permissionIndex = i
                            break
                        }
                    }
                    if (permissionIndex >= 0) {
                        val permissionState = grantResults[permissionIndex]
                        if (permissionState == PackageManager.PERMISSION_DENIED) {
                            deniedList.add(requestPermission)
                        }
                    } else {
                        Log.e("RequestPermission", "Permission "
                                + requestPermission + " has no result!")
                    }
                }
                if (deniedList.isEmpty()) {
                    request.callbackEvent?.permissionGranted()
                } else {
                    request.callbackEvent?.permissionDenied(deniedList.toTypedArray())
                }
            }
            return
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


    /**
     * 权限请求回调接口.
     * 在 Android M 及以上系统中会触发
     */
    interface PermissionEvent {
        /**
         * 成功获取权限
         */
        fun permissionGranted()

        /**
         * 用户拒绝授予权限
         *
         * @param permissions 被拒绝授予的权限
         */
        fun permissionDenied(permissions: Array<String>)
    }

    inner class PermissionRequest {
        internal var permissions: Array<String>? = null
        internal var callbackEvent: PermissionEvent? = null
    }
}