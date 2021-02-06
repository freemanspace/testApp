package com.freeman.samplekotlin.util

import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import java.lang.Exception

object Util_Json {

    /**
     * str convert to jsonarray
     */
    @Synchronized
    fun convertToJSONArray(str:String): JSONArray? {
        var jsonArray:JSONArray? = null
        try{
            var jsonParser = JSONTokener(str)
            jsonArray = jsonParser.nextValue() as JSONArray
        } catch (e:Exception){
            e.stackTrace
        }
        return jsonArray
    }

    /**
     * str convert to jsonobject
     */
    @Synchronized
    fun convertToJSONObject(str:String): JSONObject? {
        var jsonObject:JSONObject? = null
        try{
            var jsonParser = JSONTokener(str)
            jsonObject = jsonParser.nextValue() as JSONObject
        } catch (e:Exception){
            e.stackTrace
        }
        return jsonObject
    }

    /**
     * 取得json object
     */
    @Synchronized
    fun getJsonObject(jsonobj:JSONObject,key:String):JSONObject?{
        var jsonObject:JSONObject? = null
        try{
            if(!jsonobj.isNull(key)){
                jsonObject = jsonobj.getJSONObject(key)
            }
        } catch (e:Exception){
            e.stackTrace
        }
        return jsonObject
    }

    /**
     * 取得json array
     */
    @Synchronized
    fun getJsonArray(jsonobj:JSONObject,key:String):JSONArray? {
        var jsonArray:JSONArray? = null
        try{
            if(!jsonobj.isNull(key)){
                jsonArray = jsonobj.getJSONArray(key)
            }
        } catch (e:Exception){
            e.stackTrace
        }
        return jsonArray
    }

    /**
     * 取得str
     */
    @Synchronized
    fun getJsonStr(jsonobj: JSONObject,key:String):String{
        var returnStr:String = "";
        try{
            if(!jsonobj.isNull(key)){
                returnStr = jsonobj.getString(key)
            }
        } catch (e:Exception){
            e.stackTrace
            returnStr = ""
        }
        return returnStr
    }

    /**
     * 取得int
     */
    @Synchronized
    fun getJsonInt(jsonobj: JSONObject,key:String):Int{
        var returnInt:Int = 0;
        try{
            if(!jsonobj.isNull(key)){
                returnInt = jsonobj.getInt(key)
            }
        } catch (e:Exception){
            e.stackTrace
            returnInt = 0
        }
        return returnInt
    }

    /**
     * 取得long
     */
    @Synchronized
    fun getJsonLong(jsonobj: JSONObject,key:String):Long{
        var returnLong:Long = 0;
        try{
            if(!jsonobj.isNull(key)){
                returnLong = jsonobj.getLong(key)
            }
        } catch (e:Exception){
            e.stackTrace
            returnLong = 0
        }
        return returnLong
    }

    /**
     * 取得double
     */
    @Synchronized
    fun getJsonDouble(jsonobj: JSONObject,key:String):Double{
        var returnDouble:Double = 0.0;
        try{
            if(!jsonobj.isNull(key)){
                returnDouble = jsonobj.getDouble(key)
            }
        } catch (e:Exception){
            e.stackTrace
            returnDouble = 0.0
        }
        return returnDouble
    }

    /**
     * 取得boolean
     */
    @Synchronized
    fun getJsonBool(jsonobj: JSONObject,key:String,def:Boolean):Boolean{
        var returnBoolean:Boolean = def;
        try{
            if(!jsonobj.isNull(key)){
                returnBoolean = jsonobj.getBoolean(key)
            }
        } catch (e:Exception){
            e.stackTrace
            returnBoolean = def
        }
        return returnBoolean
    }
}