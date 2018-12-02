package com.br.cnorris.ui.category.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.br.cnorris.R
import kotlinx.android.synthetic.main.item_category.view.*

/**
 * Adapter para uma lista de categoria de frases
 * @author douglas.takara
 */
class CategoryListAdapter(
    private val context: Context,
    private val categories: List<String>,
    fragment: Fragment
) : RecyclerView.Adapter<CategoryListAdapter.ViewHolder>() {

    private val listener: onItemClickListener

    init {
        this.listener = fragment as onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categories[position]

        holder.txtCategory.text = category.capitalize()

        holder.view.setOnClickListener {
            listener.itemDetail(category)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val view = itemView.itemCategory
        val txtCategory = itemView.txtCategory
    }

    interface onItemClickListener {
        fun itemDetail(category: String)
    }
}
