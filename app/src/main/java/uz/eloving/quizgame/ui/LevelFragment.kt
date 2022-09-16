package uz.eloving.quizgame.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import uz.eloving.quizgame.utils.AdapterLevel
import uz.eloving.quizgame.data.MockData
import uz.eloving.quizgame.databinding.FragmentLevelBinding

class LevelFragment : Fragment() {
    private lateinit var binding: FragmentLevelBinding
    private lateinit var adapter: AdapterLevel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLevelBinding.inflate(inflater, container, false)
        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
        adapter = AdapterLevel()
        binding.rvLevel.adapter = adapter
        binding.rvLevel.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapter.updateList(MockData.data)
        return binding.root
    }

}