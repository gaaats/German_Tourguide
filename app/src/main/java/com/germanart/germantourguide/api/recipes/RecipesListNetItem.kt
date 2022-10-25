package com.germanart.germantourguide.api.recipes


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipesListNetItem(
    @SerializedName("ingredients")
    val ingredients: String?,
    @SerializedName("instructions")
    val instructions: String?,
    @SerializedName("servings")
    val servings: String?,
    @SerializedName("title")
    val title: String?
) : Parcelable {
    val _ingredients: String get() = ingredients ?: "no ingredients"
    val _instructions: String get() = instructions ?: "no instructions"
    val _servings: String get() = servings ?: "no servings"
    val _title: String get() = title ?: "no title"
}