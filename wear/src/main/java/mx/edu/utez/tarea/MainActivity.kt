package mx.edu.utez.tarea

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.wear.widget.WearableLinearLayoutManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import mx.edu.utez.tarea.databinding.ActivityMainBinding
import mx.edu.utez.tarea.menu.Menu
import mx.edu.utez.tarea.menu.MenuAdapter

class MainActivity : Activity() {
    companion object {
        const val TAG = "test_firebase"
        const val TITLE = "title"
        const val DESCRIPTION = "description"
        const val IMAGE = "image"
    }
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MenuAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = Firebase.firestore
        val listMenu = ArrayList<Menu>()
        val docRef = db.collection("menu")
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    for (doc in document) {
                        val menu = Menu(
                            doc.id,
                            doc.data[TITLE].toString(),
                            doc.data[DESCRIPTION].toString(),
                            doc.data[IMAGE].toString()
                        )
                        listMenu.add(menu)
                    }
                    adapter = MenuAdapter(listMenu, this)
                    //indicamos que se centren los elementos
                    binding.recyclearView.isEdgeItemsCenteringEnabled = true
                    //Permite dar el efecto de un giro de 360
                    binding.recyclearView.isCircularScrollingGestureEnabled = true
                    binding.recyclearView.layoutManager = WearableLinearLayoutManager(this)
                    binding.recyclearView.adapter = adapter
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }
    }
}