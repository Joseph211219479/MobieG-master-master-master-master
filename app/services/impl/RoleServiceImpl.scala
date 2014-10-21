package services.impl

import people.Role
import repository.RoleRepository.RoleRepository
import services.RoleServices
import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.TableQuery
/**
 * Created by joseph on 2014/10/07.
 */
class RoleServiceImpl extends RoleServices
{

  val roleRepo = TableQuery[RoleRepository]

  override def allServices(): List[RoleRepository#TableElementType] =
  {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession
    {
      implicit session =>

        val roleList = roleRepo.list
        roleList
    }
  }

  override def roleById(id: Long): Role =
  {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession
    {
      implicit session =>

        val roleList = roleRepo.list
        val theRole = roleList.filter(_.roleId == id)
        theRole.head
    }
  }


  override def getByName(name: String): Role =
  {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession {
      implicit session =>
        val roleList = roleRepo.list
        val theRole = roleList.filter(_.roleName == name).head
        theRole
    }
  }
}
