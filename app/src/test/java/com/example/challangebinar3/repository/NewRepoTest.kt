package com.example.challangebinar3.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.challangebinar3.Database.CartDao
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations


class NewRepoTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var cartDao: CartDao

    @Mock
    lateinit var observer: Observer<Int>

    private lateinit var newRepo: NewRepo

    @Before
    fun testSetup() {

        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
        newRepo = NewRepo(cartDao)
        newRepo.counter.observeForever(observer)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Test
    fun testIncrementCount() {
        newRepo.increment()
        verify(observer).onChanged(1)

        assertThat(newRepo.counter.value).isEqualTo(2)
    }
    @Test
    fun testDecrementCount() {
        newRepo.increment()
        newRepo.decrement()

        verify(observer).onChanged(2)

        assertThat(newRepo.counter.value).isEqualTo(1)
    }

}