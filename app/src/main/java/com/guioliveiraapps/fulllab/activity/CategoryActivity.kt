package com.guioliveiraapps.fulllab.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.guioliveiraapps.fulllab.R
import com.guioliveiraapps.fulllab.adapter.CategoryAdapter
import com.guioliveiraapps.fulllab.itemdecoration.SimpleDividerItemDecoration
import com.guioliveiraapps.fulllab.model.category.Category
import com.guioliveiraapps.fulllab.util.Utils
import com.guioliveiraapps.fulllab.viewmodel.CategoryViewModel
import kotlinx.android.synthetic.main.content_category.*
import java.util.*

class CategoryActivity : AppCompatActivity() {

    private var categoryViewModel: CategoryViewModel? = null

    private var rvCategories: RecyclerView? = null

    private var adapter: CategoryAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        val toolbar: Toolbar = findViewById(R.id.category_toolbar)
        setSupportActionBar(toolbar)
        setupToolbar()

        rvCategories = findViewById(R.id.rvCategories)

        setupViewmodel()
        categoryViewModel!!.getCategories()
    }


    private fun setupViewmodel() {
        if (categoryViewModel == null) {
            categoryViewModel = ViewModelProviders.of(this).get(CategoryViewModel::class.java)
        }

        categoryViewModel?.mResponse?.observe(this, androidx.lifecycle.Observer {
            if (it != null && it.categories!!.isNotEmpty()) {
                setupList(it.categories!!)
            }
        })
    }

    private fun setupList(categories: List<Category>) {
        if (adapter == null) {
            adapter = CategoryAdapter(categories as ArrayList<Category>, this)
            rvCategories!!.layoutManager = LinearLayoutManager(this)
            rvCategories!!.addItemDecoration(SimpleDividerItemDecoration(this))
            rvCategories!!.setHasFixedSize(true)
            rvCategories!!.isNestedScrollingEnabled = false
            rvCategories!!.adapter = adapter
        }

        Utils.fadeIn(rvCategories!!, 200)
        Utils.fadeOut(progressSearch, 1, 2)
    }

    private fun setupToolbar() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.title = "Categorias"
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
