package uz.eloving.quizgame.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateLayoutParams
import uz.eloving.quizgame.R
import uz.eloving.quizgame.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        changeIconOf()
        binding.play.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.addToBackStack(null)
                ?.replace(R.id.container, LevelFragment())?.commit()
        }
        return binding.root
    }

    private fun changeIconOf() {
        arrayListOf(
            binding.all, binding.europe, binding.asia,
            binding.northAmerica, binding.southAmerica
        ).forEach { item ->
            item.setOnClickListener {
                arrayListOf(binding.ivBgLeft, binding.bgRight).forEach {
                    it.updateLayoutParams<ConstraintLayout.LayoutParams> {
                        topToTop = item.id
                        bottomToBottom = item.id
                        arrayListOf(binding.lottieInfo, binding.lottiePlay)
                            .forEach { animation -> animation.playAnimation() }
                    }
                }
            }
        }
    }
}