package com.jiayx.component.project.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.proxyFragmentFactory
import androidx.navigation.fragment.NavHostFragment
import com.github.fragivity.loadRoot
import com.jiayx.component.lib_navigation.R
import com.jiayx.component.module_login.fragment.LoginFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        proxyFragmentFactory()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_navigation)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host) as NavHostFragment

        navHostFragment.loadRoot(LoginFragment::class)
    }
}