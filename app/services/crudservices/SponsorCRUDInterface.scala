package services.crudservices

import domain.people.Facilitator
import domain.stuff.{Sponsor, Speciality, Channel}
import repository.SponsorRepository.SponsorRepository

/**
 * Created by alex on 2014/10/09.
 */
trait SponsorCRUDInterface {

  def create( facs: Facilitator, chan: Channel, spec: Speciality, spo: Sponsor ): Sponsor
  def read(id: Long): Sponsor
  def update( desc: String, id: Long)
  def delete(id: Long)
}
