package services.impl

import repository.FacilitatorTypeRepository.FacilitatorTypeRepository
import services.FacilitatorTypeService
import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by joseph on 2014/10/21.
 */
class FacilitatorTypeImpl extends FacilitatorTypeService
{
  val facilTypeRepo = TableQuery[FacilitatorTypeRepository]


  override def getall(): List[FacilitatorTypeRepository#TableElementType] =
  {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession
    {
      implicit session =>
        val all = facilTypeRepo.list
        all
    }
  }

  override def getByName(name: String): List[FacilitatorTypeRepository#TableElementType] =
  {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession
    {
      implicit session =>
        val all = facilTypeRepo.list.filter(_.name == name)
        all
    }
  }

  override def getByFacilId(facilID: Long): List[FacilitatorTypeRepository#TableElementType] =
  {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession
    {
      implicit session =>
        val all = facilTypeRepo.list.filter(_.facilitatorId == facilID)
        all
    }
  }

  override def getById(FaciliTypeId : Long): List[FacilitatorTypeRepository#TableElementType] =
      {
        Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession
        {
        implicit session =>
          val all = facilTypeRepo.list.filter(_.id == FaciliTypeId )
            all
        }
      }
}
