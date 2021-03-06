/*
 * Syrup auto-generated support file.
 * Do not Modify this file manually.
 */
package com.shopify.syrup.support

import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type

const val DEFINED_NULL_FLAG = "__DEFINED_NULL"

interface SyrupOperation<T : Response> {
    fun getQueryString(): String
    fun decodeResponse(jsonObject: JsonObject): T
    val selections: List<Selection>
}

interface Query<T : Response> : SyrupOperation<T>

interface Mutation<T : Response> : SyrupOperation<T>

interface Response

object OperationGsonBuilder {
    var gson: Gson = createDefaultBuilder().create()

    fun createDefaultBuilder(): GsonBuilder {
        return GsonBuilder()
            .addSerializationExclusionStrategy(InputWrapperExclusionStrategy)
            .registerTypeAdapter(ID::class.java, IDTypeAdapter())
            .registerTypeAdapter(InputWrapper::class.java, InputWrapperSerializer())
    }
}

inline class ID(val id: String)

class IDTypeAdapter : JsonSerializer<ID>, JsonDeserializer<ID> {
    override fun serialize(src: ID, typeOfSrc: Type?, context: JsonSerializationContext?) = JsonPrimitive(src.toString())
    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?) = ID(json.asString)
}

data class Selection(
    val cacheKey: String,
    val name: String,
    val type: String,
    val passedGID: String? = null,
    val backingGIDReference: String? = null,
    val typeCondition: String? = null,
    val shouldSkipBasedOnConditionalDirective: Boolean = false,
    val selections: List<Selection> = listOf()
) {
    fun hasSelections() = selections.isNotEmpty()
    fun hasGID() = backingGIDReference != null
}

inline fun <reified T : Enum<T>> JsonObject.decodeEnumValue(key: String): String {
    val jsonElement = this.get(key)
    return jsonElement.asString
}

fun JsonObject.decodePrimitivesArray(key: String): ArrayList<Any> {
    val jsonArray = getAsJsonArray(key)
    val list: ArrayList<Any> = arrayListOf()
    jsonArray.filter { it.isJsonPrimitive }.mapTo(list) { it.asJsonPrimitive }
    return list
}

object InputWrapperExclusionStrategy : ExclusionStrategy {
    override fun shouldSkipField(fieldAttributes: FieldAttributes): Boolean {
        return when (fieldAttributes.name) {
            "rawQueryString", "gson", "responseType", "selections", "operationVariables" -> true
            else -> false
        }
    }

    override fun shouldSkipClass(aClass: Class<*>): Boolean {
        return false
    }
}

class InputWrapperSerializer : JsonSerializer<InputWrapper<Any?>> {
    override fun serialize(src: InputWrapper<Any?>, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement? {
        return if (src.isDefined()) {
            if (src.value() == null) {
                JsonPrimitive(DEFINED_NULL_FLAG)
            } else {
                OperationGsonBuilder.gson.toJsonTree(src.value())
            }
        } else {
            null
        }
    }
}

fun setDefinedNulls(string: String): String {
    return string.replace("\"$DEFINED_NULL_FLAG\"", "null")
}
