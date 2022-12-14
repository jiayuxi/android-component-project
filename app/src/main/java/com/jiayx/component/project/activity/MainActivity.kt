package com.jiayx.component.project.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.proxyFragmentFactory
import androidx.navigation.fragment.NavHostFragment
import com.github.fragivity.loadRoot
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.XXPermissions
import com.jiayx.component.lib_navigation.R
import com.jiayx.component.module_login.fragment.LoginFragment
import com.tbruyelle.rxpermissions2.RxPermissions

class MainActivity : AppCompatActivity() {
    private val permissions2 = arrayOf(
        Manifest.permission.READ_PHONE_STATE,
        Manifest.permission.MANAGE_EXTERNAL_STORAGE,
        Manifest.permission.CAMERA,
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.ANSWER_PHONE_CALLS,
        Manifest.permission.CALL_PHONE,
    )
    private val permissions = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_PHONE_STATE,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.CAMERA,
        Manifest.permission.ANSWER_PHONE_CALLS,
        Manifest.permission.CALL_PHONE,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        proxyFragmentFactory()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_navigation)
//        val navHostFragment = supportFragmentManager
//            .findFragmentById(R.id.nav_host) as NavHostFragment
//        navHostFragment.loadRoot(LoginFragment::class)
        findViewById<Button>(R.id.startRecord).setOnClickListener {
            startActivity(Intent(this, CameraActivity::class.java))
        }
        applyPermission()
    }


    @SuppressLint("CheckResult")
    private fun applyPermission() {
        val rxPermissions = RxPermissions(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            XXPermissions.with(this) // ????????? Android 11 ???????????????
                //.permission(Permission.Group.STORAGE)
                // ?????? Android 11 ???????????????????????????????????? Permission.Group.STORAGE
                .permission(*permissions2)
                .request(object : OnPermissionCallback {
                    override fun onGranted(permissions: List<String>, all: Boolean) {
                        if (all) {

                        }
                    }

                    override fun onDenied(permissions: List<String>, never: Boolean) {
                        if (never) {
                            Toast.makeText(
                                this@MainActivity,
                                "???????????????????????????????????????????????????",
                                Toast.LENGTH_SHORT
                            ).show()
                            // ??????????????????????????????????????????????????????????????????
                            XXPermissions.startPermissionActivity(
                                this@MainActivity,
                                permissions
                            )
                        } else {
                            Toast.makeText(this@MainActivity, "????????????????????????", Toast.LENGTH_SHORT)
                                .show()
                            finish()
                        }
                    }
                })
        } else {
            rxPermissions.request(*permissions).subscribe { aBoolean: Boolean ->
                if (aBoolean) {

                } else {
                    Toast.makeText(this@MainActivity, "??????????????????", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}