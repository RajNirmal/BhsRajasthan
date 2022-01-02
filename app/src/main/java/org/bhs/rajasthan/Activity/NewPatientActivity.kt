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
import java.util.Calendar
import kotlinx.android.synthetic.main.activity_new_patient.dob_input
import kotlinx.android.synthetic.main.activity_new_patient.registration_input
import kotlinx.android.synthetic.main.activity_new_patient.submit_button
import kotlinx.android.synthetic.main.activity_new_patient.*
import org.bhs.rajasthan.Model.Patient
import org.bhs.rajasthan.R
import org.bhs.rajasthan.util.ModelMapper.serializeToMap



class NewPatientActivity : Activity() {
    lateinit var location: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getCurrentUserLocation()
        setContentView(R.layout.activity_new_patient)
        setupComponents()
        submit_button.setOnClickListener {
            val patient = getPatientObject()
            patient.location = location
            val patientEntity = patient.formParseEntity(patient.serializeToMap())
            patientEntity.saveInBackground {
                if (it == null) {
                    Toast.makeText(applicationContext, "Saved the file !!!!", Toast.LENGTH_LONG)
                        .show()

                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun getCurrentUserLocation() {
        val fusedLocationServices =
            LocationServices.getFusedLocationProviderClient(applicationContext)
        fusedLocationServices
            .lastLocation.addOnSuccessListener {
                location = it.latitude.toString() + " , " + it.longitude.toString()
                Log.i("NewPatientActivity", " Successfully fetched location ${location}")
            }
    }

    private fun setupComponents() {
        val sex_input = findViewById<Spinner>(R.id.sex_input)
        val migrant_input = findViewById<Spinner>(R.id.migrant_input)
        val hamlet_input = findViewById<Spinner>(R.id.hamlet_input)
        val village_input = findViewById<Spinner>(R.id.village_input)
        val subcenter_input = findViewById<Spinner>(R.id.sub_center_input)
        val eligible_input = findViewById<Spinner>(R.id.eligible_input)
        val child_input = findViewById<Spinner>(R.id.child_input)
        ArrayAdapter.createFromResource(
            this,
            R.array.sex,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            sex_input.adapter = adapter
        }
        ArrayAdapter.createFromResource(
            this,
            R.array.yes_no,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            migrant_input.adapter = adapter
        }
        ArrayAdapter.createFromResource(
            this,
            R.array.one_to_ten,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            hamlet_input.adapter = adapter
        }
        ArrayAdapter.createFromResource(
            this,
            R.array.village_list,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            village_input.adapter = adapter
        }
        ArrayAdapter.createFromResource(
            this,
            R.array.sub_center_list,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            subcenter_input.adapter = adapter
        }
        ArrayAdapter.createFromResource(
            this,
            R.array.yes_no,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            eligible_input.adapter = adapter
        }
        ArrayAdapter.createFromResource(
            this,
            R.array.one_to_ten,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            child_input.adapter = adapter
        }

        dob_input.setOnClickListener {
            Log.i("NewPatientActivity", "Inside onclick listener")
            val calendar = Calendar.getInstance()
            val day = calendar.get(Calendar.DAY_OF_MONTH);
            val month = calendar.get(Calendar.MONTH);
            val year = calendar.get(Calendar.YEAR);
            // date picker dialog
            val picker: DatePickerDialog = DatePickerDialog(
                this@NewPatientActivity,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    dob_input.setText("$dayOfMonth, $monthOfYear, $year")
                },
                year,
                month,
                day
            )
            picker.show()
        }

        registration_input.setOnClickListener {
            Log.i("NewPatientActivity", "Inside onclick listener")
            val calendar = Calendar.getInstance()
            val day = calendar.get(Calendar.DAY_OF_MONTH);
            val month = calendar.get(Calendar.MONTH);
            val year = calendar.get(Calendar.YEAR);
            // date picker dialog
            val picker: DatePickerDialog = DatePickerDialog(
                this@NewPatientActivity,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    registration_input.setText("$dayOfMonth, $monthOfYear, $year")
                },
                year,
                month,
                day
            )
            picker.show()
        }
    }

    private fun getPatientObject(): Patient {
        val patient = Patient()
        patient.Bhamsha_ID = bhamasa_input.text.toString()
        patient.PCTS_ID = pcts_layout_input.text.toString()
        patient.Patient_Name = patient_name_input.text.toString()
        patient.Age = age_input.text.toString()
        patient.Sex = sex_input.selectedItem.toString()
        patient.Weight = weight_input.text.toString()
        patient.Hb = hb_input.text.toString()
        patient.Blood_Pressure = bp_input.text.toString()
        patient.height = height_input.text.toString()
        patient.DOB = dob_input.text.toString()
        patient.Caste = caste_input.text.toString()
        patient.Mobile_No = mobile_input.text.toString()
        patient.Aadhar = aadhar_input.text.toString()
        patient.Registration_date = registration_input.text.toString()
        patient.lmp = lmp_input.text.toString()
        patient.cycle_days = cycle_input.text.toString()
        patient.migrant = migrant_input.selectedItem.toString()
        patient.family_member_name = family_input.text.toString()
        patient.relation_to_patient = relative_input.text.toString()
        patient.mobile_relative = relation_mobile_input.text.toString()
        patient.hamlet = hamlet_input.selectedItem.toString()
        patient.village = village_input.selectedItem.toString()
        patient.block = block_input.text.toString()
        patient.district = district_input.text.toString()
        patient.state = state_input.text.toString()
        patient.asha_name = asha_input.text.toString()
        patient.asha_phone = asha_mobile_input.text.toString()
        patient.midwife = midwife_input.text.toString()
        patient.aanganwadi = aangwadi_input.text.toString()
        patient.sub_center = sub_center_input.selectedItem.toString()
        patient.primary_health_center = phc_input.text.toString()
        patient.community_health_center = chc_input.text.toString()
        patient.eligible = eligible_input.selectedItem.toString()
        patient.children_count = child_input.selectedItem.toString()
        patient.nfsa = nfsa_input.text.toString()
        patient.delivery_location = delivery_input.text.toString()
        patient.location = "To be done"

        return patient
    }
}

