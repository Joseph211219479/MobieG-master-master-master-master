package servicesTest

import org.scalatest.{GivenWhenThen, FeatureSpec}
import repository.ConversationRepository.ConversationRepository
import services.getMessageByDateInt
import services.impl.getMessageByDateImpl

/**
 * Created by akhona on 2014/09/25.
 */
class getMessageByDateTest extends FeatureSpec with GivenWhenThen {
  feature("Facilitators") {
    info("-----------------------------------------")
    info("Facilitators that encounters with a certain member")
    info("-----------------------------------------")
    scenario("How much times does a facilitator have an encounter with an specific member") {
      Given("Given a Connection to the Database Through a Repository")

      var convoList: List[ConversationRepository#TableElementType] = null


      val testtwo: getMessageByDateInt = new getMessageByDateImpl

      convoList = testtwo.getMessage("23 September 2014 12:00", 5)

      print(" Print Messages \n")
      for ( z <- convoList ) println(" Messages: " + z.message + " FID: " + z.facilitatorId + "\n")
      assert(convoList.head.message.contentEquals("All things shall be awesome"))



    }
  }
}