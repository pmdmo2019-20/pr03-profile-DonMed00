package es.iessaladillo.pedrojoya.profile.ui.main


import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toDrawable
import es.iessaladillo.pedrojoya.profile.R
import es.iessaladillo.pedrojoya.profile.data.local.Database
import es.iessaladillo.pedrojoya.profile.data.local.entity.Avatar
import es.iessaladillo.pedrojoya.profile.ui.avatar.AvatarActivity
import es.iessaladillo.pedrojoya.profile.utils.*
import kotlinx.android.synthetic.main.profile_activity.*

class ProfileActivity : AppCompatActivity() {
     lateinit var avatar : Avatar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_activity)
        setupViews()
    }

    private fun setupViews() {
        imgAvatar.setImageResource(Database.queryDefaultAvatar().imageResId)
        lblAvatar.setText(Database.queryDefaultAvatar().name)
        imgOnClicked()
        imgAvatar.setOnClickListener { navegateToAvatar() }
    }

    private fun navegateToAvatar() {
        //FALLA IMG ID
        avatar = Avatar(0,imgAvatar.id,lblAvatar.text.toString())
        var drawable: Drawable = getDrawable(avatar.imageResId)
        val intent = Intent(this, AvatarActivity::class.java)
        intent.putExtra("extra_name",avatar.name)
        intent.putExtra("extra_image",drawable.toString().toInt())
        startActivity(intent)

    }


    private fun imgOnClicked() {
        imgEmail.setOnClickListener { throwIntent(imgEmail) }
        imgPhone.setOnClickListener { throwIntent(imgPhone) }
        imgAddress.setOnClickListener { throwIntent(imgAddress) }
        imgWeb.setOnClickListener { throwIntent(imgWeb) }

    }

    private fun throwIntent(img: ImageView) {
        lateinit var intent: Intent
        when (img) {
            imgEmail ->
                intent = newEmailIntent(txtAddress.text.toString())
            imgPhone ->
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
            this.toast("Avatar saved")
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

}
