package com.troshchiy.akitaforreddit.ui.topnews

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import coil.annotation.ExperimentalCoilApi
import com.troshchiy.akitaforreddit.R
import com.troshchiy.akitaforreddit.appComponent
import com.troshchiy.akitaforreddit.extensions.toast
import com.troshchiy.akitaforreddit.network.RedditService
import com.troshchiy.akitaforreddit.network.data.RedditPost
import com.troshchiy.akitaforreddit.network.data.mapToDomainModel
import com.troshchiy.akitaforreddit.ui.theme.AkitaForRedditTheme
import com.troshchiy.akitaforreddit.ui.topnews.components.PostCard
import javax.inject.Inject
import kotlinx.coroutines.launch

@ExperimentalCoilApi
class TopNewsFragment : Fragment() {

    @Inject lateinit var service: RedditService

    val posts: MutableState<List<RedditPost>> = mutableStateOf(ArrayList())

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        lifecycleScope.launch {
            loadTopNews()
        }

        return ComposeView(requireContext()).apply {
            setContent() {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 8.dp)
                ) {
                    itemsIndexed(items = posts.value) { index: Int, post: RedditPost ->
                        PostCard(post) {
//                                openNewsDetailsFragment(view)
                            findNavController().navigate(R.id.action_topNewsFragment_to_newsDetailsFragment)
                            Toast.makeText(context, "Showing toast....", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    private fun openNewsDetailsFragment(view: View) {
        view.findNavController().navigate(R.id.action_topNewsFragment_to_newsDetailsFragment)
        Toast.makeText(context, "Showing toast....", Toast.LENGTH_SHORT).show()
    }

    // TODO: temp. should be in the VM
    private suspend fun loadTopNews() {
        val result = service.topNews(10, null)

        if (result.isSuccessful && result.body() != null) {
            val right = result.body()!!.mapToDomainModel()
            posts.value = right
            Log.d("TopNewsActivity", "result: $right")
        } else {
            Log.w("TopNewsActivity", "Error by loading: ${result.message()}")
            requireContext().toast("Error by loading")
        }
    }
}


@Composable
fun TopNewsList() {
    // We save the scrolling position with this state
    val scrollState = rememberLazyListState()

    LazyColumn(state = scrollState) {
        items(100) {
            ImageListItem(it)
        }
    }
}

@Composable
fun ImageListItem(index: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp) // margin
            .clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colors.surface)
            .clickable(onClick = { /* Ignoring onClick */ })
            .padding(8.dp) // padding
        ,
        verticalAlignment = Alignment.CenterVertically
    ) {
//        Image(
//            painter = rememberImagePainter(
//                data = "https://developer.android.com/images/brand/Android_Robot.png"
//            ),
//            contentDescription = "Android Logo",
//            modifier = Modifier.size(50.dp)
//        )
        Spacer(Modifier.width(10.dp))
        Text("Item #$index", style = MaterialTheme.typography.subtitle1)
    }
}

@Preview(showBackground = true)
@Composable
fun ImageListItemPreview() {
    AkitaForRedditTheme {
        ImageListItem(1)
    }
}
