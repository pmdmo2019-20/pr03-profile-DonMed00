package es.iessaladillo.pedrojoya.profile.ui.main
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.profile.data.local.Database
import es.iessaladillo.pedrojoya.profile.data.local.entity.Avatar


class ProfileActivityViewModel: ViewModel() {
     var avatar: Avatar = Database.queryDefaultAvatar()

}