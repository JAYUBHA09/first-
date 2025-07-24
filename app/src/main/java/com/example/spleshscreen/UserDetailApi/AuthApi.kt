import com.example.spleshscreen.UserDetailApi.LoginRequest
import com.example.spleshscreen.UserDetailApi.LoginResponse
import okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthApi {
    @POST("login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

}