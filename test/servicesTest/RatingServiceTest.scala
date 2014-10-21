package serviceTests

import org.scalatest.{GivenWhenThen, FeatureSpec}
import repository.RatingRepository.RatingRepository
import services.RatingServices
import services.impl.RatingServiceImpl
import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by joseph on 2014/10/01.
 */
class RatingServiceTest extends FeatureSpec with GivenWhenThen
{
  feature("Save Care Plan")
  {
    info("As a Coordinator")
    info("I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario("Create Tables in the Database ")
    {
      Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession {
        implicit session =>

          var rating : RatingServices = new RatingServiceImpl

          def ratingsOf : Unit =
          {
            var getList : List[RatingRepository#TableElementType] = List()

            getList = rating.ratingsWithRatingOf(5)
            println("testing for 5 raitngs fount with rating of 5")
            assert(getList.size == 5)
          }

          def belowRating : Unit =
          {
            var getList : List[RatingRepository#TableElementType] = List()
            getList = rating.ratingsLower(5)
            println("testing below 5 size for 5 ")
            assert(getList.size == 5)
          }

          def aboveRating : Unit =
          {
            var getList : List[RatingRepository#TableElementType] = List()
            getList = rating.ratingsLower(5)
            println("testing above 5 for 5")
            assert(getList.size == 5)
          }
      }

      }

  }
}
