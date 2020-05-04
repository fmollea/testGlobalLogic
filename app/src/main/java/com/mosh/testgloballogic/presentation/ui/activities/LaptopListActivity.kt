package com.mosh.testgloballogic.presentation.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mosh.testgloballogic.data.repository.LaptopRepository
import com.mosh.testgloballogic.data.repository.data.LaptopResponse
import com.mosh.testgloballogic.databinding.LaptopListActivityBinding
import com.mosh.testgloballogic.domain.LatopItem
import com.mosh.testgloballogic.presentation.ui.adapters.LaptopAdapter
import com.mosh.testgloballogic.presentation.viewmodels.LaptopViewModel
import com.mosh.testgloballogic.presentation.viewmodels.LaptopViewModelFactory
import com.mosh.testgloballogic.utils.NetworkStatus
import com.mosh.testgloballogic.utils.Status

class LaptopListActivity : AppCompatActivity() {

    private lateinit var viewModel : LaptopViewModel
    private lateinit var adapter : LaptopAdapter
    private lateinit var binding : LaptopListActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LaptopListActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = LaptopViewModelFactory(LaptopRepository())
        viewModel = ViewModelProviders.of(this, factory).get(LaptopViewModel::class.java)

        initView()
        observeViewModel()
    }

    private fun observeViewModel() {
        val listLatopsObserver = Observer<NetworkStatus<LaptopResponse>> {
            when(it.status) {
                Status.LOADING -> showLoading()
                Status.SUCCESS -> {
                    hideLoading()
                    showList(it.data as ArrayList<LatopItem>)
                }
                Status.ERROR -> {
                    hideLoading()
                    showErrorScreen()
                }
            }
        }

        viewModel.getLaptops().observe(this, listLatopsObserver)
    }

    private fun showErrorScreen() {
        binding.clErrorContainer.visibility = View.VISIBLE

        binding.btRetry.setOnClickListener {
            binding.clErrorContainer.visibility = View.GONE
            observeViewModel()
        }
    }

    private fun hideLoading() {
        binding.pbLoading.visibility = View.GONE
    }

    private fun showLoading() {
        binding.pbLoading.visibility = View.VISIBLE
    }


    private fun initView() {
        supportActionBar?.title = "List of laptops"

        adapter = LaptopAdapter(this, listOf())
        binding.rvListLatops.layoutManager = LinearLayoutManager(this)
        binding.rvListLatops.adapter = adapter
    }

    private fun showList(list: List<LatopItem>) {
        binding.rvListLatops.visibility = View.VISIBLE
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
}
