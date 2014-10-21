package services.impl

import repository.SpecialityRepository.SpecialityRepository
import services.SpecialityService
import scala.slick.lifted.TableQuery
import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by joseph on 2014/10/21.
 */
class SpecialityServiceImpl extends SpecialityService
{
  val specRepo = TableQuery[SpecialityRepository]

  override def getAll(): List[SpecialityRepository#TableElementType] =
  {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession {
      implicit session =>
        val all = specRepo.list
        all
    }
  }

  override def getByName(specName: String): List[SpecialityRepository#TableElementType] =
  {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession {
      implicit session =>
        val all = specRepo.list
        val name = all.filter(_.specialityName == specName)
        name
    }
  }

  override def getByFacilitatorID(facID: Long): List[SpecialityRepository#TableElementType] = {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession {
      implicit session =>
        val all = specRepo.list
        val spec = all.filter(_.facilitatorId == facID)
        spec
    }
  }

  override def getBySpecID(specID: Long): List[SpecialityRepository#TableElementType] =
    {
      Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession {
        implicit session =>
          val all = specRepo.list
          val spec = all.filter(_.id == specID)
          spec
    }
  }

}
