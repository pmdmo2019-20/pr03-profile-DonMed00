package es.iessaladillo.pedrojoya.profile.ui.avatar

import android.content.Intent
import android.view.View
import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.isChecked
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import es.iessaladillo.pedrojoya.profile.R
import es.iessaladillo.pedrojoya.profile.data.local.entity.Avatar
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class AvatarActivityTest {

    @get:Rule
    val testRule = IntentsTestRule(
        AvatarActivity::class.java, true, false
    )

    @Before
    fun setup() {
        val avatar = Avatar(2, R.drawable.bulbasur, "Bulbasur")
        testRule.launchActivity(
            Intent().putExtra("EXTRA_AVATAR", avatar)
        )
    }

    @Test
    fun shouldSelectReceivedAvatar() {
        onView(withId(R.id.chkAvatar2))
            .check(matches(isChecked()))
    }

    @Test
    fun shouldChangeSelectionWhenImageView1Clicked() {
        shouldChangeSelectionWhenViewClicked(R.id.imgAvatar1, R.id.chkAvatar1)
    }

    @Test
    fun shouldChangeSelectionWhenImageView2Clicked() {
        onView(withId(R.id.imgAvatar1)).perform(click())
        onView(withId(R.id.imgAvatar2)).perform(click())
        onView(withId(R.id.chkAvatar2)).check(matches(isChecked()))
        onView(withId(R.id.chkAvatar1)).check(matches(not<View>(isChecked())))
    }

    @Test
    fun shouldChangeSelectionWhenImageView3Clicked() {
        shouldChangeSelectionWhenViewClicked(R.id.imgAvatar3, R.id.chkAvatar3)
    }

    @Test
    fun shouldChangeSelectionWhenImageView4Clicked() {
        shouldChangeSelectionWhenViewClicked(R.id.imgAvatar4, R.id.chkAvatar4)
    }

    @Test
    fun shouldChangeSelectionWhenImageView5Clicked() {
        shouldChangeSelectionWhenViewClicked(R.id.imgAvatar5, R.id.chkAvatar5)
    }

    @Test
    fun shouldChangeSelectionWhenImageView6Clicked() {
        shouldChangeSelectionWhenViewClicked(R.id.imgAvatar6, R.id.chkAvatar6)
    }

    @Test
    fun shouldChangeSelectionWhenImageView7Clicked() {
        shouldChangeSelectionWhenViewClicked(R.id.imgAvatar7, R.id.chkAvatar7)
    }

    @Test
    fun shouldChangeSelectionWhenImageView8Clicked() {
        shouldChangeSelectionWhenViewClicked(R.id.imgAvatar8, R.id.chkAvatar8)
    }

    @Test
    fun shouldChangeSelectionWhenImageView9Clicked() {
        shouldChangeSelectionWhenViewClicked(R.id.imgAvatar9, R.id.chkAvatar9)
    }

    @Test
    fun shouldChangeSelectionWhenTextView1Clicked() {
        shouldChangeSelectionWhenViewClicked(R.id.lblAvatar1, R.id.chkAvatar1)
    }

    @Test
    fun shouldChangeSelectionWhenTextView2Clicked() {
        onView(withId(R.id.lblAvatar1)).perform(click())
        onView(withId(R.id.lblAvatar2)).perform(click())
        onView(withId(R.id.chkAvatar2)).check(matches(isChecked()))
        onView(withId(R.id.chkAvatar1)).check(matches(not<View>(isChecked())))
    }

    @Test
    fun shouldChangeSelectionWhenTextView3Clicked() {
        shouldChangeSelectionWhenViewClicked(R.id.lblAvatar3, R.id.chkAvatar3)
    }

    @Test
    fun shouldChangeSelectionWhenTextView4Clicked() {
        shouldChangeSelectionWhenViewClicked(R.id.lblAvatar4, R.id.chkAvatar4)
    }

    @Test
    fun shouldChangeSelectionWhenTextView5Clicked() {
        shouldChangeSelectionWhenViewClicked(R.id.lblAvatar5, R.id.chkAvatar5)
    }

    @Test
    fun shouldChangeSelectionWhenTextView6Clicked() {
        shouldChangeSelectionWhenViewClicked(R.id.lblAvatar6, R.id.chkAvatar6)
    }

    @Test
    fun shouldChangeSelectionWhenTextView7Clicked() {
        shouldChangeSelectionWhenViewClicked(R.id.lblAvatar7, R.id.chkAvatar7)
    }

    @Test
    fun shouldChangeSelectionWhenTextView8Clicked() {
        shouldChangeSelectionWhenViewClicked(R.id.lblAvatar8, R.id.chkAvatar8)
    }

    @Test
    fun shouldChangeSelectionWhenTextView9Clicked() {
        shouldChangeSelectionWhenViewClicked(R.id.lblAvatar9, R.id.chkAvatar9)
    }

    private fun shouldChangeSelectionWhenViewClicked(@IdRes clickedViewResId: Int, @IdRes selectedCheckBoxResId: Int) {
        onView(withId(clickedViewResId)).perform(click())
        onView(withId(selectedCheckBoxResId)).check(matches(isChecked()))
        onView(withId(R.id.chkAvatar2)).check(matches(not<View>(isChecked())))
    }

    @Test
    fun shouldChkAvatar1KeepCheckedWhenAlreadyChecked() {
        shouldKeepCheckedWhenAlreadyChecked(R.id.chkAvatar1)
    }

    @Test
    fun shouldChkAvatar2KeepCheckedWhenAlreadyChecked() {
        onView(withId(R.id.chkAvatar2)).perform(click())
        onView(withId(R.id.chkAvatar2)).check(matches(isChecked()))
    }

    @Test
    fun shouldChkAvatar3KeepCheckedWhenAlreadyChecked() {
        shouldKeepCheckedWhenAlreadyChecked(R.id.chkAvatar3)
    }

    @Test
    fun shouldChkAvatar4KeepCheckedWhenAlreadyChecked() {
        shouldKeepCheckedWhenAlreadyChecked(R.id.chkAvatar4)
    }

    @Test
    fun shouldChkAvatar5KeepCheckedWhenAlreadyChecked() {
        shouldKeepCheckedWhenAlreadyChecked(R.id.chkAvatar5)
    }

    @Test
    fun shouldChkAvatar6KeepCheckedWhenAlreadyChecked() {
        shouldKeepCheckedWhenAlreadyChecked(R.id.chkAvatar6)
    }

    @Test
    fun shouldChkAvatar7KeepCheckedWhenAlreadyChecked() {
        shouldKeepCheckedWhenAlreadyChecked(R.id.chkAvatar7)
    }

    @Test
    fun shouldChkAvatar8KeepCheckedWhenAlreadyChecked() {
        shouldKeepCheckedWhenAlreadyChecked(R.id.chkAvatar8)
    }

    @Test
    fun shouldChkAvatar19eepCheckedWhenAlreadyChecked() {
        shouldKeepCheckedWhenAlreadyChecked(R.id.chkAvatar9)
    }

    private fun shouldKeepCheckedWhenAlreadyChecked(@IdRes selectedCheckBoxResId: Int) {
        onView(withId(selectedCheckBoxResId)).perform(click())
        onView(withId(selectedCheckBoxResId)).perform(click())
        onView(withId(selectedCheckBoxResId)).check(matches(isChecked()))
    }

}
