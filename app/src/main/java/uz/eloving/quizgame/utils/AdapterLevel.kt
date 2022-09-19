package uz.eloving.quizgame.utils

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.eloving.quizgame.model.ModelCardViewLevel
import uz.eloving.quizgame.databinding.ItemLevelBinding
import java.util.ArrayList

class AdapterLevel : RecyclerView.Adapter<AdapterLevel.ViewHolder>() {
    private var list = arrayListOf<ModelCardViewLevel>()
    var onItemClick: ((category: ModelCardViewLevel) -> Unit)? = null

    inner class ViewHolder(private val binding: ItemLevelBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: ModelCardViewLevel) {
            binding.pic.setImageResource(data.pic)
            binding.degree.setImageResource(data.degree)
            binding.countOfQuestions.text = "0/${data.countOfQuestions}"
        }

        init {
            binding.cardView.setOnClickListener {
                onItemClick?.invoke(list[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ItemLevelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(holder)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: ArrayList<ModelCardViewLevel>) {
        this.list = list
        notifyDataSetChanged()
    }
}