package services

import repository.SponsorRepository.SponsorRepository

import scala.slick.lifted.TableQuery

/**
 * Created by akhona on 2014/09/25.
 */
trait getAllSponsorInt {
      def getusersponsor() : List[SponsorRepository#TableElementType]
}
