package com.itera.pam.p6.solusi

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.call.body
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

// Hands-on 1: Ktor Client Dasar & GET Request
// Endpoint yang dipakai di seluruh hands-on Pertemuan 6:
// https://jsonplaceholder.typicode.com/users (fake REST API gratis untuk belajar,
// mengembalikan array JSON berisi data user).

@Serializable
data class RemoteUser(
    val id: Int,
    val name: String,
    val email: String
)

// TODO 1: Buat HttpClient dengan ContentNegotiation + json() supaya Ktor bisa
//         otomatis decode response JSON menjadi objek Kotlin.
val client: HttpClient = HttpClient {
    install(ContentNegotiation) {
        json(Json { ignoreUnknownKeys = true })
    }
}

// TODO 2: Lakukan GET request ke "https://jsonplaceholder.typicode.com/users"
//         dan decode response body-nya menjadi List<RemoteUser>.
suspend fun fetchUsers(): List<RemoteUser> {
    return client.get("https://jsonplaceholder.typicode.com/users").body()
}

@Composable
fun Handson1ScreenSolusi() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Hands-on 1: Ktor Client Dasar & GET Request")
    }
}

// === Penjelasan ===
// 1. Perubahan pada TODO 1 (val client)
// - val client: HttpClient = HttpClient {
// Mendeklarasikan variabel client berjenis HttpClient dan menginisialisasinya menggunakan konstruktor blok builder HttpClient { ... }.
// - install(ContentNegotiation) {
// Memasang (install) plugin ContentNegotiation pada HTTP Client untuk menangani konversi data (salah satunya deserialisasi JSON ke objek Kotlin) secara otomatis.
// - json(Json { ignoreUnknownKeys = true })
// Mengonfigurasi serializer JSON (kotlinx.serialization) dengan opsi ignoreUnknownKeys = true. Opsi ini mencegah terjadinya error/crash jika API mengembalikan field JSON tambahan yang tidak kita definisikan di RemoteUser.

// 2. Perubahan pada TODO 2 (suspend fun fetchUsers)
// - suspend fun fetchUsers(): List<RemoteUser> {
// Mendeklarasikan fungsi bertanda suspend bernama fetchUsers() yang mengembalikan data ber-tipe List<RemoteUser>. Kata kunci suspend wajib digunakan karena proses request jaringan berjalan secara asinkron tanpa menghentikan (blocking) thread utama UI.
// - return client.get("https://jsonplaceholder.typicode.com/users").body()
// Mengirim HTTP GET request ke URL endpoint REST API menggunakan client.get(...), lalu memanggil ekstensi .body() untuk mendekode (parsing) otomatis isi response JSON menjadi daftar objek List<RemoteUser>.