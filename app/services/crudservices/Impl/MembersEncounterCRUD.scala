package services.crudservices.Impl

import domain.stuff.MemberEncounter
import repository.MemberEncountersRepository.MemberEncountersRepository
import services.crudservices.MembersEncounterCRUDint

import scala.slick.lifted.TableQuery

/**
 * Created by akhona on 2014/10/02.
 */
import scala.slick.driver.MySQLDriver.simple._

class MembersEncounterCRUD extends MembersEncounterCRUDint{
  val memEncounterrepo = TableQuery[MemberEncountersRepository]

  override def create(membersEncounter: MemberEncounter): MemberEncounter = {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>


      val valo = memEncounterrepo.insert(membersEncounter)
    }
    membersEncounter
  }
  override def update(id: Long, desc: String ) = {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>
      memEncounterrepo.filter(_.id === id).map(_.endTime).update(desc)
    }
  }

  override def delete(id: Long)  =
  {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>
      memEncounterrepo.filter(_.id === id).delete
    }
  }

  override def read(id: Long): List[MemberEncountersRepository#TableElementType] =
  {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>
        val enc = memEncounterrepo.list
        val other = enc.filter( p => p.id == id )
      other
    }

  }

}
