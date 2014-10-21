package repository

import people.Admin

import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.ProvenShape

/**
 * Created by joseph on 2014/09/08.
 */
object AdminRepository {

  class AdminRepository(tag:Tag) extends Table[Admin](tag, "Admin")
  {
    def id = column[Long]("ADMIN_ID", O.PrimaryKey, O.AutoInc)

    def * = id <> ( Admin, Admin.unapply )
    //override def * : ProvenShape[(String)] = (id)
  }

  val admin = TableQuery[AdminRepository]

}
