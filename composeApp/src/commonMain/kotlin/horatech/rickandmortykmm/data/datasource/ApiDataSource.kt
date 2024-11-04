package horatech.rickandmortykmm.data.datasource

import horatech.rickandmortykmm.data.model.CharacterResponse
import io.ktor.client.statement.HttpResponse

interface ApiDataSource {
    suspend fun getCharacters(): HttpResponse
}