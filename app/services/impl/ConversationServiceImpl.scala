package services.impl

import repository.ConversationMessageRepository.ConversationMessageRepository
import repository.ConversationRepository.ConversationRepository
import services.ConversationService
import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.TableQuery
/**
 * Created by joseph on 2014/09/24.
 */
class ConversationServiceImpl extends ConversationService
{
  val convoRepo = TableQuery[ConversationMessageRepository]

  override def getAllConversationsOfFacilitator(facID: Long, memID: Long): List[ConversationMessageRepository#TableElementType] =
  {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession
    {
      implicit session =>

        val convo = convoRepo.list

        val convoList = convo.filter( p => p.facilitatorId == facID && p.memberId == memID)
        convoList
    }
  }
  override def getByMemberId(memberId: Long): List[ConversationMessageRepository#TableElementType] =
  {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession {
      implicit session =>
        val convo = convoRepo.list
        val membersConvo = convo.filter(p => p.memberId == memberId)
        membersConvo
    }
  }

  override def getByFacilitatorId(facilId : Long) : List[ConversationMessageRepository#TableElementType]= {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession {
      implicit session =>
        val convo = convoRepo.list
        val facil = convo.filter(p => p.facilitatorId == facilId)
        facil
    }
  }
}
