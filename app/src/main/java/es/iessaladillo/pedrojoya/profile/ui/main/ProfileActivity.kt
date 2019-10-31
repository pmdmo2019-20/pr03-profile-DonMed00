package es.iessaladillo.pedrojoya.profile.ui.main


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import es.iessaladillo.pedrojoya.profile.R
import es.iessaladillo.pedrojoya.profile.data.local.Database
import es.iessaladillo.pedrojoya.profile.data.local.entity.Avatar
import es.iessaladillo.pedrojoya.profile.ui.avatar.AvatarActivity
import es.iessaladillo.pedrojoya.profile.ui.avatar.AvatarActivity.Companion.EXTRA_AVATAR
import es.iessaladillo.pedrojoya.profile.utils.*
import kotlinx.android.synthetic.main.profile_activity.*

class ProfileActivity : AppCompatActivity() {
     lateinit var avatar : Avatar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_activity)
        setupViews()

       // restoreInstanceState(savedInstanceState)

    }
    /*override fun onSaveInstanceState(outState: Bundle) {
        if (outState != null) {
            super.onSaveInstanceState(outState)
        }
        outState?.putParcelable(EXTRA_AVATAR,avatar)
    }
    private fun restoreInstanceState(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            avatar = savedInstanceState.getParcelable(EXTRA_AVATAR)
        }
    }*/

    private fun setupViews() {
        txtName.requestFocus()
        avatar = Database.queryDefaultAvatar()
        imgAvatar.setImageResource(avatar.imageResId)
        lblAvatar.setText(avatar.name)
        imgOnClicked()
        imgAvatar.setOnClickListener { navegateToAvatar() }
    }

    private fun navegateToAvatar() {
        //FALLA IMG ID
        val intent = Intent(this, AvatarActivity::class.java)
       // intent.putExtra("name",avatar.name)
       // intent.putExtra("image",avatar.imageResId)
        intent.putExtra(EXTRA_AVATAR,avatar)

        startActivityForResult(intent, RC_AVATAR)

    }


    private fun imgOnClicked() {
        imgEmail.setOnClickListener { throwIntent(imgEmail) }
        imgPhonenumber.setOnClickListener { throwIntent(imgPhonenumber) }
        imgAddress.setOnClickListener { throwIntent(imgAddress) }
        imgWeb.setOnClickListener { throwIntent(imgWeb) }

    }

    private fun throwIntent(img: ImageView) {
        lateinit var intent: Intent
        when (img) {
            imgEmail ->
                intent = newEmailIntent(txtAddress.text.toString())
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

    private fun save() {
        if (checkAllEditText()) {
            this.toast("Profile saved")
        }
    }

    private fun checkAllEditText(): Boolean {
        if (checkEditText(txtName) && checkEditText(txtEmail) && checkEditText(txtPhonenumber) && checkEditText(
                txtAddress
            ) && checkEditText(txtWeb)
        ) return true
        return false
    }

    private fun checkEditText(editText: EditText): Boolean {
        when (editText) {
            txtName ->
                if (editText.text.toString().isBlank()) {
                    editText.error = getString(R.string.profile_invalid_name)
                    return false
                }
            txtEmail ->
                if (!editText.text.toString().isValidEmail()) {
                    editText.error = getString(R.string.profile_invalid_email)
                    return false
                }
            txtPhonenumber ->
                if (!editText.text.toString().isValidPhone()) {
                    editText.error = getString(R.string.profile_invalid_phonenumber)
                    return false
                }
            txtAddress ->
                if (editText.text.toString().isBlank()) {
                    editText.error = getString(R.string.profile_invalid_address)
                    return false
                }
            txtWeb ->
                if (!editText.text.toString().isValidUrl()) {
                    editText.error = getString(R.string.profile_invalid_web)
                    return false
                }
        }
        return true
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if (resultCode == RESULT_OK && requestCode == RC_AVATAR && intent != null) {
            extractResult(intent)
            refreshAvatar()
        }
    }
    private fun extractResult(intent: Intent) {
        if (!intent.hasExtra(EXTRA_AVATAR)) {
            throw RuntimeException(
                "AvatarActivity must receive avatar in result intent")
        }
        avatar = intent.getParcelableExtra(EXTRA_AVATAR)
    }
    private fun refreshAvatar() {
        imgAvatar.setImageResource(avatar.imageResId)
        lblAvatar.setText(avatar.name)
    }

    companion object {
        private const val RC_AVATAR: Int = 1
    }

}
