/*
 * Copyright Notice for MinecraftRemoteClientConsole
 * Copyright (c) at Carina Sophie Schoppe 2022
 * File created on 14.06.22, 15:35 by Carina The Latest changes made by Carina on 14.06.22, 15:34 All contents of "Console.kt" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at Carina Sophie Schoppe. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of Carina Sophie Schoppe.
 */

package de.carinasophie.minecraftremoteclientconsole.graphics


import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.gson.JsonObject
import de.carinasophie.minecraftremoteclientconsole.R
import de.carinasophie.minecraftremoteclientconsole.client.Client
import de.carinasophie.minecraftremoteclientconsole.util.Packet
import de.carinasophie.minecraftremoteclientconsole.util.PacketType
import de.carinasophie.minecraftremoteclientconsole.util.Utility

class Console : AppCompatActivity() {
    private var mInterstitialAd: InterstitialAd? = null
    private var TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_console)
        Utility.activeActivity = this

        //find console out and command
        val consoleOut = findViewById<TextView>(R.id.consoleOut)
        val command = findViewById<EditText>(R.id.command)

        //load saveInstanceState
        if (savedInstanceState != null) {
            consoleOut.text = savedInstanceState.getString("consoleText")
            command.setText(savedInstanceState.getString("commandText"))
        }
        sendButton()
        Utility.logoutButton()
        Utility.chatMenuButton()
        Utility.playerMenuButton()

        var adRequest = AdRequest.Builder().build()

        InterstitialAd.load(this, "ca-app-pub-3940256099942544/1033173712", adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                adError.toString()?.let { Log.d(TAG, it) }
                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                Log.d(TAG, "Ad was loaded.")
                mInterstitialAd = interstitialAd
            }
        })
        mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdClicked() {
                // Called when a click is recorded for an ad.
                Log.d(TAG, "Ad was clicked.")
            }

            override fun onAdDismissedFullScreenContent() {
                // Called when ad is dismissed.
                Log.d(TAG, "Ad dismissed fullscreen content.")
                mInterstitialAd = null
            }

            override fun onAdImpression() {
                // Called when an impression is recorded for an ad.
                Log.d(TAG, "Ad recorded an impression.")
            }

            override fun onAdShowedFullScreenContent() {
                // Called when ad is shown.
                Log.d(TAG, "Ad showed fullscreen content.")
            }
        }
        if (mInterstitialAd != null) {
            mInterstitialAd?.show(this)
        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.")
        }

    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //find console out and command
        val consoleOut = findViewById<TextView>(R.id.consoleOut)
        val command = findViewById<EditText>(R.id.command)
        //save the command text and the console out
        outState.putString("commandText", command?.text.toString())
        outState.putString("consoleText", consoleOut?.text.toString())

    }

    private fun sendButton() {
        findViewById<Button>(R.id.send).setOnClickListener {
            val window = findViewById<EditText>(R.id.command)
            if (window.text.isNotEmpty()) {
                val json = JsonObject()
                json.addProperty("command", "${window.text}")
                Thread {
                    Client.instance.writer.println(Packet(PacketType.COMMAND, json).createJsonPacket())
                }.start()
                window.setText("command")
            }
        }
    }


}