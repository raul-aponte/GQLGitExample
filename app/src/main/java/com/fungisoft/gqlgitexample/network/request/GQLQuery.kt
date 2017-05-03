package com.fungisoft.gqlgitexample.network.request

class GQLQuery(var query: String? = null) {
    override fun toString(): String {
        return query ?: ""
    }
}