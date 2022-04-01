package org.bhs.rajasthan.Activity

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import java.util.*
import kotlinx.android.synthetic.main.activity_home_constraint.*
import org.bhs.rajasthan.R


class HomeActivity : Activity() {
    val locationRequestCode = 1265
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_constraint)
        new_patient_button.setOnClickListener {
            moveToUserDetails()
        }
        update_patient_details.setOnClickListener {
            moveToUpdatePateientDetails()
        }
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                locationRequestCode
            )
        } else {
            Log.i("HomeActivity", "Location permission already granted proceed")
        }
        Toast.makeText(this, "Please turn on your location before proceeding", Toast.LENGTH_SHORT)
            .show()
    }

    private fun moveToUpdatePateientDetails() {
        val intent = Intent(this, UpdatePatientActivity::class.java)
        startActivity(intent)
    }

    private fun moveToUserDetails() {
        val intent = Intent(this, NewPatientActivity::class.java)
        startActivity(intent)
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1000 -> {
                if (grantResults.size > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    Log.i("HomeActivity", "Location permission has been granted")
                } else {
                    Toast.makeText(this, "Location permission needed to proceed", Toast.LENGTH_LONG)
                        .show()
                    (this@HomeActivity).finishAffinity()
                }
            }
        }
    }


}