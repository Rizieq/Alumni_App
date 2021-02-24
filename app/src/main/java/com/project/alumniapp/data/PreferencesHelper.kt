package com.project.alumniapp.data

import com.orhanobut.hawk.Hawk
import com.project.alumniapp.model.ResponseLogin
import com.project.alumniapp.model.ResponseRegister

class PreferencesHelper {
    fun hasLogin(login: Boolean) {
        Hawk.put(KEY_HAS_LOGIN, login)
    }

    fun skipLogin(skip: Boolean) {
        Hawk.put(KEY_SKIP_LOGIN, skip)
    }

    fun saveLogin(login: ResponseLogin?) {
        Hawk.put(KEY_LOGIN, login)
    }

//    fun saveUser(user: UserDao?) {
//        Hawk.put(KEY_USER, user)
//    }
    fun saveToken(token: String) {
        Hawk.put(KEY_TOKEN, token)
    }
    fun getHasLogin(): Boolean = Hawk.get(KEY_HAS_LOGIN, false)

    fun isSkipLogin(): Boolean = Hawk.get(KEY_SKIP_LOGIN, false)

    fun getLoginInfo(): ResponseRegister? = Hawk.get(KEY_LOGIN)

    fun getToken(): String = Hawk.get(KEY_TOKEN, "")

    fun clear() {
        Hawk.deleteAll()
    }
    companion object {

        const val KEY_LOGIN =  ".Login"
        const val KEY_TOKEN =  ".Token"
        const val KEY_USER =  ".User"
        const val KEY_HAS_LOGIN =  ".HasLogin"
        const val KEY_SKIP_LOGIN =  ".SkipLogin"
        const val KEY_LAST_FCM_TOKEN =  ".LastFCMToken"
        const val KEY_LAST_FCM_NOTIF =  ".LastNotif"
        const val KEY_NOTIFICATION_TOKEN =  ".NotificationToken"
    }

}