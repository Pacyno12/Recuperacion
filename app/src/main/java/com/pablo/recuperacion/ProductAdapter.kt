package com.pablo.recuperacion

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pablo.recuperacion.data.model.ProductModel
import com.pablo.recuperacion.databinding.ItemProductBinding

class ProductAdapter(private val listener: (ProductModel) -> Unit) :
    ListAdapter<ProductModel, ProductAdapter.ViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProductBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = getItem(position)
        holder.binding.ivPhoto.imageUrl(product.imageUrl)
        holder.binding.tvName.text = product.name
        holder.binding.tvDiscountPrice.text = product.discountPrice.toString()
        holder.binding.tvStock.text = product.stock.toString()
        if(product.stock <= 5)
        {
            holder.binding.txtPocasUnidades.isVisible = true
        }else{
            holder.binding.txtPocasUnidades.isInvisible
        }
        holder.binding.root.setOnClickListener {
            listener(product)
        }
    }

    inner class ViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root)
}
private class DiffUtilCallback: DiffUtil.ItemCallback<ProductModel>(){
    override fun areItemsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean = oldItem.imageUrl == newItem.imageUrl

}