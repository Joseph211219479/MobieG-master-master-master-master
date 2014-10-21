package services.crudservices

import domain.people.Facilitator
import domain.stuff.Channel
import repository.ChannelRepository.ChannelRepository

/**
 * Created by akhona on 2014/10/07.
 */
trait ChannelCRUDInterface {

  def create( chan: Channel, fac: Facilitator ): Channel
  def read(id: Long): Channel
  def update( desc: String, id: Long)
  def delete(id: Long)

}
