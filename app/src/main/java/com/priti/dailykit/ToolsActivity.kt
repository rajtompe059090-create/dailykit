package com.priti.dailykit
import com.priti.dailykit.R

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.AdView
import com.google.android.material.card.MaterialCardView

class ToolsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_tools)

        MobileAds.initialize(this)

        AdsManager.preload(this)

        val banner = findViewById<AdView>(R.id.bannerAd)

        banner.loadAd(
            AdRequest.Builder().build()
        )

        findViewById<MaterialCardView>(R.id.toolCalculator)
            .setOnClickListener {

                startActivity(
                    Intent(this, CalculatorActivity::class.java)
                )
            }

        findViewById<MaterialCardView>(R.id.toolEmi)
            .setOnClickListener {

                startActivity(
                    Intent(this, EmiActivity::class.java)
                )
            }

        findViewById<MaterialCardView>(R.id.toolGst)
            .setOnClickListener {

                startActivity(
                    Intent(this, GstActivity::class.java)
                )
            }

        findViewById<MaterialCardView>(R.id.toolNotes)
            .setOnClickListener {

                startActivity(
                    Intent(this, NotesActivity::class.java)
                )
            }

        findViewById<MaterialCardView>(R.id.toolAll)
            .setOnClickListener {

                AdsManager.showInterstitial(this) {

                    startActivity(
                        Intent(this, AllToolsActivity::class.java)
                    )
                }
            }

        findViewById<MaterialCardView>(R.id.toolReward)
            .setOnClickListener {

                AdsManager.showRewarded(this) {

                    Toast.makeText(
                        this,
                        "🎁 Reward मिळाले!",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

        findViewById<MaterialCardView>(R.id.navHome)
            .setOnClickListener {

                startActivity(
                    Intent(this, HomeActivity::class.java)
                )

                finish()
            }

        findViewById<MaterialCardView>(R.id.navTools)
            .setOnClickListener {
                // Already Tools
            }

        findViewById<MaterialCardView>(R.id.navSettings)
            .setOnClickListener {

                startActivity(
                    Intent(this, SettingsActivity::class.java)
                )

                finish()
            }
    }

    override fun onDestroy() {

        findViewById<AdView?>(R.id.bannerAd)
            ?.destroy()

        super.onDestroy()
    }
}
