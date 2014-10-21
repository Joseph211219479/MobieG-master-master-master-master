package services

import domain.stuff.Channel
import people.Members
import repository.MembersRepository.MembersRepository

/**
 * Created by joseph on 2014/09/23.
 */
trait MembersService
{
  def getAllMembers() : List[MembersRepository#TableElementType]
  def getMemberByID(id : Long) : List[MembersRepository#TableElementType]
  def getConversationHistory(idMember : Long ,idFac : Long) : List[MembersRepository#TableElementType]
  def getLastServedMember(facilitatorID : Long) : List[MembersRepository#TableElementType]

}
