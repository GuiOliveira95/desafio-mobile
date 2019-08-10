package com.guioliveiraapps.fulllab.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View
import android.widget.ScrollView
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

    var offset = 0

    var canSearch = true

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
                if (offset == 0) {
                    showNotFoundProducts()
                } else {
                    showNoMoreProducts()
                }
            }
            Utils.fadeOut(progressBarHorizontal, 200, 1)
        })
    }

    private fun showNoMoreProducts() {
        Utils.fadeIn(txtNoMoreProducts, 200)
        Utils.fadeOut(txtNoProductsFound, 200, 2)
        Utils.fadeOut(progressSearch, 200, 2)
        Utils.fadeOut(progressBarHorizontal, 200, 1)
        Utils.fadeOut(txtProcurar, 200, 2)
        canSearch = false
    }

    private fun showNotFoundProducts() {
        Utils.fadeIn(txtNoProductsFound, 200)
        Utils.fadeOut(progressBarHorizontal, 200, 1)
        Utils.fadeOut(nsProducts, 200, 2)
        Utils.fadeOut(txtNoMoreProducts, 200, 2)
        Utils.fadeOut(txtProcurar, 200, 2)
        Utils.fadeOut(progressSearch, 200, 2)
    }

    private fun setupList(products: List<Product>) {
        if (adapter == null) {
            adapter = ProductAdapter(products as ArrayList<Product>, this)
            rvSearchResults!!.layoutManager = GridLayoutManager(this, 2)
            rvSearchResults!!.setHasFixedSize(true)
            rvSearchResults!!.isNestedScrollingEnabled = false
            rvSearchResults!!.adapter = adapter
        } else if (offset > 0) {
            adapter!!.addProducts(products)
        } else {
            adapter!!.replaceProducts(products)
            nsProducts.fullScroll(ScrollView.FOCUS_UP)
        }

        Utils.fadeIn(nsProducts, 200)
        Utils.fadeOut(txtNoProductsFound, 200, 2)
        Utils.fadeOut(progressBarHorizontal, 200, 1)
        Utils.fadeOut(txtNoMoreProducts, 200, 2)
        Utils.fadeOut(progressSearch, 200, 2)
        Utils.fadeOut(txtProcurar, 200, 2)
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
                                offset = 0
                                canSearch = true
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

            override fun afterTextChanged(s: Editable?) {}
        })

        nsProducts.isNestedScrollingEnabled = false
        nsProducts.isSmoothScrollingEnabled = true
        nsProducts.viewTreeObserver.addOnScrollChangedListener {
            if (nsProducts.scrollY == nsProducts.getChildAt(0).measuredHeight - nsProducts.measuredHeight) {
                if (canSearch) {
                    offset += 10
                    productViewModel?.getProducts(editSearch.text.toString(), offset.toString(), 10.toString())
                    Utils.fadeIn(progressSearch, 200)
                }
            }
        }
    }

    fun showHorizontalLoading() {
        Utils.fadeIn(progressBarHorizontal, 200)
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
