package com.oguzel.marsrealestate

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
import android.text.style.ForegroundColorSpan
import android.text.style.TypefaceSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.oguzel.marsrealestate.databinding.FragmentGetStartedBinding


class GetStartedFragment : Fragment() {

    private var _binding: FragmentGetStartedBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGetStartedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val welcomeText = binding.textView
        welcomeText.text = writeWelcome()

        binding.buttonLetsGo.setOnClickListener {
            findNavController().navigate(R.id.action_getStartedFragment_to_loginFragment)
        }

    }

    private fun writeWelcome() : SpannableString {
        val fullText = getString(R.string.let_s_find_your_nhome_in_mars_with_nmars_real_estate)
        val marsText = getString(R.string.mars)
        val marsRealEstateText = getString(R.string.mars_real_estate)
        val startIndex = fullText.indexOf(marsText)
        val endIndex = startIndex + marsText.length
        val secondStartIndex = fullText.indexOf(marsRealEstateText)
        val secondEndIndex = secondStartIndex + marsRealEstateText.length
        val sunColor = ContextCompat.getColor(requireContext(),R.color.sun)
        val boldFont = ResourcesCompat.getFont(requireContext(),R.font.opensans_extrabold)

        return SpannableString(fullText).apply {
            setSpan(
                TypefaceSpan(boldFont!!),
                startIndex,
                endIndex,
                SPAN_EXCLUSIVE_EXCLUSIVE
            )
            setSpan(
                ForegroundColorSpan(sunColor),
                startIndex,
                endIndex,
                SPAN_EXCLUSIVE_EXCLUSIVE
            )
            setSpan(
                ForegroundColorSpan(sunColor),
                secondStartIndex,
                secondEndIndex,
                SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
    }
}