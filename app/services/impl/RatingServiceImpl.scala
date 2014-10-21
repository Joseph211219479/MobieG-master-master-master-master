package services.impl

import domain.stuff.Ratings
import repository.RatingRepository.RatingRepository
import services.RatingServices
import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.TableQuery
/**
 * Created by joseph on 2014/09/25.
 */
class RatingServiceImpl extends RatingServices
{
  val ratingRepo = TableQuery[RatingRepository]

  override def ratingsWithRatingOf(rate: Int): List[RatingRepository#TableElementType] =
  {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession
    {
      implicit session =>

      val allRattings = ratingRepo.list
      val ratingList = allRattings.filter(_.rate == rate)
      ratingList
    }
  }

  override def ratingsbigger(rate: Int): List[RatingRepository#TableElementType] =
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "root").withSession
    {
      implicit session =>

        val allRattings = ratingRepo.list
        val ratingList = allRattings.filter(_.rate > rate)
        ratingList
    }

  override def ratingsLower(rate: Int): List[RatingRepository#TableElementType] =
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "root").withSession
    {
      implicit session =>

        val allRattings = ratingRepo.list
        val ratingList = allRattings.filter(_.rate < rate)
        ratingList
    }

  override def rateByFacilId(facilId: Long): List[RatingRepository#TableElementType] =
  {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "root").withSession {
      implicit session =>

        val allRattings = ratingRepo.list
        val ratingList = allRattings.filter(_.facilitatorId == facilId)
        ratingList
    }
  }

  override def rateById(id: Long): Ratings =
  {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "root").withSession {
      implicit session =>

        val allRattings = ratingRepo.list
        val ratingList = allRattings.filter(_.id == id).head
        ratingList
    }
  }

}
