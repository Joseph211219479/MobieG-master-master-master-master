package services.crudservices.Impl

import people.Role
import repository.RoleRepository.RoleRepository
import services.crudservices.RoleCRUDInterface

import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.TableQuery
/**
 * Created by akhona on 2014/10/02.
 */
class RoleCRUD extends RoleCRUDInterface{
  val role = TableQuery[RoleRepository]

  override def create( rol: Role ): Role = {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val valo = role.insert(rol)
    }
    rol
  }

  //Testing for extraction
  override def read(id: Long): Role = {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

     val repo = role.list
     val input = repo.filter(p => p.roleId == id).head
     input
    }
  }

  override def update( desc: String, id: Long) =
  {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      role.filter(_.id === id).map(_.description).update(desc)

    }
  }

  override def delete(id: Long) =
  {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      role.filter(_.id === id).delete

    }
  }
}
