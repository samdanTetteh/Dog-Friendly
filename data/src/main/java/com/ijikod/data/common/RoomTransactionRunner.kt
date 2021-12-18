package com.ijikod.data.common

import javax.inject.Inject

class RoomTransactionRunner @Inject constructor(
    private val db: DogFriendlyDatabase
) : DatabaseTransactionRunner {
    override operator fun invoke(func: () -> Unit) {
        db.runInTransaction {
            func()
        }
    }
}