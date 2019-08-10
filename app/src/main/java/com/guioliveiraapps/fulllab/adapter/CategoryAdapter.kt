package com.guioliveiraapps.fulllab.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.guioliveiraapps.fulllab.R
import com.guioliveiraapps.fulllab.SubCategoryActivity
import com.guioliveiraapps.fulllab.model.Category
import com.guioliveiraapps.fulllab.model.SubCategory
import com.guioliveiraapps.fulllab.model.SubCategoryList

class CategoryAdapter(private val list: ArrayList<Category>, private val context: Context) :
    RecyclerView.Adapter<CategoryAdapter.CategoryAdapterViewHolder>() {

    var subCategoryList = SubCategoryList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_category, parent, false)

        return CategoryAdapterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CategoryAdapterViewHolder, position: Int) {
        holder.bindData(list[position])

        subCategoryList.subcategories = holder.subCategories

        holder.containerView!!.setOnClickListener {
            try {
                val b = Bundle()
                val intent = Intent(context as Activity, SubCategoryActivity::class.java)
                b.putString("name", holder.title)
                b.putSerializable("subCategoryList", subCategoryList)
                intent.putExtras(b)
                context.startActivity(intent)
            } catch (e: Exception) {
            }
        }
    }

    class CategoryAdapterViewHolder(val containerView: View?) : RecyclerView.ViewHolder(containerView!!) {
        val txtTitle = containerView!!.findViewById(R.id.txtCategoryTitle) as TextView

        var title: String? = ""
        var subCategories: List<SubCategory>? = null

        fun bindData(category: Category) {
            txtTitle.text = category.name

            title = category.name
            subCategories = category.subCategories
        }

    }
}