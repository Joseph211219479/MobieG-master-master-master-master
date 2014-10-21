package servicesTest

import domain.stuff.MemberEncounter
import org.scalatest.{GivenWhenThen, FeatureSpec}
import repository.MemberEncountersRepository.MemberEncountersRepository
import services.getUserHistoryInt
import services.impl.getUserHistoryImpl

import scala.slick.lifted.TableQuery

/**
 * Created by akhona on 2014/09/24.
 */
class getUserHistoryTest extends FeatureSpec with GivenWhenThen {
  feature("Facilitators") {
    info("-----------------------------------------")
    info("Facilitators that encounters with a certain member")
    info("-----------------------------------------")
    scenario("How much times does a facilitator have an encounter with an specific member") {
      Given("Given a Connection to the Database Through a Repository")

      //Count the encounters

      var encoList:List[MemberEncountersRepository#TableElementType] = null

      val test: getUserHistoryInt = new getUserHistoryImpl

      encoList = test.getById(5)

      print(" Number of facilitators: " + encoList.size + " \n")
      for ( i <- encoList ) print(" MID: " + i.memberId + " FID: " + i.facilitatorId + " Start time: " + i.startTime + " End time: " + i.endTime + "\n\n")
      assert(encoList.head.memberId.equals(5))
    }
  }
}
