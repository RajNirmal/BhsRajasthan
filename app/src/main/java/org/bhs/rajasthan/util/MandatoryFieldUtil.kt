package org.bhs.rajasthan.util

import android.content.Context
import android.util.Log
import android.widget.Toast
import org.bhs.rajasthan.Model.Mandatory
import org.bhs.rajasthan.util.ModelMapper.serializeToMap

class MandatoryFieldUtil {
    companion object{
        fun <T> checkIfMandatoryFieldsAreFilled(entity: T, context: Context): Boolean{
            val classMap = entity.serializeToMap()
            var result = true
            for (field in ((entity!!::class.java).javaClass.declaredFields)) {
                Log.i("MandatoryFieldUtil", "Field name is " + field.name)
                Log.i("MandatoryFieldUtil", field.isAnnotationPresent(Mandatory::class.java).toString())
                Log.i("MandatoryFieldUtil", "Field value is " + classMap.get(field.name))
                if(field.isAnnotationPresent(Mandatory::class.java) && (classMap.get(field.name) as String).isEmpty()) {
                    result = false
                    Toast.makeText(context, "${field.name} cannot be empty, Retry after entering the details", Toast.LENGTH_LONG).show()
                    break
                }
            }
            return result
        }
    }
}