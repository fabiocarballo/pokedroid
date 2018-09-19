package com.pokedroid.domain.interactors

import com.pokedroid.domain.interactors.base.RetrieveInteractor
import com.pokedroid.domain.model.Location
import io.reactivex.Observable

class RetrieveLocations : RetrieveInteractor<Unit, List<Location>> {

    override fun retrieveBehaviorStream(params: Unit): Observable<List<Location>> {
        val fakeData = listOf(Location(1,"Ilke's Mansion"), Location(2, "Fabio's Mansion"))
        return Observable.just(fakeData)
    }
}