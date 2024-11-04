package horatech.rickandmortykmm.data.datasource

import horatech.rickandmortykmm.data.service.ApiService
import io.ktor.client.statement.HttpResponse

class ApiDataSourceImpl : ApiDataSource {

    private val apiService = ApiService()

    override suspend fun getCharacters(): HttpResponse {
        return apiService.service("/character")
    }
}