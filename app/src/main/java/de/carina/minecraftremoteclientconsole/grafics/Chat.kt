package de.carina.minecraftremoteclientconsole.grafics

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import de.carina.minecraftremoteclientconsole.R
import de.carina.minecraftremoteclientconsole.util.PopUp

class Chat : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        PopUp.activeActivity = this

    }
}