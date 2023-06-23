package com.pinekim.android_qr_scannver.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pinekim.android_qr_scannver.R
import com.pinekim.android_qr_scannver.data.model.LottoModel

class LottoRVAdapter(val context: Context) : RecyclerView.Adapter<LottoRVAdapter.ViewHolder>() {
    private var lottoNumList = mutableListOf<LottoModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LottoRVAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.lotto_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: LottoRVAdapter.ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return lottoNumList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val number1: TextView = itemView.findViewById(R.id.lotto_num_1)
        val number2: TextView = itemView.findViewById(R.id.lotto_num_2)
        val number3: TextView = itemView.findViewById(R.id.lotto_num_3)
        val number4: TextView = itemView.findViewById(R.id.lotto_num_4)
        val number5: TextView = itemView.findViewById(R.id.lotto_num_5)

        fun onBind(data: LottoModel) {

        }

    }

}