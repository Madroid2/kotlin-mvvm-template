package com.android.kotlin.utils

import io.reactivex.schedulers.TestScheduler

class TestScheduleProvider(private val scheduler: TestScheduler) : BaseSchedulerProvider {
    override fun computation() = scheduler
    override fun ui() = scheduler
    override fun io() = scheduler
}