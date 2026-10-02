package com.priti.dailykit
import com.priti.dailykit.R

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GstActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gst)

        val amount = findViewById<EditText>(R.id.etGstAmount)
        val rate = findViewById<EditText>(R.id.etGstRate)
        val result = findViewById<TextView>(R.id.tvGstResult)

        findViewById<Button>(R.id.btnCalculateGst).setOnClickListener {
            val value = amount.text.toString().toDoubleOrNull() ?: 0.0
            val gstRate = rate.text.toString().toDoubleOrNull() ?: 0.0

            if (value <= 0 || gstRate < 0) {
                result.text = "Please enter valid values"
                return@setOnClickListener
            }

            val gst = value * gstRate / 100
            val total = value + gst

            result.text = "GST Amount\n₹%.2f\n\nFinal Amount\n₹%.2f"
                .format(gst, total)
        }
    }
}
