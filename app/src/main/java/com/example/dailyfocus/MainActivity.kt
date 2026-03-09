package com.example.dailyfocus

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.FileInputStream
import java.io.FileOutputStream

// Updated today
class MainActivity : AppCompatActivity() {

    private lateinit var tvTask: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvTask = findViewById(R.id.tvTask)
        val btnAddTask = findViewById<Button>(R.id.btnAddTask)
        val btnSaveTask = findViewById<Button>(R.id.btnSaveTask)
        val btnLoadTask = findViewById<Button>(R.id.btnLoadTask)

        btnAddTask.setOnClickListener {
            val intent = Intent(this, AddTaskActivity::class.java)
            startActivity(intent)
        }

        btnSaveTask.setOnClickListener {
            val text = tvTask.text.toString()
            val fileOutput: FileOutputStream = openFileOutput("task_file.txt", MODE_PRIVATE)
            fileOutput.write(text.toByteArray())
            fileOutput.close()
        }

        btnLoadTask.setOnClickListener {
            try {
                val fileInput: FileInputStream = openFileInput("task_file.txt")
                val text = fileInput.bufferedReader().readText()
                tvTask.text = text
                fileInput.close()
            } catch (_: Exception) {
                tvTask.text = getString(R.string.no_saved_task_found)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val prefs = getSharedPreferences("DailyFocusPrefs", MODE_PRIVATE)
        val task = prefs.getString("task", "No task yet")
        tvTask.text = task
    }
}