package com.example.appcep.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import com.example.appcep.R
import com.example.appcep.util.MaskEditUtil
import com.example.appcep.util.hideKeyboard
import com.example.appcep.util.loadFragment
import com.example.appcep.util.snackBar
import com.example.appcep.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : FragmentActivity() {

    // ViewModel
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Adicionando máscara no EditText
        etCep.addTextChangedListener(MaskEditUtil.mask(etCep))

        // Funções do botão enviar
        btnSend.setOnClickListener {
            // Esconde o teclado
            it.hideKeyboard()
            if(etCep.text.toString().length == 9) {
                // Enviando CEP para o ViewModel
                viewModel.setCep(etCep.text.toString())
                tvInit.visibility = View.GONE
                MainFragment.newInstance().loadFragment(this)
            }
            else {
                it.snackBar(getString(R.string.error_numbers))
            }
        }
    }
}