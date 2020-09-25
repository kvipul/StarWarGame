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
import com.sablania.starwargame.adapters.PlayersAdapter
import com.sablania.starwargame.databinding.FragmentPlayersBinding
import com.sablania.starwargame.viewmodels.GameViewModel

class PlayersFragment : Fragment() {
    private lateinit var binding: FragmentPlayersBinding
    private lateinit var gameViewModel: GameViewModel
    private lateinit var playersAdapter: PlayersAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlayersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initViewModel()
    }

    private fun initView() {
        binding.apply {
            playersAdapter = PlayersAdapter()
            rvPlayers.adapter = playersAdapter
            rvPlayers.layoutManager = LinearLayoutManager(context)
            rvPlayers.addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    private fun initViewModel() {
        gameViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(activity!!.application)
        ).get(GameViewModel::class.java)
        gameViewModel.playersLiveData.observe(viewLifecycleOwner, Observer {
            playersAdapter.setData(it)
        })

        gameViewModel.init()
        gameViewModel.fetchPlayersData()
    }


    companion object {
        val TAG = PlayersFragment::class.java.simpleName
        fun newInstance(): PlayersFragment = PlayersFragment()
    }
}
