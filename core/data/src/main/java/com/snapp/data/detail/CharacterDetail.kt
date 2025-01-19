package com.snapp.data.detail

data class CharacterDetail(
    val name: String,
    val birthYear: String,
    val height: String,
    val speciesName: String,
    val homeWorld: String,
    val population: String,
    val films: List<FilmDetail>,
)

data class FilmDetail(
    val title: String,
    val openingCrawl: String
)
