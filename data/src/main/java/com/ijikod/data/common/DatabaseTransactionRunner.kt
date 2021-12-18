package com.ijikod.data.common

interface DatabaseTransactionRunner {
    operator fun invoke(func: () -> Unit)
}