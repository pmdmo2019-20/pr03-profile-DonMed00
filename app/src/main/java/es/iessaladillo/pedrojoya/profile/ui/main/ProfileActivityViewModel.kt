package es.iessaladillo.pedrojoya.profile.ui.main
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.profile.data.local.Database
import es.iessaladillo.pedrojoya.profile.data.local.entity.Avatar


class ProfileActivityViewModel: ViewModel() {
      private var avatar = Database.queryDefaultAvatar()

     fun getAvatar(): Avatar {
          return avatar
     }
     fun setAvatar(avatar: Avatar){
          this.avatar = avatar
     }


}