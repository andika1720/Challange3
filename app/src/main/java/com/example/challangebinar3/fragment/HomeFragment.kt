package com.example.challangebinar3.fragment
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challangebinar3.CategoryAdapter
import com.example.challangebinar3.HorizontalAdapter
import com.example.challangebinar3.R
import com.example.challangebinar3.SharePreference
import com.example.challangebinar3.dataApi.Api.APIClient
import com.example.challangebinar3.dataApi.model.CategoryMenu
import com.example.challangebinar3.dataApi.model.DataListMenu
import com.example.challangebinar3.dataApi.model.ListMenu
import com.example.challangebinar3.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {


    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

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


    private fun showCategory(data: CategoryMenu){
        val adapter = CategoryAdapter()

        adapter.sendCategoryMenu(data.data)
        binding.horizontalRev.layoutManager= LinearLayoutManager(requireActivity(), LinearLayoutManager. HORIZONTAL, false)
        binding.horizontalRev.adapter = adapter
    }

    private fun navigatetoDetail(data: DataListMenu){
        val bundle= bundleOf("DataListMenu" to data)
        findNavController().navigate(R.id.action_homeFragment_to_detailFragmentMenu,bundle)
    }

    private fun fetchListMenu(){
        APIClient.instance.getListMenu().enqueue(object : Callback<ListMenu> {
            override fun onResponse(call: Call<ListMenu>, response: Response<ListMenu>) {
                val body: ListMenu? = response.body()
                if (response.code()== 200){
                    showList(body!!)
                }

            }

            override fun onFailure(call: Call<ListMenu>, t: Throwable) {
                Log.e("ListMenuError", t.message.toString())
            }

        })
    }

    private fun fetchCategoryMenu(){
        APIClient.instance.getCategory().enqueue(object : Callback<CategoryMenu> {
            override fun onResponse(call: Call<CategoryMenu>, response: Response<CategoryMenu>) {
                val body: CategoryMenu? = response.body()
                if (response.code() == 200) {
                    showCategory(body!!)

                }
            }

            override fun onFailure(call: Call<CategoryMenu>, t: Throwable) {
                Log.e("CategoryMenuError", t.message.toString())
            }

        })
    }

}