package es.iessaladillo.pedrojoya.profile.ui.avatar

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.profile.R
import es.iessaladillo.pedrojoya.profile.data.local.Database
import es.iessaladillo.pedrojoya.profile.data.local.entity.Avatar
import kotlinx.android.synthetic.main.avatar_activity.*

class AvatarActivity : AppCompatActivity() {
    val database = Database.queryAllAvatars()
    var name : String = ""
    var image : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.avatar_activity)
        setupViews()

    }

    private fun setupViews() {
        receiveIntent()
        initAvatar()
    }

    private fun receiveIntent() {
        var datos: Bundle = this.getIntent().getExtras();
        image = datos.getInt("extra_image")
        name = datos.getString("extra_name");

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
        //imgAvatar9.setImageResource(image)
        lblAvatar9.setText(name)
    }

    private fun startAvatars(imgAvatar: ImageView, lblAvatar: TextView, i: Int) {
        val avatar: Avatar = database.get(i)
        imgAvatar.setImageResource(avatar.imageResId)
        lblAvatar.setText(avatar.name)


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.avatar_activity, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.mnuSelect) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {

        const val EXTRA_AVATAR = "EXTRA_AVATAR"
    }
}