package com.troshchiy.akitaforreddit.ui.topnews.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
    val halfPadding = 4.dp
    val padding = 8.dp

    Card(
        shape = MaterialTheme.shapes.medium,
        elevation = halfPadding,
        modifier = Modifier
            .padding(start = halfPadding, end = halfPadding, bottom = padding)
            .clickable(
                onClick = onClick
            )
    ) {
        Column(modifier = Modifier.padding(all = padding)) {
            Box(
                modifier = Modifier.height(34.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = post.title,
                    maxLines = 2,
                )
            }
            Spacer(Modifier.size(halfPadding))
            Image(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth(),
                painter = rememberImagePainter(data = post.thumbnail),
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop,
                contentDescription = "Image",
            )
            Spacer(Modifier.size(halfPadding))
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(text = post.author)
                Text(
                    text = post.numComments,
                    textAlign = TextAlign.End,
                    modifier = Modifier.align(Alignment.CenterEnd)
                )
            }
        }
    }
}

@ExperimentalCoilApi
@Preview(group = "Card")
@Composable
fun PreviewRedditPost() {
    val post = RedditPost(
        id = "rvb1r8",
        thumbnail = "https://b.thumbs.redditmedia.com/TQmTFrAO_u1dnfrbHd6fm_6cynA-SiSYslmfl6vWzcY.jpg",
        imageUrl = "https://i.redd.it/x8h3nisq7j981.jpg",
        title = "Ahhh bliss",
        author = "ListerineAfterOral",
        created_utc = "1641241397.0",
        numComments = "651",
        isVideo = false,
        videoUrl = null,
        fallbackVideoUrl = null
    )

    Box(modifier = Modifier.background(Color.Gray)) {
        RedditPost(post) { }
    }
}
