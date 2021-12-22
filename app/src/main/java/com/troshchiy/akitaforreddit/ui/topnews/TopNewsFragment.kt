package com.troshchiy.akitaforreddit.ui.topnews

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.troshchiy.akitaforreddit.appComponent
import com.troshchiy.akitaforreddit.extensions.toast
import com.troshchiy.akitaforreddit.network.RedditService
import com.troshchiy.akitaforreddit.network.data.toTopNews
import com.troshchiy.akitaforreddit.ui.theme.AkitaForRedditTheme
import javax.inject.Inject
import kotlinx.coroutines.launch

class TopNewsFragment : Fragment() {

    @Inject lateinit var service: RedditService

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

//        requireContext().appComponent.inject(this)
//
//        lifecycleScope.launch {
//            loadTopNews()
//        }

//        setContent {
//            AkitaForRedditTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(color = MaterialTheme.colors.background) {
//                    TopNewsList()
//                }
//            }
//        }

        return ComposeView(requireContext()).apply {
            setContent {
//                Surface(
//                    modifier = Modifier
//                        .background(Color(0xFFEDEAE0))
//                ) {
                    Text(text = "TopNewsFragment")
//                }
            }
        }
    }

    private suspend fun loadTopNews() {
        val result = service.topNews(10, null)

        if (result.isSuccessful && result.body() != null) {
            val right = result.body()!!.toTopNews()
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
