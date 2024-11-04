package horatech.rickandmortykmm.data.repository

import horatech.rickandmortykmm.data.model.Character

interface ApiRepository {
    suspend fun getCharacter(): List<Character>
}