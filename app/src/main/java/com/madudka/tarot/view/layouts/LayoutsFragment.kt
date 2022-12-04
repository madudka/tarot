package com.madudka.tarot.view.layouts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.madudka.tarot.utils.LayoutType
import com.madudka.tarot.R
import com.madudka.tarot.databinding.LayoutsFragmentBinding
import com.madudka.tarot.model.LayoutModel
import com.madudka.tarot.view.BaseFragment
import com.madudka.tarot.view.OnKeyboardClosedListener
import com.madudka.tarot.view.adapter.LayoutsListAdapter
import com.madudka.tarot.view.adapter.OnItemClickListener
import com.madudka.tarot.viewmodel.layouts.LayoutsFullViewModel
import com.madudka.tarot.viewmodel.layouts.LayoutsViewModel

class LayoutsFragment : BaseFragment<List<LayoutModel>>() {

    private lateinit var binding: LayoutsFragmentBinding
    private lateinit var keyboardClosedListener: OnKeyboardClosedListener
    private val viewModel: LayoutsViewModel by activityViewModels()
    private val layoutsListAdapter = LayoutsListAdapter()
    private val viewModelFull: LayoutsFullViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LayoutsFragmentBinding.inflate(inflater, container, false)
        activity?.let { keyboardClosedListener = it as OnKeyboardClosedListener }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val manager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        val animation = AnimationUtils.loadLayoutAnimation(requireContext(), R.anim.layout_anim_fall_down)
        layoutsListAdapter.clickListener = clickListener
        binding.rvLayouts.apply {
            adapter = layoutsListAdapter
            layoutManager = manager
            layoutAnimation = animation
            setHasFixedSize(true)
        }

        setSearchView()
        setFilters()

        viewModel.getLayouts().observe(viewLifecycleOwner){
            setData(it)
            updateView()
        }

        viewModel.getError().observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), R.string.dark_forces, Toast.LENGTH_SHORT).show()
        }
    }

    override fun updateView() {
        listData?.let {
            layoutsListAdapter.updateData(it)
            binding.rvLayouts.scheduleLayoutAnimation()
        }
    }

    private val clickListener = object : OnItemClickListener<LayoutModel>{
        override fun onItemClick(item: LayoutModel, position: Int) {
            viewModelFull.loadLayout(item.id)
            findNavController().navigate(R.id.action_layoutsFragment_to_infoFragment)
        }

    }

    private fun setSearchView(){
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                filterData(query)
                keyboardClosedListener.onKeyboardClosed()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterData(newText)
                return false
            }

        })
    }

    private fun filterData(s: String? = binding.searchView.query.toString()){
        val filterText = (binding.menuFilter.editText as? AutoCompleteTextView)?.text.toString()
        val typeId = LayoutType.values().firstOrNull { it.description == filterText }?.id

        listData?.let { _list ->
            var list = if (typeId == null) _list else _list.filter { it.type == typeId }
            if (!s.isNullOrBlank()) list = list.filter { it.name.lowercase().contains(s.trim()) }
            layoutsListAdapter.updateData(list)
        }
    }

    private fun setFilters(){
        val items = resources.getStringArray(R.array.layouts_filer_list)
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item_layouts_filter, items)
        (binding.menuFilter.editText as? AutoCompleteTextView)?.setAdapter(adapter)
        (binding.menuFilter.editText as? AutoCompleteTextView)?.setText("Все", false)
        (binding.menuFilter.editText as? AutoCompleteTextView)?.setDropDownBackgroundDrawable(
                ResourcesCompat.getDrawable(resources, R.drawable.custom_filter_spinner_bg_shape, null)
            )

        (binding.menuFilter.editText as? AutoCompleteTextView)?.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, _, _ ->
                filterData()
            }
    }
}