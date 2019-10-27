package es.iessaladillo.pedrojoya.profile.ui.avatar

import android.app.Activity.RESULT_OK
import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import es.iessaladillo.pedrojoya.profile.R
import es.iessaladillo.pedrojoya.profile.data.local.entity.Avatar
import org.hamcrest.Matchers.`is`
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class AvatarActivityIntentTest {

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

    // Sending result intent

    @Test
    fun shouldReturnAvatarWhenSelectMenuClicked() {
        onView(withId(R.id.imgAvatar2)).perform(click())
        onView(withId(R.id.mnuSelect)).perform(click())
        val resultCode = testRule.activityResult.resultCode
        val intent = testRule.activityResult.resultData
        assertThat(resultCode, `is`(RESULT_OK))
        assertThat(intent, hasExtra("EXTRA_AVATAR", Avatar(2, R.drawable.bulbasur, "Bulbasur")))
    }

}
