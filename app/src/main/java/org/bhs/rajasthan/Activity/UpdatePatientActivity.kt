package org.bhs.rajasthan.Activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_chose_updater.*
import org.bhs.rajasthan.R

class UpdatePatientActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chose_updater)
        antenatal_button.setOnClickListener {
            switchActivity(Intent(this, AntenatalActivity::class.java))
        }
        abortion_button.setOnClickListener {
            switchActivity(Intent(this, HomeActivity::class.java))
        }
        tetanous_button.setOnClickListener {
            switchActivity(Intent(this, HomeActivity::class.java))
        }
    }

    private fun switchActivity(intent: Intent) {
        startActivity(intent)
    }
}