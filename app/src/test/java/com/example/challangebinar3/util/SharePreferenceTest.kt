package com.example.challangebinar3.util

import android.content.Context
import android.content.SharedPreferences
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import com.google.common.truth.Truth.assertThat

class SharePreferenceTest {
    @Mock
    private lateinit var mockContext: Context

    @Mock
    private lateinit var mockSharedPreferences: SharedPreferences

    @Mock
    private lateinit var mockEditor: SharedPreferences.Editor

    @Before
    fun set(){
        @Suppress("DEPRECATION")
        MockitoAnnotations.initMocks(this)
        `when`(mockContext.getSharedPreferences("layout", Context.MODE_PRIVATE)).thenReturn(mockSharedPreferences)
        `when`(mockSharedPreferences.edit()).thenReturn(mockEditor)

    }

    @Test
    fun testGetPref() {
        val key = "keyTesting"
        val defaultValue = true

        `when`(mockSharedPreferences.getBoolean(key, defaultValue)).thenReturn(true)

        SharePreference.initial(mockContext)
        val result = SharePreference.getPref(key, defaultValue)

        verify(mockSharedPreferences).getBoolean(key, defaultValue)
        assertThat(result).isTrue()
    }
    @Test
    fun testSetPref(){
        val key = "keyTesting"
        val value = true

        SharePreference.initial(mockContext)
        SharePreference.setPref(key, value)

        verify(mockEditor).putBoolean(key, value)
        verify(mockEditor).apply()
        verify(mockEditor).commit()
    }


}
