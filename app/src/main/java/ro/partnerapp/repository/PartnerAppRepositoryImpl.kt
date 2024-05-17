package ro.partnerapp.repository

import ro.partnerapp.model.PartnerModel
import ro.partnerapp.services.PartnerAppService

class PartnerAppRepositoryImpl(
    private val partnerAppService: PartnerAppService
) : PartnerAppRepository {

    override suspend fun getPartnerList(): List<PartnerModel> =
        partnerAppService.getPartnerList()
}