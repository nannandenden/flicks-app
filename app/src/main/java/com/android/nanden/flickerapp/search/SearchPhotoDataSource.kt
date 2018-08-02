package com.android.nanden.flickerapp.search

import com.android.nanden.flickerapp.data.GetPhotosResponse
import io.reactivex.Flowable

/**
 * - SearchPhotoDataSource: provide data to presenter class
 */

interface SearchPhotoDataSource {

    fun getPhotos(paramMap: Map<String, String>): Flowable<GetPhotosResponse>
}