package ro.partnerapp.ui.partner_app.main_screen

import ro.partnerapp.model.PartnerModel

data class MainUiState(
    var partnerList: List<PartnerModel>,
    val ratingsList: List<RatingsList>,
    var selectedPartner: PartnerModel?,
    var isServerError: Boolean = false
)

data class RatingsList(
    val rating: Int,
    val partners: List<PartnerModel>
)