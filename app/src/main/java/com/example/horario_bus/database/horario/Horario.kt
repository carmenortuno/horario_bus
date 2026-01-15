package com.example.horario_bus.database.horario

import android.R.attr.name
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "horario_bus")
data class Horario (
    @PrimaryKey val id: Int,
    @ColumnInfo(name="nombre_parada") val stopName: String,
    @ColumnInfo(name="hora_llegada") val arrivalTime: Int
)