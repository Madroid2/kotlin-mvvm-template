package com.android.kotlin.utils

import io.reactivex.schedulers.Schedulers

class TrampolineScheduleProvider : BaseSchedulerProvider {
    override fun computation() = Schedulers.trampoline()
    override fun ui() = Schedulers.trampoline()
    override fun io() = Schedulers.trampoline()
}