package rs.ac.metropolitan.dz_14.views

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import rs.ac.metropolitan.dz_14.business.UseCase
import rs.ac.metropolitan.dz_14.data.Activity
import rs.ac.metropolitan.dz_14.data.Company

class AppViewModel: ViewModel() {

    private val business = UseCase()
    private var activity: Activity by mutableStateOf(Activity.OTHER)
    var granted = mutableStateOf(false)
    var tabIndex by mutableStateOf(0)

    private var _companies = MutableLiveData<List<Company>>()
    val companies: LiveData<List<Company>>
        get() = _companies

    fun loadCompanies(){
        GlobalScope.launch {
            business.load()
            business.companiesFlow.collect{value -> println(value) }
            MainScope().launch {
                business.companiesFlow.collect{
                    _companies.value = it
                }
            }
        }
    }

    fun setTabActivity(activity: Activity){
        this.activity = activity
        MainScope().launch {
            business.loadCompaniesForActivity(activity)
        }
    }

}