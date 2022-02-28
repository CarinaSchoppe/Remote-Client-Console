package de.carina.minecraftremoteclientconsole.grafics

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import de.carina.minecraftremoteclientconsole.R
import de.carina.minecraftremoteclientconsole.server.Client
import de.carina.minecraftremoteclientconsole.util.PopUp


class Login : AppCompatActivity() {


    companion object {
        lateinit var client: Client
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        PopUp.activeActivity = this

        val ip = findViewById<EditText>(R.id.ip).text
        val port = findViewById<EditText>(R.id.port).text
        val userName = findViewById<EditText>(R.id.username).text
        val password = findViewById<EditText>(R.id.password).text
        val loginButton = findViewById<Button>(R.id.loginButton)
        loginButton.setOnClickListener {
            println(findViewById<EditText>(R.id.port).text)
            //Client(ip=ip, port=port.toInt(), name=userName,password= password)
            if (client.connect()) {
                startActivity(Intent(this, Console::class.java))
            } else {
                PopUp.cratePopUp(this, "Error", "Could not connect to server")
            }
        }
    }
}