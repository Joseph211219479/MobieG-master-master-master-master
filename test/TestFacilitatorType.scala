import domain.people.Facilitator
import domain.stuff.Channel
import org.scalatest.{GivenWhenThen, FeatureSpec}
import people.FacilitatorType
import repository.ChannelRepository.ChannelRepository
import repository.FacilitatorRepository.FacilitatorRepository
import repository.FacilitatorTypeRepository.FacilitatorTypeRepository
import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.TableQuery

/**
 * Created by joseph on 2014/09/03.
 */
class TestFacilitatorType extends FeatureSpec with GivenWhenThen
{
  feature("Save Care Plan") {
    info("As a Coordinator")
    info("I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario("Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Repository")

      val facirepo = TableQuery[FacilitatorTypeRepository]
      val facilitator = TableQuery[FacilitatorRepository]



      Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

        //Creating tables
        //facirepo.ddl.create


        //info("Creating a Care Plan")

//        val fac = Facilitator("126")

  //       val chan = FacilitatorType("147", "Medical", "Help with any medical probelm", fac.id)



    //     val other = facilitator.insert(fac)

      //   val valo = facirepo.insert(chan)

        //val fac = Facilitator("121")

         //val chan = FacilitatorType("144", "John", "Spoken word", fac.id)



         //val other = facilitator.insert(fac)

         // val valo = facirepo.insert(chan)

        //assert(results.size == 2)

        //Testing for extraction
        def Read(descri: String, id: Long) =
          facirepo foreach { case (count: FacilitatorType) =>
            if (count.id === id ){
              assert(count.description.contentEquals(descri))

            }
          }


        def Update(desc: String, id: Long) = {
          facirepo.filter(_.id === id).map(_.description).update(desc)
          facirepo foreach { case (chann: FacilitatorType) =>
            if (chann.description.equals(id)) {
              assert(chann.description.contentEquals(desc))
            }
          }
        }



        def Delete(id: Long) = {
          facirepo.filter(_.id === id).delete
          facilitator.filter(_.id === id).delete
          facirepo foreach { case (chann: FacilitatorType) =>
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
        Read("Spoken word", 144)
        info("Updating things")
        Update("Spoken word", 144)
        info("Deleting things")

        Delete(144)


      }
    }
  }

  }