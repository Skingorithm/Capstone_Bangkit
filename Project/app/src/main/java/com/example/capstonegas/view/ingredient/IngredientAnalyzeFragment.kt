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
import com.example.capstonegas.R
import com.example.capstonegas.databinding.FragmentIngredientAnalyzeBinding
import com.example.capstonegas.model.Datalistset
import com.example.capstonegas.model.DatalistsetItem
import com.example.capstonegas.model.UserPreference
import com.example.capstonegas.viewmodel.IngredientAnalyzeViewModel
import com.example.capstonegas.viewmodel.ViewModelFactory

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name="user_preferences")

class IngredientAnalyzeFragment : Fragment() {

    private lateinit var arrayAdapter: ArrayAdapter<String>
    private lateinit var binding: FragmentIngredientAnalyzeBinding
    private var allIngredient: List<DatalistsetItem>? = null
    private val viewModel : IngredientAnalyzeViewModel by viewModels {
        ViewModelFactory(UserPreference.getInstance(requireContext().dataStore))
    }
    private lateinit var searchUser: SearchView
    private var token: String = ""
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
        val ingredientList = resources.getStringArray(R.array.listIngredients)

        viewModel.getUser().observe(viewLifecycleOwner) {
            token = it.token
        }

        viewModel.allIngredient.observe(viewLifecycleOwner) {
            if (it != null) {
                allIngredient = it
            }
        }

        searchUser = binding.searchIngredients
        searchUser.queryHint = "Cari Ingredient"
        listView = binding.listIngredients
        arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, ingredientList)
        listView.adapter = arrayAdapter

        searchUser.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (ingredientList.contains(query)) {
                    arrayAdapter.filter.filter(query)
                    query.let { token.let { it1 -> viewModel.getIngredient(it1, query.toString()) } }
                    viewModel.ingredient.observe(viewLifecycleOwner) {
                        if (it != null) {
                            showCardIngredient(it)
                        }

                    }

                    showLoading(true)
                    binding.imgSearch.visibility = View.INVISIBLE
                    binding.textImgSearch.visibility = View.INVISIBLE
                    binding.listIngredients.visibility = View.INVISIBLE
                } else {
                    Toast.makeText(requireContext(), "Ingredient tidak ditemukan", Toast.LENGTH_SHORT).show()
                    showLoading(false)
                    binding.cardView.visibility = View.INVISIBLE
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
                    binding.cardView.visibility = View.INVISIBLE
                    binding.imgSearch.visibility = View.VISIBLE
                    binding.textImgSearch.visibility = View.VISIBLE
                } else {
                    showLoading(false)
                    binding.listIngredients.visibility = View.INVISIBLE
                    binding.cardView.visibility = View.INVISIBLE
                    binding.imgSearch.visibility = View.VISIBLE
                    binding.textImgSearch.visibility = View.VISIBLE
                }
                return true
            }
        })

        viewModel.isLoading.observe(viewLifecycleOwner) {
            if (it) {
                showLoading(true)
            } else {
                showLoading(false)
            }
        }
    }

    private fun showCardIngredient(data: Datalistset) {
        if (data.ingredName != null) {
            binding.cardView.visibility = View.VISIBLE
            binding.ingredientName.text = data.ingredName
            binding.detailFunction.text = data.ingredFunction
            binding.detailEffect.text = data.ingredEffect
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBarIngredient.visibility = View.VISIBLE
        } else {
            binding.progressBarIngredient.visibility = View.GONE
        }
    }


}