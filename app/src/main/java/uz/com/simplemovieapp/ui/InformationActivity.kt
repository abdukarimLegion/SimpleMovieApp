package uz.com.simplemovieapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_information.*
import uz.com.simplemovieapp.R
import uz.com.simplemovieapp.models.MovieModel

class InformationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)
        val information = intent.getSerializableExtra("key") as MovieModel
        name_use.text = information.name
        name_1.text = "Movie name: ${information.name}"
        authors1.text ="Movie authors: ${information.authors}"
        about1.text = "About movie: ${information.about}"
        date1.text = "Date: ${information.date}"
    }
}