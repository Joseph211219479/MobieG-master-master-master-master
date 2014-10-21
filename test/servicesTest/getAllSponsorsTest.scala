package servicesTest

import org.scalatest.{GivenWhenThen, FeatureSpec}
import repository.SponsorRepository.SponsorRepository
import services.getAllSponsorInt
import services.impl.getAllSponsorImpl

/**
 * Created by akhona on 2014/09/25.
 */
class getAllSponsorsTest extends FeatureSpec with GivenWhenThen {
  feature("Facilitators") {
    info("-----------------------------------------")
    info("Facilitators that encounters with a certain member")
    info("-----------------------------------------")
    scenario("How much times does a facilitator have an encounter with an specific member") {
      Given("Given a Connection to the Database Through a Repository")

      var sponsorList: List[SponsorRepository#TableElementType] = null

      val testsix: getAllSponsorInt = new getAllSponsorImpl

      sponsorList = testsix.getusersponsor()

      print( " The size is " + sponsorList.size + "\n\n" )
      for ( j <- sponsorList ) println(" Sponsor name: " + j.name + " Sponsor message: " + j.message + " Sponsor url: " + j.url  + "\n" )

      assert(sponsorList.head.name.contentEquals("Magigolo"))



    }
  }
}
