package com.kleckus.chucknorrisfacts.system

class SysEnvironment{
    companion object{
        val currentEnvironment = Environment.TESTING
    }
}

enum class Environment{
    PRODUCTION, TESTING
}