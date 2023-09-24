package com.example.challangebinar3
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challangebinar3.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val foodData = ArrayList<ParcelMakanan>()

    private val category = ArrayList<Category>()

    private var viewList : Boolean = true

    private var typeLayout = true

//    var currentView = viewList

    private val drawable = arrayListOf(
        R.drawable.list_format,
        R.drawable.griddddd
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.horizontalRev.setHasFixedSize(true)
        category.addAll(getCategory())
        showCategory()

        binding.verticalRv.setHasFixedSize(true)
        foodData.addAll(getFood())
        showRecycleView()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showCategory()

        val toggleButton = binding.imageButton

        toggleButton.setOnClickListener {
            viewList = !viewList
            toggleReycylerView()
            toggleImageViewImage(toggleButton)
        }

        toggleReycylerView()

    }


    private fun getFood(): ArrayList<ParcelMakanan>{
        val dataImg = resources.obtainTypedArray(R.array.data_drawableImg)

        val name = resources.getStringArray(R.array.data_makananName)

        val price = resources.getStringArray(R.array.data_harga)

        val desc = resources.getStringArray(R.array.deskripsi_menu)

        val listFood = ArrayList<ParcelMakanan>()

        for (i in name.indices){
            val food = ParcelMakanan(dataImg.getResourceId(i,-1), name[i], price[i],desc[i] )
            listFood.add(food)
        }
        return listFood
    }

    private fun getCategory(): ArrayList<Category>{
        val dataImg = resources.obtainTypedArray(R.array.data_drawableImg)

        val name = resources.getStringArray(R.array.data_makananName)

        val listFood = ArrayList<Category>()

        for (i in name.indices){
            val food = Category(name[i],dataImg.getResourceId(i,-1))
            listFood.add(food)
        }
        return listFood

    }


    private fun toggleImageViewImage(imageView: ImageView) {
        imageView.setImageResource(drawable[if (viewList) 0 else 1])
    }

    private fun showRecycleView (){
        binding.verticalRv.layoutManager = GridLayoutManager(requireActivity() , 2)
        val foodAdapter = HorizontalAdapter(foodData)
        binding.verticalRv.adapter = foodAdapter
    }

    private  fun showCategory() {
        binding.horizontalRev.layoutManager =
        LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        binding.horizontalRev.adapter = CategoryAdapter(getCategory())
    }


    private fun showGridMenu(){
        binding.verticalRv.layoutManager = GridLayoutManager(requireActivity(), 2)
        val adapterFodd = HorizontalAdapter(foodData, gridMode = true)
        binding.verticalRv.adapter = adapterFodd
    }

    private fun showLinearmenu(){
        binding.verticalRv.layoutManager = LinearLayoutManager(requireActivity())
        val adapterFodd = HorizontalAdapter(foodData, gridMode = false)
        binding.verticalRv.adapter = adapterFodd
    }

    private fun toggleReycylerView(){
        foodData.clear()

        if(viewList){
            showGridMenu()
            typeLayout = true
        } else {
            showLinearmenu()
            typeLayout = false
        }

        val adapter = HorizontalAdapter(foodData, gridMode = typeLayout, onItemClick = {

            val actionToDetail = HomeFragmentDirections.actionHomeFragmentToDetailFragmentMenu()
            actionToDetail.ivDetail = it.image
            actionToDetail.priceMenu = it.harga
            actionToDetail.nameMenu = it.name
            actionToDetail.descDetailMenu = it.desc


            findNavController().navigate(actionToDetail)

        })

        foodData.addAll(getFood())
        binding.verticalRv.adapter = adapter
    }

}