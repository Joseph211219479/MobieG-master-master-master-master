package services

import repository.SpecialityRepository.SpecialityRepository

/**
 * Created by joseph on 2014/10/21.
 */
trait SpecialityService
{
  def getAll() : List[SpecialityRepository#TableElementType]

  def getBySpecID(specID : Long) :List[SpecialityRepository#TableElementType]

  def getByName(name : String) : List[SpecialityRepository#TableElementType]

  def getByFacilitatorID( facID : Long) : List[SpecialityRepository#TableElementType]

}
