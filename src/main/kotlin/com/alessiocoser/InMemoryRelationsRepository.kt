package com.alessiocoser

class InMemoryRelationsRepository: RelationsRepository {
    private val relations: MutableMap<String, MutableList<String>> = mutableMapOf()

    override fun follow(follower: String, followed: String) {
        followedBy(follower).add(followed)
    }

    override fun followedBy(follower: String) = relations.getOrPut(follower) { mutableListOf() }
}