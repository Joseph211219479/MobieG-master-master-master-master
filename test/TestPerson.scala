import domain.people.Facilitator
import domain.stuff.Channel
import org.scalatest.{GivenWhenThen, FeatureSpec}
import people.{Admin, Members, Person}
import repository.AdminRepository.AdminRepository
import repository.ChannelRepository.ChannelRepository
import repository.FacilitatorRepository.FacilitatorRepository
import repository.MembersRepository.MembersRepository
import repository.PersonRepository.PersonRepository
import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.TableQuery

/**
 * Created by joseph on 2014/09/03.
 */
class TestPerson extends FeatureSpec with GivenWhenThen
{
  feature("Save Care Plan") {
    info("As a Coordinator")
    info("I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario("Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Repository")

      val admin = TableQuery[AdminRepository]
      val facilitator = TableQuery[FacilitatorRepository]
      val memrepo = TableQuery[MembersRepository]
      val peeps = TableQuery[PersonRepository]


      Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

        //Creating tables
        //peeps.ddl.create


       // info("Creating a Care Plan")


     // val fac = Facilitator("124")

      // val chan = Members("13901",fac.id)

        // val adm = Admin("443200")

       //  val bantu = Person("10121","King", "Adam","Gekko","London","Cheff","bored@yahoo.com","tttddfs", adm.id, fac.id, chan.id)

        //val one = facilitator.insert(fac)


      // val two = memrepo.insert(chan)

      //  val three = admin.insert(adm)

     // val four = peeps.insert(bantu)


        //Testing for extraction
        def Read(name: String, id: Long) =
          peeps foreach { case (count: Person) =>
            if (count.id.equals(id)){
              assert(count.firstname.contentEquals(name))

            }


          }

        def Update( desc: String, id: Long) =
        {
          peeps.filter(_.id === id).map(_.firstname).update(desc)
          peeps foreach { case ( chann: Person ) =>
            if( chann.firstname.equals(id))
            {
              assert(chann.firstname.contentEquals(desc))
            }
          }
        }

                def Delete(id: Long) =
                {
                  peeps.filter(_.id === id).delete
                  admin.filter(_.id === id).delete
                  memrepo.filter(_.id === id).delete
                  facilitator.filter(_.id === id).delete
                  peeps foreach { case (chann: Person) =>
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

       // info("Reading things")
       // Read("Legend", "27")
       //info("Updating things")
      // Update("Legends", "27")
      // info("Deleting things")
        //Delete("27")

      }
    }

  }

}