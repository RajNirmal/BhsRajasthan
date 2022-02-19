package org.bhs.rajasthan.Model

import android.os.Build
import androidx.annotation.RequiresApi
import com.parse.ParseObject

data class Patient(
    @field:Mandatory var Bhamsha_ID: String = "a",
    @field:Mandatory var PCTS_ID: String = "a",
    @field:Mandatory var Patient_Name: String = "a",
    @field:Mandatory var Age: String = "a",
    @field:Mandatory var Sex: String = "a",
    @field:Mandatory var Weight: String = "a",
    var Hb: String = "a",
    var Blood_Pressure: String = "a",
    var height: String = "a",
    var DOB: String = "a",
    @field:Mandatory var Caste: String = "a",
    @field:Mandatory var Mobile_No: String = "a",
    @field:Mandatory var Aadhar: String = "a",
    @field:Mandatory var Registration_date: String = "a",
    @field:Mandatory var lmp: String = "a",
    @field:Mandatory var cycle_days: String = "a",
    @field:Mandatory var migrant: String = "a",
    @field:Mandatory var family_member_name: String = "a",
    @field:Mandatory var relation_to_patient: String = "a",
    @field:Mandatory var mobile_relative: String = "a",
    @field:Mandatory var hamlet: String = "a",
    @field:Mandatory var village: String = "a",
    @field:Mandatory var block: String = "a",
    @field:Mandatory var district: String = "a",
    @field:Mandatory var state: String = "a",
    @field:Mandatory var asha_name: String = "a",
    @field:Mandatory var asha_phone: String = "a",
    @field:Mandatory var midwife: String = "a",
    var aanganwadi: String = "a",
    @field:Mandatory var sub_center: String = "a",
    @field:Mandatory var primary_health_center: String = "a",
    @field:Mandatory var community_health_center: String = "a",
    var eligible: String = "a",
    @field:Mandatory var children_count: String = "a",
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