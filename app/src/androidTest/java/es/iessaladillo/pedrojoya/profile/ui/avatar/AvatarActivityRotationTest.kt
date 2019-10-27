package es.iessaladillo.pedrojoya.profile.ui.avatar

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.isChecked
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import es.iessaladillo.pedrojoya.profile.R
import es.iessaladillo.pedrojoya.profile.base.Rotation
import es.iessaladillo.pedrojoya.profile.data.local.entity.Avatar
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class AvatarActivityRotationTest {

    @get:Rule
    val testRule = IntentsTestRule(
        AvatarActivity::class.java, true, false
    )

    @Before
    fun setup() {
        val avatar = Avatar(1, R.drawable.pikachu, "Pikachu")
        testRule.launchActivity(
            Intent().putExtra("EXTRA_AVATAR", avatar)
        )
    }

    // Rotation

    @Test
    fun shouldHaveSameAvatarSelectedAfterRotation() {
        onView(withId(R.id.imgAvatar2)).perform(click())
        Rotation.rotateScreen(testRule.activity)
        onView(withId(R.id.chkAvatar2)).check(matches(isChecked()))
    }

}
