package ro.partnerapp.services

import retrofit2.http.GET
import retrofit2.http.Query
import ro.partnerapp.model.PartnerModel

interface PartnerAppAPIService {

    @GET("partners")
    suspend fun getPartnerList(): List<PartnerModel>

}