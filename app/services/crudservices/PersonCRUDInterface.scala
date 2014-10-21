package services.crudservices

import domain.people.Facilitator
import people.{Person, Admin, Members}
import repository.PersonRepository.PersonRepository

/**
 * Created by alex on 2014/10/09.
 */
trait PersonCRUDInterface {

  def create( fac: Facilitator, mem: Members, adm: Admin, perc: Person ): Person
  def read(id: Long): Person
  def update( desc: String, id: Long)
  def delete(id: Long)
}
