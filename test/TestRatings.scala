import domain.people.Facilitator
import domain.stuff.{Channel, Ratings}
import org.scalatest.{GivenWhenThen, FeatureSpec}
import repository.ChannelRepository.ChannelRepository
import repository.FacilitatorRepository.FacilitatorRepository
import repository.RatingRepository.RatingRepository
import scala.slick.driver.MySQLDriver.simple._

import scala.slick.lifted.TableQuery

/**
 * Created by joseph on 2014/09/03.
 */
class TestRatings extends FeatureSpec with GivenWhenThen
{
  feature("Save Care Plan") {
    info("As a Coordinator")
    info("I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario("Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Repository")

      val ratrepo = TableQuery[RatingRepository]
      val facilitator = TableQuery[FacilitatorRepository]



      Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

        //Creating tables
        //ratrepo.ddl.create


        //info("Creating a Care Plan")


         //val fac = Facilitator("13")

         //val chan = Ratings("42", 7, "Well does it son", fac.id)

         //val other = facilitator.insert(fac)

         //val valo = ratrepo.insert(chan)


        //Testing for extraction
        def Read(name: String, id: Long) =
          ratrepo foreach { case (count: Ratings) =>
            if (count.id.equals(id)){
              assert(count.comment.contentEquals(name))

            }


          }

        def Update( desc: String, id: Long) =
        {
          ratrepo.filter(_.id === id).map(_.comment).update(desc)
          ratrepo foreach { case ( chann: Ratings ) =>
            if( chann.comment.equals(id))
            {
              assert(chann.comment.contentEquals(desc))
            }
          }
        }

        def Delete(id: Long) =
        {
          ratrepo.filter(_.id === id).delete
          facilitator.filter(_.id === id).delete
          ratrepo foreach { case (chann: Ratings) =>
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
        Read("You tried mate", 42)
       info("Updating things")
        Update("You tried mate", 42)
        info("Deleting things")
        Delete(42)

      }
    }

  }

}