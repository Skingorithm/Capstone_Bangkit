package com.example.capstonegas.view.ingredient

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capstonegas.R
import com.example.capstonegas.adapter.IngredientAdapter
import com.example.capstonegas.databinding.FragmentIngredientAnalyzeBinding
import com.example.capstonegas.model.Datalistset
import com.example.capstonegas.model.DatalistsetItem
import com.example.capstonegas.model.UserPreference
import com.example.capstonegas.viewmodel.IngredientAnalyzeViewModel
import com.example.capstonegas.viewmodel.ViewModelFactory

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name="user_preferences")

class IngredientAnalyzeFragment : Fragment() {

    private lateinit var ingredientAdapter: IngredientAdapter
    private lateinit var arrayAdapter: ArrayAdapter<String>
    private lateinit var rvIngredient: RecyclerView
    private lateinit var binding: FragmentIngredientAnalyzeBinding
    private var allIngredient: List<DatalistsetItem>? = null
    private lateinit var listIngredient: List<String>
    private val viewModel : IngredientAnalyzeViewModel by viewModels {
        ViewModelFactory(UserPreference.getInstance(requireContext().dataStore))
    }
    private val ingredient = Datalistset()

    private lateinit var searchUser: SearchView
    private var token: String? = null
    private lateinit var listView: ListView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIngredientAnalyzeBinding.inflate(inflater, container, false).apply { viewLifecycleOwner
        IngredientAnalyzeViewModel}

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUser().observe(viewLifecycleOwner) {
            token = it.token
            Log.d("User Ingredient", token!!)

        }

        if (token != null) {
            viewModel.getAllIngredient(token!!)
        }

        viewModel.allIngredient.observe(viewLifecycleOwner) {
            Log.d("isi all ingridient:", it.toString())
            if (it != null) {
                allIngredient = it
            }

        }

        // string-array
        val ingredientList = resources.getStringArray(R.array.listIngredients)

//        for(item in allIngredient!!){
//            item.ingredName?.let { Log.d("IngredientFragment", it) }
//        }

        rvIngredient = binding.recyclerView
        ingredientAdapter = IngredientAdapter(ingredient)
        rvIngredient.adapter = ingredientAdapter
        searchUser = binding.searchIngredients
        searchUser.queryHint = "Cari Ingredient"
        listView = binding.listIngredients
        arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, ingredientList)
        listView.adapter = arrayAdapter

        showRecyclerView(ingredient)

        searchUser.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (ingredientList.contains(query)) {
                    arrayAdapter.filter.filter(query)
                    query.let { token?.let { it1 -> viewModel.getIngredient(it1, query.toString()) } }
                    showLoading(true)
                    binding.imgSearch.visibility = View.INVISIBLE
                    binding.textImgSearch.visibility = View.INVISIBLE
                    binding.listIngredients.visibility = View.INVISIBLE
                } else {
                    Toast.makeText(requireContext(), "Ingredient tidak ditemukan", Toast.LENGTH_SHORT).show()
                    showLoading(false)
                    binding.listIngredients.visibility = View.INVISIBLE
                    binding.imgSearch.visibility = View.VISIBLE
                    binding.textImgSearch.visibility = View.VISIBLE
                }

                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {

                if (!newText.isNullOrEmpty()) {
                    binding.listIngredients.visibility = View.VISIBLE
                    arrayAdapter.filter.filter(newText)
//                    newText.let { token?.let { it1 -> viewModel.getIngredient(it1, it) } }
                } else {
                    showLoading(false)
                    binding.listIngredients.visibility = View.INVISIBLE
                }
                return true
            }
        })

        viewModel.ingredient.observe(viewLifecycleOwner) {
            if (it != null) {
                showRecyclerView(it)
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            if (it) {
                showLoading(true)
            } else {
                showLoading(false)
            }
        }
    }

    private fun showRecyclerView(ingredient: Datalistset) {
        rvIngredient.layoutManager = LinearLayoutManager(activity)
        ingredientAdapter = IngredientAdapter(ingredient)
        rvIngredient.adapter = ingredientAdapter
        rvIngredient.itemAnimator = DefaultItemAnimator()
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBarIngredient.visibility = View.VISIBLE
        } else {
            binding.progressBarIngredient.visibility = View.GONE
        }
    }


}