package com.sample.cakesshop.view.mapper

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.sample.cakesshop.domain.model.CakeDomainModel
import junit.framework.Assert.assertEquals
import org.junit.Test


/**
 * Created by AnkitSingh on 1/3/23.
 */
class DomainToPresentationMapperTest {

    @Test
    fun `cake list is sorted and duplicates are removed from the response`() {

        val unsortedListWithDuplicates = getMockItemList()
        val sortedListWithNoDuplicates = sortedItemList()

        assertEquals(unsortedListWithDuplicates.toItemPresentationList(), sortedListWithNoDuplicates.toItemPresentationList())
    }

    private val responseData = """
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
        return gson.fromJson(responseData, typeToken)
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