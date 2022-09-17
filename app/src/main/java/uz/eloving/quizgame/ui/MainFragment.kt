package uz.eloving.quizgame.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import uz.eloving.quizgame.R
import uz.eloving.quizgame.data.DataContinent
import uz.eloving.quizgame.data.MockData
import uz.eloving.quizgame.databinding.FragmentMainBinding
import uz.eloving.quizgame.utils.AdapterContinent

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: AdapterContinent
    private var option: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        adapter = AdapterContinent()
        binding.rvContinent.adapter = adapter
        binding.rvContinent.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapter.updateList(MockData.continentData)
        adapter.onItemClick = { View ->
            MockData.continentData.forEach { it.isVisible = false }
            option = MockData.continentData.indexOf(View)
            Toast.makeText(requireContext(), option.toString(), Toast.LENGTH_SHORT).show()
            MockData.continentData[MockData.continentData.indexOf(View)] =
                DataContinent(View.image, true)
            adapter.updateList(MockData.continentData)
        }
        binding.btnPlay.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("option", option)
            parentFragmentManager.setFragmentResult("message", bundle)
            activity?.supportFragmentManager?.beginTransaction()
                ?.add(R.id.container, LevelFragment())?.commit()
        } // Replace MainFragment to LevelFragment
        return binding.root
    }
}