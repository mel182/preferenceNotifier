package com.preference.notifierapplication

import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import com.preference.preferencenotifier.baseClasses.PrefNotifierBase

class MainActivity : PrefNotifierBase() {

    private var container1:FrameLayout? = null
    private var container2:FrameLayout? = null
    private var activityTitle: TextView? = null
    private var prefButton: Button? = null
    private var counter:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        container1 = findViewById(R.id.container1)
        container2 = findViewById(R.id.container2)
        activityTitle = findViewById(R.id.activity_title)
        prefButton = findViewById(R.id.pref_update_button)

        container1?.apply {
            val fragmentContent = DemoFragment()
            val fragmentManager = supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            transaction.add(this.id,fragmentContent)
            transaction.commit()
        }

        container2?.apply {
            val fragmentContent = DemoFragment()
            val fragmentManager = supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            transaction.add(this.id,fragmentContent)
            transaction.commit()
        }

        prefButton?.setOnClickListener {
            counter++
            notifier.setValue("test","value ${counter}")
        }
    }

    override fun onPreferenceChanged(key: String, value: Any?) {
        super.onPreferenceChanged(key, value)
        activityTitle?.text = "Activity key: ${key} value: ${value}"
    }
}