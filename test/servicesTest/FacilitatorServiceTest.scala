package serviceTests

import org.scalatest.{GivenWhenThen, FeatureSpec}
import repository.FacilitatorRepository.FacilitatorRepository
import services.FacilitatorServices
import services.impl.FacilitatorsServicesImpl
import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by joseph on 2014/09/30.
 */
class FacilitatorServiceTest extends FeatureSpec with GivenWhenThen
{
  feature("Save Care Plan") {
    info("As a Coordinator")
    info("I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario("Create Tables in the Database ")
    {
      var facilitator : FacilitatorServices = new FacilitatorsServicesImpl

      Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession
      {
        implicit session =>

          def getAll =
          {
            var all : Boolean = true
            var has = true
            all = facilitator.hasMemberBeenServedByFacilitator(1000881, 5)
            assert(all == has)
          }

      }
      }
  }


}
