package com.example.challangebinar3.fragment
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challangebinar3.Category
import com.example.challangebinar3.CategoryAdapter
import com.example.challangebinar3.HorizontalAdapter
import com.example.challangebinar3.ParcelMakanan
import com.example.challangebinar3.R
import com.example.challangebinar3.ViewModel.HomeViewModel
import com.example.challangebinar3.databinding.FragmentHomeBinding
import com.example.challangebinar3.sharePreference


class HomeFragment : Fragment() {


    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeViewModel: HomeViewModel

    private lateinit var userPreference: sharePreference

    //ygdiubah
    private lateinit var horizontalAdapter: HorizontalAdapter

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
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        userPreference = sharePreference(requireContext())

        homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        homeViewModel.menuView.value = userPreference.getPrefLayout()

        //yg ditambah
        horizontalAdapter = HorizontalAdapter(foodData, homeViewModel.menuView.value ?: true)
        binding.verticalRv.adapter = horizontalAdapter


        binding.horizontalRev.setHasFixedSize(true)
        if (category.isEmpty()) {
            category.addAll(getCategory())
        }
            showCategory()

        binding.verticalRv.setHasFixedSize(true)
        if (foodData.isEmpty()) {
            foodData.addAll(getFood())
        }
        showRecycleView()

        homeViewModel.menuView.observe(viewLifecycleOwner){
            toggleCurrent()
        }

        homeViewModel.menuItem.observe(viewLifecycleOwner){ menuItem ->
            updateRv(menuItem)
        }

        itemClicked()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showCategory()

        val toggleButton = binding.imageButton
        toggleButton.setOnClickListener {
            viewList = !viewList
            toggleCurrent()
            toggleImageViewImage(toggleButton)
        }

        toggleCurrent()

    }

    private fun itemClicked() {
        horizontalAdapter =
            HorizontalAdapter(foodData, homeViewModel.menuView.value ?: true) { item ->
                Log.e("Isi Item", item.toString())

                val bundle = bundleOf("item" to item)
                Log.e("Isi Bundle", bundle.toString())
                findNavController().navigate(R.id.action_homeFragment_to_detailFragmentMenu, args = bundle)


            //    val args = Bundle()
//                args.putParcelable("item", item)
//

            }
        binding.verticalRv.adapter = horizontalAdapter
    }



    @SuppressLint("Recycle")
    private fun getFood(): ArrayList<ParcelMakanan>{
        val dataImg = resources.obtainTypedArray(R.array.data_drawableImg)
        val name = resources.getStringArray(R.array.data_makananName)
        val price = resources.getIntArray(R.array.data_harga)
        val desc = resources.getStringArray(R.array.deskripsi_menu)

        val listFood = ArrayList<ParcelMakanan>()
        for (i in name.indices){
            val food = ParcelMakanan(dataImg.getResourceId(i,-1), name[i], price[i],desc[i] )
            listFood.add(food)
        }
        return listFood
    }

    @SuppressLint("Recycle")
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


    //ditambah tipe data
    private fun toggleReycylerView(viewList: Boolean) {
        foodData.clear()

        typeLayout = if (viewList) {
            showGridMenu()
            true
        } else {
            showLinearmenu()
            false
        }

        val adapter = HorizontalAdapter(foodData, gridMode = typeLayout, onItemClick = {
            itemClicked()
        })

        foodData.addAll(getFood())
        binding.verticalRv.adapter = adapter
    }
//            val actionToDetail
//            = HomeFragmentDirections.actionHomeFragmentToDetailFragmentMenu(2)
//            actionToDetail.ivDetail = it.image
//            actionToDetail.priceMenu = it.harga
//            actionToDetail.nameMenu = it.name
//            actionToDetail.descDetailMenu = it.desc

//
//            findNavController().navigate(actionToDetail)
//
//
//
//        foodData.addAll(getFood())
//        binding.verticalRv.adapter = adapter


    //baru dibuat
    @SuppressLint("NotifyDataSetChanged")
    private fun updateRv(menuItem: ArrayList<ParcelMakanan>){
        horizontalAdapter.reloadData(menuItem)
        horizontalAdapter.gridMode = homeViewModel.menuView.value?: true
        binding.verticalRv.adapter?.notifyDataSetChanged()

    }

    //baru dibuat
    private fun toggleCurrent(){
        val togleImg = binding.imageButton
        val currentLayout: Boolean =
            homeViewModel.menuView.value ?: userPreference.getPrefLayout()

        toggleReycylerView(currentLayout)
        togleImg.setImageResource(if (currentLayout) R.drawable.list_format else R.drawable.griddddd)

        togleImg.setOnClickListener {
            val newValueList =!currentLayout
            homeViewModel.menuView.value = newValueList
            userPreference.savePrefLayout(newValueList)
        }
    }

}