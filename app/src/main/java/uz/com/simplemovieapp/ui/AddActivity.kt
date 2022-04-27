package uz.com.simplemovieapp.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_main.*
import uz.com.simplemovieapp.R
import uz.com.simplemovieapp.adapters.RecyclerViewAdapter
import uz.com.simplemovieapp.models.MovieModel
import uz.com.simplemovieapp.util.MySharedPreferences

class AddActivity : AppCompatActivity() {

    lateinit var list: ArrayList<MovieModel>
    lateinit var gson: Gson
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)


        save_btn.setOnClickListener {
            var name = edite_name.text.toString()
            var authors = authors.text.toString()
            var movie = movie.text.toString()
            var date = date.text.toString()

            if (name.isNullOrBlank() && authors.isNullOrBlank() && movie.isNullOrBlank()) {
                Toast.makeText(this, "Malumotlar mavjud emas", Toast.LENGTH_SHORT).show()
            }else if(name.isNullOrBlank()){
                Toast.makeText(this, "Name kiritilmagan", Toast.LENGTH_SHORT).show()
            }else if(authors.isNullOrBlank()){
                Toast.makeText(this, "Authorslar mavjud kiritilmagan", Toast.LENGTH_SHORT).show()
            }else if(movie.isNullOrBlank()){
                Toast.makeText(this, "About mavjud  kiritilmagan", Toast.LENGTH_SHORT).show()
            }else if (name.isNotEmpty() && authors.isNotEmpty() && movie.isNotEmpty() && date.isNotEmpty()) {
                list = ArrayList()
                gson = Gson()
                var str = MySharedPreferences.text
                if (str!!.isNotEmpty()) {
                    var type = object : TypeToken<ArrayList<MovieModel>>() {}.type
                    list.addAll(gson.fromJson(str, type))
                }
                var movieClass = MovieModel(name, authors, movie, date)
                var isHave = false
                for (i in list.indices) {
                    if (list[i].name!! == name) {
                        isHave = true
                    }
                }
                if (!isHave) {
                    list.add(movieClass)
                    MySharedPreferences.text = gson.toJson(list)

                    finish()
                } else {
                    Toast.makeText(this, "Bunday malumot mavjud", Toast.LENGTH_SHORT).show()
                }
            }
        }




    }


}