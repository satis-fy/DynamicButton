package com.example.dynamicbutton

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.dynamicbutton.databinding.Screen1FragmentBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Screen1Fragment : Fragment() {

    private var _binding: Screen1FragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = Screen1FragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            buttonNext.setOnClickListener {
                if (editTextNumber.text.toString().isEmpty()) {
                    toast("Please Enter Number")
                    return@setOnClickListener
                }

                if (editTextNumber.text.toString().toDouble() <= 0) {
                    toast("Please Enter Greater Than Zero Number")
                    return@setOnClickListener
                }

                if (editTextNumber.text.toString().length > 7) {
                    toast("Please Enter Valid Number")
                    return@setOnClickListener
                }

                root.hideKeyboard()
                parentFragmentManager.beginTransaction().apply {
                    replace(R.id.frameLayout, Screen2Fragment().also {
                        it.arguments = bundleOf(
                            "BUNDLE_ARG_NUMBER" to editTextNumber.text.toString()
                        )
                    })
                    addToBackStack(this@Screen1Fragment::class.java.simpleName)
                    commit()
                }
            }
        }
    }
}