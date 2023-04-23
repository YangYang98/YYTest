package java.com.example.jetpackdemo.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


/**
 * Create by Yang Yang on 2023/4/23
 */
class DBAndVMAndLDDemoViewModel : ViewModel() {

    var aTeamScore: MutableLiveData<Int> = MutableLiveData()
    var bTeamScore: MutableLiveData<Int> = MutableLiveData()

    private var aLastScore: Int = 0
    private var bLastScore: Int = 0

    init {
        aTeamScore.value = 0
        bTeamScore.value = 0
    }

    fun addATeamScore(interval: Int) {
        saveLastScore()
        aTeamScore.value = aTeamScore.value?.plus(interval)
    }

    fun addBTeamScore(interval: Int) {
        saveLastScore()
        bTeamScore.value = bTeamScore.value?.plus(interval)
    }

    fun undo() {
        aTeamScore.value = aLastScore
        bTeamScore.value = bLastScore
    }

    fun reset() {
        aTeamScore.value = 0
        bTeamScore.value = 0
    }

    private fun saveLastScore() {
        aLastScore = aTeamScore.value ?: 0
        bLastScore = bTeamScore.value ?: 0
    }

}