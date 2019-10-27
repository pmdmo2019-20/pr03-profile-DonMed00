package es.iessaladillo.pedrojoya.profile.ui.main


import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.net.Uri
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.*
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import es.iessaladillo.pedrojoya.profile.R
import es.iessaladillo.pedrojoya.profile.base.DrawableMatcher
import es.iessaladillo.pedrojoya.profile.data.local.entity.Avatar
import es.iessaladillo.pedrojoya.profile.ui.avatar.AvatarActivity
import org.hamcrest.Matchers
import org.hamcrest.core.AllOf.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class ProfileActivityIntentTest {

    @get:Rule
    val testRule = IntentsTestRule(ProfileActivity::class.java)

    @Before
    fun setup() {
        onView(withId(R.id.txtName)).perform(closeSoftKeyboard())
    }

    // Implicit intents

    @Test
    fun shouldEmailSendIntent() {
        onView(withId(R.id.txtEmail)).perform(typeText("baldomero@gmail.com"), closeSoftKeyboard())
        onView(withId(R.id.imgEmail)).perform(click())
        intended(
            Matchers.allOf(hasAction(Intent.ACTION_SENDTO), hasData("mailto:baldomero@gmail.com"))
        )
    }

    @Test
    fun shouldPhonenumberSendIntent() {
        onView(withId(R.id.txtPhonenumber)).perform(typeText("666666666"), closeSoftKeyboard())
        onView(withId(R.id.imgPhonenumber)).perform(click())
        intended(Matchers.allOf(hasAction(Intent.ACTION_DIAL), hasData("tel:666666666")))
    }

    @Test
    fun shouldAddressSendIntent() {
        onView(withId(R.id.txtAddress)).perform(
            typeText("Avda. Duque de Rivas"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.imgAddress)).perform(click())
        intended(
            Matchers.allOf(
                hasAction(Intent.ACTION_VIEW),
                hasData(Uri.parse("geo:0,0?q=Avda. Duque de Rivas"))
            )
        )
    }

    @Test
    fun shouldWebSendIntent() {
        val txtWeb = onView(withId(R.id.txtWeb))
        txtWeb.perform(click(), replaceText("http://www.iessaladillo.es"), closeSoftKeyboard())
        onView(withId(R.id.imgWeb)).perform(click())
        intended(
            allOf(
                hasAction(Intent.ACTION_VIEW),
                hasData(Uri.parse("http://www.iessaladillo.es"))
            )
        )
    }

    // Explicit Intent

    @Test
    fun shouldAvatarSendIntent() {
        onView(withId(R.id.imgAvatar)).perform(click())
        // Check sending intent
        val avatar = Avatar(1, R.drawable.pikachu, "Pikachu")
        intended(
            allOf(
                hasComponent(AvatarActivity::class.java.name),
                hasExtra(AvatarActivity.EXTRA_AVATAR, avatar)
            )
        )
    }

    @Test
    fun shouldReceiveRightIntent() {
        // Setup the result intent. Needed if is not one of your activities.
        val resultData = Intent()
        val avatar = Avatar(2, R.drawable.bulbasur, "Bulbasur")
        resultData.putExtra(AvatarActivity.EXTRA_AVATAR, avatar)
        val result = Instrumentation.ActivityResult(
            Activity.RESULT_OK, resultData
        )
        // We simulate the result intent.
        intending(hasComponent(AvatarActivity::class.java.name)).respondWith(result)

        onView(withId(R.id.imgAvatar)).perform(click())
        // Check result set to views.
        onView(withId(R.id.imgAvatar))
            .check(matches(DrawableMatcher(avatar.imageResId)))
        onView(withId(R.id.lblAvatar)).check(
            matches(withText(avatar.name))
        )
    }

}
