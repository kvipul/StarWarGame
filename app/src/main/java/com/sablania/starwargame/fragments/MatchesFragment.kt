package com.sablania.starwargame.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sablania.starwargame.adapters.MatchesAdapter
import com.sablania.starwargame.databinding.FragmentMatchesBinding
import com.sablania.starwargame.viewmodels.GameViewModel

class MatchesFragment : Fragment() {
    private lateinit var binding: FragmentMatchesBinding
    private lateinit var gameViewModel: GameViewModel
    private lateinit var matchesAdapter: MatchesAdapter
    private var playerId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        playerId = arguments!!.getInt(ID)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMatchesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initViewModel()
    }

    private fun initView() {
        binding.apply {
            matchesAdapter = MatchesAdapter(playerId) {

            }
            rvMatches.adapter = matchesAdapter
            rvMatches.layoutManager = LinearLayoutManager(context)
            rvMatches.addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    private fun initViewModel() {
        gameViewModel = ViewModelProvider(
            activity!!.viewModelStore,
            ViewModelProvider.AndroidViewModelFactory(activity!!.application)
        ).get(GameViewModel::class.java)
        gameViewModel.matchesLiveData.observe(viewLifecycleOwner, Observer {
            matchesAdapter.setData(it)
        })

        gameViewModel.fetchMatchData(playerId)
    }


    companion object {
        val TAG = MatchesFragment::class.java.simpleName
        const val ID = "ID"
        fun newInstance(playerId: Int): MatchesFragment = MatchesFragment().apply {
            arguments = Bundle().apply {
                putInt(ID, playerId)
            }
        }
    }
}
