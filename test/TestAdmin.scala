

import domain.stuff.{ConversationMessage, MemberEncounter, Channel}
import org.scalatest.{BeforeAndAfter, FunSuite, FeatureSpec, GivenWhenThen}
import domain.people.Facilitator
import people.Admin
import repository.AdminRepository.AdminRepository
import repository.ChannelRepository.ChannelRepository
import repository.ConversationMessageRepository.ConversationMessageRepository
import repository.FacilitatorRepository.FacilitatorRepository
import repository.MemberEncountersRepository.MemberEncountersRepository

import scala.slick.driver.MySQLDriver.simple._
import org.scalatest.{GivenWhenThen, FeatureSpec}

import scala.slick.lifted.TableQuery
import scalaz.std.list


/**
 * Created by joseph on 2014/09/03.
 */
class TestAdmin extends FeatureSpec with GivenWhenThen {
  feature("Save Care Plan") {
    info("As a Coordinator")
    info("I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario("Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Repository")

      val adminrepo = TableQuery[AdminRepository]
      val memEncounterrepo = TableQuery[MemberEncountersRepository]
      val conversationMessage = TableQuery[ConversationMessageRepository]



      Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

        //Creating tables
        //admin.ddl.create

        //Inserting
        //info("Creating Admin")
        //val admins = Admin("12")

        //val valo = adminrepo.insert(admins)
        //memEncounterrepo.ddl.create

       // val insertMemEncounter = MemberEncounter("104","23 September 2014 20:00","23 September 2014 21:00","4","5")

       // val valo = memEncounterrepo.insert(insertMemEncounter)

        //conversationMessage.ddl.create
        //val insertConversationMess = ConversationMessage("1","","11","5","4")
        //val valo = conversationMessage.insert(insertConversationMess)

        //Testing for extraction
        def Read(id: Long) =
          adminrepo foreach { case (count: Admin) =>
            info("Fail")
            if(count.id === id )
            {
              assert(count.id == id)
              info("Success")
            }

        }

        def Update(id: Long) = {

          adminrepo.filter(_.id === id).map(_.id).update(id)
          adminrepo foreach { case (chann: Admin) =>
           if (chann.id.equals(id)) {
              assert(chann.id == id)
           }
          }
        }


        def Delete(id: Long) = {
          adminrepo.filter(_.id === id).delete
          adminrepo foreach { case (chann: Admin) =>
            if (!chann.id.equals(id)) {
              assert(chann.id !== id)
              info("Checked")
            }
            else {
              info("Not checked")
            }
          }
        }

        info("Reading things")
        Read(12)
        info("Updating things")
        Update(12)
        info("Deleting things")
        Delete(12)

      }
    }

  }
}


