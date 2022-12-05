package com.example.dynamicbutton

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.dynamicbutton.databinding.Screen2FragmentBinding
import kotlinx.coroutines.*

class Screen2Fragment : Fragment() {

    private var _binding: Screen2FragmentBinding? = null
    private val binding get() = _binding!!

    private val number: String by lazy {
        arguments?.getString("BUNDLE_ARG_NUMBER") ?: ""
    }

    private val listNumber = mutableListOf<MyData>()
    private val myAdapter: MyAdapter by lazy {
        MyAdapter(listNumber) { myData ->
            myData.bgColor = R.color.colorRed
            myAdapter.updateItem(myData)

            if (!myAdapter.getAdapterList().all { it.bgColor == R.color.colorRed }) {
                val nextData = listNumber.getRandomElementWithBlue()
                myAdapter.updateItem(nextData)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = Screen2FragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            addDataInList()
            if (listNumber.isNotEmpty()) {
                val myData = listNumber.getRandomElementWithBlue()
                listNumber[listNumber.indexOf(myData)] = myData
                binding.recyclerView.adapter = myAdapter
            }
        }
    }

    private suspend fun addDataInList() = withContext(Dispatchers.IO) {
        for (i in 1 until (number.toInt() + 1)) {
            listNumber.add(MyData(i))
        }
    }
}