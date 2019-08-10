package com.guioliveiraapps.fulllab.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.guioliveiraapps.fulllab.R
import com.guioliveiraapps.fulllab.model.category.SubCategory

class SubCategoryAdapter(private val list: ArrayList<SubCategory>, private val context: Context) :
    RecyclerView.Adapter<SubCategoryAdapter.SubCategoryAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubCategoryAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_category, parent, false)

        return SubCategoryAdapterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holderSub: SubCategoryAdapterViewHolder, position: Int) {
        holderSub.bindData(list[position])

        holderSub.containerView!!.setOnClickListener {
            Toast.makeText(context, holderSub.title, Toast.LENGTH_SHORT).show()
        }
    }

    class SubCategoryAdapterViewHolder(val containerView: View?) : RecyclerView.ViewHolder(containerView!!) {
        val txtTitle = containerView!!.findViewById(R.id.txtCategoryTitle) as TextView

        var title: String? = ""

        fun bindData(subCategory: SubCategory) {
            txtTitle.text = subCategory.name

            title = subCategory.name
        }

    }
}