package rs.ac.metropolitan.dz_14.business

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import rs.ac.metropolitan.dz_14.data.Company
import rs.ac.metropolitan.dz_14.net.ApiService
import rs.ac.metropolitan.dz_14.net.RetrofitHelper

class Repository {
    var companiesFlow: Flow<List<Company>> = flowOf(listOf<Company>())

    suspend fun loadCompanies(){
        val apiService = RetrofitHelper.getInstance().create(ApiService::class.java)
        val companies = apiService.getAll()
        companiesFlow = flowOf(companies)
    }

    suspend fun createCompany(company: Company){
        val apiService = RetrofitHelper.getInstance().create(ApiService::class.java)
        val result = apiService.add(company)
    }

    suspend fun deleteCompany(id: String){
        val apiService = RetrofitHelper.getInstance().create(ApiService::class.java)
        val result = apiService.delete(id)
    }
}