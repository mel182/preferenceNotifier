package com.preference.preferencenotifier.interfaces

/**
 * The preference state listener interface
 *
 * @author Melchior Vrolijk
 * @since 1.0
 */
interface PreferenceStateListener {
    fun onPreferenceChanged(key:String,value:Any?) {}
}