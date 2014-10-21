package services

import people.{Person, Members}
import repository.FacilitatorRepository.FacilitatorRepository
import repository.MembersRepository.MembersRepository

/**
 * Created by joseph on 2014/09/24.
 */
trait FacilitatorServices
{
  //def getAllMembersServed(facilitatorID :String) : List[MembersRepository#TableElementType]


  def getAllFacilitators() : List[FacilitatorRepository#TableElementType]

  def hasMemberBeenServedByFacilitator(memberID :Long, facilitatorID : Long) : Boolean

  def getByID(id : Long ): Person
}
