/*
 * Copyright Notice for MinecraftRemoteClientConsole
 * Copyright (c) at Carina Sophie Schoppe 2022
 * File created on 14.06.22, 16:02 by Carina The Latest changes made by Carina on 14.06.22, 15:51 All contents of "PlayerMenu.kt" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at Carina Sophie Schoppe. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of Carina Sophie Schoppe.
 */

package de.carinasophie.minecraftremoteclientconsole.graphics

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import de.carinasophie.minecraftremoteclientconsole.R

class PlayerMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_menu)

    }
}