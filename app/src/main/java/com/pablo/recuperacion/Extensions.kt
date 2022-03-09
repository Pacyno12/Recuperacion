package com.pablo.recuperacion

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.imageUrl(imageUrl: String?) {
    if (imageUrl.isNullOrEmpty()) return
    Picasso.get().load(imageUrl).into(this)

}