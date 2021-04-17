package com.fongfuse.githubdemo.main

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.fongfuse.githubdemo.base.BaseActivity
import com.fongfuse.githubdemo.data.BaseResponse
import com.fongfuse.githubdemo.databinding.ActivityMainBinding
import com.fongfuse.githubdemo.extension.setScrollListenerLoadMore
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewModel: MainViewModel by viewModel()

    private val mainAdapter: MainAdapter by lazy {
        MainAdapter(this)
    }

    private val linearLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private val items = ArrayList<BaseResponse?>()

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initSearchView()
        initRecyclerView()
        initViewModel()
    }

    private fun initSearchView() {
        binding.apply {
            svSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let {
                        if (it.isNotEmpty()) {
                            items.clear()
                            viewModel.callSearchUsers(query)
                            svSearch.clearFocus()
                        } else {
                            svSearch.clearFocus()
                        }
                    }
                    return true
                }

                override fun onQueryTextChange(p0: String?): Boolean = false
            })
        }
    }

    private fun initViewModel() {
        viewModel.loading.observe(this, {
            setViewLoading(it)
        })

        viewModel.items.observe(this, {
            setViewVisible(it)
            setItems(it)
        })
    }

    private fun setViewLoading(it: Boolean) {
        binding.apply {
            if (it) {
                this.layoutLoading.root.visibility = View.VISIBLE
            }else {
                this.layoutLoading.root.visibility = View.GONE
            }
        }
    }

    private fun setViewVisible(items: List<BaseResponse>) {
        binding.apply {
            if (items.isEmpty()) {
                this.layoutEmpty.root.visibility = View.VISIBLE
            } else {
                this.layoutEmpty.root.visibility = View.GONE
            }
        }
    }

    private fun initRecyclerView() {
        binding.rvItem.apply {
            this.layoutManager = linearLayoutManager
            this.adapter = mainAdapter
        }
    }

    private fun setItems(items: List<BaseResponse>) {
        this.items.addAll(items)
        mainAdapter.setItems(this.items)
    }


}