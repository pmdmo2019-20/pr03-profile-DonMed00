package es.iessaladillo.pedrojoya.profile.ui.main


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import es.iessaladillo.pedrojoya.profile.R
import es.iessaladillo.pedrojoya.profile.base.DrawableMatcher
import es.iessaladillo.pedrojoya.profile.data.local.Database
import org.hamcrest.Matchers.isEmptyOrNullString
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class ProfileActivityTest {

    @get:Rule
    val testRule = IntentsTestRule(ProfileActivity::class.java)

    @Before
    fun setup() {
        onView(withId(R.id.txtName)).perform(closeSoftKeyboard())
    }

    // Initial state.

    @Test
    fun shouldNameHasFocusInitially() {
        onView(withId(R.id.txtName)).check(matches(hasFocus()))
    }

    @Test
    fun shouldAvatarHasDefaultOneInitially() {
        val avatar = Database.queryDefaultAvatar()
        onView(withId(R.id.imgAvatar))
            .check(matches(DrawableMatcher(avatar.imageResId)))
        onView(withId(R.id.lblAvatar)).check(matches(withText(avatar.name)))
    }

    // EditText should show error when content is invalid.

    @Test
    fun shouldNameEditTextShowErrorWhenInvalidData() {
        onView(withId(R.id.txtName)).perform(click(), closeSoftKeyboard(), replaceText(""))
        onView(withId(R.id.mnuSave)).perform(click())
        onView(withId(R.id.txtName)).check(
            matches(
                hasErrorText(
                    testRule.activity.getString(R.string.profile_invalid_name)
                )
            )
        )
    }

    @Test
    fun shouldEmailEditTextShowErrorWhenInvalidData() {
        onView(withId(R.id.txtName)).perform(click(), closeSoftKeyboard(), replaceText("test"))
        onView(withId(R.id.txtEmail)).perform(click(), closeSoftKeyboard(), replaceText("test"))
        onView(withId(R.id.mnuSave)).perform(click())
        onView(withId(R.id.txtEmail)).check(
            matches(
                hasErrorText(
                    testRule.activity.getString(R.string.profile_invalid_email)
                )
            )
        )
    }

    @Test
    fun shouldPhonenumberEditTextShowErrorWhenInvalidData() {
        onView(withId(R.id.txtName)).perform(click(), closeSoftKeyboard(), replaceText("test"))
        onView(withId(R.id.txtEmail)).perform(click(), closeSoftKeyboard(), replaceText("test@test.com"))
        onView(withId(R.id.txtPhonenumber)).perform(click(), closeSoftKeyboard(), replaceText("1"))
        onView(withId(R.id.mnuSave)).perform(click())
        onView(withId(R.id.txtPhonenumber)).check(
            matches(
                hasErrorText(
                    testRule.activity.getString(R.string.profile_invalid_phonenumber)
                )
            )
        )
    }

    @Test
    fun shouldAddressEditTextShowErrorWhenInvalidData() {
        onView(withId(R.id.txtName)).perform(click(), closeSoftKeyboard(), replaceText("test"))
        onView(withId(R.id.txtEmail)).perform(click(), closeSoftKeyboard(), replaceText("test@test.com"))
        onView(withId(R.id.txtPhonenumber)).perform(click(), closeSoftKeyboard(), replaceText("666666666"))
        onView(withId(R.id.txtAddress)).perform(click(), closeSoftKeyboard(), replaceText(""))
        onView(withId(R.id.mnuSave)).perform(click())
        onView(withId(R.id.txtAddress)).check(
            matches(
                hasErrorText(
                    testRule.activity.getString(R.string.profile_invalid_address)
                )
            )
        )
    }

    @Test
    fun shouldWebEditTextShowErrorWhenInvalidData() {
        onView(withId(R.id.txtName)).perform(click(), closeSoftKeyboard(), replaceText("test"))
        onView(withId(R.id.txtEmail)).perform(click(), closeSoftKeyboard(), replaceText("test@test.com"))
        onView(withId(R.id.txtPhonenumber)).perform(click(), closeSoftKeyboard(), replaceText("666666666"))
        onView(withId(R.id.txtAddress)).perform(click(), closeSoftKeyboard(), replaceText("test"))
        onView(withId(R.id.txtWeb)).perform(click(), closeSoftKeyboard(), replaceText("test"))
        onView(withId(R.id.mnuSave)).perform(click())
        onView(withId(R.id.txtWeb)).check(
            matches(
                hasErrorText(
                    testRule.activity.getString(R.string.profile_invalid_web)
                )
            )
        )
    }

    // EditText should show no error when content is valid.

    @Test
    fun shouldNameEditTextShowNoErrorWhenValidData() {
        onView(withId(R.id.txtName)).perform(click(), closeSoftKeyboard(), replaceText("test"))
        onView(withId(R.id.txtName)).check(matches(hasErrorText(isEmptyOrNullString())))
    }

    @Test
    fun shouldEmailEditTextShowNoErrorWhenValidData() {
        onView(withId(R.id.txtEmail)).perform(
            click(), closeSoftKeyboard(),
            replaceText("test@test.com")
        )
        onView(withId(R.id.txtEmail)).check(matches(hasErrorText(isEmptyOrNullString())))
    }

    @Test
    fun shouldPhonenumberEditTextShowNoErrorWhenValidData() {
        onView(withId(R.id.txtPhonenumber)).perform(
            click(), closeSoftKeyboard(),
            replaceText("666666666")
        )
        onView(withId(R.id.txtPhonenumber)).check(matches(hasErrorText(isEmptyOrNullString())))
    }

    @Test
    fun shouldAddressEditTextShowNoErrorWhenValidData() {
        onView(withId(R.id.txtAddress)).perform(click(), closeSoftKeyboard(), replaceText("test"))
        onView(withId(R.id.txtAddress)).check(matches(hasErrorText(isEmptyOrNullString())))
    }

    @Test
    fun shouldWebEditTextShowNoErrorWhenValidData() {
        onView(withId(R.id.txtWeb)).perform(
            click(), closeSoftKeyboard(),
            replaceText("http://www.test.com")
        )
        onView(withId(R.id.txtWeb)).check(matches(hasErrorText(isEmptyOrNullString())))
    }

    // ImeOptions.

    @Test
    fun shouldNameEditTextGoForwardWhenImeOptionsClicked() {
        onView(withId(R.id.txtName)).perform(
            typeText("test"),
            pressImeActionButton()
        )
        onView(withId(R.id.txtEmail)).perform(closeSoftKeyboard()).check(matches(hasFocus()))
    }

    @Test
    fun shouldEmailEditTextGoForwardWhenImeOptionsClicked() {
        onView(withId(R.id.txtEmail)).perform(
            typeText("test"),
            pressImeActionButton()
        )
        onView(withId(R.id.txtPhonenumber)).perform(closeSoftKeyboard()).check(matches(hasFocus()))
    }

    @Test
    fun shouldPhonenumberEditTextGoForwardWhenImeOptionsClicked() {
        onView(withId(R.id.txtPhonenumber)).perform(
            typeText("66666666"),
            pressImeActionButton()
        )
        onView(withId(R.id.txtAddress)).perform(closeSoftKeyboard()).check(matches(hasFocus()))
    }

    @Test
    fun shouldAddressEditTextGoForwardWhenImeOptionsClicked() {
        onView(withId(R.id.txtAddress)).perform(
            typeText("test"),
            pressImeActionButton()
        )
        onView(withId(R.id.txtWeb)).perform(closeSoftKeyboard()).check(matches(hasFocus()))
    }

}
