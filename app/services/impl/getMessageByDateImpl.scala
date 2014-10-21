package services.impl

import repository.MemberEncountersRepository.MemberEncountersRepository

import scala.slick.driver.MySQLDriver.simple._
import repository.ConversationRepository.ConversationRepository
import services.getMessageByDateInt

/**
 * Created by akhona on 2014/09/24.
 */
class getMessageByDateImpl extends getMessageByDateInt{

  val warerepo = TableQuery[MemberEncountersRepository]
  val convorepo = TableQuery[ConversationRepository]

  override def getMessage(dtime: String, id: Long): List[ConversationRepository#TableElementType] =
  {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val war = warerepo.list
      val convo = convorepo.list

      val geterOne = war.filter( f => f.memberId == id && f.startTime == dtime  )
      val geterTwo = convo.filter( z => z.facilitatorId == geterOne.head.facilitatorId)

      geterTwo

    }
  }
}
