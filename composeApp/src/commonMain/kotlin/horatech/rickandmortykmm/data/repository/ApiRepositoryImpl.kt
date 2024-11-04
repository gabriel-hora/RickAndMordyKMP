package horatech.rickandmortykmm.data.repository

import horatech.rickandmortykmm.data.datasource.ApiDataSource
import horatech.rickandmortykmm.data.datasource.ApiDataSourceImpl
import horatech.rickandmortykmm.data.model.Character
import horatech.rickandmortykmm.data.model.CharacterResponse
import io.ktor.client.call.body

class ApiRepositoryImpl : ApiRepository {

    private val apiDataSource: ApiDataSource = ApiDataSourceImpl()

    override suspend fun getCharacter(): List<Character> {
        return apiDataSource.getCharacters().body<CharacterResponse>().results
    }
}