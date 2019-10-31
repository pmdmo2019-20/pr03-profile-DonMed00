package es.iessaladillo.pedrojoya.profile.ui.main


import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import es.iessaladillo.pedrojoya.profile.data.local.entity.Avatar
class ProfileActivityViewModel: ViewModel() {

    private var avatar = MutableLiveData<Avatar>()

}