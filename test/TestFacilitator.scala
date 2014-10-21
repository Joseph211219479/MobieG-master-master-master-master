import org.scalatest.{GivenWhenThen, FeatureSpec}
import domain.people.Facilitator

/**
 * Created by joseph on 2014/09/03.
 */
class TestFacilitator extends FeatureSpec with GivenWhenThen
{
  feature("Create Facilitator")
  {
    info("Facilitator")
    scenario("create Facilitator"){
      Given("Facilitator")

      val conversation = Facilitator(1)

      assert( conversation.id == (1))
    }
  }

}