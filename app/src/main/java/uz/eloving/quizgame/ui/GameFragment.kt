package uz.eloving.quizgame.ui

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import uz.eloving.quizgame.R
import uz.eloving.quizgame.data.MockData
import uz.eloving.quizgame.data.PrefManager
import uz.eloving.quizgame.databinding.FragmentGameBinding
import uz.eloving.quizgame.utils.SafeClickListener.Companion.setSafeOnClickListener
import java.util.*


open class GameFragment : Fragment() {
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

        isInternetOn()

        return binding.root
    }


    private fun isInternetOn(): Boolean {
        val connec =
            requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        //check for network connections
        if (connec.getNetworkInfo(0)!!.state == NetworkInfo.State.CONNECTED || connec.getNetworkInfo(
                0
            )!!.state == NetworkInfo.State.CONNECTING || connec.getNetworkInfo(1)!!.state == NetworkInfo.State.CONNECTING || connec.getNetworkInfo(
                1
            )!!.state == NetworkInfo.State.CONNECTED
        ) {

            Toast.makeText(activity, "Connected", Toast.LENGTH_LONG).show()
            flags = arrayListOf(binding.flag1, binding.flag2, binding.flag3, binding.flag4)
            allQuestions = when (PrefManager.getContinent(requireContext())) {
                0 -> MockData.all.size
                1 -> MockData.asia.size
                2 -> MockData.europe.size
                3 -> MockData.north_america.size
                4 -> MockData.south_america.size
                else -> MockData.all.size

            }
            arrayListOf(binding.main, binding.secondary).forEach {
                it.setSafeOnClickListener {

                }
            }
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

            option = when (PrefManager.getContinent(requireContext())) {
                0 -> MockData.all
                1 -> MockData.europe
                2 -> MockData.asia
                3 -> MockData.north_america
                4 -> MockData.south_america
                else -> MockData.europe
            }
            downloadPhoto(option)


            flags.forEach { flag ->
                flag.setOnClickListener {
                    timer.cancel()
                    if (flags.indexOf(flag) == usedData.indexOf(correctAnswerIndex!!)) {
                        flag.setBackgroundResource(R.drawable.correct_border_shape)
                        correct++
                        Handler().postDelayed({
                            downloadPhoto(option)
                        }, 2000)
                    } else {
                        flag.setBackgroundResource(R.drawable.incorrect_border_shape)
                        incorrect++
                        Handler().postDelayed({
                            downloadPhoto(option)
                        }, 2000)
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
            return true
        } else if (connec.getNetworkInfo(0)!!.state == NetworkInfo.State.DISCONNECTED ||
            connec.getNetworkInfo(1)!!.state == NetworkInfo.State.DISCONNECTED
        ) {

            Toast.makeText(activity, "Not Connected", Toast.LENGTH_LONG).show()
            progressDialog()
            return false
        }
        return false
    }

    fun progressDialog() {
        val prgDialog = ProgressDialog(requireContext())
        prgDialog.setTitle("Progress Dialog")
        prgDialog.setMessage("check the internet")
        prgDialog.show()


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
        binding.flag1.setBackgroundResource(R.drawable.flag_background)
        binding.flag2.setBackgroundResource(R.drawable.flag_background)
        binding.flag3.setBackgroundResource(R.drawable.flag_background)
        binding.flag4.setBackgroundResource(R.drawable.flag_background)

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
                ).into(flag)
            }
        }

    }

    private fun backPressed() {
        timer.cancel()
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.container, MainFragment())?.commit()
    }

    private fun startTimer() {

        if (incorrect < 4) {
            binding.progressHorizontal.progress = 16_000
            timer = object : CountDownTimer(16_000, 1_000) {
                override fun onTick(millisUntilFinished: Long) {


                    if (millisUntilFinished < 6000) {
                        binding.timer.setTextColor(0xFFE6DFDF.toInt())
                        binding.cadrtimer.setCardBackgroundColor(0xFFD30C0C.toInt())

                    } else if (millisUntilFinished > 6000) {
                        binding.timer.setTextColor(0xFF090808.toInt())
                        binding.cadrtimer.setCardBackgroundColor(0xFFE6DFDF.toInt())

                    }


                    binding.timer.text = timeConversion((millisUntilFinished / 1_000).toInt())
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

    private fun timeConversion(totalSeconds: Int): String {
        val MINUTES_IN_AN_HOUR = 60
        val SECONDS_IN_A_MINUTE = 60
        val seconds = totalSeconds % SECONDS_IN_A_MINUTE
        val totalMinutes = totalSeconds / SECONDS_IN_A_MINUTE
        val minutes = totalMinutes % MINUTES_IN_AN_HOUR
        return "$seconds"
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

    @SuppressLint("CommitPrefEdits")
    private fun next() {
        timer.cancel()
        when (PrefManager.getContinent(requireContext())) {
            0 -> PrefManager.setHighScoreAll(requireContext(), correct)
            1 -> PrefManager.setHighScoreAsia(requireContext(), correct)
            2 -> PrefManager.setHighScoreEurope(requireContext(), correct)
            3 -> PrefManager.setHighScoreNorthAmerica(requireContext(), correct)
            4 -> PrefManager.setHighScoreSouthAmerica(requireContext(), correct)
        }


        Toast.makeText(requireContext(), correct.toString(), Toast.LENGTH_SHORT).show()
        activity?.supportFragmentManager?.beginTransaction()
            ?.add(R.id.container, ResultFragment())?.commit()
    }


}