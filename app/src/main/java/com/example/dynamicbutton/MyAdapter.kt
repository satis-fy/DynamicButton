package com.example.dynamicbutton

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dynamicbutton.databinding.ItemButtonBinding

class MyAdapter(
    private val listNumber: MutableList<MyData>,
    private val onItemCLick: (MyData) -> Unit
) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val binding: ItemButtonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MyData) {
            binding.buttonDynamic.apply {
                text = item.number.toString()
                setBackgroundColors(item.bgColor)
                isEnabled = item.bgColor == R.color.colorBlue

                setOnClickListener {
                    onItemCLick.invoke(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemButtonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listNumber[position])
    }

    override fun getItemCount() = listNumber.size

    fun updateItem(myData: MyData) {
        val index = listNumber.indexOf(myData)
        listNumber[index] = myData
        notifyItemChanged(index)
    }

    fun getAdapterList() = listNumber
}