package com.example.idiomsolitaire

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.idiomsolitaire.databinding.ItemIdiomSearchResultBinding
import com.example.idiomsolitaire.model.local.IdiomResult

class IdiomResultAdapter(private var data: List<IdiomResult>) :
    RecyclerView.Adapter<IdiomResultAdapter.IdiomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IdiomViewHolder =
        IdiomViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_idiom_search_result,
                parent,
                false
            )
        )


    override fun getItemCount(): Int = data.size


    override fun onBindViewHolder(holder: IdiomResultAdapter.IdiomViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun setData(newData: List<IdiomResult>) {
        data = newData
        notifyDataSetChanged()
    }

    class IdiomViewHolder(private val binding: ItemIdiomSearchResultBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(idiomResult: IdiomResult) {
            binding.data = idiomResult
        }
    }

}