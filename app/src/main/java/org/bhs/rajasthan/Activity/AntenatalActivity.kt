package org.bhs.rajasthan.Activity

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_antenatal_check.*
import kotlinx.android.synthetic.main.activity_new_patient.*
import org.bhs.rajasthan.Model.AntenatalCheck
import org.bhs.rajasthan.R
import org.bhs.rajasthan.util.ModelMapper.serializeToMap
import java.util.*

class AntenatalActivity : Activity() {
    lateinit var location: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_antenatal_check)
        setupComponents()
        ante_submit_button.setOnClickListener {
            val anteNatal = getAntenatalObject()
//            checkIfAllMandatoryFieldsArePresent(anteNatal)
            //break
            val antenatalEntity = anteNatal.formParseEntity(anteNatal.serializeToMap())
            antenatalEntity.saveInBackground {
                if (it == null) {
                    Toast.makeText(
                        applicationContext,
                        "Saved the antenatal check details !!!!",
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
            }
        }
        location_button.setOnClickListener {
            getCurrentUserLocation()
        }
    }

//    private fun checkIfAllMandatoryFieldsArePresent(val entity: AntenatalCheck) {
//        entity.anc_visit_count.isNullOrEmpty()
//        return true
//    }

    private fun setupComponents() {
        val high_risk = findViewById<Spinner>(R.id.ante_high_risk_input)
        val folic_acid = findViewById<Spinner>(R.id.ante_folic_input)
        val folic_acid_weight = findViewById<Spinner>(R.id.ante_folic_weight_input)
        val anc_visit_count_spinner = findViewById<Spinner>(R.id.ante_visit_count_input)
        ArrayAdapter.createFromResource(
            this,
            R.array.yes_no,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            high_risk.adapter = adapter
            high_risk.setSelection(1)
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.yes_no,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            folic_acid.adapter = adapter
            folic_acid.setSelection(1)
        }
        ArrayAdapter.createFromResource(
            this,
            R.array.folic_acid_weight,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            folic_acid_weight.adapter = adapter
        }
        ArrayAdapter.createFromResource(
            this,
            R.array.anc_visit_count,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            anc_visit_count_spinner.adapter = adapter
        }
        ante_dov_input.setOnClickListener {
            Log.i("AntenatalActivity", "Inside onclick listener")
            val calendar = Calendar.getInstance()
            val day = calendar.get(Calendar.DAY_OF_MONTH);
            val month = calendar.get(Calendar.MONTH);
            val year = calendar.get(Calendar.YEAR);
            // date picker dialog
            val picker: DatePickerDialog = DatePickerDialog(
                this@AntenatalActivity,
                { view, year, monthOfYear, dayOfMonth ->
                    ante_dov_input.setText("$dayOfMonth,  ${monthOfYear + 1}, $year")
                },
                year,
                month,
                day
            )
            picker.show()
        }
    }

    @SuppressLint("MissingPermission")
    private fun getCurrentUserLocation() {
        val fusedLocationServices =
            LocationServices.getFusedLocationProviderClient(applicationContext)
        fusedLocationServices
            .lastLocation.addOnSuccessListener {
                Toast.makeText(this, "Successfully fetched the location", Toast.LENGTH_SHORT).show()
                location = it.latitude.toString() + " , " + it.longitude.toString()
                Log.i("AntenatalActivity", " Successfully fetched location ${location}")
            }
        fusedLocationServices.lastLocation.addOnFailureListener {
            Toast.makeText(
                this,
                "Unable to fetch location, Turn on GPS and try again",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun getAntenatalObject(): AntenatalCheck {
        val entity = AntenatalCheck()
        entity.pcts_id = ante_pcts_layout_input.text.toString()
        entity.asha_name = ante_asha_layout_input.text.toString()
        entity.date_of_visit = ante_dov_input.text.toString()
        entity.patient_weight = ante_weight_input.text.toString()
        entity.patient_hb = ante_hb_input.text.toString()
        entity.patient_bp = ante_bp_input.text.toString()
        entity.high_risk = ante_high_risk_input.selectedItem.toString()
        entity.folic_acid = ante_folic_input.selectedItem.toString()
        entity.folic_acid_grams = ante_folic_weight_input.selectedItem.toString()
        entity.location = location
        entity.anc_visit_count = ante_visit_count_input.selectedItem.toString()
        return entity
    }
}