package org.bhs.rajasthan.Model

import android.os.Build
import androidx.annotation.RequiresApi
import com.parse.ParseObject

data class TetanousShotDetails(
    var pcts_id: String = "a",
    var asha_name: String = "a",
    var tetanous_shot: String = "a",
    var tetanous_shot_date: String = "a"
){
    @RequiresApi(Build.VERSION_CODES.N)
    fun formParseEntity(details: Map<String, Any>): ParseObject {
        var entity: ParseObject = ParseObject("TetanousShot")
        details.forEach { key, varue -> entity.put(key, varue) }
        return entity
    }
}
