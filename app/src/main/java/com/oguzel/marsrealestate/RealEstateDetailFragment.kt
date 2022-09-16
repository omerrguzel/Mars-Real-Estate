package com.oguzel.marsrealestate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.oguzel.marsrealestate.databinding.FragmentRealEstateDetailBinding

class RealEstateDetailFragment : Fragment(), ItemUtil {

    private var _binding: FragmentRealEstateDetailBinding? = null
    private val binding get() = _binding!!
    private val args: RealEstateDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRealEstateDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    fun initView() {
        bindImage(binding.imageViewMarsImage, args.imageURL)
        binding.textViewID.text = args.id
        binding.buttonBuyOrRent.text =
            requireContext().getString(R.string.buy_or_rent, args.type?.uppercase())
        binding.textViewPrice.text = formatPrice(args.price) + "$"
    }
}
