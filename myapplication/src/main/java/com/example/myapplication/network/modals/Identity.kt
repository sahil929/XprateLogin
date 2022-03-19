package com.example.myapplication.network.modals

data class Identity(
    val createdAt: String,
    val id: String,
    val identityType: String,
    val isIdentityHolderAndCreatorSame: Boolean,
    val isPrimary: Boolean,
    val isSocial: Boolean,
    val isSync: Boolean,
    val isVerified: Boolean,
    val targetTimes: Int,
    val uniqueIdentity: String
)