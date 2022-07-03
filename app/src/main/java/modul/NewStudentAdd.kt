package modul

class NewStudentAdd {
    var new_add_id:Int?=null
    var new_add_familya:String?=null
    var new_add_ism:String?=null
    var new_add_otasi:String?=null
    var new_add_date:String?=null
    var intent_groupname:String?=null

    constructor(
        new_add_id: Int?,
        new_add_familya: String?,
        new_add_ism: String?,
        new_add_otasi: String?,
        new_add_date: String?,
        intent_groupname: String?
    ) {
        this.new_add_id = new_add_id
        this.new_add_familya = new_add_familya
        this.new_add_ism = new_add_ism
        this.new_add_otasi = new_add_otasi
        this.new_add_date = new_add_date
        this.intent_groupname = intent_groupname
    }

    constructor(
        new_add_familya: String?,
        new_add_ism: String?,
        new_add_otasi: String?,
        new_add_date: String?,
        intent_groupname: String?
    ) {
        this.new_add_familya = new_add_familya
        this.new_add_ism = new_add_ism
        this.new_add_otasi = new_add_otasi
        this.new_add_date = new_add_date
        this.intent_groupname = intent_groupname
    }

    constructor()


}