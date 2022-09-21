package uz.eloving.quizgame.ui

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import uz.eloving.quizgame.R
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
        parentFragmentManager.setFragmentResultListener(
            "message3",
            viewLifecycleOwner
        ) { _: String, bundle: Bundle ->
            setStars(bundle.getInt("correct"))
        }
        return binding.root
    }

    private fun backPressed() {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.container, MainFragment())?.commit()
    }

    private fun setStars(data: Int) {
        when (data) {
            in 0..3 -> {
                binding.star1.visibility = View.VISIBLE
            }
            in 4..6 -> {
                binding.star1.visibility = View.VISIBLE
                binding.star2.visibility = View.VISIBLE
            }
            in 7..9 -> {
                binding.star1.visibility = View.VISIBLE
                binding.star2.visibility = View.VISIBLE
                binding.star3.visibility = View.VISIBLE
            }
            in 10..12 -> {
                binding.star1.visibility = View.VISIBLE
                binding.star2.visibility = View.VISIBLE
                binding.star3.visibility = View.VISIBLE
                binding.star4.visibility = View.VISIBLE
            }
            in 13..15 -> {
                binding.star1.visibility = View.VISIBLE
                binding.star2.visibility = View.VISIBLE
                binding.star3.visibility = View.VISIBLE
                binding.star4.visibility = View.VISIBLE
                binding.star5.visibility = View.VISIBLE
            }
            else -> {

            }
        }

    }

}