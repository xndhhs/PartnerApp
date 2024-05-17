package ro.partnerapp.services

import ro.partnerapp.model.PartnerModel

class PartnerAppServiceImpl(
    private val partnerAppAPIService: PartnerAppAPIService
) : PartnerAppService {
    override suspend fun getPartnerList(): List<PartnerModel> =
        partnerAppAPIService.getPartnerList()
}
