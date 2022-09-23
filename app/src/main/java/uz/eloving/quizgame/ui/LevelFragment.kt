package uz.eloving.quizgame.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import uz.eloving.quizgame.R
import uz.eloving.quizgame.data.MockData
import uz.eloving.quizgame.databinding.FragmentLevelBinding
import uz.eloving.quizgame.utils.AdapterLevel

class LevelFragment : Fragment() {
    private lateinit var binding: FragmentLevelBinding
    private lateinit var adapter: AdapterLevel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLevelBinding.inflate(inflater, container, false)
        binding.btnBack.setOnClickListener {
            backPressed()
        }
        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    backPressed()
                    isEnabled = false
                }
            }) // orqaga bosilishni eshitadi
        parentFragmentManager.setFragmentResultListener(
            "message",
            viewLifecycleOwner
        ) { _: String, bundle: Bundle ->
            when (bundle.getInt("option")) {
                0 -> MockData.levelData.forEach { it.countOfQuestions = MockData.all.size }
                1 -> MockData.levelData.forEach { it.countOfQuestions = MockData.asia.size }
                2 -> MockData.levelData.forEach { it.countOfQuestions = MockData.europe.size }
                3 -> MockData.levelData.forEach { it.countOfQuestions = MockData.north_america.size }
                4 -> MockData.levelData.forEach { it.countOfQuestions = MockData.south_america.size }
            }
            parentFragmentManager.setFragmentResult(
                "message2",
                Bundle(bundleOf("option" to bundle.getInt("option")))
            )
        } // ota fragmentdan eski bolasini natijasini oladi
        adapter = AdapterLevel()
        binding.rvLevel.adapter = adapter
        binding.rvLevel.layoutManager =
            LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            ) // LayoutManager changed to vertically
        adapter.updateList(MockData.levelData) // Add Levels to RecyclerView
        adapter.onItemClick = {
            if (it.clickable) {
                activity?.supportFragmentManager?.beginTransaction()
                    ?.add(R.id.container, GameFragment())?.commit()
            }

        }
        return binding.root
    }

    private fun backPressed() {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.container, MainFragment())?.commit()
        activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
    }
}