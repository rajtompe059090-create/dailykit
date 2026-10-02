package com.priti.dailykit
import com.priti.dailykit.R

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow

class EmiActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emi)

        val amount = findViewById<EditText>(R.id.etLoanAmount)
        val rate = findViewById<EditText>(R.id.etInterest)
        val months = findViewById<EditText>(R.id.etMonths)
        val result = findViewById<TextView>(R.id.tvEmiResult)

        findViewById<Button>(R.id.btnCalculateEmi).setOnClickListener {
            val p = amount.text.toString().toDoubleOrNull() ?: 0.0
            val annualRate = rate.text.toString().toDoubleOrNull() ?: 0.0
            val n = months.text.toString().toIntOrNull() ?: 0

            if (p <= 0 || annualRate < 0 || n <= 0) {
                result.text = "Please enter valid values"
                return@setOnClickListener
            }

            val r = annualRate / 12.0 / 100.0

            val emi = if (r == 0.0) {
                p / n
            } else {
                p * r * (1 + r).pow(n) / ((1 + r).pow(n) - 1)
            }

            val total = emi * n
            val interest = total - p

            result.text = "Monthly EMI\n₹%.2f\n\nTotal Payment\n₹%.2f\n\nTotal Interest\n₹%.2f"
                .format(emi, total, interest)
        }
    }
}
