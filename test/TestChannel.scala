
import domain.stuff.Channel
import org.scalatest.{BeforeAndAfter, FunSuite, GivenWhenThen, FeatureSpec}
import domain.people.Facilitator
import scala.slick.driver.MySQLDriver.simple._
import repository.ChannelRepository.ChannelRepository
import repository.FacilitatorRepository.FacilitatorRepository
import org.scalatest.Assertions._

import scala.slick.lifted.TableQuery

/**
 * Created by joseph on 2014/09/03.
 */
class TestChannel extends FeatureSpec with GivenWhenThen {

  feature("Save Care Plan") {
    info("As a Coordinator")
    info("I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario("Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Repository")

      val channel = TableQuery[ChannelRepository]
      val facilitator = TableQuery[FacilitatorRepository]



      Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

        //Creating tables
        //( channel.ddl ++ facilitator.ddl ).create


        info("Creating a Care Plan")
         // val chan = Channel("6", "Phumeza", "She is simply the best", "8")

         // val fac = Facilitator("8")

          //val other = facilitator.insert(fac)

         // val valo = channel.insert(chan)
        //assert(results.size == 2)

        //Testing for extraction
        def Read(name: String, id: Long) =
          channel foreach { case (count: Channel) =>
            if (count.id.equals(id)){
              assert(count.name.contentEquals(name))

            }


          }

        def Update( desc: String, id: Long) =
        {
          channel.filter(_.id === id).map(_.description).update(desc)
          channel foreach { case ( chann: Channel ) =>
            if( chann.description.equals(id))
            {
              assert(chann.description.contentEquals(desc))
            }
          }
        }

        def Delete(id: Long) =
        {
            channel.filter(_.id === id).delete
            facilitator.filter(_.id === id).delete
          channel foreach { case (chann: Channel) =>
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
        Read("Hotness sana",1)
        info("Updating things")
        Update("Hotness sana", 1)
        info("Deleting things")
        Delete(1)

      }
    }

  }
}
