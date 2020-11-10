package com.example.studynotesapp.ui.adapters

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.studynotesapp.R
import com.example.studynotesapp.data.entities.Term
import com.example.studynotesapp.databinding.StudyTermItemBinding
import com.example.studynotesapp.databinding.TermListItemBinding
import dagger.hilt.android.qualifiers.ApplicationContext

class StudyTermAdapter(val termListListener: StudyTermListener) : ListAdapter<Term, StudyTermAdapter.ViewHolder>(TermListDiffUtilCallBack()) {



    inner class ViewHolder(val binding : StudyTermItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : Term, clickListener: StudyTermListener){
            binding.term = item
            binding.executePendingBindings()
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudyTermAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = StudyTermItemBinding.inflate(layoutInflater, parent, false)

        val scale = parent.context.resources.displayMetrics.density

        binding.cvFront.cameraDistance = 8000 * scale
        binding.cvBack.cameraDistance = 8000 * scale

        val front_anim = AnimatorInflater.loadAnimator(parent.context, R.animator.front_animation) as AnimatorSet
        val back_anim = AnimatorInflater.loadAnimator(parent.context, R.animator.back_animator) as AnimatorSet

        var isFront = true //has to be in onCreateViewHolder so it is separate for each view, flip will not work correctly if not

        binding.cvFront.setOnClickListener {


            if(isFront){
                front_anim.setTarget(binding.cvFront)
                back_anim.setTarget(binding.cvBack)
                front_anim.start()
                back_anim.start()
                isFront = false
            }else{
                front_anim.setTarget(binding.cvBack)
                back_anim.setTarget(binding.cvFront)
                front_anim.start()
                back_anim.start()
                isFront = true
            }
        }

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StudyTermAdapter.ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(item, termListListener)
    }


    class StudyTermListener(val clickListener: (id: Long) -> Unit){
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