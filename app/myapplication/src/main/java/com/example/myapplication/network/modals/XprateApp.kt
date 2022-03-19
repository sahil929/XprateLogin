package com.example.myapplication.network.modals

import android.content.Context

class XprateApp private constructor(
    context: Context?,
    var org_secret_key: String? = null,
    var org_secret_token: String? = null
) {

    data class Builder(
        var org_secret_key: String? = null,
        var org_secret_token: String? = null,
        var context: Context? = null
    ) {
        fun init(context: Context) = apply {
            this.context = context
        }

        fun setSecretKey(org_secret_key: String?) = apply {
            if (!org_secret_key.isNullOrEmpty()) this.org_secret_key = org_secret_key
        }

        fun setSecretToken(org_secret_token: String?) = apply {
            if (!org_secret_token.isNullOrEmpty()) this.org_secret_token = org_secret_token
        }

        fun build() = XprateApp(context, org_secret_key, org_secret_token)
    }
}