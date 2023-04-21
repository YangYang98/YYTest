package java.com.example.jetpackdemo.model

import android.util.Log
import androidx.databinding.ObservableField
import java.com.example.jetpackdemo.data.User


/**
 * 双向绑定ObservableField
 *
 * Create by Yang Yang on 2023/4/20
 */
class UserViewModel2 constructor(var user: User) {

    private val userObservableField: ObservableField<User> = ObservableField(user)

    init {
        userObservableField.set(user)
    }

    fun getUserName(): String? {
        return userObservableField.get()?.userName
    }

    fun setUserName(userName: String?) {
        if (!userName.isNullOrEmpty() && userName != userObservableField.get()?.userName) {
            userObservableField.get()?.userName = userName
            Log.d("BBBBBB", "setUserName:$userName")
        }
    }

}