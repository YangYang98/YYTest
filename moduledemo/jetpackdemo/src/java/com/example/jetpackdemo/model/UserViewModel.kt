package java.com.example.jetpackdemo.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.jetpackdemo.BR
import java.com.example.jetpackdemo.data.User


/**
 * 双向绑定
 *
 * Create by Yang Yang on 2023/4/20
 */
class UserViewModel constructor(var user: User) : BaseObservable() {

    @Bindable
    fun getUserName(): String? {
        return user.userName
    }

    fun setUserName(userName: String?) {
        if (!userName.isNullOrEmpty() && userName != user.userName) {
            user.userName = userName
            notifyPropertyChanged(BR.userName)
        }
    }
}