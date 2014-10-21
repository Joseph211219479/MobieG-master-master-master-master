package services.crudservices.Impl

import domain.people.Facilitator
import people.Members
import repository.FacilitatorRepository.FacilitatorRepository
import repository.MembersRepository.MembersRepository
import services.crudservices.MembersCRUDInterface

import scala.slick.lifted.TableQuery

/**
 * Created by akhona on 2014/10/02.
 */
import scala.slick.driver.MySQLDriver.simple._

class MembersCRUD extends MembersCRUDInterface{
  val memrepo = TableQuery[MembersRepository]
  val facilitator = TableQuery[FacilitatorRepository]

  override def create( mem: Members, fac: Facilitator ): Members= {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val other = facilitator.insert(fac)
      val valo = memrepo.insert(mem)
    }
    mem
  }

  override def read(id: Long): List[MembersRepository#TableElementType] = {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val repo = memrepo.list
      val input = repo.filter( p=> p.id == id )
      input
    }
  }

  override def update( desc: Long, id: Long) =
  {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      memrepo.filter(_.id === id).map(_.facilitator_id).update(desc)

    }
  }

  override def delete(id: Long) =
  {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      memrepo.filter(_.id === id).delete
      facilitator.filter(_.id === id).delete

    }
  }
}
