package mx.edu.utez.tarea.menu

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import mx.edu.utez.tarea.MainActivityDescription
import mx.edu.utez.tarea.R
import mx.edu.utez.tarea.databinding.ActivityPreviewRecyclearBinding

class SubmenuAdapter(val listSubmenu: MutableList<Menu>, val context: Context) :
    RecyclerView.Adapter<SubmenuAdapter.Holder>() {
    class Holder(val binding: ActivityPreviewRecyclearBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubmenuAdapter.Holder {
        val binding = DataBindingUtil.inflate<ActivityPreviewRecyclearBinding>(
            LayoutInflater.from(context),
            R.layout.activity_preview_recyclear, parent, false
        )
        return Holder(binding)
    }
    override fun getItemCount(): Int {
        return listSubmenu.size
    }
    override fun onBindViewHolder(holder: SubmenuAdapter.Holder, position: Int) {
        val submenu = listSubmenu[position]
        holder.binding.title.text = submenu.title
        Picasso.get().load(submenu.image).into(holder.binding.icon);
        holder.binding.root.setOnClickListener {
            context.apply {
                val intent = Intent(context, MainActivityDescription::class.java)
                intent.putExtra("title", submenu.title)
                intent.putExtra("description", submenu.description)
                intent.putExtra("image", submenu.image)
                startActivity(intent)
            }
        }
    }
}