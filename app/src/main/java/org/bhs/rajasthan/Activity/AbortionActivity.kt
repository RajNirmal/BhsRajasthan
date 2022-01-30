package org.bhs.rajasthan.Activity

import android.app.Activity
import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_abortion_details.*
import kotlinx.android.synthetic.main.activity_antenatal_check.*
import kotlinx.android.synthetic.main.activity_chose_updater.*
import org.bhs.rajasthan.Model.AbortionDetail
import org.bhs.rajasthan.R
import org.bhs.rajasthan.util.ModelMapper.serializeToMap
import java.util.*
import org.bhs.rajasthan.util.MandatoryFieldUtil

class AbortionActivity: Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_abortion_details)
        setupComponents()
        abortion_submit_button.setOnClickListener {
            val abortion_details = getAbortionDetails()
            if (!MandatoryFieldUtil.checkIfMandatoryFieldsAreFilled(
                    abortion_details,
                    applicationContext
                )
            ) {
                return@setOnClickListener
            }
            val abortion_entity = abortion_details.formParseEntity(abortion_details.serializeToMap())
            abortion_entity.saveInBackground {
                if (it == null) {
                    Toast.makeText(applicationContext, "Saved the abortion details !!!!", Toast.LENGTH_LONG)
                        .show()

                }
            }
        }
    }

    private fun getAbortionDetails(): AbortionDetail {
        val abortionDetails = AbortionDetail()
        abortionDetails.pcts_id = abortion_pcts_layout_input.text.toString()
        abortionDetails.asha_name = abortion_asha_layout_input.text.toString()
        abortionDetails.abortion_date = abortion_date_input.text.toString()
        abortionDetails.abortion_place = abortion_place_input.text.toString()
        abortionDetails.abortion_type = abortion_type_input.selectedItem.toString()
        abortionDetails.abortion_iucd = abortion_iud_input.selectedItem.toString()
        abortionDetails.abortion_iucd_date = abortion_iud_date_input.text.toString()

        return abortionDetails

    }

    private fun setupComponents() {
        ArrayAdapter.createFromResource(
            this,
            R.array.abortion_type,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            abortion_type_input.adapter = adapter
        }
        ArrayAdapter.createFromResource(
            this,
            R.array.yes_no,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            abortion_iud_input.adapter = adapter
        }

        abortion_date_input.setOnClickListener {
            Log.i("AntenatalActivity", "Inside onclick listener")
            val calendar = Calendar.getInstance()
            val day = calendar.get(Calendar.DAY_OF_MONTH);
            val month = calendar.get(Calendar.MONTH);
            val year = calendar.get(Calendar.YEAR);
            // date picker dialog
            val picker: DatePickerDialog = DatePickerDialog(
                this@AbortionActivity,
                { view, year, monthOfYear, dayOfMonth ->
                    abortion_date_input.setText("$dayOfMonth,  ${monthOfYear + 1 }, $year")
                },
                year,
                month,
                day
            )
            picker.show()
        }
        abortion_iud_date_input.setOnClickListener {
            Log.i("AntenatalActivity", "Inside onclick listener")
            val calendar = Calendar.getInstance()
            val day = calendar.get(Calendar.DAY_OF_MONTH);
            val month = calendar.get(Calendar.MONTH);
            val year = calendar.get(Calendar.YEAR);
            // date picker dialog
            val picker: DatePickerDialog = DatePickerDialog(
                this@AbortionActivity,
                { view, year, monthOfYear, dayOfMonth ->
                    abortion_iud_date_input.setText("$dayOfMonth,  ${monthOfYear + 1 }, $year")
                },
                year,
                month,
                day
            )
            picker.show()
        }
    }
}