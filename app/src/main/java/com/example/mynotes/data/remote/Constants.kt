package com.example.mynotes.data.remote

const val BASE_URL = "http://192.168.5.60:8001"
const val REGISTER = "/register"
const val LOGIN = "/login"
const val GET_NOTES = "/getNotes"
const val DELETE_NOTE = "/deleteNote"
const val ADD_NOTE = "/addNote"
const val ADD_OWNER_TO_NOTE = "/addOwnerToNote"
const val AUTHORIZATION = "Authorization"
const val ACCEPT = "Accept"
const val CONTENT_TYPE = "application/json"
const val BEARER = "Bearer "
const val UNAUTHORIZED_CODE = 401
object Constants {
    val IGNORE_AUTH_URLS = listOf(REGISTER)
}