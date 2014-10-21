package servicesTest

import services.crudservices.Impl.CreateTables
import scala.slick.driver.MySQLDriver.simple._
import org.scalatest.{GivenWhenThen, FeatureSpec}

/**
 * Created by joseph on 2014/10/17.
 */
class CreateTablesTest extends FeatureSpec with GivenWhenThen
{
  feature("Save Care Plan") {
    info("As a Coordinator")
    info("I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario("Create Tables in the Database ") {
      var cretabs = new CreateTables
      Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession {
        implicit session =>

            cretabs.createTables()
      }
      }
    }

}
