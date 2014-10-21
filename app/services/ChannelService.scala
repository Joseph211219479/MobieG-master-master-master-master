package services

import domain.stuff.Channel
import repository.ChannelRepository.ChannelRepository

/**
 * Created by joseph on 2014/09/25.
 */
trait ChannelService
{
  def getAllChannels(): List[ChannelRepository#TableElementType]

  def chooseChannel(ChannelId : Long) : List[ChannelRepository#TableElementType]

  def getChannelByName(name : String) : Channel

  def getChanByFacilitatorId( facId : Long) : Channel
}
