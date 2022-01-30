package org.bhs.rajasthan.util

import android.content.Context
import android.util.Log
import android.widget.Toast
import org.bhs.rajasthan.Model.AbortionDetail
import org.bhs.rajasthan.Model.AntenatalCheck
import org.bhs.rajasthan.Model.Mandatory
import org.bhs.rajasthan.Model.Patient
import org.bhs.rajasthan.Model.TetanousShotDetails
import org.bhs.rajasthan.util.ModelMapper.serializeToMap

class MandatoryFieldUtil {
    companion object {
        fun <T> checkIfMandatoryFieldsAreFilled(entity: T, context: Context): Boolean {
            val classMap = entity.serializeToMap()
            var result = true
            //Android studio says this cast is unnecessary but it is
            val castedEntity = when (entity) {
                is AntenatalCheck -> entity as AntenatalCheck
                is Patient -> entity as Patient
                is AbortionDetail -> entity as AbortionDetail
                is TetanousShotDetails -> entity as TetanousShotDetails
                else -> entity as Patient
            }
            for (field in (castedEntity.javaClass.declaredFields)) {
                Log.i("MandatoryFieldUtil", "Field name is " + field.name)
                Log.i(
                    "MandatoryFieldUtil",
                    field.isAnnotationPresent(Mandatory::class.java).toString()
                )
                Log.i("MandatoryFieldUtil", "Field value is " + classMap.get(field.name))
                if (field.isAnnotationPresent(Mandatory::class.java) && (classMap.get(field.name) as String).isEmpty()) {
                    result = false
                    Toast.makeText(
                        context,
                        "${
                            field.name.toString().toUpperCase()
                        } cannot be empty, Retry after entering the details",
                        Toast.LENGTH_LONG
                    ).show()
                    break
                }
            }
            return result
        }
    }
}