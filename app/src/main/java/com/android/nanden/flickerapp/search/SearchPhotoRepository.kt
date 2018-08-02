package com.android.nanden.flickerapp.search

import com.android.nanden.flickerapp.data.GetPhotosResponse
import com.android.nanden.flickerapp.services.FlickerApiService
import io.reactivex.Flowable

/**
 * SearchPhotoRepository - implementation class for [SearchPhotoDataSource]
 */
class SearchPhotoRepository(private val apiService: FlickerApiService): SearchPhotoDataSource {

    override fun getPhotos(paramMap: Map<String, String>): Flowable<GetPhotosResponse> = apiService.getPhotos(paramMap)

}