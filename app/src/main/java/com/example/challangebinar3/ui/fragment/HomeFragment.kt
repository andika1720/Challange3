package com.example.challangebinar3.ui.fragment
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challangebinar3.adapter.CategoryAdapter
import com.example.challangebinar3.adapter.HorizontalAdapter
import com.example.challangebinar3.R
import com.example.challangebinar3.viewModel.HomeViewModel
import com.example.challangebinar3.util.SharePreference
import com.example.challangebinar3.dataApi.model.CategoryMenu
import com.example.challangebinar3.dataApi.model.DataListMenu
import com.example.challangebinar3.dataApi.model.ListMenu
import com.example.challangebinar3.databinding.FragmentHomeBinding
import com.example.challangebinar3.util.Status
import org.koin.android.ext.android.inject



class HomeFragment : Fragment() {


    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel : HomeViewModel by inject()
    private var viewList : Boolean = true

    private val drawable = arrayListOf(
        R.drawable.list_format,
        R.drawable.griddddd
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        viewList = SharePreference.getPref("VIEW LIST", true)

            val toggleButton = binding.imageButton

            toggleButton.setOnClickListener {
                viewList = !viewList
                toggleImageViewImage(toggleButton)
                SharePreference.setPref("viewList", viewList)
                fetchListMenu()
            }
            fetchCategoryMenu()
            fetchListMenu()


            return binding.root

    }


    private fun toggleImageViewImage(imageView: ImageView) {
        imageView.setImageResource(drawable[if (viewList) 0 else 1])
    }

    @Suppress("KotlinConstantConditions")
    private fun showList(data: ListMenu){
        if (viewList){
            val adapter = HorizontalAdapter(viewList, object : HorizontalAdapter.OnClickListener{
                override fun itemClick(data: DataListMenu) {
                    navigatetoDetail(data)
                }
            })
            adapter.sendListMenu(data.data)
            binding.verticalRv.layoutManager = GridLayoutManager(requireActivity(),2)
            binding.verticalRv.adapter = adapter
        } else {
            val adapter = HorizontalAdapter(viewList, object  : HorizontalAdapter.OnClickListener {
                override fun itemClick(data: DataListMenu) {
                    navigatetoDetail(data)
                }

            })
            adapter.sendListMenu(data.data)
            binding.verticalRv.layoutManager = LinearLayoutManager(requireActivity())
            binding.verticalRv.adapter = adapter
        }
    }


    private fun showCategory(data: CategoryMenu?) {
        val adapter = CategoryAdapter()

        adapter.sendCategoryMenu(data?.data ?: emptyList())
        binding.horizontalRev.layoutManager= LinearLayoutManager(requireActivity(), LinearLayoutManager. HORIZONTAL, false)
        binding.horizontalRev.adapter = adapter
    }

    private fun navigatetoDetail(data: DataListMenu) {

        val bundle= bundleOf("key" to data)
        Log.e("Bundle", bundle.toString())
        findNavController().navigate(R.id.action_homeFragment_to_detailFragmentMenu,bundle)
    }

    private fun fetchListMenu(){
        viewModel.getAllList().observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBarList.isVisible = false
                    showList(it.data!!)
                }
                Status.ERROR -> {
                    binding.progressBarList.isVisible = false
                    Log.e("Errorr" , it.message.toString())
                }
                Status.LOADING -> {
                    binding.progressBarList.isVisible = true
                }
            }
        }
    }



    private fun fetchCategoryMenu(){
        viewModel.getAllCategory().observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    showCategory(it.data)
                    binding.progressBarCategory.isVisible = false
                }
                Status.ERROR -> {
                    binding.progressBarCategory.isVisible = false
                    Log.e("Errorr" , it.message.toString())
                }
                Status.LOADING ->{
                    binding.progressBarCategory.isVisible = true
                }
            }
        }
    }

}