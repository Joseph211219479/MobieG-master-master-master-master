package services

import repository.ConversationRepository.ConversationRepository

/**
 * Created by akhona on 2014/09/24.
 */
trait getMessageByDateInt {
      def getMessage( dtime : String, id : Long ) : List[ConversationRepository#TableElementType]
}
