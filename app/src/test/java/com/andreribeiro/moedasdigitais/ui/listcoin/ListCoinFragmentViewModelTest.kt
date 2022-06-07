package com.andreribeiro.moedasdigitais.ui.listcoin

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.andreribeiro.moedasdigitais.model.CoinModel
import com.andreribeiro.moedasdigitais.repository.ICoinRepository
import com.andreribeiro.moedasdigitais.viewmodel.CoinListFragmentViewModel
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.withContext
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CoinListFragmentViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: CoinListFragmentViewModel

    private val dispatcher = TestCoroutineDispatcher()

    @Mock
    private lateinit var CoinListLiveDataObserver: Observer<List<CoinModel>>

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when view model getListCoin get success`() {
        // Arrange
        val listCoinModel = listOf(
            CoinModel(
                "2",
                "Carlos",
                1,
                1.0F,
                1.0F,
                1.0F,
                1.0F,
                "asdasd",
                "asdas",
                "asdasd"
            )
        )

        val coinRepository = MockRepository(listCoinModel)
        viewModel =
            CoinListFactory(coinRepository).create(CoinListFragmentViewModel::class.java)
        viewModel.coinList.observeForever(CoinListLiveDataObserver)

        // Act
        viewModel.getCoinList()

        // Assert
        verify(CoinListLiveDataObserver).onChanged(listCoinModel)
    }
}

class MockRepository(
    private val listCoin: List<CoinModel>
) : ICoinRepository {

    override suspend fun getCoinsByType(type: Int): List<CoinModel> {
        return withContext(Dispatchers.IO) {
            listCoin
        }
    }
}
