package com.alessiocoser

sealed class Command
class FollowCommand(val follower: String, val followed: String) : Command()
class WallCommand(val owner: String) : Command()
class NewMessageCommand(val owner: String, val text: String) : Command()
class UserMessagesCommand(val owner: String) : Command()