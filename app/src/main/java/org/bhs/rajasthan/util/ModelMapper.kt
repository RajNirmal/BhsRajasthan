package org.bhs.rajasthan.util

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.parse.ParseObject
import org.bhs.rajasthan.Model.Patient

object ModelMapper{
    val gson = Gson()

    //convert a data class to a map
    fun <T> T.serializeToMap(): Map<String, Any> {
        return convert()
    }

    //convert a map to a data class
    inline fun <reified T> Map<String, Any>.toDataClass(): T {
        return convert()
    }

    //convert an object of type I to type O
    inline fun <I, reified O> I.convert(): O {
        val json = gson.toJson(this)
        return gson.fromJson(json, object : TypeToken<O>() {}.type)
    }
}