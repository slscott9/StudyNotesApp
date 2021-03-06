package com.example.studynotesapp.other

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.studynotesapp.data.domain.DomainFolder
import com.example.studynotesapp.data.domain.DomainSet
import com.example.studynotesapp.data.entities.Term
import org.w3c.dom.Text

@BindingAdapter("setFolderName")
fun TextView.setFolderName(item: DomainFolder?){
    item?.name?.let { text = item.name }
}

@BindingAdapter("setFolderUserName")
fun TextView.setFolderUserName(item: DomainFolder?){
    item?.userName?.let { text = item.userName }
}

@BindingAdapter("setSetName")
fun TextView.setSetName(item: DomainSet?){
    item?.setName?.let {
        text = item.setName
    }
}

@BindingAdapter("setFolderTermCount")
fun TextView.setFolderTermCount(item: DomainFolder?){
    item?.setCount?.let { text = "${item.setCount} Sets" }
}

@BindingAdapter("setUserInitial")
fun TextView.setUserInitial(item : DomainFolder?){
    item?.userName?.let { text = "${item.userName[0]}" }
}

@BindingAdapter("setTermCount")
fun TextView.setTermCount(item : DomainSet?){
    item?.termCount?.let { text = "${item.termCount} terms" }
}

@BindingAdapter("setUserName")
fun TextView.setUserName(item: DomainSet?) {
    item?.userName?.let { text = item.userName }
}

@BindingAdapter("setUserInitial")
fun TextView.setUserInitial(item: DomainSet?) {
    item?.userName?.let { text = "${item.userName[0]}" }
}

@BindingAdapter("setTermQuestion")
fun TextView.setTermQuestion(item: Term?){
    item?.question?.let { text = item.question }
}

@BindingAdapter("setTermAnswer")
fun TextView.setTermAnswer(item : Term?){
    item?.answer?.let { text = item.answer }
}