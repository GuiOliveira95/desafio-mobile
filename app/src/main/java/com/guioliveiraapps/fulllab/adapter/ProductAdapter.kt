package com.guioliveiraapps.fulllab.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.guioliveiraapps.fulllab.R
import com.guioliveiraapps.fulllab.model.Product
import com.guioliveiraapps.fulllab.model.Seller
import com.guioliveiraapps.fulllab.model.Sku
import com.guioliveiraapps.fulllab.util.Utils


class ProductAdapter(private val list: ArrayList<Product>, private val context: Context) :
    RecyclerView.Adapter<ProductAdapter.ProductAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_product, parent, false)

//        view.layoutParams.width = view.measuredWidth / 2
        val parentWidth = parent.rootView.width
        val layoutParams = view.layoutParams
        layoutParams.width = parentWidth / 2
        view.layoutParams = layoutParams

        return ProductAdapterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ProductAdapterViewHolder, position: Int) {
        holder.bindData(list[position])

        holder.containerView!!.setOnClickListener {
            Toast.makeText(context, holder.title, Toast.LENGTH_SHORT).show()
            //            if(holder.prodId > 0) {
//                try {
//                    val b = Bundle()
//                    val intent = Intent(context as Activity, ProductDetailActivity::class.java)
//                    product.id = holder.prodId
//                    product.name = holder.description.text.toString()
//                    product.price = 0.0
//                    product.measure = Measure(holder.amount, holder.unit)
//                    product.thumbUrl = holder.image_url
//                    b.putString("origin_screen","list-receipts")
//                    b.putSerializable("product", product)
//                    intent.putExtras(b)
//                    context.startActivity(intent)
//                } catch (e: Exception) {
//                    Log.d("ITEM_RECEIPT_ADAPTER", e.message.toString())
//                }
//            }
        }

    }

    fun addProducts(products: List<Product>) {
        list.addAll(products)
        notifyDataSetChanged()
    }

    fun replaceProducts(products: List<Product>) {
        list.clear()
        list.addAll(products)
        notifyDataSetChanged()
    }

    class ProductAdapterViewHolder(val containerView: View?) : RecyclerView.ViewHolder(containerView!!) {
        val ivImage = containerView!!.findViewById(R.id.image) as ImageView
        val txtTitle = containerView!!.findViewById(R.id.txtProductTitle) as TextView
        val txtPrecoTabela = containerView!!.findViewById(R.id.txtPrecoTabela) as TextView
        val txtPrecoFinal = containerView!!.findViewById(R.id.txtPrecoFinal) as TextView
        val txtPrecoParcelado = containerView!!.findViewById(R.id.txtPrecoParcelado) as TextView
        val txtTotalDesconto = containerView!!.findViewById(R.id.txtTotalDesconto) as TextView

        var title: String? = ""
        var precoTabela: Double? = 0.0
        var precoFinal: Double? = 0.0
        var desconto: Double? = 0.0

        fun bindData(product: Product) {

            val map: HashMap<String, Any> = getSkuAndSeller(product)
            val sku: Sku = map["sku"] as Sku
            val seller: Seller = map["seller"] as Seller

            txtTitle.text = sku.name
            txtPrecoTabela.text = seller.listPrice.toString()
            txtPrecoFinal.text = seller.price.toString()

            val textParcela: String
            textParcela =
                seller.bestInstallment!!.count.toString() + "x de R$ " + seller.bestInstallment!!.value.toString()
            txtPrecoParcelado.text = textParcela

            if (seller.offer != null && seller.offer!! > 0) {
                txtTotalDesconto.text = seller.offer.toString()
            }

            Utils.glideImage(
                containerView?.context!!,
                sku.images!![0].imageUrl!!,
                ivImage,
                R.drawable.product_placeholder
            )

            title = sku.name
            precoTabela = seller.listPrice
            precoFinal = seller.price
            desconto = seller.offer
        }

        private fun getSkuAndSeller(product: Product): HashMap<String, Any> {

            val map: HashMap<String, Any> = HashMap()

            for (sku in product.skus!!) {
                for ((i, seller) in sku.sellers!!.withIndex()) {
                    if (i == 0) {
                        map["sku"] = sku
                        map["seller"] = seller
                    } else {
                        val s: Seller = map["seller"] as Seller
                        if (seller.price!! < s.price!!) {
                            map.clear()
                            map["sku"] = sku
                            map["seller"] = seller
                        }
                    }
                }
            }

            return map
        }

    }


}