package ro.partnerapp.interactors

import ro.partnerapp.model.PartnerModel

interface PartnerAppUseCase {

    suspend fun getPartnerList(): List<PartnerModel>
}