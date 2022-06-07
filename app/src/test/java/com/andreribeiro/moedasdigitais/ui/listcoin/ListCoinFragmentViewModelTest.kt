package com.andreribeiro.moedasdigitais.ui.listcoin

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.andreribeiro.moedasdigitais.model.CoinModel
import com.andreribeiro.moedasdigitais.repository.ICoinRepository
import com.andreribeiro.moedasdigitais.viewmodel.ListCoinFragmentViewModel
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
class ListCoinFragmentViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: ListCoinFragmentViewModel

    private val dispatcher = TestCoroutineDispatcher()

    @Mock
    private lateinit var listCoinLiveDataObserver: Observer<List<CoinModel>>

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
            ListCoinFactory(coinRepository).create(ListCoinFragmentViewModel::class.java)
        viewModel.coinList.observeForever(listCoinLiveDataObserver)

        // Act
        viewModel.getCoinList()

        // Assert
        verify(listCoinLiveDataObserver).onChanged(listCoinModel)
    }
}

class MockRepository(
    private val listCoin: List<CoinModel>
) : ICoinRepository {

    override suspend fun getCoin(type: Int): List<CoinModel> {
        return withContext(Dispatchers.IO) {
            listCoin
        }
    }
}
