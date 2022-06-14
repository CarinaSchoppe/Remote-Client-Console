/*
 * Copyright Notice for MinecraftRemoteClientConsole
 * Copyright (c) at Carina Sophie Schoppe 2022
 * File created on 14.06.22, 15:35 by Carina The Latest changes made by Carina on 14.06.22, 15:34 All contents of "Login.kt" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at Carina Sophie Schoppe. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of Carina Sophie Schoppe.
 */

package de.carinasophie.minecraftremoteclientconsole.graphics

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import de.carinasophie.minecraftremoteclientconsole.R
import de.carinasophie.minecraftremoteclientconsole.client.Client
import de.carinasophie.minecraftremoteclientconsole.util.PopUp
import de.carinasophie.minecraftremoteclientconsole.util.Utility


class Login : AppCompatActivity() {

    companion object {
        lateinit var client: Client
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        println(Utility.activeActivity.javaClass.name)
        var ip = findViewById<EditText>(R.id.ip).text
        val port = findViewById<EditText>(R.id.port).text
        val userName = findViewById<EditText>(R.id.username).text
        val password = findViewById<EditText>(R.id.password).text
        outState.putString("username", userName.toString())
        outState.putString("password", password.toString())
        outState.putString("ip", ip.toString())
        outState.putString("port", port.toString())
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Utility.activeActivity = this
        val ip = findViewById<EditText>(R.id.ip)
        val port = findViewById<EditText>(R.id.port)
        val userName = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)

        if (savedInstanceState != null) {
            ip.setText(savedInstanceState.getString("ip"))
            port.setText(savedInstanceState.getString("port"))
            userName.setText(savedInstanceState.getString("username"))
            password.setText(savedInstanceState.getString("password"))
        }

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