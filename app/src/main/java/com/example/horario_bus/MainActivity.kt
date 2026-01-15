package com.example.horario_bus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.horario_bus.database.AppDatabase
import com.example.horario_bus.database.horario.Horario
import com.example.horario_bus.ui.theme.Horario_busTheme
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val context = LocalContext.current
            val db = remember { AppDatabase.getDatabase(context) }
            val dao = remember { db.horarioDao() }

            val horarios by dao.getAll().collectAsState(initial = emptyList())

            val scope = rememberCoroutineScope()

            MaterialTheme {
                Column(Modifier.padding(16.dp)) {

                    Button(onClick = {
                        scope.launch {
                            dao.insert(
                                Horario(
                                    id = 0,
                                    stopName = "Parada Centro",
                                    arrivalTime = 830 // ejemplo
                                )
                            )
                        }
                    }) {
                        Text("Añadir Horario")
                    }

                    Spacer(Modifier.height(16.dp))

                    Text("Horarios en BD: ${horarios.size}")

                    Spacer(Modifier.height(8.dp))

                    horarios.forEach { h ->
                        Text("- ${h.stopName} -> ${h.arrivalTime}")
                    }
                }
            }
        }
    }
}

