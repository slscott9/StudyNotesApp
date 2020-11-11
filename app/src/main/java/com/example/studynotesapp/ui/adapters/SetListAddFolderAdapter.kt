package com.example.studynotesapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.studynotesapp.data.domain.DomainSet
import com.example.studynotesapp.databinding.SetListItemBinding
import timber.log.Timber

class SetListAddFolderAdapter : ListAdapter<DomainSet, SetListAddFolderAdapter.ViewHolder>(SetDiffUtil()) {

     val itemClickedList = ArrayList<DomainSet>()
    var tracker: SelectionTracker<Long>? = null

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    inner class ViewHolder(val binding : SetListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item : DomainSet, isActivated: Boolean = false){
            binding.domainSet = item
            binding.executePendingBindings()
            itemView.isActivated = isActivated
            if(isActivated){
                itemClickedList.add(item)
                Timber.i(itemClickedList.toString())
            }else{
                itemClickedList.remove(item)
            }
        }

        fun getItemDetails(): ItemDetailsLookup.ItemDetails<Long> =
            object : ItemDetailsLookup.ItemDetails<Long>() {
                override fun getPosition(): Int = adapterPosition
                override fun getSelectionKey(): Long? = itemId
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = SetListItemBinding.inflate(layoutInflater, parent, false)



        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        tracker?.let {
            holder.bind(item, it.isSelected(position.toLong()))

        }
    }

    class SetDiffUtil : DiffUtil.ItemCallback<DomainSet>() {
        override fun areItemsTheSame(oldItem: DomainSet, newItem: DomainSet): Boolean {
            return oldItem.setId == newItem.setId
        }

        override fun areContentsTheSame(oldItem: DomainSet, newItem: DomainSet): Boolean {
            return oldItem == newItem
        }


    }

}