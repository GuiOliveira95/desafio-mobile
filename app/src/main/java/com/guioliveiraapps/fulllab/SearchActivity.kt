package com.guioliveiraapps.fulllab

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.guioliveiraapps.fulllab.adapter.ProductAdapter
import com.guioliveiraapps.fulllab.model.Product
import com.guioliveiraapps.fulllab.util.Utils
import com.guioliveiraapps.fulllab.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.content_search.*
import java.util.*


class SearchActivity : AppCompatActivity() {

    private var timer: Timer? = null

    var productViewModel: ProductViewModel? = null

    var adapter: ProductAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val toolbar: Toolbar = findViewById(R.id.search_toolbar)
        setSupportActionBar(toolbar)
        setupToolbar()

        setupListeners()

        setupViewmodel()

    }

    private fun setupViewmodel() {
        if (productViewModel == null) {
            productViewModel = ViewModelProviders.of(this).get(ProductViewModel::class.java)
        }

        productViewModel?.mResponse?.observe(this, androidx.lifecycle.Observer {
            if (it != null && !it.products!!.isEmpty()) {
                setupList(it.products!!)
            }
        })
    }

    private fun setupList(products: List<Product>) {
        if (adapter == null) {
            adapter = ProductAdapter(products as ArrayList<Product>, this)
            rvSearchResults!!.layoutManager = GridLayoutManager(this, 2)
            rvSearchResults!!.setHasFixedSize(true)
            rvSearchResults!!.isNestedScrollingEnabled = false
            rvSearchResults!!.adapter = adapter
        } else {
            adapter!!.replaceProducts(products)
        }
    }

    private fun setupToolbar() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
    }

    private fun setupListeners() {
        ivClear.setOnClickListener { editSearch.setText("") }

        editSearch!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (editSearch.text.toString() == "") {
                    Utils.fadeOut(ivClear, 200, 1)
                } else {
                    Utils.fadeIn(ivClear, 200)
                }

                if (timer != null) {
                    timer!!.cancel()
                }

                timer = Timer()
                timer!!.schedule(object : TimerTask() {
                    override fun run() {
                        runOnUiThread {
                            if (count > 0) {
                                if (editSearch.text.length > 2) {
                                    productViewModel!!.getProducts(editSearch.text.toString(), "0", "10")
                                }
                            } else {

                            }
                        }
                    }
                }, 500)

            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
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
