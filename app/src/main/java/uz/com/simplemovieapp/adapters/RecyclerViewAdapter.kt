package uz.com.simplemovieapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_rv.view.*
import uz.com.simplemovieapp.R
import uz.com.simplemovieapp.models.MovieModel

class RecyclerViewAdapter(
    var list: List<MovieModel>,
    var myItemClickListiner: MyItemClickListiner
) :
    RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    inner class MyViewHolder(var itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(movieModel: MovieModel, position: Int) {
            itemView.avengers.text = movieModel.name
            itemView.text_tv.text = movieModel.authors
            itemView.date.text = movieModel.date

            itemView.setOnClickListener {
                myItemClickListiner.itemClickListener(movieModel, position)
            }

            itemView.edit.setOnClickListener {
                myItemClickListiner.editClickListener(movieModel, position)
            }

            itemView.delete.setOnClickListener {
                myItemClickListiner.deletClickListener(movieModel, position)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var itemView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movieModel = list[position]
        holder.onBind(movieModel, position)
    }

    override fun getItemCount(): Int = list.size

    interface MyItemClickListiner {
        fun itemClickListener(movieClass: MovieModel, position: Int)
        fun editClickListener(movieClass: MovieModel, position: Int)
        fun deletClickListener(movieClass: MovieModel, position: Int)
    }
}