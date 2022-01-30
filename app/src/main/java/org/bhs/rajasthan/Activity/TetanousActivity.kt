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
import kotlinx.android.synthetic.main.activity_tetanous_details.*
import org.bhs.rajasthan.Model.AbortionDetail
import org.bhs.rajasthan.Model.TetanousShotDetails
import org.bhs.rajasthan.R
import org.bhs.rajasthan.util.ModelMapper.serializeToMap
import java.util.*
import org.bhs.rajasthan.util.MandatoryFieldUtil

class TetanousActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tetanous_details)
        setupComponents()
        tetanous_submit_button.setOnClickListener {
            val tetanous_details = getTetanousShotDetails()
            if (!MandatoryFieldUtil.checkIfMandatoryFieldsAreFilled(
                    tetanous_details,
                    applicationContext
                )
            ) {
                return@setOnClickListener
            }
            val tetanous_entity =
                tetanous_details.formParseEntity(tetanous_details.serializeToMap())
            tetanous_entity.saveInBackground {
                Log.i("TetanousActivity","Error while save $it")
                if (it == null) {
                    Toast.makeText(
                        applicationContext,
                        "Saved the tetanous shot details !!!!",
                        Toast.LENGTH_LONG
                    )
                        .show()

                }
            }
        }
    }

    private fun getTetanousShotDetails(): TetanousShotDetails {
        val tetanousShotDetail = TetanousShotDetails()
        tetanousShotDetail.pcts_id = tetanous_pcts_layout_input.text.toString()
        tetanousShotDetail.asha_name = tetanous_asha_layout_input.text.toString()
        tetanousShotDetail.tetanous_shot = tetanous_count_input.selectedItem.toString()
        tetanousShotDetail.tetanous_shot_date = tetanous_date_input.text.toString()

        return tetanousShotDetail

    }

    private fun setupComponents() {
        ArrayAdapter.createFromResource(
            this,
            R.array.tetanous_shot_count,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            tetanous_count_input.adapter = adapter
        }


        tetanous_date_input.setOnClickListener {
            Log.i("TetanousActivity", "Inside onclick listener")
            val calendar = Calendar.getInstance()
            val day = calendar.get(Calendar.DAY_OF_MONTH);
            val month = calendar.get(Calendar.MONTH);
            val year = calendar.get(Calendar.YEAR);
            // date picker dialog
            val picker: DatePickerDialog = DatePickerDialog(
                this@TetanousActivity,
                { view, year, monthOfYear, dayOfMonth ->
                    tetanous_date_input.setText("$dayOfMonth,  ${monthOfYear + 1}, $year")
                },
                year,
                month,
                day
            )
            picker.show()
        }

    }
}