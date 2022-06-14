/*
 * Copyright Notice for MinecraftRemoteClientConsole
 * Copyright (c) at Carina Sophie Schoppe 2022
 * File created on 14.06.22, 15:35 by Carina The Latest changes made by Carina on 14.06.22, 15:34 All contents of "PopUp.kt" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at Carina Sophie Schoppe. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of Carina Sophie Schoppe.
 */

package de.carinasophie.minecraftremoteclientconsole.util

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.Button
import android.widget.TextView
import de.carinasophie.minecraftremoteclientconsole.R


object PopUp {

    fun createGoodPopUp(activity: Activity, title: String, message: String) {
        var popDialog = Dialog(activity)
        popDialog.setContentView(R.layout.pop_up_good)
        popDialog.setCancelable(false)
        popDialog.show()
        val confirmButton = popDialog.findViewById<Button>(R.id.confirmGood)
        val messageText = popDialog.findViewById<TextView>(R.id.messageTextBad)
        var heading = popDialog.findViewById<TextView>(R.id.headLineBad)
        messageText.text = message
        heading.text = title
        popDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        confirmButton.setOnClickListener {
            popDialog.dismiss()
        }
    }

    fun createBadPopUp(activity: Activity, title: String, message: String) {
        var popDialog = Dialog(activity)
        popDialog.setContentView(R.layout.pop_up_bad)
        popDialog.setCancelable(false)
        popDialog.show()
        val confirmButton = popDialog.findViewById<Button>(R.id.confirmBad)
        val messageText = popDialog.findViewById<TextView>(R.id.messageTextBad)
        var heading = popDialog.findViewById<TextView>(R.id.headLineBad)
        messageText.text = message
        heading.text = title
        popDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        confirmButton.setOnClickListener {
            popDialog.dismiss()
        }
    }

}