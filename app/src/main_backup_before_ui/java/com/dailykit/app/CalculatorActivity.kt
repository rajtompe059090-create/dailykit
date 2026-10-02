package com.priti.dailykit

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CalculatorActivity : AppCompatActivity() {

    private lateinit var display: TextView

    private var firstNumber = 0.0
    private var operator = ""
    private var enteringNewNumber = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_calculator)

        display = findViewById(R.id.txtDisplay)

        val numberButtons = listOf(
            R.id.btn0,
            R.id.btn1,
            R.id.btn2,
            R.id.btn3,
            R.id.btn4,
            R.id.btn5,
            R.id.btn6,
            R.id.btn7,
            R.id.btn8,
            R.id.btn9
        )

        numberButtons.forEach { id ->

            findViewById<Button>(id).setOnClickListener {

                val value = (it as Button).text.toString()

                if (enteringNewNumber || display.text == "0") {
                    display.text = value
                    enteringNewNumber = false
                } else {
                    display.append(value)
                }
            }
        }

        findViewById<Button>(R.id.btnDot).setOnClickListener {

            if (!display.text.contains(".")) {
                display.append(".")
                enteringNewNumber = false
            }
        }

        findViewById<Button>(R.id.btnClear).setOnClickListener {

            display.text = "0"
            firstNumber = 0.0
            operator = ""
            enteringNewNumber = true
        }

        setOperator(R.id.btnPlus, "+")
        setOperator(R.id.btnMinus, "-")
        setOperator(R.id.btnMultiply, "*")
        setOperator(R.id.btnDivide, "/")

        findViewById<Button>(R.id.btnEquals).setOnClickListener {

            val secondNumber = display.text.toString().toDoubleOrNull() ?: 0.0

            val result = when (operator) {

                "+" -> firstNumber + secondNumber

                "-" -> firstNumber - secondNumber

                "*" -> firstNumber * secondNumber

                "/" -> {
                    if (secondNumber == 0.0) {
                        display.text = "Error"
                        enteringNewNumber = true
                        return@setOnClickListener
                    }

                    firstNumber / secondNumber
                }

                else -> secondNumber
            }

            display.text = formatResult(result)

            enteringNewNumber = true
            operator = ""
        }
    }

    private fun setOperator(id: Int, op: String) {

        findViewById<Button>(id).setOnClickListener {

            firstNumber = display.text.toString().toDoubleOrNull() ?: 0.0

            operator = op

            enteringNewNumber = true
        }
    }

    private fun formatResult(value: Double): String {

        return if (value % 1.0 == 0.0) {
            value.toLong().toString()
        } else {
            value.toString()
        }
    }
}
