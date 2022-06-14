package de.carinasophie.minecraftremoteclientconsole.graphics

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import de.carina.minecraftremoteclientconsole.R
import de.carinasophie.minecraftremoteclientconsole.client.Client
import de.carinasophie.minecraftremoteclientconsole.util.PopUp


class Login : AppCompatActivity() {


    companion object {
        lateinit var client: Client
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        PopUp.activeActivity = this
        loginButton()


    }

    private fun loginButton() {
        /* var ip = findViewById<EditText>(R.id.ip).text
         val port = findViewById<EditText>(R.id.port).text
         val userName = findViewById<EditText>(R.id.username).text
         val password = findViewById<EditText>(R.id.password).text*/
        var ip = "10.0.2.2"
        val port = 8080
        val userName = "test"
        val password = "test"
        val loginButton = findViewById<Button>(R.id.loginButton)
        if (ip.equals("localhost", true))
            ip = "10.0.2.2"
        loginButton.setOnClickListener {
            try {
                port.toString().toInt()
            } catch (e: Exception) {
                PopUp.createBadPopUp(this, "Port Error", "The port must be a number")
                return@setOnClickListener
            }
            val instance = Client(ip = ip.toString(), port = port.toString().toInt(), name = userName.toString(), password = password.toString())
            client = instance
            if (!client.connect()) {
                PopUp.createBadPopUp(this, "Error", "Could not connect to server")
            }


        }
    }
}