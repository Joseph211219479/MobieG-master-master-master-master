package services.crudservices.Impl

import domain.people.Facilitator
import people.FacilitatorType
import repository.FacilitatorRepository.FacilitatorRepository
import repository.FacilitatorTypeRepository.FacilitatorTypeRepository
import services.crudservices.FacilitatorTypeCRUDInterface

import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.TableQuery
/**
 * Created by akhona on 2014/10/02.
 */
class FacilitatorTypeCRUD extends FacilitatorTypeCRUDInterface {
  val facirepo = TableQuery[FacilitatorTypeRepository]
  val facilitator = TableQuery[FacilitatorRepository]

  override def create(fac: Facilitator, typ: FacilitatorType): FacilitatorType = {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val other = facilitator.insert(fac)
      val valo = facirepo.insert(typ)
    }
    typ
  }

  override def read(id: Long): FacilitatorType = {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

     val repo = facirepo.list
     val input = repo.filter(p => p.id == id).head
     input
    }
  }

  override def update(desc: String, id: Long) = {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      facirepo.filter(_.id === id).map(_.description).update(desc)

    }
  }

  override def delete(id: Long) = {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      facirepo.filter(_.id === id).delete
      facilitator.filter(_.id === id).delete

    }
  }
}
