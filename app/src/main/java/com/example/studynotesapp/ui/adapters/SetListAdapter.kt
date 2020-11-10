package com.example.studynotesapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.studynotesapp.data.domain.DomainSet
import com.example.studynotesapp.databinding.SetListItemBinding

class SetListAdapter(val clickListener: SetListListener) : ListAdapter<DomainSet, SetListAdapter.ViewHolder>(SetListDiffUtilCallback()){


    class ViewHolder(val binding : SetListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : DomainSet, clickListener: SetListListener){

            binding.domainSet = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = SetListItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }
}

class SetListListener(val clickListener : (id: Long) -> Unit) {

    fun onClick(domainSet: DomainSet) {
        clickListener(domainSet.setId)
    }
}

class SetListDiffUtilCallback : DiffUtil.ItemCallback<DomainSet>() {
    override fun areItemsTheSame(oldItem: DomainSet, newItem: DomainSet): Boolean {
        return oldItem.setId == newItem.setId
    }

    override fun areContentsTheSame(oldItem: DomainSet, newItem: DomainSet): Boolean {
        return oldItem == newItem
    }
}
