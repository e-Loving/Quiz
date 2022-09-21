package uz.eloving.quizgame.ui

import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
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
    private var keys = 3
    private lateinit var timer: CountDownTimer
    private var allQuestions = 15
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
                    isEnabled = false
                    backPressed()
                }
            })
        parentFragmentManager.setFragmentResultListener(
            "message2",
            viewLifecycleOwner
        ) { _: String, bundle: Bundle ->
            option = when (bundle.getInt("option")) {
                0 -> MockData.all
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
                timer.cancel()
                if (flags.indexOf(flag) == usedData.indexOf(correctAnswerIndex!!)) {
                    correct++
                    downloadPhoto(option)
                } else {
                    incorrect++
                    downloadPhoto(option)
                }

            }
        }
        binding.btnBack.setOnClickListener {
            backPressed()
        }
        binding.keyCard.setOnClickListener {
            when (usedData.indexOf(correctAnswerIndex)) {
                0 -> alertCorrect(binding.flag1)
                1 -> alertCorrect(binding.flag2)
                2 -> alertCorrect(binding.flag3)
                3 -> alertCorrect(binding.flag4)
            }

        }
        return binding.root
    }

    private fun downloadPhoto(continent: HashMap<String, String>) {
        when (incorrect) {
            0 -> binding.ivHeart1.visibility = View.VISIBLE
            1 -> binding.ivHeart1.visibility = View.GONE
            2 -> binding.ivHeart2.visibility = View.GONE
            3 -> binding.ivHeart3.visibility = View.GONE
            4 -> {
                binding.ivHeart4.visibility = View.GONE
                next()
            }
        }
        if (allQuestions == 0) {
            next()
        } else {
            binding.allQuestions.text = allQuestions.toString()
            allQuestions--
            reset()
            when (keys) {
                2 -> binding.ivKey1.visibility = View.GONE
                1 -> binding.ivKey2.visibility = View.GONE
                0 -> {
                    binding.ivKey3.visibility = View.GONE
                    binding.keyCard.isClickable = false
                }
            }
            usedData.clear()
            startTimer()
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
                ).placeholder(R.drawable.ic_flags).into(flag)
            }
        }

    }

    private fun backPressed() {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.container, MainFragment())?.commit()
    }

    private fun startTimer() {
        if (incorrect < 4) {
            binding.progressHorizontal.progress = 30_000
            timer = object : CountDownTimer(30_000, 1_000) {
                override fun onTick(millisUntilFinished: Long) {
                    binding.progressHorizontal.progress = millisUntilFinished.toInt()
                }

                @RequiresApi(Build.VERSION_CODES.M)
                override fun onFinish() {
                    incorrect++
                    allQuestions--
                    downloadPhoto(option)
                }
            }
            timer.start()
        }

    }

    private fun alertCorrect(img: ImageView) {
        flags.forEach {
            it.alpha = 0.3f
        }
        img.alpha = 1F
        timer.cancel()
        keys--
    }

    private fun reset() {
        arrayListOf(binding.flag1, binding.flag2, binding.flag3, binding.flag4).forEach {
            it.alpha = 1F
        }
    }

    private fun next() {
        timer.cancel()
        parentFragmentManager.setFragmentResult(
            "message3",
            Bundle(bundleOf("option" to bundleOf("correct" to correct)))
        )
        Toast.makeText(requireContext(), correct.toString(), Toast.LENGTH_SHORT).show()
        activity?.supportFragmentManager?.beginTransaction()
            ?.add(R.id.container, ResultFragment())?.commit()
    }
}