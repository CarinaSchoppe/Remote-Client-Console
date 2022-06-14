package de.carinasophie.minecraftremoteclientconsole.graphics

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import de.carina.minecraftremoteclientconsole.R

import de.carinasophie.minecraftremoteclientconsole.util.PopUp

class PlayerMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_menu)
        PopUp.activeActivity = this
    }
}