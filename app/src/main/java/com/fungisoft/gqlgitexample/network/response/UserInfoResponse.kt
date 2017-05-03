package com.fungisoft.gqlgitexample.network.response

class UserInfoResponse {
    @com.google.gson.annotations.SerializedName("data")
    var userData: UserData? = null
}

class UserData {
    var viewer: UserInfo? = null
}

class UserInfo {
    var id: String? = null
    var login: String? = null
    var name: String? = null

    override fun toString(): String {
        return "UserInfo(id=$id, login=$login, name=$name)"
    }
}
