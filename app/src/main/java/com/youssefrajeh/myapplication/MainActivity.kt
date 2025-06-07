package com.youssefrajeh.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    private lateinit var adapter: MovieAdapter
    private val movieList = mutableListOf<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = MovieAdapter(movieList)
        findViewById<RecyclerView>(R.id.recyclerView).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }

        val titleInput = findViewById<EditText>(R.id.titleInput)
        val directorInput = findViewById<EditText>(R.id.directorInput)
        val ratingInput = findViewById<RatingBar>(R.id.ratingInput)

        findViewById<Button>(R.id.addButton).setOnClickListener {
            val title = titleInput.text.toString()
            val director = directorInput.text.toString()
            val rating = ratingInput.rating

            if (title.isNotBlank() && director.isNotBlank()) {
                movieList.add(Movie(title, director, rating))
                adapter.notifyItemInserted(movieList.size - 1)
                titleInput.text.clear()
                directorInput.text.clear()
                ratingInput.rating = 0f
            }
        }

        findViewById<Button>(R.id.removeLastButton).setOnClickListener {
            if (movieList.isNotEmpty()) {
                val lastIndex = movieList.size - 1
                movieList.removeAt(lastIndex)
                adapter.notifyItemRemoved(lastIndex)
            }
        }

        findViewById<Button>(R.id.removeAllButton).setOnClickListener {
            val size = movieList.size
            if (size > 0) {
                movieList.clear()
                adapter.notifyItemRangeRemoved(0, size)
            }
        }
    }
}
