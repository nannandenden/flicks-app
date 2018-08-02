package com.android.nanden.flickerapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.android.nanden.flickerapp.search.*
import com.android.nanden.flickerapp.services.FlickerApiService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    private lateinit var dataSource: SearchPhotoDataSource

    private lateinit var fragment: SearchPhotoContract.View

    private lateinit var presenter: SearchPhotoContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        // instantiate retrofit
        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

        // instantiate service
        val apiService = retrofit.create(FlickerApiService::class.java)

        dataSource = SearchPhotoRepository(apiService)
        fragment = SearchPhotoFragment.newInstance()
        presenter = SearchPhotoPresenter(dataSource, fragment)

        // add fragment
        val ft = supportFragmentManager.beginTransaction()
        ft.add(R.id.flContainer, fragment as SearchPhotoFragment)
        ft.commit()

    }


}
