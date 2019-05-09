package net.abm.searchArtist.screen.home

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.AndroidSupportInjection
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

import kotlinx.android.synthetic.main.home_fragment_view.*

import net.abm.searchArtist.R
import net.abm.searchArtist.data.model.album.Album
import net.abm.searchArtist.extensions.observeLiveData
import net.abm.searchArtist.screen.details.DetailsActivity
import net.abm.searchArtist.screen.home.view.MainAdapter
import net.abm.searchArtist.utils.EndlessRecyclerViewScrollListener
import net.abm.searchArtist.utils.Utils
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class HomeFragment : Fragment() , MainAdapter.LastItemListener{

    companion object {
        const val TAG = "tHomeFragment"
        fun newInstance() = HomeFragment()

    }

    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory
    private lateinit var viewModel: HomeViewModel
    lateinit var endlessScroll : EndlessRecyclerViewScrollListener
    val searchSubject = PublishSubject.create<String>()
    private var adapter = MainAdapter(this)
    private var compositeDisposable = CompositeDisposable()
    private var page = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment_view, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()

//        val time = TestClass()
//        time.startTimer(handler = Handler() ,
//            stuffToDo = { Log.d("testTag" , "hello") } ,
//            stopTimeInSeconds = 5 ,
//            timePassed = 0 ,
//            interval = 1000)
    }

    override fun onLastItemClicked(album: Album) {
        val intent = Intent(activity , DetailsActivity::class.java)
        intent.putExtra(DetailsActivity.EXTRA_TITLE , album.name)
        intent.putExtra(DetailsActivity.EXTRA_SUB_TITLE , album.artist)
        intent.putExtra(DetailsActivity.EXTRA_LINK , album.url)
        startActivity(intent)
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        viewModel = ViewModelProviders.of(this ,
            viewModelFactory).get(HomeViewModel::class.java)
        observViewModel()
    }


    override fun onDetach() {
        super.onDetach()
        compositeDisposable.clear()
    }

    private fun observViewModel() {

        Log.d(TAG , "observViewModel , start observing")
        observeLiveData(viewModel.getAlbumData()){ updateView(it) }
        observeLiveData(viewModel.getProgress()){ updateProgress(it) }
//        viewModel.nextPage(1)

    }

    private fun updateProgress(aBoolean: Boolean) {
        if (aBoolean) {
            mainProgress.visibility = View.VISIBLE
        } else {
            mainProgress.visibility = View.GONE

        }
    }

    private fun updateView(item: List<Album>) {
//        mainText.text = auth.accessToken
        Log.d("testTag" , "setting data into adapter")

        item?.let {results ->
            Log.d("testTag" , "setting data into adapter")
            adapter.clear()
            adapter.setData(results)
        }


    }


    private fun initUi() {
        rvMain.adapter = adapter
        rvMain.layoutManager = LinearLayoutManager(activity)

        endlessScroll = object :
            EndlessRecyclerViewScrollListener(rvMain.layoutManager as LinearLayoutManager){
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                loadMore()
            }

        }
        rvMain.addOnScrollListener(endlessScroll)


        svMain.setOnClickListener {
            svMain.isIconified = !svMain.isIconified
        }

        svMain.setOnQueryTextListener(object  : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
//                searchSubject.onComplete()
//                svMain.isIconified = true

                Utils.hideKeyboard(context!! , svMain)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d("testTag "," search word ${newText }")

                searchSubject.onNext(newText!!)
                page = 1
                endlessScroll.resetState()
                return true

            }

        })

        subscribeToSearchQuery()

    }

    private fun loadMore() {
        page++
        viewModel.nextPage(page)
    }

    private fun subscribeToSearchQuery() {
        compositeDisposable.add(searchSubject
            .debounce(550  ,TimeUnit.MILLISECONDS)
            .filter {
                !it.isNullOrBlank()
            }
            .subscribe {
                searchString(it!!)
            })
    }

    private fun searchString(search: String) {

        viewModel.nextSearch(search)
    }
}
