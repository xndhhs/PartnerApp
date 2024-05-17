package ro.partnerapp.interactors

import ro.partnerapp.model.PartnerModel
import ro.partnerapp.repository.PartnerAppRepository

class PartnerAppUseCaseImpl(
    private val partnerAppRepository: PartnerAppRepository
) : PartnerAppUseCase {

    override suspend fun getPartnerList(): List<PartnerModel> =
        partnerAppRepository.getPartnerList()

}