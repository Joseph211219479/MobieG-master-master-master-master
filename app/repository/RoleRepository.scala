package repository

import people.Role

import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.ProvenShape

/**
 * Created by joseph on 2014/09/08.
 */
object RoleRepository {
    class RoleRepository(tag: Tag) extends Table[Role](tag, "Role"){

      def id = column[Long]("ROLE_ID", O.PrimaryKey ,O.AutoInc)

      def name = column[String]("ROLE_NAME")

      def description = column[String]("ROLE_DESCRIPTION")

      def * = (id, name, description) <> ( Role.tupled, Role.unapply )
      //override def * : ProvenShape[(String, String, String)] = (id, name, description)
    }

  val role = TableQuery[RoleRepository]

}
