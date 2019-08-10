package com.guioliveiraapps.fulllab.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.guioliveiraapps.fulllab.R
import com.guioliveiraapps.fulllab.adapter.SubCategoryAdapter
import com.guioliveiraapps.fulllab.model.category.SubCategory
import com.guioliveiraapps.fulllab.model.SubCategoryList
import java.util.*

class SubCategoryActivity : AppCompatActivity() {

    private var rvSubCategories: RecyclerView? = null

    private var adapter: SubCategoryAdapter? = null

    lateinit var title: String
    lateinit var subCategories: SubCategoryList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_category)

        try {
            title = intent.getStringExtra("name") as String
            subCategories = intent.getSerializableExtra("subCategoryList") as SubCategoryList
        } catch (ignored: Exception) {}

        val toolbar: Toolbar = findViewById(R.id.sub_category_toolbar)
        setSupportActionBar(toolbar)
        setupToolbar()

        rvSubCategories = findViewById(R.id.rvSubCategories)

        setupList(subCategories.subcategories!!)
    }


    private fun setupList(categories: List<SubCategory>) {
        if (adapter == null) {
            adapter = SubCategoryAdapter(categories as ArrayList<SubCategory>, this)
            rvSubCategories!!.layoutManager = LinearLayoutManager(this)
            rvSubCategories!!.setHasFixedSize(true)
            rvSubCategories!!.isNestedScrollingEnabled = false
            rvSubCategories!!.adapter = adapter
        }
    }

    private fun setupToolbar() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.title = title
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
