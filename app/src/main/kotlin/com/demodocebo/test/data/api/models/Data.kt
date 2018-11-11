package com.demodocebo.test.data.api.models

class Data (
    var total_count: Int,
    var total_page_count: Int,
    var current_page_size: Int,
    var current_page: Int,
    var has_more_data: Boolean,
    var count: Int,
    var items: List<Item>
)
