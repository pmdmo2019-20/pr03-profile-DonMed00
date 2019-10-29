package es.iessaladillo.pedrojoya.profile.data.local.entity


class Avatar {
    var id: Int = 0
    var imageResId: Int = 0
    var name: String = ""

    constructor(id: Int, imageResId: Int, name: String) {
        this.id = id
        this.imageResId = imageResId
        this.name = name
    }

}
