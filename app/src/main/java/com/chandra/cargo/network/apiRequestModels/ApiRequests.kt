package com.chandra.cargo.network.apiRequestModels

data class LoginRequest(val mobile: String,val isOfficial: Int,val password: String)
data class CitizenLoginRequest(val mobile: String,val isOfficial: Int)
data class SignUpRequest(val firstName: String,val lastName: String,val mobile: String,val email: String,val gender: String,val block_id: Int,val village_id: Int,val district_id: Int)
data class GrievanceListRequest(val type: Int)
data class CheckLoginRequest(val isOfficial: Int)

