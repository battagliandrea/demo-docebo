package com.demodocebo.test.data.api.models

data class Catalog (
        var _links: List<String>,
        var extra_data: ExtraData,
        var version: String,
        var data: Data
)
