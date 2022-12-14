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
import uz.eloving.quizgame.model.ModelCardViewContinent
import uz.eloving.quizgame.data.MockData
import uz.eloving.quizgame.data.PrefManager
import uz.eloving.quizgame.databinding.FragmentMainBinding
import uz.eloving.quizgame.utils.AdapterContinent

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: AdapterContinent
    private var option = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        adapter = activity?.let { AdapterContinent(it) }!!
        binding.rvContinent.adapter = adapter
        binding.rvContinent.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapter.updateList(MockData.continentData)
        adapter.onItemClick = { View ->
            MockData.continentData.forEach { it.isVisible = false }
            option = MockData.continentData.indexOf(View)
            MockData.continentData[MockData.continentData.indexOf(View)] =
                ModelCardViewContinent(View.image, true)
            adapter.updateList(MockData.continentData)
        }
        option = PrefManager.getContinent(requireContext())
        binding.btnPlay.setOnClickListener {
            PrefManager.setContinent(requireContext(), option)
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, LevelFragment())?.commit() // fragmentlarni alishtiradi
        }

        adapter.onItemClick2={ View ->
            MockData.continentData.forEach { it.isVisible = false }
            option = MockData.continentData.indexOf(View)
            MockData.continentData[MockData.continentData.indexOf(View)] =
                ModelCardViewContinent(View.image, true)
            adapter.updateList(MockData.continentData)
            PrefManager.setContinent(requireContext(), option)
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, LevelFragment())?.commit()
        }

        return binding.root
    }


}