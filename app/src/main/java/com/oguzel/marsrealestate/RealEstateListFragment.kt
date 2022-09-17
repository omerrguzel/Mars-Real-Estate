package com.oguzel.marsrealestate

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.oguzel.marsrealestate.data.MarsInfoItem
import com.oguzel.marsrealestate.databinding.FragmentRealEstateListBinding
import com.oguzel.marsrealestate.listener.IMarsPhotoClickListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RealEstateListFragment : Fragment() {

    private var _binding: FragmentRealEstateListBinding? = null
    private val binding get() = _binding!!
    private val gridRecyclerViewAdapter = GridRecyclerViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRealEstateListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupData()
    }

    /**
     * Function to bind recyclerView adapter to recyclerView in UI
     */
    private fun setupRecyclerView() = binding.marsGridPhotos.apply {
        adapter = gridRecyclerViewAdapter
        layoutManager = GridLayoutManager(requireContext(), 2)
        gridRecyclerViewAdapter.setMarsPhotoOnClickListener(object : IMarsPhotoClickListener {
            override fun onClick(name : MarsInfoItem) {
                val action =
                    RealEstateListFragmentDirections.actionRealEstateListFragmentToRealEstateDetailFragment(
                        name.id,
                        name.price,
                        name.type,
                        name.img_src
                    )
                findNavController().navigate(action)
            }
        })
    }

    /**
     * Function to get properties of MarsApi and set to recyclerView adapter
     */
    private fun setupData() {
        MarsApi.retrofitService.getProperties().enqueue(object : Callback<List<MarsInfoItem>> {
            override fun onResponse(
                call: Call<List<MarsInfoItem>>,
                response: Response<List<MarsInfoItem>>
            ) {
                response.body()?.let { responseList ->
                    gridRecyclerViewAdapter.setData(responseList)
                    Log.v("TAG", responseList.toString())
                }
            }

            override fun onFailure(call: Call<List<MarsInfoItem>>, t: Throwable) {
                Log.v("TAG", t.message.toString())
            }
        })
    }
}
