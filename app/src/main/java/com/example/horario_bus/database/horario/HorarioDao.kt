package com.example.horario_bus.database.horario

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow



@Dao
interface HorarioDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(horario: Horario)

    @Query("SELECT * FROM horario_bus ORDER BY hora_llegada ASC")
    fun getAll(): Flow<List<Horario>>

    @Query("SELECT * FROM horario_bus WHERE id = :id LIMIT 1")
    suspend fun getById(id: Int): Horario?

    @Query("DELETE FROM horario_bus")
    suspend fun deleteAll()
}