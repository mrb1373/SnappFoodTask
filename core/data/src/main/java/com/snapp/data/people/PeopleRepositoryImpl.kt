package com.snapp.data.people

import com.snapp.common.suspendRunCatching
import com.snapp.network.api.PeopleService
import com.snapp.network.model.People
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class PeopleRepositoryImpl @Inject constructor(
    private val peopleService: PeopleService
): PeopleRepository  {
    override suspend fun getPeople(id: String): Result<People> = coroutineScope {
        suspendRunCatching {
            peopleService.getPeople(id)
        }
    }
}