package com.priti.dailykit
import com.priti.dailykit.R

import android.app.Activity
import android.content.Context
import android.widget.Toast
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback

object AdsManager {

    private const val INTERSTITIAL_ID =
        "ca-app-pub-3940256099942544/1033173712"

    private const val REWARDED_ID =
        "ca-app-pub-3940256099942544/5224354917"

    private var interstitialAd: InterstitialAd? = null
    private var rewardedAd: RewardedAd? = null

    fun preload(context: Context) {

        val request = AdRequest.Builder().build()

        if (interstitialAd == null) {
            InterstitialAd.load(
                context,
                INTERSTITIAL_ID,
                request,
                object : InterstitialAdLoadCallback() {

                    override fun onAdLoaded(ad: InterstitialAd) {
                        interstitialAd = ad
                    }

                    override fun onAdFailedToLoad(error: LoadAdError) {
                        interstitialAd = null
                    }
                }
            )
        }

        if (rewardedAd == null) {
            RewardedAd.load(
                context,
                REWARDED_ID,
                request,
                object : RewardedAdLoadCallback() {

                    override fun onAdLoaded(ad: RewardedAd) {
                        rewardedAd = ad
                    }

                    override fun onAdFailedToLoad(error: LoadAdError) {
                        rewardedAd = null
                    }
                }
            )
        }
    }

    fun showInterstitial(
        activity: Activity,
        onFinished: () -> Unit
    ) {

        val ad = interstitialAd

        if (ad == null) {
            preload(activity)
            onFinished()
            return
        }

        interstitialAd = null

        ad.fullScreenContentCallback =
            object : FullScreenContentCallback() {

                override fun onAdDismissedFullScreenContent() {
                    preload(activity)
                    onFinished()
                }

                override fun onAdFailedToShowFullScreenContent(
                    adError: com.google.android.gms.ads.AdError
                ) {
                    preload(activity)
                    onFinished()
                }
            }

        ad.show(activity)
    }

    fun showRewarded(
        activity: Activity,
        onReward: () -> Unit
    ) {

        val ad = rewardedAd

        if (ad == null) {
            preload(activity)

            Toast.makeText(
                activity,
                "Reward video अजून load होत आहे.",
                Toast.LENGTH_SHORT
            ).show()

            return
        }

        rewardedAd = null

        ad.fullScreenContentCallback =
            object : FullScreenContentCallback() {

                override fun onAdDismissedFullScreenContent() {
                    preload(activity)
                }

                override fun onAdFailedToShowFullScreenContent(
                    adError: com.google.android.gms.ads.AdError
                ) {
                    preload(activity)
                }
            }

        ad.show(
            activity
        ) { _: RewardItem ->
            onReward()
        }
    }
}
