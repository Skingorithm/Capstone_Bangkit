package com.example.capstonegas.view.ingredient

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capstonegas.adapter.IngredientAdapter
import com.example.capstonegas.databinding.FragmentIngredientAnalyzeBinding
import com.example.capstonegas.model.Datalistset
import com.example.capstonegas.viewmodel.IngredientAnalyzeViewModel

class IngredientAnalyzeFragment : Fragment() {

    private lateinit var ingredientAdapter: IngredientAdapter
    private lateinit var rvIngredient: RecyclerView
    private lateinit var binding: FragmentIngredientAnalyzeBinding
    private val ingredientAnalyzeViewModel by viewModels<IngredientAnalyzeViewModel>()
    private val ingredient = Datalistset()

    private lateinit var searchUser: SearchView
    private lateinit var token: String

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
        rvIngredient = binding.recyclerView
        ingredientAdapter = IngredientAdapter(ingredient)
        rvIngredient.adapter = ingredientAdapter
        searchUser = binding.searchIngredients
        searchUser.queryHint = "Cari Ingredient"

        ingredientAnalyzeViewModel.getUser().observe(viewLifecycleOwner) {
            token = it.token
        }

        showRecyclerView(ingredient)

        searchUser.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                if (!newText.isNullOrEmpty()) {
                    newText.let { ingredientAnalyzeViewModel.getIngredient(token, it) }
                    showLoading(true)
                } else {
                    showLoading(false)
                }
                return true
            }
        })

        ingredientAnalyzeViewModel.ingredient.observe(viewLifecycleOwner) {
            if (it != null) {
                showRecyclerView(it)
            }
        }

        ingredientAnalyzeViewModel.isLoading.observe(viewLifecycleOwner) {
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