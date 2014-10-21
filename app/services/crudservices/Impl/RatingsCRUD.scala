package services.crudservices.Impl

import domain.people.Facilitator
import domain.stuff.Ratings
import repository.FacilitatorRepository.FacilitatorRepository
import repository.RatingRepository.RatingRepository
import services.crudservices.RatingsCRUDInterface

import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.TableQuery

/**
 * Created by akhona on 2014/10/02.
 */
class RatingsCRUD extends RatingsCRUDInterface{
  val ratrepo = TableQuery[RatingRepository]
  val facilitator = TableQuery[FacilitatorRepository]

  override def create( fac: Facilitator, rat: Ratings ): Ratings ={
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val other = facilitator.insert(fac)

      val valo = ratrepo.insert(rat)
    }
    rat
  }

  //Testing for extraction
  override def read(id: Long): Ratings = {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

     val repo = ratrepo.list
     val input = repo.filter( p=> p.id == id ).head
      input
    }
  }

  override def update( desc: String, id: Long) =
  {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      ratrepo.filter(_.id === id).map(_.comment).update(desc)

    }
  }

  override def delete(id: Long) =
  {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      ratrepo.filter(_.id === id).delete
      facilitator.filter(_.id === id).delete

    }
  }
}
