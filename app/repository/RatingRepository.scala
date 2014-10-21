package repository

import domain.stuff.Ratings
import repository.FacilitatorRepository.FacilitatorRepository

import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.{ProvenShape, ForeignKeyQuery}


/**
 * Created by joseph on 2014/09/08.
 */
object RatingRepository {
  class RatingRepository(tag:Tag) extends Table[Ratings](tag,"Rating")
  {
    def id = column[Long]("Rating_ID", O.PrimaryKey,O.AutoInc)
    def rate = column[Int]("Rating_Rate")
    def comment = column[String]("Rating_COMMENT")
    def facilitatorID = column[Long]("Rating_FACILITATORID")

    def * = (id, rate, comment, facilitatorID) <> ( Ratings.tupled, Ratings.unapply )
    //override def * : ProvenShape[(String, Int, String, String)] = (id, rate, comment, facilitatorID)

    def facilitator = foreignKey("FAC_FK", facilitatorID, TableQuery[FacilitatorRepository])(_.id)
  }

  val rating = TableQuery[RatingRepository]

}
