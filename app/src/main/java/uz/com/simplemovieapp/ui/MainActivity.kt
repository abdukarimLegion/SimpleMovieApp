package uz.com.simplemovieapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import uz.com.simplemovieapp.R
import uz.com.simplemovieapp.adapters.RecyclerViewAdapter
import uz.com.simplemovieapp.models.MovieModel
import uz.com.simplemovieapp.util.MySharedPreferences

class MainActivity : AppCompatActivity() {
    lateinit var list: ArrayList<MovieModel>
    lateinit var recyclerViewAdapter: RecyclerViewAdapter
    lateinit var gson: Gson
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MySharedPreferences.init(this)
        loadAdapter()
        img_view.setOnClickListener {
            val intent = Intent(this,AddActivity::class.java)
            startActivity(intent)
        }



    }

    override fun onResume() {
        MySharedPreferences.init(this)
        super.onResume()
        loadAdapter()
        recyclerViewAdapter = RecyclerViewAdapter(list, object : RecyclerViewAdapter.MyItemClickListiner {
            override fun itemClickListener(movieClass: MovieModel, position: Int) {
                val intent = Intent(this@MainActivity,InformationActivity::class.java)
                intent.putExtra("key",list[position])
                startActivity(intent)
            }

            override fun editClickListener(movieClass: MovieModel, position: Int) {
                val intent = Intent(this@MainActivity, EditActivity::class.java)
                intent.putExtra("position",list[position])
                recyclerViewAdapter.notifyItemChanged(position)
                startActivity(intent)
            }
            override fun deletClickListener(movieModel: MovieModel, position: Int) {
                list.remove(movieModel)
                recyclerViewAdapter.notifyItemRemoved(position)
                recyclerViewAdapter.notifyItemRangeChanged(position,list.size)
                MySharedPreferences.clear()
                if (MySharedPreferences.text!!.isEmpty()){
                    MySharedPreferences.text = gson.toJson(list)
                }

            }

        })
        rv.layoutManager =LinearLayoutManager(this)
        rv.adapter = recyclerViewAdapter

    }
    private fun loadAdapter() {
        list = ArrayList()
        val str = MySharedPreferences.text
        gson = Gson()
        if (str!!.isNotEmpty()){
            val type = object: TypeToken<ArrayList<MovieModel>>(){}.type
            list.addAll(gson.fromJson(str,type))
        }
    }
}