package uz.com.simplemovieapp.util

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

class app : Application() {
    override fun onCreate() {
        super.onCreate()
        MySharedPreferences.init(applicationContext)
    }
}

object MySharedPreferences {

    private const val NAME = "ToDoApp"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var gson: Gson

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(NAME, MODE)
        gson = Gson()
    }

    private inline fun SharedPreferences.edit(editor: (SharedPreferences.Editor) -> Unit) {

        val edit = edit()
        editor(edit)
        edit.apply()

    }
    fun clear(){
        text=""
    }

    var text:String?
        get()= sharedPreferences.getString("key1","")
        set(value) = sharedPreferences.edit {
            if (value!=null){
                it.putString("key1",value)
            }
        }


}