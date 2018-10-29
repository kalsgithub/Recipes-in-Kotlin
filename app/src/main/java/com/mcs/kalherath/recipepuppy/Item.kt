package com.mcs.kalherath.recipepuppy

import com.google.gson.annotations.SerializedName

data class Item(val title : String,
                val href : String,
                val ingredients : String,
                val thumbnail : String)
