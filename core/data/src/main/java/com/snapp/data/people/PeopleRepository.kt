package com.snapp.data.people

import com.snapp.network.model.People

interface PeopleRepository {
    suspend fun getPeople(id: String): Result<People>
}