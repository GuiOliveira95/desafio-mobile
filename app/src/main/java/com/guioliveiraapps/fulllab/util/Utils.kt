package com.guioliveiraapps.fulllab.util

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.request.RequestOptions
import java.text.NumberFormat
import java.util.*

class Utils {

    companion object {
        fun glideImage(mContext: Context, imgUrl: String, mImage: ImageView, placeholder: Int) {
            var requestOptions = RequestOptions()
            requestOptions = requestOptions.transforms(FitCenter())

            if (placeholder != 0) {
                requestOptions.error(placeholder)
                requestOptions.fallback(placeholder)
            }

            Glide.with(mContext)
                .load(imgUrl)
                .apply(requestOptions)
                .into(mImage)
        }

        fun getPriceFormated(price: Double): String {
            val ptBr = Locale("pt", "BR")
            return NumberFormat.getCurrencyInstance(ptBr).format(price)
        }

        fun fadeIn(v: View, duration: Long) {
            if (v.visibility != View.VISIBLE) {
                v.alpha = 0.0f
                v.visibility = View.VISIBLE
                v.animate()
                    .alpha(1.0f)
                    .setDuration(duration)
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationStart(animation: Animator) {
                            super.onAnimationStart(animation)
                            v.visibility = View.VISIBLE
                        }

                    })
            }
        }

        fun fadeOut(v: View, duration: Long, type: Int) {
            // TYPE: 1 -> FICAR INVISIBLE NO FINAL
            // TYPE: 2 -> FICAR GONE NO FINAL

            if (v.visibility == View.VISIBLE) {
                v.alpha = 1.0f
                v.animate()
                    .alpha(0.0f)
                    .setDuration(duration)
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            super.onAnimationEnd(animation)
                            if (type == 1) {
                                v.visibility = View.INVISIBLE
                            } else if (type == 2) {
                                v.visibility = View.GONE
                            } else {
                                v.visibility = View.INVISIBLE
                            }
                        }
                    })
            }
        }
    }
}