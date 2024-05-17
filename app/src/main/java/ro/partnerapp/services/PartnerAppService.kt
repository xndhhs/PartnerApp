package ro.partnerapp.services

import ro.partnerapp.model.PartnerModel

interface PartnerAppService {

    suspend fun getPartnerList(): List<PartnerModel>
}
