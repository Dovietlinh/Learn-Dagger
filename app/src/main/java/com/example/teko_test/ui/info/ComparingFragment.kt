package com.example.teko_test.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.teko_test.R
import com.example.teko_test.ui.ViewModelFragment
import com.example.teko_test.ui.details.DetailsViewModel

class ComparingFragment : ViewModelFragment() {
    private val viewModel: DetailsViewModel by injectActivityVIewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.comparing_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}