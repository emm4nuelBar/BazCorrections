package com.example.baz_android_capstone.presentation.viewmodels

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.baz_android_capstone.data.db.BookDatabase
import com.example.baz_android_capstone.data.models.availableBook.Book
import com.example.baz_android_capstone.data.models.availableBook.Payload
import com.example.baz_android_capstone.data.network.BookAPI
import com.example.baz_android_capstone.data.repository.Repository
import io.reactivex.Single
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever


class BookViewModelTest {

    @get:Rule
    val schedulers = RxImmediateSchedulerRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val orderResponse =
        Book(
            success = true,
            payload = listOf(
                Payload("btc_mxn"),
                Payload("eth_mxn"),
                Payload("xrp_mxn"),
                Payload("ltc_mxn"),
                Payload("bch_btc"),
                Payload("bch_mxn"),
                Payload("tusd_btc"),
                Payload("tusd_mxn"),
                Payload("mana_btc"),
                Payload("mana_mxn"),
                Payload("bat_btc")
            )
        )

    private val observeResponse = Single.just(orderResponse)

    private lateinit var bookViewModel: BookViewModel

    @InjectMocks
    private lateinit var repository: Repository

    @Mock
    private lateinit var cryptoApi: BookAPI

    @Mock
    private lateinit var cryptoDb: BookDatabase

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        cryptoApi = mock()
        cryptoDb = mock()

        repository = Repository(cryptoApi, cryptoDb)

        bookViewModel = BookViewModel(repository)
    }

    @Test
    fun `getBooksRx returns data`() {
        whenever(repository.getBooksRxJava()).thenReturn(observeResponse)

        bookViewModel.getBooksRx()

        Assert.assertNotNull(bookViewModel.books.value.data!!.payload)
    }

    @Test
    fun `getBooksRx returns 11 entries`() {
        whenever(repository.getBooksRxJava()).thenReturn(observeResponse)

        bookViewModel.getBooksRx()

        Assert.assertEquals(bookViewModel.books.value.data!!.payload.count(), 11)
    }
}