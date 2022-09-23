package uz.eloving.quizgame.ui

import android.content.Context
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import uz.eloving.quizgame.R
import uz.eloving.quizgame.data.PrefManager
import uz.eloving.quizgame.databinding.FragmentResultBinding

class ResultFragment : Fragment() {
    private lateinit var binding: FragmentResultBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    backPressed()
                    isEnabled = false
                }
            })
        binding = FragmentResultBinding.inflate(inflater, container, false)
        setStars(
            when (PrefManager.getContinent(requireContext())) {
                0 -> PrefManager.getHighScoreAll(requireContext())
                1 -> PrefManager.getHighScoreAsia(requireContext())
                2 -> PrefManager.getHighScoreEurope(requireContext())
                3 -> PrefManager.getHighScoreNorthAmerica(requireContext())
                4 -> PrefManager.getHighScoreSouthAmerica(requireContext())
                else -> PrefManager.getHighScoreAll(requireContext())
            }
        )
        binding.tryAgain.setOnClickListener {
            backPressed()
        }
        return binding.root

    }

    private fun backPressed() {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.container, MainFragment())?.commit()
    }


    private fun setStars(data: Int) {
        val target = when (data) {
            in 0..3 -> 1
            in 4..6 -> 2
            in 7..9 -> 3
            in 10..12 -> 4
            in 13..15 -> 5
            else -> 1
        }
        val arr = arrayListOf(
            binding.star1,
            binding.star2,
            binding.star3,
            binding.star4,
            binding.star5
        )
        arr.forEach {
            if (arr.indexOf(it) <= target) {
                it.setImageResource(R.drawable.yulduzcha1)
            }
        }
    }
}