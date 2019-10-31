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
import kotlinx.android.synthetic.main.avatar_activity.*

class AvatarActivity : AppCompatActivity() {
    val database = Database.queryAllAvatars()
    val listCheckbox: ArrayList<CheckBox> = ArrayList()

    lateinit var avatar: Avatar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.avatar_activity)
        setupViews()
//Test fail, hacer de nuevo para que funcione con imagenes
    }

    private fun setupViews() {
        receiveIntent()
        initCheckbox()
        initAvatar()

    }

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


    private fun receiveIntent() {
        if (intent != null && intent.hasExtra(EXTRA_AVATAR) && intent.extras.getParcelable<Avatar>(
                EXTRA_AVATAR
            ) != null
        ) {
            avatar = intent.getParcelableExtra(EXTRA_AVATAR)
        }
        //Excepcion


    }

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

    private fun selectInitAvatar() {
        for (item in database) {
            if (item.imageResId == avatar.imageResId) {
                listCheckbox[item.id - 1].isChecked = !listCheckbox[item.id - 1].isChecked
            }
        }
    }

    private fun startAvatars(imgAvatar: ImageView, lblAvatar: TextView, i: Int) {
        val avatar: Avatar = database[i]
        imgAvatar.setImageResource(avatar.imageResId)
        lblAvatar.text = avatar.name

    }

    private fun selectAvatar() {
        chooseAvatar()
        setActivityResult(avatar)
        finish()
    }

    private fun chooseAvatar() {
        var i: Int =0
        for (item in listCheckbox){
            if(item.isChecked){
                avatar = database[i]
            }
            i++
        }
    }

    private fun setActivityResult(avatar: Avatar) {
        val result = Intent().putExtras(
            bundleOf(EXTRA_AVATAR to avatar))
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