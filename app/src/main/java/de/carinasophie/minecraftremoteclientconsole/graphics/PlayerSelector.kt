/*
 * Copyright Notice for MinecraftRemoteClientConsole
 * Copyright (c) at Carina Sophie Schoppe 2022
 * File created on 7/25/22, 11:35 AM by Carina The Latest changes made by Carina on 7/25/22, 11:31 AM All contents of "PlayerSelector.kt" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at Carina Sophie Schoppe. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of Carina Sophie Schoppe.
 */

package de.carinasophie.minecraftremoteclientconsole.graphics

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import de.carinasophie.minecraftremoteclientconsole.R
import de.carinasophie.minecraftremoteclientconsole.util.Player
import de.carinasophie.minecraftremoteclientconsole.util.Utility


class PlayerSelector : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_selector)
        Utility.activeActivity = this
        Utility.chatMenuButton()
        Utility.consoleMenuButton()
        Utility.logoutButton()
        sendButton()
        Utility.refresh()
        refreshButton()
    }

    companion object {
        fun tableRowListener(view: View) {
            //get the table row that was clicked
            val tableRow = view as TableRow
            tableRow.setBackgroundColor(R.color.green)
            //get all tableRows of a given tableLayout
            val tableLayout = tableRow.parent as TableLayout
            for (tableRows in tableLayout.children) {
                //get the tableRow that is currently selected
                val selectedTableRow = tableRows as TableRow
                //set the background color of the selected tableRow to white
                if (selectedTableRow != tableRow) {
                    selectedTableRow.setBackgroundColor(R.color.white)
                }
            }

            val playerObject: Player = Utility.playersList.find { it.name == tableRow.getChildAt(0).toString() && it.ping.toString() == tableRow.getChildAt(7).toString() }!!

        }
    }

    private fun sendButton() {

    }

    private fun refreshButton() {
        findViewById<Button>(R.id.refreshButton).setOnClickListener {
            Utility.refresh()
        }

    }


}