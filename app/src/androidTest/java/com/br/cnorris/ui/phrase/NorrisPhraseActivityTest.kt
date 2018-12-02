package com.br.cnorris.ui.phrase

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.rule.ActivityTestRule
import com.br.cnorris.R
import org.hamcrest.Matchers.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(androidx.test.ext.junit.runners.AndroidJUnit4::class)
class NorrisPhraseActivityTest {

    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(NorrisPhraseActivity::class.java,
        true,
        false)

    @Before fun setUp(){
        val intent = Intent()
        intent.putExtra("EXTRA_CATEGORY", "dev")
        activityTestRule.launchActivity(intent)
    }

    @Test
    fun displayComponentsOnInit(){
        onView(ViewMatchers.withId(R.id.lytPhraseLoading)).check(matches(isDisplayed()))
        Thread.sleep(3000)
        onView(ViewMatchers.withId(R.id.imgPhraseCNorris)).check(matches(isDisplayed()))
        onView(ViewMatchers.withId(R.id.txtCategoryChosen)).check(matches(isDisplayed()))
        onView(ViewMatchers.withId(R.id.txtCNorrisPhrase)).check(matches(isDisplayed()))
        onView(ViewMatchers.withId(R.id.lytCardView)).check(matches(isDisplayed()))
        onView(ViewMatchers.withId(R.id.txtCNorrisPhraseLink)).check(matches(isDisplayed()))
    }

    @Test
    fun verifyOnClickLink(){
        Thread.sleep(3000)
        Intents.init()
        val expectedIntent = allOf(hasAction(Intent.ACTION_VIEW))
        onView(ViewMatchers.withId(R.id.txtCNorrisPhraseLink)).perform(click())
        Intents.intended(expectedIntent)
    }
}