package com.guioliveiraapps.fulllab.model

class ProductQuery {
    var query: String? = null
    var offset: String? = null
    var size: String? = null

    constructor(query: String?, offset: String?, size: String?) {
        this.query = query
        this.offset = offset
        this.size = size
    }
}