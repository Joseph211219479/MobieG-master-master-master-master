package services.crudservices

import domain.people.Facilitator
import people.Members
import repository.MembersRepository.MembersRepository

/**
 * Created by alex on 2014/10/09.
 */
trait MembersCRUDInterface {

  def create( mem: Members, fac: Facilitator ): Members
  def read(id: Long): List[MembersRepository#TableElementType]
  def update( desc: Long, id: Long)
  def delete(id: Long)

}
