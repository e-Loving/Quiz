package uz.eloving.quizgame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import uz.eloving.quizgame.databinding.FragmentInfoBinding
import uz.eloving.quizgame.ui.MainFragment

class InfoFragment : Fragment() {
    private lateinit var binding: FragmentInfoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoBinding.inflate(inflater, container, false)


binding.infoBackBtn.setOnClickListener {
    backPressed()
}
        binding.tvInfo.text = "Who wants to be a master of finding a country image and name the Flag helps you.\n" +
                "The Flag Application is useful for learning country flags and names.\n" +
                "There are only country flags and names, not only images, but also games.\n" +
                "The game is like a quiz country's name is given. You must find country images.\n" +
                "Use it. And Enjoy \uD83D\uDE0A"


        binding.btnShare.setOnClickListener {
            Toast.makeText(requireContext(), "Endi qo'yamiz ", Toast.LENGTH_SHORT).show()
        }


        return binding.root
    }

    private fun backPressed() {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.container, MainFragment())?.commit()
        activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
    }
}