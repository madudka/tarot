package com.madudka.tarot.view.cards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.madudka.tarot.CardType
import com.madudka.tarot.R
import com.madudka.tarot.databinding.CardsFragmentBinding
import com.madudka.tarot.model.CardModel
import com.madudka.tarot.view.BaseFragment
import com.madudka.tarot.view.adapter.CardsListAdapter
import com.madudka.tarot.view.adapter.OnItemClickListener
import com.madudka.tarot.viewmodel.cards.CardFullViewModel
import com.madudka.tarot.viewmodel.cards.CardsViewModel

class CardsFragment : BaseFragment<List<CardModel>>() {

    private lateinit var binding: CardsFragmentBinding
    private val viewModel: CardsViewModel by activityViewModels()
    private val cardsListAdapter = CardsListAdapter()
    private val viewModelFull: CardFullViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CardsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val manager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        val animation = AnimationUtils.loadLayoutAnimation(requireContext(), R.anim.layout_anim_fall_down)
        cardsListAdapter.clickListener = clickListener
        binding.rvCards.apply {
            adapter = cardsListAdapter
            layoutManager = manager
            layoutAnimation = animation
            setHasFixedSize(true)
        }

        setSearchView()
        setChips()

        viewModel.getCards().observe(viewLifecycleOwner){
            setData(it)
            updateView()
        }
    }

    override fun updateView() {
        listData?.let { list ->
            cardsListAdapter.updateData(list)
            binding.rvCards.scheduleLayoutAnimation()
        }
    }

    private val clickListener = object : OnItemClickListener<CardModel>{
        override fun onItemClick(item: CardModel, position: Int) {
            viewModelFull.loadCardFull(item.id)
            findNavController().navigate(R.id.action_cardsFragment_to_cardsInfoFragment)
        }
    }

    private fun setSearchView(){
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                filterData(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterData(newText)
                return false
            }
        })
    }

    private fun filterData(s: String? = binding.searchView.query.toString()) {
        val cardTypeList = mutableListOf(
            CardType.MAJOR_ARCANA,
            CardType.WANDS,
            CardType.CUPS,
            CardType.SWORDS,
            CardType.PENTACLES
        )

        if (!binding.chipMajorArcana.isChecked) cardTypeList.remove(CardType.MAJOR_ARCANA)
        if (!binding.chipWands.isChecked) cardTypeList.remove(CardType.WANDS)
        if (!binding.chipCups.isChecked) cardTypeList.remove(CardType.CUPS)
        if (!binding.chipSwords.isChecked) cardTypeList.remove(CardType.SWORDS)
        if (!binding.chipPentacles.isChecked) cardTypeList.remove(CardType.PENTACLES)

        s?.let {
            listData?.let { list ->
                cardsListAdapter.updateData(
                    list.filter { item -> item.type in cardTypeList.map { it.id } }
                        .filter { it.name.lowercase().contains(s.trim()) }
                )
            }
        }
    }

    private fun setChips(){
        binding.chipGroupFilter.setOnCheckedStateChangeListener { _, _ ->
            filterData()
        }
    }
}