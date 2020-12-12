package com.preference.notifierapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.preference.preferencenotifier.baseClasses.PrefNotifierBaseFragment

/**
 * A simple [Fragment] subclass.
 * Use the [DemoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DemoFragment : PrefNotifierBaseFragment() {

    private var title:TextView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_demo, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        title = view?.findViewById(R.id.fragment_tv)
    }

    override fun onPreferenceChanged(key: String, value: Any?) {
        super.onPreferenceChanged(key, value)
        title?.text = "Pref update\nkey: ${key}\nvalue: ${value}"
    }
}