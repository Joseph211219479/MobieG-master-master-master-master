package services

import repository.PersonRepository.PersonRepository

/**
 * Created by akhona on 2014/09/24.
 */
trait getFacilitatorTypeInt {
      def getType( typ : String ) : List[PersonRepository#TableElementType]
}
