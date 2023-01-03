package com.sample.cakesshop.data.repository

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.nhaarman.mockitokotlin2.whenever
import com.sample.cakesshop.data.mapper.toCakeDomainList
import com.sample.cakesshop.data.model.Cake
import com.sample.cakesshop.data.remote.CakeApiService
import com.sample.cakesshop.domain.model.ApiResponse
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Created by AnkitSingh on 12/28/22.
 */
class CakeRepositoryImplTest {
    @Mock
    private lateinit var cakeApiService: CakeApiService

    private lateinit var cakeRepositoryImpl: CakeRepositoryImpl


    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        cakeRepositoryImpl = CakeRepositoryImpl(cakeApiService)
    }

    @Test
    fun `get cake list returns success scenario`()  = runBlocking {
        val testData = getMockItemList()
        val expectedOutput = ApiResponse.Success(testData.toCakeDomainList())
        whenever(cakeApiService.getCakesList()).thenReturn(testData)

        val flow = cakeRepositoryImpl.getCakesList()

        // Verify
        flow.collect { data->
            assertEquals(expectedOutput, data)
        }
    }

    @Test
    fun `get cake items returns error`()  = runBlocking {
        val exception = RuntimeException("Network Error")
        val expectedOutput = ApiResponse.Error(exception)
        whenever(cakeApiService.getCakesList()).thenThrow(exception)

        val flow = cakeRepositoryImpl.getCakesList()

        // Verify
        flow.collect{ data ->
            assertEquals(expectedOutput, data)
        }
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

    private fun getMockItemList() : List<Cake> {
        val gson = GsonBuilder().create()
        val typeToken = object : TypeToken<List<Cake>>() {}.type
        return gson.fromJson(expectedResponseData, typeToken)
    }

}