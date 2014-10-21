package services.crudservices

import domain.people.Facilitator
import domain.stuff.Conversation
import repository.ConversationRepository.ConversationRepository

/**
 * Created by alex on 2014/10/09.
 */
trait ConversationCRUDInterface {

  def create( fac: Facilitator, convo: Conversation ): Conversation
  def read(id: Long): Conversation
  def update( desc: String, id: Long)
  def delete(id: Long)

}
