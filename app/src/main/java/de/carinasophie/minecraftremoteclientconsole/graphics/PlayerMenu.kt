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

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import de.carinasophie.minecraftremoteclientconsole.R
import de.codecrafters.tableview.TableView
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter

class PlayerMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_menu)

        val array: List<Array<out String>> = listOf(arrayOf("1", "2", "3"), arrayOf("4", "5", "6"), arrayOf("7", "8", "9"))

        val tableView = findViewById<TableView<Array<String>>>(R.id.tableView)
        tableView.setHeaderBackgroundColor(Color.BLUE)
        tableView.headerAdapter = SimpleTableHeaderAdapter(this, "a", "b", "c")
        tableView.columnCount = 3
/*        tableView.dataAdapter = SimpleTableDataAdapter(this, array)*/


    }
}