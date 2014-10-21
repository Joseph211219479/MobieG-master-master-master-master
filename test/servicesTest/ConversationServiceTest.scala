package serviceTests

import org.scalatest.{GivenWhenThen, FeatureSpec}
import repository.ConversationMessageRepository.ConversationMessageRepository
import services.ConversationService
import services.impl.ConversationServiceImpl
import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by joseph on 2014/09/30.
 */
class ConversationServiceTest extends FeatureSpec with GivenWhenThen
{
  feature("Save Care Plan")
  {
    info("As a Coordinator")
    info("I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario("Create Tables in the Database ")
      {
        var conversation : ConversationService = new ConversationServiceImpl

        Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession
        {
          implicit session =>

            def getAll : Unit =
            {
              var getallConvoID :List[ConversationMessageRepository#TableElementType] = null
              getallConvoID = conversation.getAllConversationsOfFacilitator(4,5)
              assert(getallConvoID.size == 1)
            }


        }
      }
  }
}
