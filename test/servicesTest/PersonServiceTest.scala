package serviceTests

import org.scalatest.{GivenWhenThen, FeatureSpec}
import repository.PersonRepository.PersonRepository
import services.PersonServices
import services.impl.PersonServiceImpl
import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by joseph on 2014/10/01.
 */
class PersonServiceTest extends FeatureSpec with GivenWhenThen
{
  feature("Save Care Plan")
  {
    info("As a Coordinator")
    info("I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario("Create Tables in the Database ")
    {
      var person : PersonServices = new PersonServiceImpl

      Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession
      {
        implicit session =>

          def getall : Unit =
          {
            var getAll : List[PersonRepository#TableElementType] = List()
            getAll = person.getAllPeople()
            getAll
          }

          def getById : Unit =
          {
            var per  = person.getPersonWithId(5)
            assert(per.firstname == "Sparrow" )
            println("checking for person id = Sparrow")
          }
      }

    }
  }
}
