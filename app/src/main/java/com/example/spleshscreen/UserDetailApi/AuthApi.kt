import com.example.spleshscreen.UserDetailApi.LoginRequest
import com.example.spleshscreen.UserDetailApi.LoginResponse
import com.example.spleshscreen.UserDetailApi.UserDetails
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthApi {
    @POST("login")
    suspend fun login(@Body request: LoginRequest): LoginResponse


}

interface MeApi {
    @GET("me")
    suspend fun getUserDetails(@Header("Authorization") token: String): UserDetails
}