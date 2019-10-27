package es.iessaladillo.pedrojoya.profile.ui.main


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import es.iessaladillo.pedrojoya.profile.R
import es.iessaladillo.pedrojoya.profile.base.DrawableMatcher
import es.iessaladillo.pedrojoya.profile.base.Rotation
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class ProfileActivityRotationTest {

    @get:Rule
    val testRule = IntentsTestRule(ProfileActivity::class.java)

    @Before
    fun setup() {
        onView(withId(R.id.txtName)).perform(closeSoftKeyboard())
    }

    // Avatar

    @Test
    fun shouldHaveSameAvatarAfterRotation() {
        onView(withId(R.id.imgAvatar)).perform(click())
        // Perform click on AvatarActivity to send result and finish.
        val bulbasur = "Bulbasur"
        onView(withText(bulbasur)).perform(click())
        onView(withId(R.id.mnuSelect)).perform(click())
        Rotation.rotateScreen(testRule.activity)
        // Check result set to views.
        onView(withId(R.id.imgAvatar))
            .check(matches(DrawableMatcher(R.drawable.bulbasur)))
        onView(withId(R.id.lblAvatar)).check(
            matches(withText(bulbasur))
        )
    }

}
