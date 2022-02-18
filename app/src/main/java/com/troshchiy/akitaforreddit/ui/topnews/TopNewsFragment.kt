package com.troshchiy.akitaforreddit.ui.topnews

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import coil.annotation.ExperimentalCoilApi
import com.troshchiy.akitaforreddit.appComponent
import com.troshchiy.akitaforreddit.network.data.RedditPost
import com.troshchiy.akitaforreddit.ui.topnews.components.PostCard
import javax.inject.Inject

@ExperimentalCoilApi
class TopNewsFragment : Fragment() {

    @Inject lateinit var viewModel: TopNewsViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return ComposeView(requireContext()).apply {
            setContent() {
                val posts = viewModel.posts.value

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 8.dp)
                ) {
                    itemsIndexed(items = posts) { _: Int, post: RedditPost ->
                        PostCard(post) { openNewsDetailsFragment(post) }
                    }
                }
            }
        }
    }

    private fun openNewsDetailsFragment(post: RedditPost) {
        findNavController().navigate(TopNewsFragmentDirections.toNewsDetailsFragments(post))
    }
}
