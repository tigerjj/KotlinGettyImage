package kr.co.tigerlee.samplekotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.EditText
import kr.co.tigerlee.samplekotlin.model.GettyImageResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null
    private var imageRecyclerAdapter: GettyImageRecyclerAdapter? = null

    private val DEFAULT_GRID_SIZE = 2

    private var query: String = ""
    private var isLoading = false
    private var isLastPage = false
    private var currentPage = 1

    private val PAGE_SIZE = 30


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView) as RecyclerView

        imageRecyclerAdapter = GettyImageRecyclerAdapter()
        recyclerView?.adapter = imageRecyclerAdapter

        val layoutManager = GridLayoutManager(applicationContext, DEFAULT_GRID_SIZE)
        recyclerView?.layoutManager = layoutManager

        val dimenTopBottom = resources.getDimensionPixelSize(R.dimen.top_bottom)
        val dimenLeftRight = resources.getDimensionPixelSize(R.dimen.left_right)

        val spaceItemDecoration = SpaceItemDecoration(dimenTopBottom, dimenTopBottom, dimenLeftRight, dimenLeftRight)
        recyclerView?.addItemDecoration(spaceItemDecoration)
        recyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val visibleItemCount = layoutManager.getChildCount()
                val totalItemCount = layoutManager.getItemCount()
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (!isLoading && !isLastPage) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0
                            && totalItemCount >= PAGE_SIZE) {
                        loadMoreItems()
                    }
                }
            }
        })


        var button = findViewById(R.id.searchButton) as Button
        button.setOnClickListener({
            var queryEditText = findViewById(R.id.queryEditText) as EditText
            queryEditText.text?.toString()?.trim()?.let {
                q -> requestGettyImage(q, 1)
                imageRecyclerAdapter?.clear()
                query = q
                currentPage = 1
            }
        })

    }

    fun loadMoreItems(){
        isLoading = true
        requestGettyImage(query, ++currentPage)
    }

    fun requestGettyImage(query: String, page: Int, pageSize: Int = PAGE_SIZE) {

        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("https://api.gettyimages.com/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        var gettyImageService = retrofit.create(GettyImageService::class.java)
        var callResponse = gettyImageService.gettyImages(query, page, pageSize)

        callResponse.enqueue(object : Callback<GettyImageResponse> {
            override fun onResponse(call: Call<GettyImageResponse>?, response: Response<GettyImageResponse>?) {
                response?.body()?.let { gettyImageResponse ->
                    recyclerView?.adapter = imageRecyclerAdapter
                    imageRecyclerAdapter?.addAll(gettyImageResponse.images)

                    isLoading = false
                    isLastPage = gettyImageResponse.images.size < PAGE_SIZE
                }
            }

            override fun onFailure(call: Call<GettyImageResponse>?, t: Throwable?) {
            }
        })
    }
}