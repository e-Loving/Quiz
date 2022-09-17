package uz.eloving.quizgame.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import uz.eloving.quizgame.R
import uz.eloving.quizgame.data.MockData
import uz.eloving.quizgame.databinding.FragmentMainBinding
import uz.eloving.quizgame.utils.AdapterContinent
import uz.eloving.quizgame.data.DataContinent

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: AdapterContinent
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
            MockData.continentData[MockData.continentData.indexOf(View)] =
                DataContinent(View.image, true)
            adapter.updateList(MockData.continentData)
        }
        binding.btnPlay.setOnClickListener {
            LevelFragment().arguments?.putString("name", "name")
            activity?.supportFragmentManager?.beginTransaction()?.addToBackStack(null)
                ?.replace(R.id.container, LevelFragment())?.commit()
        } // Replace MainFragment to LevelFragment
        return binding.root
    }

}