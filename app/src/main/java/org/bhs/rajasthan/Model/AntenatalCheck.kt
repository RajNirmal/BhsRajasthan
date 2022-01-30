package org.bhs.rajasthan.Model

import android.os.Build
import androidx.annotation.RequiresApi
import com.parse.ParseObject

data class AntenatalCheck(
    @field:Mandatory var pcts_id: String = "a",
    @field:Mandatory var asha_name: String = "a",
    var date_of_visit: String = "a",
    var patient_weight: String = "a",
    var patient_hb: String = "a",
    var patient_bp: String = "b",
    var high_risk: String = "no",
    var folic_acid: String = "no",
    var folic_acid_grams: String = "180",
    var location: String = "b",
    @field:Mandatory var anc_visit_count: String = "ANC 1"
) {
    @RequiresApi(Build.VERSION_CODES.N)
    fun formParseEntity(details: Map<String, Any>): ParseObject {
        var entity: ParseObject = ParseObject("Antenatal")
        details.forEach { key, varue -> entity.put(key, varue) }
        return entity
    }
}

annotation class Mandatory
