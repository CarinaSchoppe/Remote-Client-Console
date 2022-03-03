package de.carina.minecraftremoteclientconsole.grafics

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.JsonObject
import de.carina.minecraftremoteclientconsole.R
import de.carina.minecraftremoteclientconsole.client.Client
import de.carina.minecraftremoteclientconsole.util.Packet
import de.carina.minecraftremoteclientconsole.util.PacketType
import de.carina.minecraftremoteclientconsole.util.PopUp

class Console : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_console)
        PopUp.activeActivity = this
        sendButton()
        logout()

    }

    private fun logout() {
        val button = findViewById<Button>(R.id.logout)
        button.setOnClickListener {
            Client.instance.logout()
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }

    private fun sendButton() {
        val button = findViewById<Button>(R.id.send)
        button.setOnClickListener {
            val text = findViewById<EditText>(R.id.command)
            if (text.text.isNotEmpty()) {
                val json = JsonObject()
                json.addProperty("command", "${text.text}")
                Thread({
                    Client.instance.writer.println(Packet(PacketType.COMMAND, json).createJsonPacket())
                }).start()
                text.setText("command")
            }
        }
    }

}