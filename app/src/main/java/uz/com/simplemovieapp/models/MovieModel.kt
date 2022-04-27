package uz.com.simplemovieapp.models

import java.io.Serializable

data class MovieModel(
    var name : String,
    var authors : String,
    var about : String,
    var date : String
): Serializable
