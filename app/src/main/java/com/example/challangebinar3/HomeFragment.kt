package com.example.challangebinar3
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.challangebinar3.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val foodData = ArrayList<ParcelMakanan>()

    private val viewList : Boolean = true

    var currentView = viewList

    private val drawable = arrayListOf(
        R.drawable.list_format,
        R.drawable.griddddd
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.verticalRv.setHasFixedSize(true)
        foodData.addAll(getFood())
        showRecycleView()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.listimg.setOnClickListener {
            if (currentView == viewList){
                binding.listimg.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireActivity(), R.drawable.list_format
                    )
                )
                gridview()
            } else{
                binding.listimg.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireActivity(), R.drawable.griddddd
                    )
                )
                viewList()
            }
        }
    }

    private fun viewList() {

    }

    private fun gridview(){

    }
    private fun getFood(): ArrayList<ParcelMakanan>{
        val dataImg = resources.obtainTypedArray(R.array.data_drawableImg)

        val name = resources.getStringArray(R.array.data_makananName)

        val price = resources.getStringArray(R.array.data_harga)

        val desc = resources.getStringArray(R.array.deskripsi_menu)

        val listFood = ArrayList<ParcelMakanan>()

        for (i in name.indices){
            val food = ParcelMakanan(dataImg.getResourceId(i,-1), name[i], price[i], desc[i] )
            listFood.add(food)
        }
        return listFood
    }

    private fun showRecycleView (){
        binding.verticalRv.layoutManager = GridLayoutManager(requireActivity(), 2 )
        val foodAdapter = HorizontalAdapter(foodData)
        binding.verticalRv.adapter = foodAdapter
    }



}