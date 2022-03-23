package com.example.kevin_marvel_mvvm_clean_architecture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kevin_marvel_mvvm_clean_architecture.Domain.Models.Characters
import com.example.kevin_marvel_mvvm_clean_architecture.Ui.LIstCharacters.CharactersViewModel
import com.example.kevin_marvel_mvvm_clean_architecture.Util.CharListAdapter
import com.example.kevin_marvel_mvvm_clean_architecture.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    var flag = 3
    var paginatedValue = 0
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CharListAdapter
    private lateinit var layoutManager: GridLayoutManager
    private val viewModel : CharactersViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView = binding.MarvelRecyclerView
        layoutManager = GridLayoutManager(this,2)
        CharactersRecyclerView()
        recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(layoutManager.findLastVisibleItemPosition()==layoutManager.itemCount-1)
                    paginatedValue += 20
                    viewModel.getAllCharData(paginatedValue)
                    callApi()
            }
        })
    }

    private fun callApi() {
        CoroutineScope(Dispatchers.Main).launch {
            repeat(flag){
                viewModel._marvelval.collect{
                    when{
                        it.isLoading ->{
                            binding.progressBar.visibility = View.VISIBLE

                        }
                        it.error.isNotBlank() ->{
                            binding.progressBar.visibility = View.GONE
                            flag = 0
                            Toast.makeText(this@MainActivity,it.error,Toast.LENGTH_LONG).show()

                        }
                        it.characterList.isNotEmpty() ->{
                            binding.progressBar.visibility = View.GONE
                            flag = 0
                            adapter.setData(it.characterList as ArrayList<Characters>)
                        }
                    }
                    delay(1000)
                }

            }
        }
    }

    private fun CharactersRecyclerView() {
        adapter = CharListAdapter(this, ArrayList())
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }
}