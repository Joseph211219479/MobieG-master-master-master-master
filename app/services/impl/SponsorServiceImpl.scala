package services.impl

import domain.stuff.{Channel, Sponsor}
import repository.ChannelRepository.ChannelRepository
import repository.SponsorRepository.SponsorRepository
import services.SponsorService
import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by joseph on 2014/10/16.
 */
class SponsorServiceImpl extends SponsorService
{
  val allS = TableQuery[SponsorRepository]
  val chanList = TableQuery[ChannelRepository]

  override def getusersponsor(): List[SponsorRepository#TableElementType] =
  {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val peeps = allS.list
      val chan = chanList.list
      val showS = peeps.filter(s => s.channelId == (chan.filter(p => p.id  == p.id)) )
      showS
    }
  }

  override def getAllSponsors(): List[SponsorRepository#TableElementType] =
  {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession
    {
      implicit session =>
        val peeps = allS.list
        peeps
    }
  }

  override def getSponsorById(id: Long): Sponsor =
  {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession {
      implicit session =>
        val peeps = allS.list
        val sponsor = peeps.filter(_.id == id)
        sponsor.head
    }
  }

  override def getSponsorByName(name: String): Sponsor =
  {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession {
      implicit session =>
        val peeps = allS.list
        val sponsor = peeps.filter(_.name == name)
        sponsor.head
    }
  }

}
