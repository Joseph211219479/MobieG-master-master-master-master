package services.crudservices

import domain.stuff.ConversationMessage
import repository.ConversationMessageRepository.ConversationMessageRepository

/**
 * Created by joseph on 2014/10/22.
 */
trait ConversationMessageCRUDInterface {
  def create(convoMessage: ConversationMessage): ConversationMessage
  def read(id: Long): List[ConversationMessageRepository#TableElementType]
  def update(mess: String, id: Long)
  def delete(id: Long)
}
