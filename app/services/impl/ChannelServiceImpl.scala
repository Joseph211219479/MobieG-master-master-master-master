package services.impl

import domain.stuff.Channel
import repository.ChannelRepository.ChannelRepository
import services.ChannelService
import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.TableQuery
/**
 * Created by joseph on 2014/09/25.
 */
class ChannelServiceImpl extends ChannelService
{
  val channelRepo = TableQuery[ChannelRepository]

  override def getAllChannels(): List[ChannelRepository#TableElementType] =
  {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession
    {
      implicit session =>
      val channelListo = channelRepo.list
      channelListo
    }
  }

  override def chooseChannel(channelId : Long) : List[ChannelRepository#TableElementType] =
  {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession
    {
      implicit session =>

        val channelList = channelRepo.list
        val channel = channelList.filter(_.id == channelId)
        channel
    }
  }

  override def getChannelByName(name: String): Channel =
  {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession
    {
      implicit session =>

        val channelList = channelRepo.list
        val channel = channelList.filter(_.name == name)
        channel.head
    }
  }

  override def getChanByFacilitatorId(facId: Long): Channel =
  {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession
    {
      implicit session =>

        val channelList = channelRepo.list
        val channel = channelList.filter(_.facilitatorId == facId)
        channel.head
    }
  }

}
