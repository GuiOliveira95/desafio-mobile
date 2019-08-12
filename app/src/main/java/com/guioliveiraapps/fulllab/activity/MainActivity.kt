package com.guioliveiraapps.fulllab.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.guioliveiraapps.fulllab.R
import com.guioliveiraapps.fulllab.adapter.ProductAdapter
import com.guioliveiraapps.fulllab.model.product.Product
import com.guioliveiraapps.fulllab.util.Utils
import com.guioliveiraapps.fulllab.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var productViewModel: ProductViewModel? = null

    private var adapter: ProductAdapter? = null

    private var rvProducts: RecyclerView? = null

    private var offset = 0

    private var canSearch = true

    private var drawerLayout: DrawerLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.main_toolbar)
        setSupportActionBar(toolbar)

        setupViewmodel()
        setupNestedScrollListener()

        rvProducts = findViewById(R.id.rvProducts)



        drawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout!!.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)

        productViewModel?.getProducts("", offset.toString(), 10.toString())

    }

    private fun setupNestedScrollListener() {
        nsProducts.isNestedScrollingEnabled = false
        nsProducts.viewTreeObserver.addOnScrollChangedListener {
            if (nsProducts.scrollY == nsProducts.getChildAt(0).measuredHeight - nsProducts.measuredHeight) {
                if (canSearch) {
                    offset += 10
                    productViewModel?.getProducts("", offset.toString(), 10.toString())
                    canSearch = false
                }
            }
        }
    }

    private fun setupViewmodel() {
        if (productViewModel == null) {
            productViewModel = ViewModelProviders.of(this).get(ProductViewModel::class.java)
        }

        productViewModel?.mResponse?.observe(this, Observer {
            if (it != null && it.products!!.isNotEmpty()) {
                setupList(it.products!!)
            } else {
                showNoMoreProducts()
            }
            canSearch = true
        })
    }

    private fun showNoMoreProducts() {
        progressSearch.visibility = View.GONE
        Utils.fadeIn(txtNoMoreProducts, 200)
        canSearch = false
    }

    private fun setupList(products: List<Product>) {
        if (adapter == null) {
            adapter = ProductAdapter(products as ArrayList<Product>, this)
            rvProducts!!.layoutManager = GridLayoutManager(this, 2)
            rvProducts!!.setHasFixedSize(true)
            rvProducts!!.isNestedScrollingEnabled = false
            rvProducts!!.adapter = adapter
        } else {
            adapter!!.addProducts(products)
        }
    }

    override fun onBackPressed() {
        if (drawerLayout!!.isDrawerOpen(GravityCompat.START)) {
            drawerLayout!!.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.main_action_search -> {
                startActivity(Intent(this, SearchActivity::class.java))
                true
            }
            R.id.main_action_categorias -> {
                startActivity(Intent(this, CategoryActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                drawerLayout!!.closeDrawer(GravityCompat.START)
            }
            R.id.nav_category -> {
                startActivity(Intent(this, CategoryActivity::class.java))
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
