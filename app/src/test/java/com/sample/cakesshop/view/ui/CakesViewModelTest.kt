package com.sample.cakesshop.view.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.sample.cakesshop.data.model.Cake
import com.sample.cakesshop.data.remote.CakeApiService
import com.sample.cakesshop.data.repository.CakeRepositoryImpl
import com.sample.cakesshop.domain.model.ApiResponse
import com.sample.cakesshop.domain.model.CakeDomainModel
import com.sample.cakesshop.domain.usecase.GetCakesUseCase
import com.sample.cakesshop.view.mapper.toItemPresentationList
import com.sample.cakesshop.view.model.CakePresentationModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Created by AnkitSingh on 1/3/23.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class CakesViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private val dispatcher = TestCoroutineDispatcher()

    @Mock
    private lateinit var cakeListObserver: Observer<List<CakePresentationModel>>

    @Mock
    private lateinit var errorObserver: Observer<Boolean>

    @Mock
    private lateinit var getCakesUseCase: GetCakesUseCase

    private lateinit var viewModel: CakesViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        MockitoAnnotations.openMocks(this)
        viewModel = CakesViewModel(getCakesUseCase)
    }

    @Test
    fun `get cake list returns sorted list with no duplicates`() = runTest {
        val testData = getMockItemList()

        whenever(getCakesUseCase()).thenReturn(flowOf(ApiResponse.Success(testData)))

        viewModel.getToastItemsData()
        viewModel.cakeItems.observeForever(cakeListObserver)

        verify(cakeListObserver).onChanged(testData.toItemPresentationList())

        viewModel.cakeItems.removeObserver(cakeListObserver)
    }

    @Test
    fun `get cake list returns cake data success scenario`() = runTest {
        val testData = getMockItemList()

        whenever(getCakesUseCase()).thenReturn(flowOf(ApiResponse.Success(testData)))

        viewModel.getToastItemsData()
        viewModel.cakeItems.observeForever(cakeListObserver)

        verify(cakeListObserver).onChanged(testData.toItemPresentationList())

        viewModel.cakeItems.removeObserver(cakeListObserver)
    }

    @Test
    fun `cake list returns error scenario`() = runTest {
        val exception = Exception()

        whenever(getCakesUseCase()).thenReturn(flowOf(ApiResponse.Error(exception)))

        viewModel.getToastItemsData()
        viewModel.error.observeForever(errorObserver)

        verify(errorObserver).onChanged(true)

        viewModel.error.removeObserver(errorObserver)
    }

    private val expectedResponseData = """
[  
   {  
      "title":"Lemon cheesecake",
      "desc":"A cheesecake made of lemon",
      "image":"https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg"
   },
   {  
      "title":"victoria sponge",
      "desc":"sponge with jam",
      "image":"https://upload.wikimedia.org/wikipedia/commons/0/05/111rfyh.jpg"
   },
   {  
      "title":"Carrot cake",
      "desc":"Bugs bunnys favourite",
      "image":"https://hips.hearstapps.com/del.h-cdn.co/assets/18/08/1519321610-carrot-cake-vertical.jpg"
   },
   {  
      "title":"Banana cake",
      "desc":"Donkey kongs favourite",
      "image":"http://ukcdn.ar-cdn.com/recipes/xlarge/ff22df7f-dbcd-4a09-81f7-9c1d8395d936.jpg"
   },
   {  
      "title":"Birthday cake",
      "desc":"a yearly treat",
      "image":"https://www.frenchvillagebakery.co.uk/databaseimages/prd_8594342__painted_pink_and_gold_cake_512x640.jpg"
   },
   {  
      "title":"Lemon cheesecake",
      "desc":"A cheesecake made of lemon",
      "image":"https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg"
   },
   {  
      "title":"victoria sponge",
      "desc":"sponge with jam",
      "image":"https://upload.wikimedia.org/wikipedia/commons/0/05/111rfyh.jpg"
   },
   {  
      "title":"Carrot cake",
      "desc":"Bugs bunnys favourite",
      "image":"https://hips.hearstapps.com/del.h-cdn.co/assets/18/08/1519321610-carrot-cake-vertical.jpg"
   },
   {  
      "title":"Banana cake",
      "desc":"Donkey kongs favourite",
      "image":"http://ukcdn.ar-cdn.com/recipes/xlarge/ff22df7f-dbcd-4a09-81f7-9c1d8395d936.jpg"
   },
   {  
      "title":"Birthday cake",
      "desc":"a yearly treat",
      "image":"https://www.frenchvillagebakery.co.uk/databaseimages/prd_8594342__painted_pink_and_gold_cake_512x640.jpg"
   },
   {  
      "title":"Lemon cheesecake",
      "desc":"A cheesecake made of lemon",
      "image":"https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg"
   },
   {  
      "title":"victoria sponge",
      "desc":"sponge with jam",
      "image":"https://upload.wikimedia.org/wikipedia/commons/0/05/111rfyh.jpg"
   },
   {  
      "title":"Carrot cake",
      "desc":"Bugs bunnys favourite",
      "image":"https://hips.hearstapps.com/del.h-cdn.co/assets/18/08/1519321610-carrot-cake-vertical.jpg"
   },
   {  
      "title":"Banana cake",
      "desc":"Donkey kongs favourite",
      "image":"http://ukcdn.ar-cdn.com/recipes/xlarge/ff22df7f-dbcd-4a09-81f7-9c1d8395d936.jpg"
   },
   {  
      "title":"Birthday cake",
      "desc":"a yearly treat",
      "image":"https://www.frenchvillagebakery.co.uk/databaseimages/prd_8594342__painted_pink_and_gold_cake_512x640.jpg"
   },
   {  
      "title":"Lemon cheesecake",
      "desc":"A cheesecake made of lemon",
      "image":"https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg"
   },
   {  
      "title":"victoria sponge",
      "desc":"sponge with jam",
      "image":"https://upload.wikimedia.org/wikipedia/commons/0/05/111rfyh.jpg"
   },
   {  
      "title":"Carrot cake",
      "desc":"Bugs bunnys favourite",
      "image":"https://hips.hearstapps.com/del.h-cdn.co/assets/18/08/1519321610-carrot-cake-vertical.jpg"
   },
   {  
      "title":"Banana cake",
      "desc":"Donkey kongs favourite",
      "image":"http://ukcdn.ar-cdn.com/recipes/xlarge/ff22df7f-dbcd-4a09-81f7-9c1d8395d936.jpg"
   },
   {  
      "title":"Birthday cake",
      "desc":"a yearly treat",
      "image":"https://www.frenchvillagebakery.co.uk/databaseimages/prd_8594342__painted_pink_and_gold_cake_512x640.jpg"
   }
]
"""

    private fun getMockItemList() : List<CakeDomainModel> {
        val gson = GsonBuilder().create()
        val typeToken = object : TypeToken<List<CakeDomainModel>>() {}.type
        return gson.fromJson(expectedResponseData, typeToken)
    }

    private val sortedResponseData = """
[  
   {  
      "title":"Banana cake",
      "desc":"Donkey kongs favourite",
      "image":"http://ukcdn.ar-cdn.com/recipes/xlarge/ff22df7f-dbcd-4a09-81f7-9c1d8395d936.jpg"
   },
   {  
      "title":"Birthday cake",
      "desc":"a yearly treat",
      "image":"https://www.frenchvillagebakery.co.uk/databaseimages/prd_8594342__painted_pink_and_gold_cake_512x640.jpg"
   },
   {  
      "title":"Carrot cake",
      "desc":"Bugs bunnys favourite",
      "image":"https://hips.hearstapps.com/del.h-cdn.co/assets/18/08/1519321610-carrot-cake-vertical.jpg"
   },
   {  
      "title":"Lemon cheesecake",
      "desc":"A cheesecake made of lemon",
      "image":"https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg"
   },
   {  
      "title":"victoria sponge",
      "desc":"sponge with jam",
      "image":"https://upload.wikimedia.org/wikipedia/commons/0/05/111rfyh.jpg"
   }
]
"""

    private fun sortedItemList() : List<CakeDomainModel> {
        val gson = GsonBuilder().create()
        val typeToken = object : TypeToken<List<CakeDomainModel>>() {}.type
        return gson.fromJson(sortedResponseData, typeToken)
    }

}
