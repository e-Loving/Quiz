package uz.eloving.quizgame.utils

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.eloving.quizgame.data.DataContinent
import uz.eloving.quizgame.databinding.ItemContinentBinding

class AdapterContinent : RecyclerView.Adapter<AdapterContinent.ViewHolder>() {
    private var list = arrayListOf<DataContinent>()
    var onItemClick: ((category: DataContinent) -> Unit)? = null

    inner class ViewHolder(private val binding: ItemContinentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataContinent) {
            val items = arrayListOf(
                binding.ivBgRight,
                binding.ivBgLeft,
                binding.lottieInfo,
                binding.lottiePlay
            )
            if (data.isVisible) {
                items.forEach {
                    it.visibility = View.VISIBLE
                    listOf(
                        binding.lottieInfo,
                        binding.lottiePlay
                    ).forEach { o -> o.playAnimation() }
                }
            } else {
                items.forEach {
                    it.visibility = View.GONE
                }
            }
            binding.ivContinent.setImageResource(data.image)
        }

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(list[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterContinent.ViewHolder {
        val holder =
            ItemContinentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(holder)
    }

    override fun onBindViewHolder(holder: AdapterContinent.ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: ArrayList<DataContinent>) {
        this.list = list
        notifyDataSetChanged()
    }
}