package com.example.appcep.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.appcep.R
import com.example.appcep.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.content_cep.*
import java.util.Observer as Observer1

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    // ViewModel da MainActivity
    private val viewModel: MainViewModel by activityViewModels()

    private var fragmentListViews: List<View>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.content_cep, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Lista de Fragments
        fragmentListViews = listOf(layCep, progressBar, tvError)

        // Observadores do viewModel
        viewModel.cep.observe(viewLifecycleOwner, Observer {
            it.let {
                tvCep.text = it.cep
                tvLogradouro.text = it.logradouro
                tvBairro.text = it.bairro
                tvLocalidade.text = it.localidade
                tvUf.text = it.uf
                tvDdd.text = it.ddd
                layCep.setVisible(fragmentListViews)
            }
        })
        viewModel.error.observe(viewLifecycleOwner, Observer {
            if (it) tvError.setVisible(fragmentListViews)
        })
        viewModel.errorType.observe(viewLifecycleOwner, Observer {
            tvError.text =
                if (it == 0) getString(R.string.error) else getString(R.string.error_exist)
        })
        viewModel.loading.observe(viewLifecycleOwner, Observer {
            if (it) progressBar.setVisible(fragmentListViews)
        })
    }

    // Setando view atual no fragment
    private fun View.setVisible(listViews: List<View>?) = listViews?.map { if (it == this) it.visibility = View.VISIBLE else it.visibility = View.GONE }


}