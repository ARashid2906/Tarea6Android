package mx.edu.utez.tarea.menu
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import mx.edu.utez.tarea.R
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import mx.edu.utez.tarea.MainActivityList
import mx.edu.utez.tarea.databinding.ActivityPreviewRecyclearBinding
class MenuAdapter(val listMenu: MutableList<Menu>, val context: Context) :
    RecyclerView.Adapter<MenuAdapter.Holder>() {
    class Holder(val binding: ActivityPreviewRecyclearBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = DataBindingUtil.inflate<ActivityPreviewRecyclearBinding>(
            LayoutInflater.from(context),
            R.layout.activity_preview_recyclear, parent, false
        )
        return Holder(binding)
    }
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val menu = listMenu[position]
        holder.binding.title.text = menu.title
        Picasso.get().load(menu.image).into(holder.binding.icon);
        holder.binding.root.setOnClickListener {
            context.apply {
                val intent = Intent(context, MainActivityList::class.java)
                intent.putExtra("title", menu.title)
                intent.putExtra("description", menu.description )
                intent.putExtra("image", menu.image)
                startActivity(intent)
            }
        }
    }
    override fun getItemCount(): Int {
        return listMenu.size
    }
}