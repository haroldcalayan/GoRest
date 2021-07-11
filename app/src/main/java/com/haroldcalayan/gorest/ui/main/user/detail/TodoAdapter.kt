package com.haroldcalayan.gorest.ui.main.user.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.haroldcalayan.gorest.BR
import com.haroldcalayan.gorest.R
import com.haroldcalayan.gorest.data.model.Todo
import com.haroldcalayan.gorest.databinding.AdapterTodoBinding

class TodoAdapter(
    private var data: List<Todo>
) : RecyclerView.Adapter<TodoAdapter.TodoAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoAdapterViewHolder {
        val binding: AdapterTodoBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.adapter_todo,
            parent,
            false
        )
        return TodoAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoAdapterViewHolder, position: Int) {
        holder.bind(data[position])
        holder?.itemView?.tag = data[position]
    }

    override fun getItemCount() = data.size

    fun updateData(data: List<Todo>) {
        this.data = data
        notifyDataSetChanged()
    }

    class TodoAdapterViewHolder(private val binding: AdapterTodoBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(todo: Todo) {
            binding.setVariable(BR.todo, todo)
            binding.executePendingBindings()
        }
    }
}