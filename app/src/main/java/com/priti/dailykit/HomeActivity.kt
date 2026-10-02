package com.priti.dailykit
import com.priti.dailykit.R

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        findViewById<MaterialCardView>(R.id.cardCalculator).setOnClickListener {
            startActivity(Intent(this, CalculatorActivity::class.java))
        }

        findViewById<MaterialCardView>(R.id.cardEmi).setOnClickListener {
            startActivity(Intent(this, EmiActivity::class.java))
        }

        findViewById<MaterialCardView>(R.id.cardGst).setOnClickListener {
            startActivity(Intent(this, GstActivity::class.java))
        }

        findViewById<MaterialCardView>(R.id.cardNotes).setOnClickListener {
            startActivity(Intent(this, NotesActivity::class.java))
        }

        findViewById<MaterialCardView>(R.id.cardAllTools).setOnClickListener {
            startActivity(Intent(this, AllToolsActivity::class.java))
        }

        findViewById<MaterialCardView>(R.id.cardSettings).setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

        findViewById<MaterialCardView>(R.id.navHome).setOnClickListener {
            // Already on Home
        }

        findViewById<MaterialCardView>(R.id.navTools).setOnClickListener {
            startActivity(Intent(this, ToolsActivity::class.java))
        }

        findViewById<MaterialCardView>(R.id.navSettings).setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }
}
