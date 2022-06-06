package com.example.capstonegas.view.ingredient

import android.content.Context
import android.os.Bundle
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
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capstonegas.R
import com.example.capstonegas.adapter.IngredientAdapter
import com.example.capstonegas.databinding.FragmentIngredientAnalyzeBinding
import com.example.capstonegas.model.Datalistset
import com.example.capstonegas.model.UserModel
import com.example.capstonegas.model.UserPreference
import com.example.capstonegas.viewmodel.IngredientAnalyzeViewModel
import com.example.capstonegas.viewmodel.ViewModelFactory
import kotlinx.coroutines.flow.Flow

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name="user_preferences")

class IngredientAnalyzeFragment : Fragment() {

    private lateinit var ingredientAdapter: IngredientAdapter
    private lateinit var arrayAdapter: ArrayAdapter<String>
    private lateinit var rvIngredient: RecyclerView
    private lateinit var binding: FragmentIngredientAnalyzeBinding
    private val viewModel : IngredientAnalyzeViewModel by viewModels {
        ViewModelFactory(UserPreference.getInstance(requireContext().dataStore))
    }
    private val ingredient = Datalistset()

    private lateinit var searchUser: SearchView
    private lateinit var token: String
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

        val listINgredient = resources.getStringArray(R.array.listIngredients)

        rvIngredient = binding.recyclerView
        ingredientAdapter = IngredientAdapter(ingredient)
        rvIngredient.adapter = ingredientAdapter
        searchUser = binding.searchIngredients
        searchUser.queryHint = "Cari Ingredient"
        listView = binding.listIngredients
        arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, listINgredient)
        listView.adapter = arrayAdapter


        viewModel.getUser().observe(viewLifecycleOwner) {
            token = it.token
        }

        showRecyclerView(ingredient)

        searchUser.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (listINgredient.contains(query)) {
                    arrayAdapter.filter.filter(query)
                    query.let { viewModel.getIngredient(token, query.toString()) }
                    showLoading(true)
                } else {
                    Toast.makeText(requireContext(), "Ingredient tidak ditemukan", Toast.LENGTH_SHORT).show()
                    showLoading(false)
                    binding.listIngredients.visibility = View.INVISIBLE
                }

                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {

                if (!newText.isNullOrEmpty()) {
                    binding.listIngredients.visibility = View.VISIBLE
                    arrayAdapter.filter.filter(newText)
                    newText.let { viewModel.getIngredient(token, it) }
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