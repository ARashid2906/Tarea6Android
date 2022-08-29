package mx.edu.utez.tarea

import android.app.Activity
import android.os.Bundle
import com.squareup.picasso.Picasso
import mx.edu.utez.tarea.databinding.ActivityMainDescriptionBinding

class MainActivityDescription : Activity() {
    companion object {
        const val TITLE = "title"
        const val DESCRIPTION = "description"
        const val IMAGE = "image"
    }
    private lateinit var binding: ActivityMainDescriptionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle = intent.extras
        val title = binding.title
        val description = binding.description
        val txtimage = bundle?.getString("image").toString()
        title.text = bundle?.getString("title").toString()
        description.text = bundle?.getString("description").toString()
        Picasso.get().load(txtimage).into(binding.iconDesc)
    }
}