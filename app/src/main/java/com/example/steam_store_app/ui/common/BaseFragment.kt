package com.example.steam_store_app.ui.common

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.steam_store_app.MainActivity


abstract class BaseFragment<B : ViewBinding>(
    layoutId: Int,
) : Fragment(layoutId) {

    var binding: B? = null
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = provideFragmentBinding(inflater, container)
        mainActivity = activity as MainActivity
        return binding?.root
    }

    abstract fun provideFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): B

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun Context.hideKeyboard(){
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
    }
}