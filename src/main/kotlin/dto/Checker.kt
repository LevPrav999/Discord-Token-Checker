package dto

interface Checker {
    fun checkFile(path: String)
    fun checkToken(token: String)
}