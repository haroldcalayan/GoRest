package com.haroldcalayan.gorest.ui.main.user.master

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.haroldcalayan.gorest.BR
import com.haroldcalayan.gorest.R
import com.haroldcalayan.gorest.data.model.User
import com.haroldcalayan.gorest.databinding.AdapterUserBinding

class UserAdapter(
    private var data: List<User>,
    private val listener: UserAdapterListener
) : RecyclerView.Adapter<UserAdapter.UserAdapterViewHolder>() {

    interface UserAdapterListener {
        fun onUserItemClick(user: User)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapterViewHolder {
        val binding: AdapterUserBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.adapter_user,
            parent,
            false
        )
        return UserAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserAdapterViewHolder, position: Int) {
        holder.bind(data[position])
        holder?.itemView?.tag = data[position]
        holder?.itemView?.setOnClickListener { listener.onUserItemClick(data[position]) }
    }

    override fun getItemCount() = data.size

    fun updateData(data: List<User>) {
        this.data = data
        notifyDataSetChanged()
    }

    class UserAdapterViewHolder(private val binding: AdapterUserBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.setVariable(BR.user, user)
            binding.executePendingBindings()
        }
    }
}