package com.android.nanden.flickerapp.services

import com.android.nanden.flickerapp.data.GetPhotosResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface FlickerApiService {

    @GET("rest")
    fun getPhotos(@QueryMap paramMap: Map<String, String>): Flowable<GetPhotosResponse>
}