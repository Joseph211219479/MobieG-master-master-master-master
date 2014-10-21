package repository

import domain.stuff.Speciality
import repository.FacilitatorRepository.FacilitatorRepository

import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.{ProvenShape, ForeignKeyQuery}

/**
 * Created by joseph on 2014/09/08.
 */
object SpecialityRepository {

  class SpecialityRepository (tag: Tag) extends Table[Speciality](tag, "Speciality")
  {
    def id = column[Long]("SPECIALITY_ID", O.PrimaryKey,O.AutoInc)
    def name =  column[String]("SPECIALITY_NAME")
    def description = column[String]("SPECIALITY_DESCRIPTION")
    def facilitatorID = column[Long]("SPECIALITY_FACILITATORID")

    def * = (id, name, description, facilitatorID) <> ( Speciality.tupled, Speciality.unapply )

    //override def * : ProvenShape[(String, String, String, String)] = (id, name, description, facilitatorID)

    def facilitator = foreignKey("FAC_FK", facilitatorID, TableQuery[FacilitatorRepository])(_.id)
  }

  val speciality= TableQuery[SpecialityRepository]

}
