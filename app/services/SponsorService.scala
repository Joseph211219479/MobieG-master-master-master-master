package services

import domain.stuff.{Channel, Sponsor}
import repository.SponsorRepository.SponsorRepository

/**
 * Created by joseph on 2014/10/16.
 */
trait SponsorService
{
  def getusersponsor() : List[SponsorRepository#TableElementType]

  def getAllSponsors() : List[SponsorRepository#TableElementType]

  def getSponsorById(id : Long) : Sponsor

  def getSponsorByName(name : String) : Sponsor


}
