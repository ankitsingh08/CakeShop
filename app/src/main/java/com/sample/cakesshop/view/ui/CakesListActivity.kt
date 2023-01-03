package com.sample.cakesshop.view.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import com.sample.cakesshop.databinding.CakesListActivityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CakesListActivity : AppCompatActivity() {

    private lateinit var binding: CakesListActivityBinding

    private val itemsViewModel: CakesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CakesListActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )

        val adapter = CakesListAdapter()
        binding.recyclerView.adapter = adapter

        itemsViewModel.cakeItems.observe(this) {
            binding.loader.visibility = View.GONE
            binding.recyclerView.visibility = View.VISIBLE
            binding.btnRetry.visibility = View.GONE
            binding.tvNetworkError.visibility = View.GONE

            adapter.setItemsList(it)
        }

        itemsViewModel.error.observe(this) {
            if (it) {
                binding.loader.visibility = View.GONE
                binding.tvNetworkError.visibility = View.VISIBLE
                binding.recyclerView.visibility = View.GONE
                binding.btnRetry.visibility = View.VISIBLE

                binding.btnRetry.setOnClickListener { itemsViewModel.getToastItemsData() }
            }
        }
    }
}
