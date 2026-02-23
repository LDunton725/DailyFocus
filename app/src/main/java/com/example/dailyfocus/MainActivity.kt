package com.example.dailyfocus

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvTask: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvTask = findViewById(R.id.tvTask)
        val btnAddTask = findViewById<Button>(R.id.btnAddTask)

        btnAddTask.setOnClickListener {
            val intent = Intent(this, AddTaskActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        val prefs = getSharedPreferences("DailyFocusPrefs", MODE_PRIVATE)
        val task = prefs.getString("task", "No task yet")
        tvTask.text = task
    }
}