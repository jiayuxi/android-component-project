package com.jiayx.component.module_login.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jiayx.component.module_login.databinding.LoginFragmentBinding

/**
 *  author : Jia yu xi
 *  date : 2022/9/12 22:00:00
 *  description :
 */
class LoginFragment : Fragment() {
    private val binding: LoginFragmentBinding by lazy {
        LoginFragmentBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}