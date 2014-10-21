package services


import repository.MemberEncountersRepository.MemberEncountersRepository

/**
 * Created by akhona on 2014/09/23.
 */
trait getUserHistoryInt {

    def counter( id : Long ) : List[MemberEncountersRepository#TableElementType]

}


