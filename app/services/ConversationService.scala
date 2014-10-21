package services

import repository.ConversationMessageRepository.ConversationMessageRepository


/**
 * Created by joseph on 2014/09/24.
 */
trait ConversationService
{
  def getAllConversationsOfFacilitator(facID :Long, memID :Long ) : List[ConversationMessageRepository#TableElementType]

  def getByMemberId(id : Long) : List[ConversationMessageRepository#TableElementType]

  def getByFacilitatorId(id : Long) : List[ConversationMessageRepository#TableElementType]

}
