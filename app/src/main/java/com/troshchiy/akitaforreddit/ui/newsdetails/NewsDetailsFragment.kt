package com.troshchiy.akitaforreddit.ui.newsdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.material.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.compose.rememberImagePainter
import com.troshchiy.akitaforreddit.network.data.RedditPost

class NewsDetailsFragment : Fragment() {

    private val safeArgs: NewsDetailsFragmentArgs by navArgs()
    private val post: RedditPost by lazy(LazyThreadSafetyMode.NONE) { safeArgs.post }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        return ComposeView(requireContext()).apply {
            setContent() {
                Surface() {
                    Image(
                        painter = rememberImagePainter(data = post.thumbnail),
                        alignment = Alignment.Center,
                        contentScale = ContentScale.Crop,
                        contentDescription = "Image",
                    )
                }
            }
        }
    }
}
