package com.troshchiy.akitaforreddit.network.data

fun TopNewsDto.mapToDomainModel() = listingData.children.map { childrenToRedditPost(it) }

private fun childrenToRedditPost(children: TopNewsDto.ListingData.Children): RedditPost {
    val redditPost = children.redditPost

    return RedditPost(
        id = redditPost.id,
        thumbnail = redditPost.thumbnail,
        imageUrl = redditPost.url,
        title = redditPost.title,
        author = redditPost.author,
        created_utc = redditPost.created_utc,
        numComments = redditPost.numComments,
        isVideo = redditPost.isVideo,
        videoUrl = redditPost.media?.reddit_video?.scrubber_media_url,
        fallbackVideoUrl = redditPost.media?.reddit_video?.fallback_url,
    )
}
