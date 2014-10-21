package services

import people.{Person, Admin}
import repository.AdminRepository.AdminRepository
import repository.PersonRepository.PersonRepository

/**
 * Created by joseph on 2014/10/21.
 */
trait AdminService
{
  def getAdminById(id : Long) : List[PersonRepository#TableElementType]

  def getAllAdmin() : List[PersonRepository#TableElementType]

}
