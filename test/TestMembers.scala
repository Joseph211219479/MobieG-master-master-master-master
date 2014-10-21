import domain.people.Facilitator
import domain.stuff.Channel
import org.scalatest.{GivenWhenThen, FeatureSpec}
import people.Members
import repository.ChannelRepository.ChannelRepository
import repository.FacilitatorRepository.FacilitatorRepository
import repository.MembersRepository.MembersRepository
import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.TableQuery

/**
 * Created by joseph on 2014/09/03.
 */
class TestMembers extends FeatureSpec with GivenWhenThen
{
  feature("Save Care Plan") {
    info("As a Coordinator")
    info("I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario("Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Repository")

      val memrepo = TableQuery[MembersRepository]
      val facilitator = TableQuery[FacilitatorRepository]



      Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

        //Creating tables
        //  memrepo.ddl.create


       //info("Creating a Care Plan")


        //val fac = Facilitator("100000")

//       val chan = Members("113",fac.id)

  //     val other = facilitator.insert(fac)

    //    val valo = memrepo.insert(chan)


        //Testing for extraction
        def Read(others: String, id: Long) =
          memrepo foreach { case (count: Members) =>
            if (count.id.equals(id)){
              assert(count.facilitatorId.equals(others))

            }


          }



        def Update( desc: Long, id: Long) =
        {
          memrepo.filter(_.id === id).map(_.facilitator_id).update(desc)
          memrepo foreach { case ( chann: Members ) =>
            if( chann.id.equals(id))
            {
              assert(chann.facilitatorId.equals(desc))
            }
          }
        }

        def Delete(id: Long) =
        {
          memrepo.filter(_.id === id).delete
          facilitator.filter(_.id === id).delete
          memrepo foreach { case (chann: Members) =>
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
        Read("123", 101)
        info("Updating things")
       // Update("8", 61)
        info("Deleting things")
       // Delete("83")

      }
    }

  }

}