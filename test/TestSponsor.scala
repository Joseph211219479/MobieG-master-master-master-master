import domain.people.Facilitator
import domain.stuff.{Speciality, Channel, Sponsor}
import org.scalatest.{GivenWhenThen, FeatureSpec}
import repository.ChannelRepository.ChannelRepository
import repository.FacilitatorRepository.FacilitatorRepository
import repository.SpecialityRepository.SpecialityRepository
import repository.SponsorRepository.SponsorRepository
import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.TableQuery

/**
 * Created by joseph on 2014/09/03.
 */
class TestSponsor extends FeatureSpec with GivenWhenThen
{
  feature("Save Care Plan") {
    info("As a Coordinator")
    info("I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario("Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Repository")

      val channel = TableQuery[ChannelRepository]
      val spons = TableQuery[SponsorRepository]
      val fac = TableQuery[FacilitatorRepository]
      val specs = TableQuery[SpecialityRepository]

      Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

        //Creating tables
        //spons.ddl.create


       // info("Creating a Care Plan")
      //  val fecs = Facilitator("702")

       ///  val chan = Channel("602", "Mai", "Host them",fecs.id)

        /// val spo = Speciality("702", "jazee", "what it do main",fecs.id)

       // val into = Sponsor("412", "Magigolo", "hhps://www.google.com","Take the money and go", "4554",chan.id)

       // val other = fac.insert(fecs)

       /// val valo = channel.insert(chan)

       /// val vho = specs.insert(spo)

        //val nser = spons.insert(into)

        //Testing for extraction
        def Read(name: String, id: Long) =
          spons foreach { case (count: Sponsor) =>
            if (count.id.equals(id)){
              assert(count.message.contentEquals(name))

            }
          }

        def Update( desc: String, id: Long) =
        {
          spons.filter(_.id === id).map(_.message).update(desc)
          spons foreach { case ( chann: Sponsor ) =>
            if( chann.id.equals(id))
            {
              assert(chann.message.contentEquals(desc))
            }
          }
        }

               def Delete(id: Long) =
               {
                 spons.filter(_.id === id).delete
                 channel.filter(_.id === id).delete
                 fac.filter(_.id === id).delete
                 specs.filter(_.id === id).delete
                 spons foreach { case (chann: Sponsor) =>
                   if (!chann.id.equals(id)) {
                     assert(chann.id !== id)
                     info("Checked")
                   }
                   else
                   {
                     info("Not checked")
                   }
                 }
               }

        info("Reading things")
        Read("Take the money and go", 41)
        info("Updating things")
        Update("Hotness na ngoku", 41)
        info("Deleting things")
        Delete(41)

      }
    }

  }

}