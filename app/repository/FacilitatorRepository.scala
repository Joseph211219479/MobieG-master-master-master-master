package repository

import domain.people.{Facilitators, Facilitator}

import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.ProvenShape

/**
 * Created by joseph on 2014/09/08.
 */
object FacilitatorRepository {
    class FacilitatorRepository(tag: Tag) extends Table[Facilitator](tag, "Facilitator") {
        def id = column[Long]("FACILITATOR_ID", O.PrimaryKey, O.AutoInc)
     // def hiden = column[String](" ")
        def * = id <> ( Facilitator, Facilitator.unapply )

     //def * : ProvenShape[(String)] = (id)
    }

  val facilitator = TableQuery[FacilitatorRepository]
}
