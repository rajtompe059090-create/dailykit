package com.priti.dailykit

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class AllToolsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_tools)

        findViewById<LinearLayout>(R.id.toolCalculator).setOnClickListener {
            startActivity(Intent(this, CalculatorActivity::class.java))
        }

        findViewById<LinearLayout>(R.id.toolEmi).setOnClickListener {
            startActivity(Intent(this, EmiActivity::class.java))
        }

        findViewById<LinearLayout>(R.id.toolGst).setOnClickListener {
            startActivity(Intent(this, GstActivity::class.java))
        }

        findViewById<LinearLayout>(R.id.toolNotes).setOnClickListener {
            startActivity(Intent(this, NotesActivity::class.java))
        }
    }
}
