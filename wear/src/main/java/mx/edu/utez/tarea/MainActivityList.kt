package mx.edu.utez.tarea
import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.wear.widget.WearableLinearLayoutManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import mx.edu.utez.tarea.databinding.ActivityMainListBinding
import mx.edu.utez.tarea.menu.Menu
import mx.edu.utez.tarea.menu.MenuAdapter
import mx.edu.utez.tarea.menu.SubmenuAdapter
class MainActivityList : Activity() {
    companion object {
        const val TITLE = "title"
        const val DESCRIPTION = "description"
        const val IMAGE = "image"
        const val TAG= "test_firebase"
    }
    private lateinit var binding: ActivityMainListBinding
    private lateinit var adapter: SubmenuAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = Firebase.firestore
        val listSubmenu= ArrayList<Menu>()
        val bundle = intent.extras
        val title = bundle?.getString("title")
        val docRef = db.collection(title.toString())
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null)  {
                    for(doc in document){
                        val subMenu=Menu(doc.id, doc.data[TITLE].toString(),
                            doc.data[DESCRIPTION].toString(),doc.data[IMAGE].toString())
                        listSubmenu.add(subMenu)
                    }
                    adapter=SubmenuAdapter(listSubmenu,this)
                    //indicamos que se centren los elementos
                    binding.reciclearView.isEdgeItemsCenteringEnabled=true
                    //Permite dar el efecto de un giro de 360
                    binding.reciclearView.isCircularScrollingGestureEnabled=true
                    binding.reciclearView.layoutManager= WearableLinearLayoutManager(this)
                    binding.reciclearView.adapter=adapter
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }
    }
}