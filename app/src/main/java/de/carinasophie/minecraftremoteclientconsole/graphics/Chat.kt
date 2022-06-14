package de.carinasophie.minecraftremoteclientconsole.graphics

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import de.carina.minecraftremoteclientconsole.R
import de.carinasophie.minecraftremoteclientconsole.util.PopUp

class Chat : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("hier")
        setContentView(R.layout.activity_chat)
        PopUp.activeActivity = this

    }
}