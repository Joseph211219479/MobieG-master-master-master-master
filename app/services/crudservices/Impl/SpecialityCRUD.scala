package services.crudservices.Impl

import domain.people.Facilitator
import domain.stuff.Speciality
import repository.FacilitatorRepository.FacilitatorRepository
import repository.SpecialityRepository.SpecialityRepository
import services.crudservices.SpecialityCRUDInterface

import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.TableQuery
/**
 * Created by akhona on 2014/10/02.
 */
class SpecialityCRUD extends SpecialityCRUDInterface{
  val specsd = TableQuery[SpecialityRepository]
  val facilitator = TableQuery[FacilitatorRepository]


  def create( fac: Facilitator, spec: Speciality ): Speciality = {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val other = facilitator.insert(fac)
      val valo = specsd.insert(spec)
    }
    spec
  }

  //Testing for extraction
  def read(id: Long): Speciality = {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val repo = specsd.list
      val input = repo.filter( p => p.id == id).head
      input
    }
  }

  def update( desc: String, id: Long) =
  {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      specsd.filter(_.id === id).map(_.description).update(desc)

    }
  }

  def delete(id: Long) =
  {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      specsd.filter(_.id === id).delete
      facilitator.filter(_.id === id).delete

    }
  }
}
