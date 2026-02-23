package com.example.dailyfocus

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        val etTask = findViewById<EditText>(R.id.etTask)
        val btnSave = findViewById<Button>(R.id.btnSave)
        val btnCancel = findViewById<Button>(R.id.btnCancel)

        btnSave.setOnClickListener {
            val taskText = etTask.text.toString().trim()

            val prefs = getSharedPreferences("DailyFocusPrefs", MODE_PRIVATE)
            prefs.edit()
                .putString("task", if (taskText.isEmpty()) "No task yet" else taskText)
                .apply()

            finish()
        }

        btnCancel.setOnClickListener {
            finish()
        }
    }
}