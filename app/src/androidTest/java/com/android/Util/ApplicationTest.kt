package com.android.Util

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.android.kotlin.ui.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ApplicationTest {

    @get:Rule
    val activity = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testAdd() {
        onView(withId(R.id.activity_main_show_trends_button)).perform(click())
    }
}