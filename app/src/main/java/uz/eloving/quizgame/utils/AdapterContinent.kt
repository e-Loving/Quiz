package uz.eloving.quizgame.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import uz.eloving.quizgame.R
import uz.eloving.quizgame.data.PrefManager
import uz.eloving.quizgame.model.ModelCardViewContinent
import uz.eloving.quizgame.databinding.ItemContinentBinding
import uz.eloving.quizgame.ui.LevelFragment
import uz.eloving.quizgame.utils.SafeClickListener.Companion.setSafeOnClickListener

class AdapterContinent(private var activity: FragmentActivity) : RecyclerView.Adapter<AdapterContinent.ViewHolder>() {
    private var list = arrayListOf<ModelCardViewContinent>()
    var onItemClick: ((category: ModelCardViewContinent) -> Unit)? = null

    inner class ViewHolder(private val binding: ItemContinentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ModelCardViewContinent) {
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
            binding.lottiePlay.setSafeOnClickListener {
                activity.supportFragmentManager.beginTransaction()
                    .replace(R.id.container, LevelFragment()).commit()
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
    fun updateList(list: ArrayList<ModelCardViewContinent>) {
        this.list = list
        notifyDataSetChanged()
    }
}