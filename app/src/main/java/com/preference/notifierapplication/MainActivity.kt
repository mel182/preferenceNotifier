package com.preference.notifierapplication

import android.os.Bundle
import android.util.Log
import com.preference.preferencenotifier.baseClasses.PrefNotifierBase

class MainActivity : PrefNotifierBase() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val testValue = notifier.getString("test")
        val testValue12 = notifier.getString("test12")

        Log.i("TAG","Value test: ${testValue}")
        Log.i("TAG","Value test 12: ${testValue12}")

//        subscribe()

//        val sharedPreferences: SharedPreferences = this.getSharedPreferences("testPreference", Context.MODE_PRIVATE)
//
//        sharedPreferences.edit().putString("test12","value1234").apply()
//        sharedPreferences.edit().putInt("testInt",1).apply()
//        init().migrate(sharedPreferences)


//        val setSets:Any = HashSet<String>()

//        setSets.add("value1")
//        setSets.add("value2")
//        setSets.add("value3")

//        if ( setSets is HashSet<*>)
//        {
//            Log.i("TAG","Set sets: ${setSets as HashSet<String>}")
//        } else {
//            Log.i("TAG","Is not hash set string")
//        }


//        sharedPreferences.edit().putStringSet("test_set",setSets)







//        val keys = sharedPreferences.all
//
//        keys.forEach { pref ->
//            Log.i("TAG","Key: ${pref.key}, value: ${pref.value}")
//        }


//        Log.i("TAG","onCreate main activity")

//        notifier.setValue("test12","value12")

//        notifier.setValue("test","Test value")
//
//        val value = notifier.getString("test")
//        Log.i("TAG","value stored: ${value}")

    }

    override fun onPreferenceChanged(key: String, value: Any?) {
        super.onPreferenceChanged(key, value)
        Log.i("TAG","Updated pref, key: ${key} with value: ${value}")
    }
}