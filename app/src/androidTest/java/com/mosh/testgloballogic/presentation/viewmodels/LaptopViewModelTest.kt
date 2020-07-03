package com.mosh.testgloballogic.presentation.viewmodels

import com.mosh.testgloballogic.data.repository.LaptopRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

import org.junit.Assert.*

class LaptopViewModelTest {

    @Mock
    private lateinit var repository: LaptopRepository

    private lateinit var viewModel: LaptopViewModel


    @Before
    fun setUp() {
        viewModel = LaptopViewModel(repository)
    }

    @Test
    fun getLaptops() {

    }
}