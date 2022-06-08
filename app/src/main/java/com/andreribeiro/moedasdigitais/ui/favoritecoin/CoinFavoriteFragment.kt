package com.andreribeiro.moedasdigitais.ui.favoritecoin
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.andreribeiro.moedasdigitais.databinding.FragmentFavoriteCoinBinding

class CoinFavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteCoinBinding? = null
    private val binding: FragmentFavoriteCoinBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteCoinBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
