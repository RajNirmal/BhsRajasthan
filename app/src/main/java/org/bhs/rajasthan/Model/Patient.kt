package org.bhs.rajasthan.Model

import android.os.Build
import androidx.annotation.RequiresApi
import com.parse.ParseObject

data class Patient(
    var Bhamsha_ID: String = "a",
    var PCTS_ID: String = "a",
    var Patient_Name: String = "a",
    var Age: String = "a",
    var Sex: String = "a",
    var Weight: String = "a",
    var Hb: String = "a",
    var Blood_Pressure: String = "a",
    var height: String = "a",
    var DOB: String = "a",
    var Caste: String = "a",
    var Mobile_No: String = "a",
    var Aadhar: String = "a",
    var Registration_date: String = "a",
    var lmp: String = "a",
    var cycle_days: String = "a",
    var migrant: String = "a",
    var family_member_name: String = "a",
    var relation_to_patient: String = "a",
    var mobile_relative: String = "a",
    var hamlet: String = "a",
    var village: String = "a",
    var block: String = "a",
    var district: String = "a",
    var state: String = "a",
    var asha_name: String = "a",
    var asha_phone: String = "a",
    var midwife: String = "a",
    var aanganwadi: String = "a",
    var sub_center: String = "a",
    var primary_health_center: String = "a",
    var community_health_center: String = "a",
    var eligible: String = "a",
    var children_count: String = "a",
    var nfsa: String = "a",
    var delivery_location: String = "a",
    var above_poverty: String = "yes"
) {

    @RequiresApi(Build.VERSION_CODES.N)
    fun formParseEntity(details: Map<String, Any>): ParseObject {
        var entity: ParseObject = ParseObject("Patient2")
        details.forEach { key, varue -> entity.put(key, varue) }
        return entity
    }
}