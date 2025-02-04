package com.ms.learning.conversations.api;

import com.ms.learning.conversations.dto.ConversationRequest;
import com.ms.learning.conversations.dto.ConversationResponse;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ConversationResource implements ConversationsApi {

  @Override
  public ConversationResponse postConversationMessages(ConversationRequest conversationRequest) {
    return null;
  }
}
