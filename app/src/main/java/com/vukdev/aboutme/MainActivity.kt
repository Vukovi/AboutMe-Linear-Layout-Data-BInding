package com.vukdev.aboutme

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.view.inputmethod.InputBinding
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
//import androidx.databinding.DataBindingComponent
//import android.databinding.DataBindingUtil
import com.vukdev.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // s obzirom da sam uveo property binding, ne trebaju mi vise instance ui-ja
    // niti mi treba da ih instanciram
//    lateinit var editText: EditText
//    lateinit var nicknameTextView: TextView


    private var mojiPodaci = DataClass("Vuk")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        setContentView(R.layout.activity_main)
//        Umesto ove setContentView metode moramo da instanciramo binding

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.mojaDataKlasa = mojiPodaci

        // ovo nam vise ne treba zbog property-ja binding
//        editText = findViewById(R.id.nickname_edit)
//        nicknameTextView = findViewById(R.id.nicknameText)

//        findViewById<Button>(R.id.done_button).setOnClickListener {
//            addNickName(it)
//        }
        binding.doneButton.setOnClickListener {
            addNickName(it)
        }
    }

    private fun addNickName(view: View) {
//        nicknameTextView.text = editText.text
//        editText.visibility = View.GONE
//        view.visibility = View.GONE
//        nicknameTextView.visibility = View.VISIBLE

        // apply koristim da ne bih svakom pojedincano prilazio preko binding.UIElement
        binding.apply {
            // ovde cu umesto propertija koristiti binding data iz xml-a koji je povezan
            // sa nicknameTextom, pa ce ovaj preko njega dobijati informaciju
            mojaDataKlasa?.nadimak = nicknameEdit.text.toString()
//            nicknameText.text = nicknameEdit.text
            invalidateAll()
            nicknameEdit.visibility = View.GONE
            view.visibility = View.GONE
            nicknameText.visibility = View.VISIBLE
        }

        // Sakrivanje tastature
        val inoutMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inoutMethodManager.hideSoftInputFromWindow(view.windowToken,0)
    }
}
