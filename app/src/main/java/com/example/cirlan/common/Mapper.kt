package com.example.cirlan.common

interface Mapper<F,T> {

    fun mapFrom(from: F): T

}