package services.crudservices.Impl

import domain.people.Facilitator
import people.{Admin, Members, Person}
import repository.AdminRepository.AdminRepository
import repository.FacilitatorRepository.FacilitatorRepository
import repository.MembersRepository.MembersRepository
import repository.PersonRepository.PersonRepository
import services.crudservices.PersonCRUDInterface
import org.mindrot.jbcrypt.BCrypt

import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.TableQuery

/**
 * Created by akhona on 2014/10/02.
 */
class PersonCRUD extends PersonCRUDInterface {
  val admin = TableQuery[AdminRepository]
  val facilitator = TableQuery[FacilitatorRepository]
  val memrepo = TableQuery[MembersRepository]
  val peeps = TableQuery[PersonRepository]

  override def create(fac: Facilitator, mem: Members, adm: Admin, perc: Person): Person = {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val three = admin.insert(adm)

      val one = facilitator.insert(fac)

      val two = memrepo.insert(mem)



      //encryption stuff


      //val person = new Person(perc.id,perc.title,perc.firstname,perc.surname,perc.othername,perc.username,perc.password,perc.email,perc.adminId,perc.facilitatorId,perc.membersId)

      val four = peeps.insert(perc)

      //decrypt
     // BCrypt.checkpw("admin", encryptPass)
    }
    perc
  }

  override def read(id: Long): Person = {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val input = peeps.list

      val other = input.filter( p => p.id == id).head
      other
    }
  }

  override def update(desc: String, id: Long) = {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      peeps.filter(_.id === id).map(_.firstname).update(desc)

    }
  }

  override def delete(id: Long) = {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      peeps.filter(_.id === id).delete
      admin.filter(_.id === id).delete
      memrepo.filter(_.id === id).delete
      facilitator.filter(_.id === id).delete
    }
  }
}
