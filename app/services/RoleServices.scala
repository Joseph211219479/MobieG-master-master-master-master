package services

import people.Role
import repository.RoleRepository.RoleRepository

/**
 * Created by joseph on 2014/10/03.
 */
trait RoleServices
{
  def allServices() : List[RoleRepository#TableElementType]

  def roleById(id : Long): Role

  def getByName(name : String) : Role
}
