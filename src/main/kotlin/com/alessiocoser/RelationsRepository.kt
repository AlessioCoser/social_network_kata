package com.alessiocoser

interface RelationsRepository {
    fun follow(follower: String, followed: String)

    fun followedBy(follower: String): List<String>
}