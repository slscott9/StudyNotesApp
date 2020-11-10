package com.example.studynotesapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.studynotesapp.data.entities.Term
import com.example.studynotesapp.databinding.TermListItemBinding

class TermListAdapter(val termListListener: TermListListener) : ListAdapter<Term, TermListAdapter.ViewHolder>(TermListDiffUtilCallBack()) {


    inner class ViewHolder(val binding : TermListItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : Term, clickListener: TermListListener){
            binding.term = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TermListAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = TermListItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TermListAdapter.ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, termListListener)
    }


    class TermListListener(val clickListener: (id: Long) -> Unit){
        fun onClick(term: Term){
            clickListener(term.termId)
        }
    }



    class TermListDiffUtilCallBack() : DiffUtil.ItemCallback<Term>() {
        override fun areItemsTheSame(oldItem: Term, newItem: Term): Boolean {
            return oldItem.termId == newItem.termId
        }

        override fun areContentsTheSame(oldItem: Term, newItem: Term): Boolean {
            return oldItem == newItem
        }
    }
}