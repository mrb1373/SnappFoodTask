package com.snapp.testing

import com.snapp.network.model.People

val peopleTestData = People(
    name = "Luke Skywalker",
    height = "172",
    mass = "77",
    gender = "male",
    birthYear = "19BBY",
    homeWorld = "https://swapi.dev/api/planets/1/",
    films = listOf(
        "https://swapi.dev/api/films/1/",
        "https://swapi.dev/api/films/2/",
        "https://swapi.dev/api/films/3/",
        "https://swapi.dev/api/films/6/"
    ),
    species = emptyList(),
    vehicles = listOf(
        "https://swapi.dev/api/vehicles/14/",
        "https://swapi.dev/api/vehicles/30/"
    ),
    starships = listOf(
        "https://swapi.dev/api/starships/12/",
        "https://swapi.dev/api/starships/22/"
    ),
    url = "https://swapi.dev/api/people/1/"
)