package de.carina.minecraftremoteclientconsole.util

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import de.carina.minecraftremoteclientconsole.R

object PopUp {

    lateinit var activeActivity: AppCompatActivity
    fun createGoodPopUp(activity: Activity, title: String, message: String) {
        var popDialog = Dialog(activity)
        popDialog.setContentView(R.layout.pop_up_good)
        popDialog.setCancelable(false)
        popDialog.show()
        val confirmButton = popDialog.findViewById<Button>(R.id.confirmBad)
        val messageText = popDialog.findViewById<TextView>(R.id.messageTextBad)
        var heading = popDialog.findViewById<TextView>(R.id.headLineBad)
        messageText.setText(message)
        heading.setText(title)
        popDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
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
        messageText.setText(message)
        heading.setText(title)
        popDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        confirmButton.setOnClickListener {
            popDialog.dismiss()
        }
    }

}