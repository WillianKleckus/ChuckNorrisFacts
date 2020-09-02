package com.kleckus.chucknorrisfacts.system

class SysEnvironment{
    companion object{
        val currentEnvironment = Environment.PRODUCTION
    }
}

enum class Environment{
    PRODUCTION, TESTING
}