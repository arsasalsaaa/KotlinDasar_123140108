package com.itera.pam.p7.solusi

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.itera.pam.p7.db.AppDatabase
import com.itera.pam.p7.db.Catatan
import kotlinx.coroutines.flow.Flow
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList

// Hands-on 2: SQLDelight — Insert & Query
// Lengkapi CatatanRepository di bawah agar bisa membaca semua catatan
// sebagai Flow (otomatis emit ulang saat data berubah) dan menambah/
// menghapus catatan. Kamu akan memakai repository ini di Latihan 3.

class CatatanRepository(private val database: AppDatabase) {

    fun getAll(): Flow<List<Catatan>> {
        // TODO 1: import app.cash.sqldelight.coroutines.asFlow dan
        //         app.cash.sqldelight.coroutines.mapToList, lalu:
        return database.catatanQueries.selectAll()
            .asFlow()
            .mapToList(kotlinx.coroutines.Dispatchers.Default)
        TODO("Lengkapi getAll()")
    }

    fun tambah(judul: String, isi: String) {
        // TODO 2:
        database.catatanQueries.insertCatatan(judul, isi)
    }

    fun hapus(id: Long) {
        // TODO 3: d
        database.catatanQueries.deleteById(id)
    }
}

@Composable
fun Handson2ScreenSolusi(database: AppDatabase) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Hands-on 2: SQLDelight Repository")
        Text("Lengkapi CatatanRepository di file ini (Handson2Repository.kt), lalu buka Latihan 3 untuk mencobanya langsung di UI.")
    }
}

// === Penjelasan ===
// 1. import app.cash.sqldelight.coroutines.asFlow & import app.cash.sqldelight.coroutines.mapToList
// Menambahkan import library SQLDelight Coroutines extensions agar query SQLDelight dapat diubah menjadi Kotlin Flow yang reaktif.
//
// 2. TODO 1
// database.catatanQueries.selectAll() : mengambil semua data catatan.
// .asFlow() : mengubah query tersebut menjadi reaktif berbasis Kotlin Flow.
//◦ .mapToList(kotlinx.coroutines.Dispatchers.Default) : memetakan hasil query menjadi bentuk List<Catatan> yang berjalan pada background thread (Dispatchers.Default), sehingga otomatis memperbarui data (emit ulang) saat ada perubahan di database.
//
// 3. TODO 2 : database.catatanQueries.insertCatatan(judul, isi)
// Memanggil query insertCatatan yang didefinisikan di file SQLDelight untuk memasukkan data catatan baru dengan parameter judul dan isi.
//
// 4. database.catatanQueries.deleteById(id) (pada TODO 3)
// Memanggil query deleteById untuk menghapus data catatan dari database berdasarkan primary key id catatan yang dipilih.