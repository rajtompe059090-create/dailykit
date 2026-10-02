package com.priti.dailykit
import com.priti.dailykit.R

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class NotesActivity : AppCompatActivity() {

    private lateinit var note: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        note = findViewById(R.id.etNote)

        val prefs = getSharedPreferences("dailykit_notes", MODE_PRIVATE)
        note.setText(prefs.getString("note", ""))

        findViewById<Button>(R.id.btnSaveNote).setOnClickListener {
            prefs.edit()
                .putString("note", note.text.toString())
                .apply()

            Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.btnClearNote).setOnClickListener {
            note.setText("")
            prefs.edit().remove("note").apply()
            Toast.makeText(this, "Note cleared", Toast.LENGTH_SHORT).show()
        }
    }
}
