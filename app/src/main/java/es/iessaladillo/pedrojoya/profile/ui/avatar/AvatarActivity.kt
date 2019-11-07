package es.iessaladillo.pedrojoya.profile.ui.avatar

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import es.iessaladillo.pedrojoya.profile.R
import es.iessaladillo.pedrojoya.profile.data.local.Database
import es.iessaladillo.pedrojoya.profile.data.local.entity.Avatar
import es.iessaladillo.pedrojoya.profile.utils.toast
import kotlinx.android.synthetic.main.avatar_activity.*

class AvatarActivity : AppCompatActivity() {
    private val database = Database.queryAllAvatars()
    private val listCheckbox: ArrayList<CheckBox> = ArrayList()

    lateinit var avatar: Avatar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.avatar_activity)
        setupViews()
    }

    /**
     * Init the activity and their elements
     */
    private fun setupViews() {
        receiveIntent()
        initCheckbox()
        initAvatar()

    }

    /**
     * Add the checkboxs to a list, late call their checkedCheckbox() when click
     */
    private fun initCheckbox() {
        listCheckbox.add(chkAvatar1)
        listCheckbox.add(chkAvatar2)
        listCheckbox.add(chkAvatar3)
        listCheckbox.add(chkAvatar4)
        listCheckbox.add(chkAvatar5)
        listCheckbox.add(chkAvatar6)
        listCheckbox.add(chkAvatar7)
        listCheckbox.add(chkAvatar8)
        listCheckbox.add(chkAvatar9)
        for (item in listCheckbox) {
            item.setOnClickListener { checkedCheckBox(item) }
        }

    }

    /**
     * @param item CheckBox
     * Check if checkbox is enabled,if not, enable it, and disable the rest
     */
    private fun checkedCheckBox(item: CheckBox) {
        for (itemAux in listCheckbox) {
            if (itemAux != item) {
                if (itemAux.isChecked) {
                    itemAux.isChecked = false
                }
            } else {
                itemAux.isChecked = true
            }
        }

    }

    /**
     * This receive intent of avatar
     */
    private fun receiveIntent() {
        if (intent != null && intent.hasExtra(EXTRA_AVATAR) && intent.extras.getParcelable<Avatar>(
                EXTRA_AVATAR
            ) != null
        ) {
            avatar = intent.getParcelableExtra(EXTRA_AVATAR)
        } else {
            this.toast("Receive Data Failed")
        }
    }

    /**
     * Init the differents images with avatars
     */
    private fun initAvatar() {
        startAvatars(imgAvatar1, lblAvatar1, 0)
        startAvatars(imgAvatar2, lblAvatar2, 1)
        startAvatars(imgAvatar3, lblAvatar3, 2)
        startAvatars(imgAvatar4, lblAvatar4, 3)
        startAvatars(imgAvatar5, lblAvatar5, 4)
        startAvatars(imgAvatar6, lblAvatar6, 5)
        startAvatars(imgAvatar7, lblAvatar7, 6)
        startAvatars(imgAvatar8, lblAvatar8, 7)
        startAvatars(imgAvatar9, lblAvatar9, 8)
        selectInitAvatar()

    }

    /**
     * Select the avatar that is received
     */
    private fun selectInitAvatar() {
        for (item in database) {
            if (item.imageResId == avatar.imageResId) {
                listCheckbox[item.id - 1].isChecked = !listCheckbox[item.id - 1].isChecked
            }
        }
    }

    /**
     * @param imgAvatar ImageView
     * @param lblAvatar TextView
     * @param i Int
     * Assigned for the img, their values of the avatars
     */
    private fun startAvatars(imgAvatar: ImageView, lblAvatar: TextView, i: Int) {
        val avatar: Avatar = database[i]
        imgAvatar.setImageResource(avatar.imageResId)
        lblAvatar.text = avatar.name

    }

    /**
     *Return the avatar choosed to the first activity
     */
    private fun selectAvatar() {
        chooseAvatar()
        setActivityResult(avatar)
        finish()
    }

    /**
     * Depend of the checkbox enabled, choose a avatar
     */
    private fun chooseAvatar() {
        for ((i, item) in listCheckbox.withIndex()) {
            if (item.isChecked) {
                avatar = database[i]
            }
        }
    }

    /**
     * @param avatar Avatar
     * Receive the avatar
     */
    private fun setActivityResult(avatar: Avatar) {
        val result = Intent().putExtras(
            bundleOf(EXTRA_AVATAR to avatar)
        )
        setResult(RESULT_OK, result)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.avatar_activity, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.mnuSelect) {
            selectAvatar()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {

        const val EXTRA_AVATAR = "EXTRA_AVATAR"
    }
}