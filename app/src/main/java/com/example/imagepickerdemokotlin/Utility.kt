package com.example.imagepickerdemokotlin

import android.Manifest
 import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.app.Activity
import androidx.core.app.ActivityCompat
import android.content.DialogInterface
import android.os.Build
import android.annotation.TargetApi
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat



object Utility {

    val MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    fun checkPermission(context: Context): Boolean {
        val currentAPIVersion = Build.VERSION.SDK_INT
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        context as Activity,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    )
                ) {
                    val alertBuilder = AlertDialog.Builder(context)
                    alertBuilder.setCancelable(true)
                    alertBuilder.setTitle("Permission necessary")
                    alertBuilder.setMessage("External storage permission is necessary")
                    alertBuilder.setPositiveButton(android.R.string.yes,
                        DialogInterface.OnClickListener { dialog, which ->
                            ActivityCompat.requestPermissions(
                                context as Activity,
                                arrayOf(READ_EXTERNAL_STORAGE),
                                MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE
                            )
                        })
                    val alert = alertBuilder.create()
                    alert.show()
                } else {
                    ActivityCompat.requestPermissions(
                        context as Activity,
                        arrayOf(READ_EXTERNAL_STORAGE),
                        MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE
                    )
                }
                return false
            } else {
                return true
            }
        } else {
            return true
        }
    }
}
