package uz.eloving.quizgame.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import com.bumptech.glide.Glide
import uz.eloving.quizgame.R
import uz.eloving.quizgame.data.MockData
import uz.eloving.quizgame.databinding.FragmentGameBinding
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class GameFragment : Fragment() {
    private lateinit var binding: FragmentGameBinding
    private lateinit var flags: ArrayList<ImageView>
    private var correctAnswerIndex: Int? = null
    private var correct = 0
    private var incorrect = 0
    private var allAnswer = 15
    private var usedData = arrayListOf<Int>()
    private lateinit var option: HashMap<String, String>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameBinding.inflate(inflater, container, false)
        flags = arrayListOf(binding.flag1, binding.flag2, binding.flag3, binding.flag4)
        val randoms = arrayListOf<Int>()
        for (item in 0..3) {
            randoms.add(Random().nextInt(3))
        }
        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    backPressed()
                    isEnabled = false
                }
            })
        parentFragmentManager.setFragmentResultListener(
            "message2",
            viewLifecycleOwner
        ) { _: String, bundle: Bundle ->
            option = when (bundle.getInt("option")) {
                1 -> MockData.europe
                2 -> MockData.asia
                3 -> MockData.north_america
                4 -> MockData.south_america
                else -> MockData.europe
            }
            downloadPhoto(option)
        }
        flags.forEach { flag ->
            flag.setOnClickListener {
                if (flags.indexOf(flag) == usedData.indexOf(correctAnswerIndex!!)) {
                    Toast.makeText(requireContext(), "fine", Toast.LENGTH_SHORT).show()
                    correct++
                    downloadPhoto(option)
                } else {
                    incorrect++
                    when (incorrect) {
                        1 -> binding.ivHeart1.visibility = View.GONE
                        2 -> binding.ivHeart2.visibility = View.GONE
                        3 -> binding.ivHeart3.visibility = View.GONE
                        else -> {
                            Toast.makeText(requireContext(), "tugadi", Toast.LENGTH_SHORT)
                                .show()
                            binding.ivHeart4.visibility = View.GONE
                        }
                    }
                    downloadPhoto(option)
                }
            }
        }
        binding.btnBack.setOnClickListener {
            backPressed()
        }
        return binding.root
    }

    private fun downloadPhoto(continent: HashMap<String, String>) {
        usedData.clear()
        flags.forEach { flag ->
            var random = Random().nextInt(continent.size)
            while (random in usedData) {
                random = Random().nextInt(continent.size)
            }
            usedData.add(random)
            correctAnswerIndex = usedData[Random().nextInt(usedData.size)]
            binding.countryName.text = continent.values.toList()[correctAnswerIndex!!]
            Glide.with(requireContext()).load(
                "https://countryflagsapi.com/png/" + continent.keys.toList()[random]
            ).into(flag)
        }
    }

    private fun backPressed() {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.container, MainFragment())?.commit()
    }
}