package rs.ac.metropolitan.dz_14.net

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import rs.ac.metropolitan.dz_14.data.Company

interface ApiService {

    @GET(Constants.ACTIVITY_URL)
    suspend fun getAll(): List<Company>
    @POST(Constants.ACTIVITY_URL)
    suspend fun add(@Body company: Company)
    @DELETE(Constants.ACTIVITY_URL+"/{id}")
    suspend fun delete(@Path("id") id: String)

}