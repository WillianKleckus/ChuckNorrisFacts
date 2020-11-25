package com.kleckus.domain.models

sealed class InjectionTags {
    sealed class Data{
        object CnApi : Data()
    }
}