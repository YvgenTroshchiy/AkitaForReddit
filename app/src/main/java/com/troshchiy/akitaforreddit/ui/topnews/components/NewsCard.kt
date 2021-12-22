package com.troshchiy.akitaforreddit.ui.topnews.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.troshchiy.akitaforreddit.network.data.RedditPost

@ExperimentalCoilApi
@Composable
fun RedditPost(
    post: RedditPost,
    onClick: () -> Unit
) {
    val padding = 4.dp

    Card(
        shape = MaterialTheme.shapes.medium,
        elevation = padding,
        modifier = Modifier.padding(start = padding, end = padding, bottom = 8.dp)
    ) {
        Box(modifier = Modifier.padding(all = padding)) {
            Column() {
                Box(
                    modifier = Modifier.height(34.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Quit my job last night, it was nice to be home to make the kids breakfast and take them to school today! Off to hunt for a new opportunity, wish me luck :)",
                        maxLines = 1,
                    )
                }
                Spacer(Modifier.size(padding))
                Image(
                    modifier = Modifier
                        .requiredHeight(140.dp)
                        .fillMaxWidth(),
                    painter = rememberImagePainter(
                        data = "https://developer.android.com/images/brand/Android_Robot.png"
                    ),
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Crop,
                    contentDescription = "Image",
                )
                Spacer(Modifier.size(padding))
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(text = "hestolemysmile")
                    Text(
                        text = "11920",
                        textAlign = TextAlign.End,
                        modifier = Modifier.align(Alignment.CenterEnd)
                    )
                }
            }
        }
    }
}

@Preview(group = "Card")
@Composable
fun PreviewRedditPost() {
    Box(modifier = Modifier.background(Color.Gray)) {
//        RedditPost()
    }
}
