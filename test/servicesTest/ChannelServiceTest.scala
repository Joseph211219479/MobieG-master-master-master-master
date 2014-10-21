package serviceTests

import org.scalatest.{GivenWhenThen, FeatureSpec}
import repository.ChannelRepository.ChannelRepository
import services.ChannelService
import services.impl.ChannelServiceImpl
import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by joseph on 2014/09/30.
 */
class ChannelServiceTest  extends FeatureSpec with GivenWhenThen
{
  feature("Save Care Plan")
  {
    info("As a Coordinator")
    info("I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario("Create Tables in the Database ")
    {
      Given("Given a Connection to the Database Through a Repository")

      var allChannels : ChannelService = new ChannelServiceImpl

      Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession
      {
          implicit session =>

            def allMembers : Unit =
            {
              var chanList : List[ChannelRepository#TableElementType] = List()
              var list = allChannels.getAllChannels()
              println("testing for 5 channels")
              assert(list.size == 5 )
            }

            def chooseChannel: Unit =
            {
              var chanList: List[ChannelRepository#TableElementType] = List()
              chanList = allChannels.chooseChannel(3)
              assert(chanList.head.name == "Alex")
            }
      }
    }
  }
}

