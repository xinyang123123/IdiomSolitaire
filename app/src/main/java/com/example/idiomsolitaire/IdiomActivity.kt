package com.example.idiomsolitaire

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.idiomsolitaire.databinding.ActivityIdiomBinding
import org.jetbrains.anko.toast

class IdiomActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIdiomBinding
    private lateinit var viewModel: IdiomViewModel
    private lateinit var adapter: IdiomResultAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()
        initBinding()
        initRv()
        listenerField()
        initSearchView()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(IdiomViewModel::class.java)
    }

    private fun initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_idiom)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    private fun initRv() {
        adapter = IdiomResultAdapter(listOf(), viewModel)
        binding.rvResult.layoutManager = LinearLayoutManager(this)
        binding.rvResult.adapter = adapter
    }

    private fun initSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String?): Boolean {
                viewModel.search(text)
                return false
            }

            override fun onQueryTextChange(text: String?): Boolean {
                viewModel.search(text)
                return false
            }
        })
    }

    private fun listenerField() {
        viewModel.errorMessage.observe(this, Observer { toast(it) })
        viewModel.searchResult.observe(this, Observer(adapter::setData))
        viewModel.searchCount.observe(this, Observer { binding.searchView.setQuery(it, true) })
    }

}
