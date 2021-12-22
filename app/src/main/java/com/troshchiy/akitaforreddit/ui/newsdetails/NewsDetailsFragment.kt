package com.troshchiy.akitaforreddit.ui.newsdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment

class NewsDetailsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val red = Color(0xffff0000)
        val blue = Color(red = 0f, green = 0f, blue = 1f)

        return ComposeView(requireContext()).apply {
            setContent() {
                Surface(color = red) {
                    Text(text = "NewsDetailsFragment")
                }
            }
        }
    }
}
