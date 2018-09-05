package com.pokedroid.domain.interactors

import com.pokedroid.domain.interactors.base.RetrieveInteractor
import com.pokedroid.domain.model.Location
import io.reactivex.Observable

class RetrieveLocations : RetrieveInteractor<Unit, List<Location>> {

    override fun retrieveBehaviorStream(params: Unit): Observable<List<Location>> {
        val fakeData = listOf<Location>()
        return Observable.just(fakeData)
    }
}