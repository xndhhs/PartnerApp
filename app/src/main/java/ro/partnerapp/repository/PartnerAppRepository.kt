package ro.partnerapp.repository

import ro.partnerapp.model.PartnerModel

interface PartnerAppRepository {

    suspend fun getPartnerList(): List<PartnerModel>

}