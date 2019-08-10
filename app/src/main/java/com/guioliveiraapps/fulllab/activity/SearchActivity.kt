package com.guioliveiraapps.fulllab.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.guioliveiraapps.fulllab.R
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
            if (it != null && it.products!!.isNotEmpty()) {
                setupList(it.products!!)
            } else {
                showNoProductsFound()
            }
            hideHorizontalLoading()
        })
    }

    private fun showRecycler() {
        Utils.fadeIn(rvSearchResults, 200)
    }

    private fun hideRecycler() {
        Utils.fadeOut(rvSearchResults, 200, 1)
    }

    private fun showNoProductsFound() {
        hideRecycler()
        if (adapter != null) adapter!!.clear()
        showNoProductsTxt()
        hideTxtProcurar()
    }

    private fun showNoProductsTxt() {
        Utils.fadeIn(txtNoProductsFound, 200)
    }

    private fun hideNoProductsTxt() {
        Utils.fadeOut(txtNoProductsFound, 1, 1)
    }

    private fun setupList(products: List<Product>) {
        if (adapter == null) {
            adapter = ProductAdapter(products as ArrayList<Product>, this)
            rvSearchResults!!.layoutManager = GridLayoutManager(this, 2)
            rvSearchResults!!.setHasFixedSize(true)
            rvSearchResults!!.isNestedScrollingEnabled = false
            rvSearchResults!!.adapter = adapter
            hideTxtProcurar()
        } else {
            adapter!!.replaceProducts(products)
        }

        showRecycler()
        hideNoProductsTxt()
        hideHorizontalLoading()
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
                                    showHorizontalLoading()
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

    fun showHorizontalLoading() {
        Utils.fadeIn(progressBarHorizontal, 200)
    }

    fun hideHorizontalLoading() {
        Utils.fadeOut(progressBarHorizontal, 200, 1)
    }

    fun hideTxtProcurar() {
        Utils.fadeOut(txtProcurar, 200, 1)
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
