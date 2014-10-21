package services.impl

import domain.stuff.Channel
import repository.ChannelRepository.ChannelRepository
import repository.MembersRepository.MembersRepository
import repository.PersonRepository.PersonRepository
import services.{ChannelService, MembersService}
import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by joseph on 2014/09/23.
 */
class MemberServiceImpl extends MembersService
{
  val memberRepo = TableQuery[MembersRepository]
  val channelRepo = TableQuery[ChannelRepository]
  val personRepo = TableQuery[PersonRepository]

  override def getAllMembers(): List[MembersRepository#TableElementType] = {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession
    {
      implicit session =>

        val obj = memberRepo.list
        //val ok = obj.filter(_.id == "Member1")
        obj
    }
  }

  override def getMemberByID(id: Long): List[MembersRepository#TableElementType] = {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession
    {
      implicit session =>

        val memberobj = memberRepo.list
        val member = memberobj.filter(_.id == id)

        member
    }
  }

  override def getConversationHistory(idMember: Long, idFac : Long): List[MembersRepository#TableElementType] =
  {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession
    {
      implicit session =>

        val memberobj = memberRepo.list
        val member = memberobj.filter(_.id == idMember )
        val list = member.filter(_.facilitatorId == idFac )

        list
    }
  }

  override def getLastServedMember(facilitatorID : Long): List[MembersRepository#TableElementType] =
  {
    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession
    {
      implicit session =>

        val allmemberList = memberRepo.list
        val memberServed = allmemberList.filter(_.facilitatorId == facilitatorID)
        // getting last served as name suggests
        val lastServed = memberServed.tail
        //should use the actual date once Alex is done updating database
        lastServed
    }
  }


}
