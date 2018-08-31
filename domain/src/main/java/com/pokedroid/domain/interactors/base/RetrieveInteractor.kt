package com.pokedroid.domain.interactors.base

import io.reactivex.Observable

/**
 * Retrieves changes from the data layer.
 * It returns an [Observable] that emits updates for the retrieved object. The returned [Observable] will never complete, but it can
 * error if there are any problems performing the required actions to serve the data.
 *
 * @param Data the type of the retrieved data.
 * @param Params required parameters for the retrieve operation.
 */
interface RetrieveInteractor<in Params, Data>  {

    fun retrieveBehaviorStream(params: Params): Observable<Data>
}