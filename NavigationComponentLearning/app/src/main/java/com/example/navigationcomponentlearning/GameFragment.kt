package com.example.navigationcomponentlearning

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_game.*

class GameFragment : Fragment() {

  // private val args by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController: NavController = Navigation.findNavController(view)

        tv_numGame.text = arguments?.getString("number")

        val button: Button = view.findViewById(R.id.b_finishGame)

        val action =
            GameFragmentDirections.actionGameFragmentToEndGameFragment("Hey im the data you pass!")
        button.setOnClickListener {
            navController.navigate(action)
        }

    }

}