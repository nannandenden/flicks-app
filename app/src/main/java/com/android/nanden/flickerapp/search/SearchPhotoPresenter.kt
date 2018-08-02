package com.android.nanden.flickerapp.search

import com.android.nanden.flickerapp.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * - SearchPhotoPresenter implementation class for [SearchPhotoContract.Presenter]
 */

class SearchPhotoPresenter(private val dataSource: SearchPhotoDataSource, private var view: SearchPhotoContract.View): SearchPhotoContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    init {
        view.presenter = this
    }

    override fun start() {
        compositeDisposable.clear()
    }

    override fun searchPhoto(keyword: CharSequence) {
        val paramMap = generateParameterMap(keyword.toString())
        val disposable = dataSource.getPhotos(paramMap)
                .debounce(250, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            view.displayResult(it.photoMeta.galleryItems)
                        },
                        {
                            println("$LOG_TAG error: ${it.localizedMessage}")
                        },
                        {
                            println("$LOG_TAG complete the searchPhoto call")
                        }
                )
        compositeDisposable.add(disposable)
    }

    private fun generateParameterMap(keyword: String): Map<String, String> {
        val paramMap = HashMap<String, String>()
        paramMap["method"] = VALUE_METHOD
        paramMap["api_key"] = API_KEY
        paramMap["format"] = VALUE_FORMAT
        paramMap["nojsoncallback"] = VALUE_JSON_CALLBACK
        paramMap["extras"] = VALUE_EXTRAS
        paramMap["text"] = keyword
        return paramMap
    }

    override fun unSubscribe() {
        compositeDisposable.clear()
    }

    companion object {
        val LOG_TAG: String = SearchPhotoPresenter::class.java.simpleName
    }

}
