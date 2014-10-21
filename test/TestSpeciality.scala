import domain.people.Facilitator
import domain.stuff.{Channel, Speciality}
import org.scalatest.{GivenWhenThen, FeatureSpec}
import repository.ChannelRepository.ChannelRepository
import repository.FacilitatorRepository.FacilitatorRepository
import repository.SpecialityRepository.SpecialityRepository
import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.TableQuery

/**
 * Created by joseph on 2014/09/03.
 */
class TestSpeciality extends FeatureSpec with GivenWhenThen
{
  feature("Save Care Plan") {
    info("As a Coordinator")
    info("I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario("Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Repository")

      val specsd = TableQuery[SpecialityRepository]
      val facilitator = TableQuery[FacilitatorRepository]



      Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

         //Creating tables
          //specsd.ddl.create


        //info("Creating a Care Plan")


        //val fac = Facilitator("05")

        //val chan = Speciality("66", "Hire", "we tried", fac.id)

        //val other = facilitator.insert(fac)

        //val valo = specsd.insert(chan)


        //Testing for extraction
        def Read(name: String, id: Long) =
           specsd foreach { case (count: Speciality) =>
            if (count.id.equals(id)){
              assert(count.specialityDescription.contentEquals(name))

            }


          }

        def Update( desc: String, id: Long) =
        {
          specsd.filter(_.id === id).map(_.description).update(desc)
          specsd foreach { case ( chann: Speciality ) =>
            if( chann.id.equals(id))
            {
              assert(chann.specialityDescription.contentEquals(desc))
            }
          }
        }

                def Delete(id: Long) =
                {
                  specsd.filter(_.id === id).delete
                  facilitator.filter(_.id === id).delete
                  specsd foreach { case (chann: Speciality) =>
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
        Read("Hotness", 66)
        info("Updating things")
        Update("Wooloo",66)
        info("Deleting things")
        Delete(66)

      }
    }

  }

}
