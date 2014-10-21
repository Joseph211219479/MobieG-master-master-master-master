package services.impl

import repository.FacilitatorTypeRepository.FacilitatorTypeRepository
import repository.PersonRepository.PersonRepository
import services.getFacilitatorTypeInt
import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by akhona on 2014/09/24.
 */
class getFacilitatorTypeImpl extends getFacilitatorTypeInt{

  val prepo = TableQuery[PersonRepository]
  val ftrepo = TableQuery[FacilitatorTypeRepository]

  override def getType(typ: String): List[PersonRepository#TableElementType] =
  {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>


        val aList = prepo.list

        val bList = ftrepo.list


        val p1 = bList.filter( f => f.name.equalsIgnoreCase(typ) )
        val p2 = aList.filter( p => p.facilitatorId != 0 )

        p2

    }
  }
}
