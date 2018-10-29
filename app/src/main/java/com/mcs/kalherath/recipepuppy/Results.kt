package com.mcs.kalherath.recipepuppy

import com.google.gson.annotations.SerializedName

data class Results(val title: String,
                   val version: String,
                   val href: String,
                   val results: MutableList<Item>)