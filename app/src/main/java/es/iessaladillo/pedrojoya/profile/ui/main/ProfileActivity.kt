package es.iessaladillo.pedrojoya.profile.ui.main


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.profile.R
import es.iessaladillo.pedrojoya.profile.ui.avatar.AvatarActivity
import es.iessaladillo.pedrojoya.profile.ui.avatar.AvatarActivity.Companion.EXTRA_AVATAR
import es.iessaladillo.pedrojoya.profile.utils.*
import kotlinx.android.synthetic.main.profile_activity.*

class ProfileActivity : AppCompatActivity() {
    private val viewModel: ProfileActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_activity)
        setupViews()
    }

    /**
     * Init the activity and their elements
     */
    private fun setupViews() {
        txtName.requestFocus()
        refreshAvatar()
        imagesOnClicked()
        imgAvatar.setOnClickListener { navegateToAvatar() }
    }


    /**
     * When avatar is clicked, navegate to the other activity, sending the avatar and waiting for result
     */
    private fun navegateToAvatar() {
        val intent = Intent(this, AvatarActivity::class.java)
        intent.putExtra(EXTRA_AVATAR, viewModel.getAvatar())
        startActivityForResult(intent, RC_AVATAR)

    }


    /**
     * When the images are clicked, them call his throwIntent(image).
     */
    private fun imagesOnClicked() {
        imgEmail.setOnClickListener { throwIntent(imgEmail) }
        imgPhonenumber.setOnClickListener { throwIntent(imgPhonenumber) }
        imgAddress.setOnClickListener { throwIntent(imgAddress) }
        imgWeb.setOnClickListener { throwIntent(imgWeb) }

    }

    /**
     * @param img ImageView
     * Depend of the img, do a intent different to the defaults apps.
     */
    private fun throwIntent(img: ImageView) {
        lateinit var intent: Intent
        when (img) {
            imgEmail ->
                intent = newEmailIntent(txtEmail.text.toString())
            imgPhonenumber ->
                intent = newDialIntent(txtPhonenumber.text.toString())
            imgAddress ->
                intent = newSearchInMapIntent(txtAddress.text.toString())
            imgWeb ->
                intent = newViewUriIntent(Uri.parse(txtWeb.text.toString()))
        }

        if (isActivityAvailable(this, intent)) {
            startActivity(intent)
        } else {
            this.toast(getString(R.string.invalid_Intent))
        }
    }

    /**
     * If checkAllEditText(), close the keyboard(if it open) and show a toast with a message
     */
    private fun save() {
        if (checkAllEditText()) {
            imgAvatar.hideSoftKeyboard()
            this.toast("Profile saved")
        }
    }

    /**
     * If all editTexts are ok, return true
     * @return Boolean
     */
    private fun checkAllEditText(): Boolean {
        if (checkEditText(txtName) && checkEditText(txtEmail) && checkEditText(txtPhonenumber) && checkEditText(
                txtAddress
            ) && checkEditText(txtWeb)
        ) return true
        return false
    }

    /**
     * @param editText EditText
     * Depend of editText, check if it´s ok. For example, isn´t empty.
     * @return Boolean
     */
    private fun checkEditText(editText: EditText): Boolean {
        when (editText) {
            txtName ->
                if (editText.text.toString().isBlank()) {
                    editText.error = getString(R.string.profile_invalid_name)
                    editText.requestFocus()
                    return false
                }
            txtEmail ->
                if (!editText.text.toString().isValidEmail()) {
                    editText.error = getString(R.string.profile_invalid_email)
                    editText.requestFocus()
                    return false
                }
            txtPhonenumber ->
                if (!editText.text.toString().isValidPhone()) {
                    editText.error = getString(R.string.profile_invalid_phonenumber)
                    editText.requestFocus()
                    return false
                }
            txtAddress ->
                if (editText.text.toString().isBlank()) {
                    editText.error = getString(R.string.profile_invalid_address)
                    editText.requestFocus()
                    return false
                }
            txtWeb ->
                if (!editText.text.toString().isValidUrl()) {
                    editText.error = getString(R.string.profile_invalid_web)
                    editText.requestFocus()
                    return false
                }
        }

        return true
    }

    /**
     * @param requestCode Int
     * @param resultCode Int
     * @param intent Intent?
     * Override Method: Check if intent is ok and call exctract(intent) and refreshAvatar()
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if (resultCode == RESULT_OK && requestCode == RC_AVATAR && intent != null) {
            extractResult(intent)
            refreshAvatar()
        }
    }

    /**
     * @param intent Intent
     * Gets the avatar in intent
     */
    private fun extractResult(intent: Intent) {
        if (!intent.hasExtra(EXTRA_AVATAR)) {
            throw RuntimeException(
                "AvatarActivity must receive avatar in result intent"
            )
        }
        viewModel.setAvatar(intent.getParcelableExtra(EXTRA_AVATAR))

    }

    /**
     * Adquires the name and resId of avatar actual
     */
    private fun refreshAvatar() {
        imgAvatar.setImageResource(viewModel.getAvatar().imageResId)
        lblAvatar.text = viewModel.getAvatar().name

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.profile_activity, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.mnuSave) {
            save()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val RC_AVATAR: Int = 1
    }

}
