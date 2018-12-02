package com.br.cnorris.ui.category

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.br.cnorris.ConstantUtils
import com.br.cnorris.R
import com.br.cnorris.ui.category.fragment.CategoryListFragment
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(androidx.test.ext.junit.runners.AndroidJUnit4::class)
class CategoryListActivityTest {

    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(CategoryListActivity::class.java)

    @Before
    fun setUp(){
        activityTestRule.activity.supportFragmentManager
            .beginTransaction()
            .add(CategoryListFragment(), ConstantUtils.CategoryListFragment_TAG)
            .commit()
    }

    @Test
    fun displayComponentsOnInit() {
        onView(ViewMatchers.withId(R.id.viewCategoryContainer)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerCategory)).check(matches(isDisplayed()))
    }
}