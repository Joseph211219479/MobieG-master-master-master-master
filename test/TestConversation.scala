import domain.people.Facilitator
import domain.stuff.{Channel, Conversation}
import org.scalatest.{GivenWhenThen, FeatureSpec}
import repository.ChannelRepository.ChannelRepository
import repository.ConversationRepository.ConversationRepository
import repository.FacilitatorRepository.FacilitatorRepository
import scala.slick.driver.MySQLDriver.simple._

import scala.slick.lifted.TableQuery

/**
 * Created by joseph on 2014/09/03.
 */
class TestConversation extends FeatureSpec with GivenWhenThen
{
  feature("Save Care Plan") {
    info("As a Coordinator")
    info("I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario("Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Repository")

      val convorepo = TableQuery[ConversationRepository]
      val facilitator = TableQuery[FacilitatorRepository]



      Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

        //Creating tables
       // convorepo.ddl.create


         //info("Creating conversations")
          //val fac = Facilitator("15")



          //val chan = Conversation("12", "Winners win all the time", "4")


          //val other = facilitator.insert(fac)
          //val valo = convorepo.insert(chan)


        //Testing for extraction
        def Read(name: String, id: Long) =
          convorepo foreach { case (count: Conversation) =>
            if (count.id === id ){
              assert(count.message.contentEquals(name))

            }


          }


        def Update( desc: String, id: Long) =
        {
          convorepo.filter(_.id === id).map(_.message).update(desc)
          convorepo foreach { case ( chann: Conversation ) =>
            if( chann.message.equals(id))
            {
              assert(chann.message.contentEquals(desc))
            }
          }
        }


        def Delete(id: Long) =
        {
          convorepo.filter(_.id === id).delete
          facilitator.filter(_.id === id).delete
          convorepo foreach { case (chann: Conversation) =>
            if (!chann.id.equals(id)) {
              assert(chann.id !== id)
              info("Checked")
            }
            else
            {
              info("Not checked")
            }
          }
        }

        info("Reading things")
        Read("Hotness sanas", 22)

        info("Updating things")
        Update("Hotnesss sana", 22)
        info("Deleting things")
        Delete(22)

      }
    }

  }

}