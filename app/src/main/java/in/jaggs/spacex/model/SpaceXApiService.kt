package `in`.jaggs.spacex.model

import io.reactivex.Observable
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface SpaceXApiService {
    @GET("info")
    fun info(
        @Query("filter") filter: String
    ): Observable<SpaceXApi.Info>

    companion object {
        fun create(): SpaceXApiService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create()
                )
                .addConverterFactory(
                    GsonConverterFactory.create()
                )
                .baseUrl("https://api.spacexdata.com/v3/")
                .build()

            return retrofit.create(SpaceXApiService::class.java)
        }
    }
}