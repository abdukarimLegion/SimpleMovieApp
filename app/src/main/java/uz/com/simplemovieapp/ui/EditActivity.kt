package uz.com.simplemovieapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_edit.*
import uz.com.simplemovieapp.R
import uz.com.simplemovieapp.models.MovieModel
import uz.com.simplemovieapp.util.MySharedPreferences

class EditActivity : AppCompatActivity() {
    lateinit var list: ArrayList<MovieModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        MySharedPreferences.init(this)
        var list1 = ArrayList<MovieModel>()
        var position = intent.getSerializableExtra("position") as MovieModel
        edite_name1.setText(position.name)
        authors1.setText(position.authors)
        movie1.setText(position.about)
        date1.setText(position.date)

        edite_btn.setOnClickListener {
            var gson = Gson()
            var edite_name = edite_name1.text.toString()
            var edite_authors = authors1.text.toString()
            var movie1 = movie1.text.toString()
            var date1 = date1.text.toString()
            var text = MySharedPreferences.text
            if (text!!.isNotEmpty()) {
                var type = object : TypeToken<ArrayList<MovieModel>>() {}.type
                list1.addAll(gson.fromJson(text, type))
            }
            if (edite_name.isEmpty()&& edite_authors.isNotEmpty() && movie1.isNotEmpty() && date1.isNotEmpty()){
                Toast.makeText(this, "Kino nomi mavju edmas", Toast.LENGTH_SHORT).show()
            }else if (edite_name.isNotEmpty() && edite_authors.isNotEmpty() && movie1.isNotEmpty() && date1.isNotEmpty()) {
                list1.removeAll(listOf(position))
                MySharedPreferences.clear()
                if (MySharedPreferences.text!!.isEmpty()) {
                    list1.add(MovieModel(edite_name, edite_authors, movie1, date1))
                    MySharedPreferences.text = gson.toJson(list1)
                }
                Toast.makeText(this, "Save", Toast.LENGTH_SHORT).show()
                finish()
            }
            //The data is incomplete
        }
    }
}