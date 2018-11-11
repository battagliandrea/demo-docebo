package com.demodocebo.test.data.api.models

import com.google.gson.annotations.SerializedName

class Item  (
    var is_affiliate: Boolean,
    var is_user_subscribed: Int,
    var price_status: Int,
    var access_status: Int,
    var item_slug: String,
    var item_thumbnail: String,
    var item_language_label: String,
    var duration: String,
    var course_type: String,
    var item_type_int: String,
    var is_opened: String,
    var item_can_enroll: String,
    var item_policy: String,
    var item_visibility: String,
    var is_new: Boolean,
    var item_rating: Int,
    var item_rating_option: String,
    var item_price: String,
    var item_language: String,
    var item_category: String,
    var item_description: String,
    var item_name: String,
    var item_code: String,
    var item_type: String,
    var item_id: String
) : Comparable<Item> {


    override fun compareTo(other: Item): Int = this.item_name.compareTo(other.item_name);
}
