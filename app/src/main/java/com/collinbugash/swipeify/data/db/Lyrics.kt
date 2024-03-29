package com.collinbugash.swipeify.data.db


data class LyricTrack(
    val lyrics: Lyrics
)

data class Lyrics(
    val lines: List<Line>,
    val language: String
)

data class Line(
    val words: String
)