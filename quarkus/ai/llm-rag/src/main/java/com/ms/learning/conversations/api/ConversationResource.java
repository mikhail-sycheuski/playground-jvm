package com.ms.learning.conversations.api;

import com.ms.learning.conversations.api.producing.ConversationsApi;
import com.ms.learning.conversations.api.producing.dto.ConversationMessage;
import com.ms.learning.conversations.api.producing.dto.ConversationRequest;
import com.ms.learning.conversations.api.producing.dto.ConversationResponse;
import com.ms.learning.conversations.service.ModelOperatorService;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class ConversationResource implements ConversationsApi {

  private final ModelOperatorService modelOperator;

  @Override
  public ConversationResponse postConversationMessages(ConversationRequest conversationRequest) {
    ConversationMessage responseMessage =
        modelOperator.handleConversationMessage(conversationRequest.getUserMessage());
    return ConversationResponse.builder().responseMessage(responseMessage).build();
  }
}
