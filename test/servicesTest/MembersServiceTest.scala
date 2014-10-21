package serviceTests

import org.scalatest.{GivenWhenThen, FeatureSpec}
import repository.ChannelRepository.ChannelRepository
import repository.MembersRepository.MembersRepository
import services.MembersService
import services.impl.MemberServiceImpl
import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by joseph on 2014/09/23.
 */
class MembersServiceTest  extends FeatureSpec with GivenWhenThen
{
  feature("Save Care Plan")
  {
    info("As a Coordinator")
    info("I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario("Create Tables in the Database ")
    {
      Given("Given a Connection to the Database Through a Repository")


      val allmembers : MembersService = new MemberServiceImpl

      Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession
      {
        implicit session =>
          def getallMembers : Unit =
            {
              var memberList : List[MembersRepository#TableElementType] = List()
              memberList = allmembers.getAllMembers()
              println("the amount of members is testing for 3")
              assert(memberList.size == 3)
          }

          def getbyId : Unit =
          {
            val value = allmembers.getMemberByID(665583)
            assert(value.head.facilitatorId == 8)
          }

          def getConversationHistory: Unit =
          {
            var memberList : List[MembersRepository#TableElementType] = List()
            memberList = allmembers.getConversationHistory(665583,8)
            assert(memberList.size == 1)
          }
      }

    }
  }
}
