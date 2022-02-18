package com.troshchiy.akitaforreddit.extensions

import android.content.Context
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment

fun Context.toast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, text, duration).show()

fun Fragment.navigate(@IdRes resId: Int) {
    NavHostFragment.findNavController(this).navigate(resId)
}

fun Fragment.navigate(directions: NavDirections) {
    NavHostFragment.findNavController(this).navigate(directions)
}
