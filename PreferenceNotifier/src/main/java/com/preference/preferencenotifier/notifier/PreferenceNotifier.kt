@file:Suppress("UNCHECKED_CAST")

package com.preference.preferencenotifier.notifier

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.preference.preferencenotifier.constant.DefaultValue
import com.preference.preferencenotifier.models.PreferenceValue

/**
 * The preference notifier object class which detect preference change and notify through the [LiveData] instance.
 *
 * @author Melchior Vrolijk
 * @since 1.0
 */
object PreferenceNotifier {

    //region Local instance
    private var context:Context? = null
    private lateinit var sharedPreference:SharedPreferences

    private val updatablePreferenceValue = MutableLiveData<PreferenceValue>()
    val updatedPreferenceValue: LiveData<PreferenceValue>
    get() = updatablePreferenceValue
    //endregion

    //region initializer
    fun init(context:Context?) : PreferenceNotifier
    {
        context?.let { notifierContext ->
            PreferenceNotifier.context = notifierContext
            sharedPreference = notifierContext.getSharedPreferences(notifierContext.packageName,Context.MODE_PRIVATE)
        }?: kotlin.run {
            throw NullPointerException("Context provided is null")
        }

        return this
    }
    //endregion

    //region Migrate
    /**
     * Migrate the current preference to the preference notifier
     * @param existingSharedPreference The existing preference
     * @since 1.0
     */
    fun migrate(existingSharedPreference: SharedPreferences): PreferenceNotifier
    {
        if (this::sharedPreference.isInitialized)
        {
            val keys = existingSharedPreference.all
            val editor = sharedPreference.edit()
            keys.forEach { pref ->

                pref.value?.let { preferenceFound ->

                    when(preferenceFound)
                    {
                        is String -> editor.putString(pref.key,pref.value as String).apply()

                        is Int -> editor.putInt(pref.key,pref.value as Int).apply()

                        is Boolean -> editor.putBoolean(pref.key,pref.value as Boolean).apply()

                        is Float -> editor.putFloat(pref.key,pref.value as Float).apply()

                        is Long -> editor.putLong(pref.key,pref.value as Long).apply()

                        is HashSet<*> -> editor.putStringSet(pref.key,pref.value as HashSet<String>)

                        else -> {}
                    }
                }
            }
        }

        return this
    }
    //endregion

    //region Get preference string value
    fun getString(key:String):String
    {
        return if (isSharedPreferenceInitialized())
        {
            sharedPreference.getString(key,DefaultValue.STRING) ?: DefaultValue.STRING
        } else {
            DefaultValue.STRING
        }
    }

    fun getString(key:String, defaultValue:String):String
    {
        return if (isSharedPreferenceInitialized())
        {
            sharedPreference.getString(key,defaultValue) ?: defaultValue
        } else {
            DefaultValue.STRING
        }
    }
    //endregion

    //region Get preference int
    fun getInt(key:String):Int
    {
        return sharedPreference.getInt(key,DefaultValue.INT)
    }

    fun getInt(key:String, defaultValue:Int):Int
    {
        return sharedPreference.getInt(key,defaultValue)
    }
    //endregion

    //region Get preference boolean
    fun getBoolean(key:String):Boolean
    {
        return sharedPreference.getBoolean(key,DefaultValue.BOOLEAN)
    }

    fun getBoolean(key:String, defaultValue:Boolean):Boolean
    {
        return sharedPreference.getBoolean(key,defaultValue)
    }
    //endregion

    //region Get preference float
    fun getFloat(key:String):Float
    {
        return sharedPreference.getFloat(key,DefaultValue.FLOAT)
    }

    fun getFloat(key:String, defaultValue:Float):Float
    {
        return sharedPreference.getFloat(key,defaultValue)
    }
    //endregion

    //region Set value
    /**
     * Set preference value
     * @param key The preference key
     * @param value The preference value (which can be any of the supported preference type)
     */
    fun setValue(key:String?, value:Any?)
    {
        if (key != null && isSharedPreferenceInitialized())
        {
            val editor = sharedPreference.edit()
            when(value)
            {
                is String -> {

                    editor.putString("test",null)

                    editor.putString(key,value as String)
                }

                is Int -> {
                    editor.putInt(key,value as Int)
                }

                is Boolean -> {
                    editor.putBoolean(key,value as Boolean)
                }

                is Float -> {
                    editor.putFloat(key,value as Float)
                }

                is Long -> {
                    editor.putLong(key,value as Long)
                }

                is HashSet<*> -> {
                    editor.putStringSet(key,value as HashSet<String>)
                }
            }

            editor.apply()
            updatablePreferenceValue.value = PreferenceValue(key,value)
        }
    }
    //endregion

    //region is shared preference initialized
    private fun isSharedPreferenceInitialized(): Boolean
    {
        return this::sharedPreference.isInitialized
    }
    //endregion

}