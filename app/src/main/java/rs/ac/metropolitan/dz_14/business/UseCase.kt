package rs.ac.metropolitan.dz_14.business

import kotlinx.coroutines.flow.Flow
import rs.ac.metropolitan.dz_14.data.Activity
import rs.ac.metropolitan.dz_14.data.Company

class UseCase {
    private val repository = Repository()
    var companiesFlow: Flow<List<Company>> = repository.companiesFlow

    suspend fun load(){
        repository.loadCompanies()
        companiesFlow = repository.companiesFlow
    }

    suspend fun loadCompaniesForActivity(activity: Activity){
        println("loadCompaniesForActivity $activity")
        companiesFlow = repository.companiesFlow.getPriority(activity)
    }
}