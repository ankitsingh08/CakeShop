package com.sample.cakesshop.view.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sample.cakesshop.R
import com.sample.cakesshop.databinding.ItemBinding
import com.sample.cakesshop.view.model.CakePresentationModel

class CakesListAdapter : RecyclerView.Adapter<CakesListAdapter.ViewHolder>() {

    private var items = listOf<CakePresentationModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setItemsList(itemList: List<CakePresentationModel>) {
        items = itemList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CakePresentationModel) {
            binding.tvTitle.text = item.title

            Glide.with(binding.root.context)
                .load(item.image)
                .placeholder(R.drawable.placeholder_icon)
                .into(binding.ivIcon)

            binding.tvTitle.setOnClickListener {
                binding.root.context?.let {
                    val builder = AlertDialog.Builder(it)
                    builder.apply {
                        setPositiveButton(
                            R.string.ok
                        ) { dialog, _ ->
                            dialog.dismiss()
                        }
                    }
                    builder.setMessage(item.desc)
                    builder.create().show()
                }
            }
        }
    }
}
