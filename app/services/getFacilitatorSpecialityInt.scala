package services

import repository.PersonRepository.PersonRepository
import repository.SpecialityRepository.SpecialityRepository

/**
 * Created by akhona on 2014/09/25.
 */
trait getFacilitatorSpecialityInt {
  def getSpeciality( speciality : String ) : List[PersonRepository#TableElementType]

  def getAll() : List[SpecialityRepository#TableElementType]
}
