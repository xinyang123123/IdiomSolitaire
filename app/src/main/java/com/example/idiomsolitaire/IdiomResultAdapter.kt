package com.example.idiomsolitaire

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.idiomsolitaire.databinding.ItemIdiomSearchResultBinding
import com.example.idiomsolitaire.model.local.IdiomResult

class IdiomResultAdapter(
    private var data: List<IdiomResult>,
    private val idiomViewModel: IdiomViewModel
) :
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


    override fun onBindViewHolder(holder: IdiomViewHolder, position: Int) {
        var item = data[position]
        holder.bind(item)
        holder.binding.root.setOnClickListener {
            idiomViewModel.searchCount.value = item.lastPinyin
        }
    }

    fun setData(newData: List<IdiomResult>) {
        data = newData
        notifyDataSetChanged()
    }

    class IdiomViewHolder(val binding: ItemIdiomSearchResultBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(idiomResult: IdiomResult) {
            binding.data = idiomResult
        }
    }

}