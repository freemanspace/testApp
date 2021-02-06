package com.freeman.samplekotlin.data

import org.json.JSONObject
import java.lang.Exception

class Test_Json :Base_Json(),OnJsonParser {

    var page = 0
    var per_page = 0
    var total = 0
    var total_pages = 0
    var datas:ArrayList<Test_Data> = ArrayList()

    override fun parser(str: String?) {
        try{
            datas.clear()
            var jsonData = JsonParser.convertToJSonObject(str)
            page = JsonParser.getInt(jsonData,"page")
            per_page = JsonParser.getInt(jsonData,"per_page")
            total = JsonParser.getInt(jsonData,"total")
            total_pages = JsonParser.getInt(jsonData,"total_pages")
            var jsonArray = JsonParser.getJsonArray(jsonData,"data")
            var jsonObject:JSONObject?=null
            if(jsonArray!=null){
                var testData:Test_Data? = null
                for (i in 0 .. jsonArray.length()){
                    jsonObject = jsonArray.getJSONObject(i)
                    testData = Test_Data()
                    testData.id = JsonParser.getInt(jsonObject,"id")
                    testData.email = JsonParser.getString(jsonObject,"email")
                    testData.first_name = JsonParser.getString(jsonObject,"first_name")
                    testData.last_name = JsonParser.getString(jsonObject,"last_name")
                    testData.avatar = JsonParser.getString(jsonObject,"avatar")
                    datas.add(testData)
                }
            }
        } catch (e:Exception){
            success = false
        }
    }


    public class Test_Data{
        var id = 0
        var email = ""
        var first_name = ""
        var last_name = ""
        var avatar = ""
    }
}