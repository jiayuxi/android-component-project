package com.jiayx.component.module_map.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jiayx.component.module_map.databinding.ModuleMapFragmentBinding

/**
 *  author : Jia yu xi
 *  date : 2022/9/13 23:28:28
 *  description :
 */
class MapFragment : Fragment() {
    private val binding: ModuleMapFragmentBinding by lazy {
        ModuleMapFragmentBinding.inflate(layoutInflater)
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