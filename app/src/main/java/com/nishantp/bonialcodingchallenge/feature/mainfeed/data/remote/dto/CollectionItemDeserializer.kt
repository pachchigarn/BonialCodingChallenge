package com.nishantp.bonialcodingchallenge.feature.mainfeed.data.remote.dto

import com.google.gson.*
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class CollectionItemDeserializer : JsonDeserializer<List<ContentX>> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): List<ContentX> {
        return if (json?.isJsonObject == true) {
            val item = context?.deserialize<ContentX>(json, ContentX::class.java)
            if(item == null) emptyList() else listOf(item)
        } else {
            Gson().fromJson(json, object : TypeToken<List<ContentX>>() {}.type)
        }
    }
}