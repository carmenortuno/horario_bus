package com.example.horario_bus.database.horario

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HorarioDao {
    @Query("SELECT * FROM horario_bus ORDER BY hora_llegada ASC")
    fun obtenerTodos(): List<Horario>

    @Query("SELECT * FROM horario_bus WHERE nombre_parada = :nombreParada ORDER BY hora_llegada ASC")
    fun obtenerPorNombreParada(nombreParada: String): List<Horario>

    @Insert
    fun insertarHorario(horario: Horario)

    @Delete
    fun eliminarHorario(id: Int)
}