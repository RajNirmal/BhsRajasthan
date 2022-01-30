package org.bhs.rajasthan.Model

import android.os.Build
import androidx.annotation.RequiresApi
import com.parse.ParseObject

data class AbortionDetail(
    var pcts_id: String = "a",
    var asha_name: String = "a",
    var abortion_place: String = "a",
    var abortion_type: String = "a",
    var abortion_date: String = "a",
    var abortion_iucd: String = "a",
    var abortion_iucd_date: String = "a"
){
    @RequiresApi(Build.VERSION_CODES.N)
    fun formParseEntity(details: Map<String, Any>): ParseObject {
        var entity: ParseObject = ParseObject("AbortionDetails")
        details.forEach { key, varue -> entity.put(key, varue) }
        return entity
    }
}
