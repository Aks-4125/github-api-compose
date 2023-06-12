package ext.aks4125.githubapicompose.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserApiModel(
    @field:Json(name = "login") var userId: String,
    @field:Json(name = "id") var id: Int,
    @field:Json(name = "node_id") var nodeId: String? = null,
    @field:Json(name = "avatar_url") var avatarUrl: String,
    @field:Json(name = "name") var name: String?,
    @field:Json(name = "bio") var bio: String? = null,
    @field:Json(name = "followers") var followers: Int?,
    @field:Json(name = "following") var following: Int?,
    @field:Json(name = "message") val message: String?
)
