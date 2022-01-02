package org.bhs.rajasthan.Model

import android.os.Build
import androidx.annotation.RequiresApi
import com.parse.ParseObject

data class AntenatalCheck(
    var pcts_id: String = "a",
    var asha_name: String = "a",
    var date_of_visit: String = "a",
    var patient_weight: String = "a",
    var patient_hb: String = "a",
    var patient_bp: String = "b",
    var high_risk: String = "no",
    var folic_acid: String = "no",
    var folic_acid_grams: String = "180"
) {
    @RequiresApi(Build.VERSION_CODES.N)
    fun formParseEntity(details: Map<String, Any>): ParseObject {
        var entity: ParseObject = ParseObject("Antenatal")
        details.forEach { key, varue -> entity.put(key, varue) }
        return entity
    }
}
