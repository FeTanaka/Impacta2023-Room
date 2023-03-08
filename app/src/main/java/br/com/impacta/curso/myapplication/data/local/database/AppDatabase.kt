package br.com.impacta.curso.myapplication.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.impacta.curso.myapplication.data.local.daos.ContatoDao
import br.com.impacta.curso.myapplication.data.models.Contato

@Database(entities = [Contato::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun contatoDao(): ContatoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "appDatabase"
                ).build()
                INSTANCE = instance
//                cargaInicial()
                instance
            }
        }

        private fun cargaInicial() {
            INSTANCE?.contatoDao()?.inserir(Contato(1, "Fernando", "011xxxxxx"))
            INSTANCE?.contatoDao()?.inserir(Contato(2, "Fernando 2", "011xxxxxx"))
        }
    }
}