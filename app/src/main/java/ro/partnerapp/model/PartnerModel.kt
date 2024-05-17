package ro.partnerapp.model

import com.google.gson.annotations.SerializedName

data class PartnerModel(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("rating") val rating: Int? = null,
    @SerializedName("image_url") val imageUrl: String? = null,
)
