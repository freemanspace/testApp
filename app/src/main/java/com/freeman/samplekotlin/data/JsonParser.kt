package com.freeman.samplekotlin.data

import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener

class JsonParser {

    @Synchronized
    fun parser(onJsonParser: OnJsonParser, strData: String?) {
        onJsonParser.parser(strData)
    }

    companion object{

        /**
         * 將str轉為JSONArray
         * @param str
         * @return
         */
        @Synchronized
        fun convertToJSONArray(str: String?): JSONArray? {
            var jsonArray: JSONArray? = null
            try {
                val jsonParser = JSONTokener(str)
                jsonArray = jsonParser.nextValue() as JSONArray
            } catch (e: Exception) {
            }
            return jsonArray
        }

        /**
         * 將str轉為JSONObject
         * @param str
         * @return
         */
        @Synchronized
        fun convertToJSonObject(str: String?): JSONObject? {
            var jsonObject: JSONObject? = null
            try {
                val jsonParser = JSONTokener(str)
                jsonObject = jsonParser.nextValue() as JSONObject
            } catch (e: java.lang.Exception) {
            }
            return jsonObject
        }

        /**
         * 取得jsonObject
         * @param jsonObject
         * @param key
         * @return
         */
        @Synchronized
        fun getJsonObject(jsonObject: JSONObject?, key: String?): JSONObject? {
            var returnJsonObject: JSONObject? = null
            try {
                if (jsonObject?.isNull(key) == false) {
                    returnJsonObject = jsonObject.getJSONObject(key)
                }
            } catch (e: java.lang.Exception) {
            }
            return returnJsonObject
        }

        /**
         * 取得JSONArray
         * @param jsonObject
         * @param key
         * @return
         */
        @Synchronized
        fun getJsonArray(jsonObject: JSONObject?, key: String?): JSONArray? {
            var returnJsonArray: JSONArray? = null
            try {
                if (jsonObject?.isNull(key) == false) {
                    returnJsonArray = jsonObject.getJSONArray(key)
                }
            } catch (e: java.lang.Exception) {
            }
            return returnJsonArray
        }

        /**
         * 取得String有default值
         * @param jsonObject
         * @param key
         * @param defStr
         * @return
         */
        @Synchronized
        fun getDefString(jsonObject: JSONObject?, key: String, defStr: String): String {
            var returnString = ""
            returnString = try {
                if (jsonObject?.isNull(key) == false) {
                    jsonObject.getString(key)
                } else {
                    defStr
                }
            } catch (e: java.lang.Exception) {
                defStr
            }
            return returnString.trim()
        }

        /**
         * 取得String
         * @param jsonObject
         * @param key
         * @return
         */
        @Synchronized
        fun getString(jsonObject: JSONObject?, key: String): String {
            var returnString = ""
            try {
                if (jsonObject?.isNull(key) == false) {
                    returnString = jsonObject.getString(key)
                }
            } catch (e: java.lang.Exception) {
            }
            return returnString.trim()
        }

        /**
         * 取得int
         * @param jsonObject
         * @param key
         * @return
         */
        fun getInt(jsonObject: JSONObject?, key: String): Int {
            var returnInt = 0
            try {
                if (jsonObject?.isNull(key) == false) {
                    returnInt = jsonObject.getInt(key)
                }
            } catch (e: java.lang.Exception) {
            }
            return returnInt
        }

        /**
         * 取得int有default值
         * @param jsonObject
         * @param key
         * @param defInt
         * @return
         */
        fun getDefInt(jsonObject: JSONObject?, key: String, defInt: Int): Int {
            var returnInt = 0
            returnInt = try {
                if (jsonObject?.isNull(key) == false) {
                    jsonObject.getInt(key)
                } else {
                    defInt
                }
            } catch (e: java.lang.Exception) {
                defInt
            }
            return returnInt
        }

        /**
         * 取得long有default值
         * @param jsonObject
         * @param key
         * @return
         */
        fun getLong(jsonObject: JSONObject?, key: String): Long {
            var returnLong: Long = 0
            try {
                if (jsonObject?.isNull(key) == false) {
                    returnLong = jsonObject.getLong(key)
                }
            } catch (e: java.lang.Exception) {
            }
            return returnLong
        }

        /**
         * 取得long有default值
         * @param jsonObject
         * @param key
         * @param defLong
         * @return
         */
        fun getDefLong(jsonObject: JSONObject?, key: String, defLong: Long): Long {
            var returnLong: Long = 0
            returnLong = try {
                if (jsonObject?.isNull(key) == false) {
                    jsonObject.getLong(key)
                } else {
                    defLong
                }
            } catch (e: java.lang.Exception) {
                defLong
            }
            return returnLong
        }

        /**
         * 取得double
         * @param jsonObject
         * @param key
         * @return
         */
        fun getDouble(jsonObject: JSONObject?, key: String): Double {
            var returnDouble = 0.0
            try {
                if (jsonObject?.isNull(key) == false) {
                    returnDouble = jsonObject.getDouble(key)
                }
            } catch (e: java.lang.Exception) {
            }
            return returnDouble
        }

        /**
         * 取得double有default值
         * @param jsonObject
         * @param key
         * @param defDouble
         * @return
         */
        fun getDefDouble(jsonObject: JSONObject?, key: String, defDouble: Double): Double {
            var returnDouble = 0.0
            returnDouble = try {
                if (jsonObject?.isNull(key) == false) {
                    jsonObject.getDouble(key)
                } else {
                    defDouble
                }
            } catch (e: java.lang.Exception) {
                defDouble
            }
            return returnDouble
        }

        /**
         * 取得boolean
         * @param jsonObject
         * @param key
         * @return
         */
        fun getBoolean(jsonObject: JSONObject?, key: String): Boolean {
            var returnBoolean = false
            try {
                if (jsonObject?.isNull(key) == false) {
                    returnBoolean = jsonObject.getBoolean(key)
                }
            } catch (e: java.lang.Exception) {
            }
            return returnBoolean
        }

        /**
         * 取得boolean有default值
         * @param jsonObject
         * @param key
         * @param defBoolean
         * @return
         */
        fun getDefBoolean(jsonObject: JSONObject?, key: String, defBoolean: Boolean): Boolean {
            var returnBoolean = false
            returnBoolean = try {
                if (jsonObject?.isNull(key) == false) {
                    jsonObject.getBoolean(key)
                } else {
                    defBoolean
                }
            } catch (e: java.lang.Exception) {
                defBoolean
            }
            return returnBoolean
        }
    }




}