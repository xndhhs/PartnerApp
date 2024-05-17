package ro.partnerapp.services

import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import okhttp3.OkHttpClient
import org.koin.android.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientFactory {
    fun create(): Retrofit {
        val builder = OkHttpClient.Builder()
            builder.addInterceptor(OkHttpProfilerInterceptor())
        val client = builder.build()
        return Retrofit.Builder()
            .baseUrl("https://662f244a43b6a7dce30e7f2a.mockapi.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}