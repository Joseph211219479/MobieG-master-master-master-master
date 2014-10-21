import domain.stuff.Channel
import org.scalatest.{GivenWhenThen, FeatureSpec}
import people.Role
import repository.ChannelRepository.ChannelRepository
import repository.FacilitatorRepository.FacilitatorRepository
import repository.RoleRepository.RoleRepository
import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.TableQuery

/**
 * Created by joseph on 2014/09/03.
 */
class TestRole extends FeatureSpec with GivenWhenThen
{
  feature("Save Care Plan") {
    info("As a Coordinator")
    info("I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario("Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Repository")

      val role = TableQuery[RoleRepository]




      Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

        //Creating tables
         // role.ddl.create


        //info("Creating a Care Plan")
        //val chan = Role("14", "assistant", "Very easy")

        //val valo = role.insert(chan)


       //Testing for extraction
        def Read(name: String, id: Long) =
          role foreach { case (count: Role) =>
            if (count.roleId.equals(id)){
              assert(count.description.contentEquals(name))

            }


          }

        def Update( desc: String, id: Long) =
        {
          role.filter(_.id === id).map(_.description).update(desc)
          role foreach { case ( chann: Role ) =>
            if( chann.roleId.equals(id))
            {
              assert(chann.description.contentEquals(desc))
            }
          }
        }

                def Delete(id: Long) =
                {
                  role.filter(_.id === id).delete
                  role foreach { case (chann: Role) =>
                    if (!chann.roleId.equals(id)) {
                      assert(chann.roleId !== id)
                      info("Checked")
                    }
                    else
                    {
                      info("Not checked")
                    }
                  }
                }

        info("Reading things")
        Read("Hotness sanas", 14)
        info("Updating things")
        Update("Hotness sana", 14)
        info("Deleting things")
        Delete(14)

      }
    }

  }

}