package services.impl


import repository.MemberEncountersRepository.MemberEncountersRepository

import services.getUserHistoryInt


import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.TableQuery

/**
 * Created by akhona on 2014/09/23.
 */
class getUserHistoryImpl extends getUserHistoryInt{


  val warehouse = TableQuery[MemberEncountersRepository]


  def counter(id: Long): List[MemberEncountersRepository#TableElementType] = {

    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>
    val listo = warehouse.list


    val outp = listo.filter( f => f.memberId == id )

    outp

    }
    }

}