package com.example.studynotesapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.studynotesapp.data.domain.DomainFolder
import com.example.studynotesapp.databinding.FolderListItemBinding


class FolderListAdapter(val clickListener: FolderListListener) : ListAdapter<DomainFolder, FolderListAdapter.ViewHolder>(FolderListAdapterDiffUtilCallback()) {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FolderListAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = FolderListItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FolderListAdapter.ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener )
    }

    class ViewHolder(val binding : FolderListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DomainFolder, clickListener: FolderListListener) {
            binding.domainFolder = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    class FolderListListener(val clickListener: (id: Long) -> Unit){
        fun onClick(folder: DomainFolder){
            clickListener(folder.folderId)
        }
    }

    class FolderListAdapterDiffUtilCallback : DiffUtil.ItemCallback<DomainFolder>() {
        override fun areItemsTheSame(oldItem: DomainFolder, newItem: DomainFolder): Boolean {
            return oldItem.folderId == newItem.folderId
        }

        override fun areContentsTheSame(oldItem: DomainFolder, newItem: DomainFolder): Boolean {
            return oldItem == newItem
        }
    }
}