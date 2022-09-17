package uz.eloving.quizgame.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentResultListener
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
            })
        parentFragmentManager.setFragmentResultListener(
            "message",
            viewLifecycleOwner
        ) { _: String, bundle: Bundle ->
            Toast.makeText(requireContext(), bundle.getInt("option").toString(), Toast.LENGTH_SHORT)
                .show()
        }
        adapter = AdapterLevel()
        binding.rvLevel.adapter = adapter
        binding.rvLevel.layoutManager =
            LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            ) // LayoutManager changed to vertically
        adapter.updateList(MockData.leveData) // Add Levels to RecyclerView
        adapter.onItemClick = {
            Toast.makeText(requireContext(), it.countOfQuestions.toString(), Toast.LENGTH_SHORT)
                .show()
        }
        return binding.root
    }

    private fun backPressed() {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.container, MainFragment())?.commit()
    }
}