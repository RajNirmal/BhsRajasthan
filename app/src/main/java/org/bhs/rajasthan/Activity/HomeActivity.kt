package org.bhs.rajasthan.Activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home_constraint.*
import org.bhs.rajasthan.R

class HomeActivity : Activity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_constraint)
        new_patient_button.setOnClickListener {
            moveToUserDetails()
        }
        update_patient_details.setOnClickListener {
            moveToUpdatePateientDetails()
        }
    }

    private fun moveToUpdatePateientDetails() {
        val intent = Intent(this, UpdatePatientActivity::class.java)
        startActivity(intent)
    }

    private fun moveToUserDetails() {
        val intent = Intent(this, NewPatientActivity::class.java)
        startActivity(intent)
    }


}