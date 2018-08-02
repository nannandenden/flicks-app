package com.android.nanden.flickerapp.search

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.*
import com.android.nanden.flickerapp.R
import com.android.nanden.flickerapp.data.GalleryItem
import kotlinx.android.synthetic.main.fragment_search.*

/**
 * SearchPhotoFragment
 */
class SearchPhotoFragment: Fragment(), SearchPhotoContract.View {

    override lateinit var presenter: SearchPhotoContract.Presenter

    private lateinit var adapter: SearchPhotoAdapter
    private lateinit var galleryItems: ArrayList<GalleryItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View?
        = inflater?.inflate(R.layout.fragment_search, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.start()
        setupView()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        if (menu != null) {
            menu.clear()
            inflater?.inflate(R.menu.photo_search_menu, menu)
            val searchItem = menu.findItem(R.id.menuSearch)
            val searchView = searchItem.actionView as SearchView
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    return false
                }
                override fun onQueryTextChange(p0: String?): Boolean {
                    if (p0 != null && p0.length >= 3) presenter.searchPhoto(p0)
                    return true
                }
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unSubscribe()
    }

    override fun displayResult(newItems: List<GalleryItem>) {
        galleryItems.clear()
        galleryItems.addAll(newItems)
        adapter.notifyItemChanged(0, galleryItems.size - 1)
        rvPhoto.scrollToPosition(0)
    }

    private fun init() {
        setHasOptionsMenu(true)
        galleryItems = ArrayList()
        adapter = SearchPhotoAdapter(galleryItems, context)
    }

    private fun setupView() {
        rvPhoto.adapter = adapter
        rvPhoto.layoutManager = LinearLayoutManager(context)
    }

    companion object {
        fun newInstance() = SearchPhotoFragment()
    }
}

