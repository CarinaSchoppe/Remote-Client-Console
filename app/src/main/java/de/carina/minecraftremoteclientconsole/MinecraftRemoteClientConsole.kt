package de.carina.minecraftremoteclientconsole

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class MinecraftRemoteClientConsole : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val ip = findViewById<EditText>(R.id.ip).text.toString()
        val port = findViewById<EditText>(R.id.port).text.toString()
        val userName = findViewById<EditText>(R.id.username).text.toString()
        val password = findViewById<EditText>(R.id.password).text.toString()
        val loginButton = findViewById<Button>(R.id.loginButton)

        loginButton.setOnClickListener {
            startActivity(Intent(this, Console::class.java))
        }
    }
}