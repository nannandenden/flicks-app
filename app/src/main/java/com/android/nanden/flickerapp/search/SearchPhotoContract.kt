package com.android.nanden.flickerapp.search

import com.android.nanden.flickerapp.BasePresenter
import com.android.nanden.flickerapp.BaseView
import com.android.nanden.flickerapp.data.GalleryItem

interface SearchPhotoContract {

    interface View: BaseView<Presenter> {
        fun displayResult(newItems: List<GalleryItem>)
    }

    interface Presenter: BasePresenter {

        fun unSubscribe()

        fun searchPhoto(keyword: CharSequence)

    }
}