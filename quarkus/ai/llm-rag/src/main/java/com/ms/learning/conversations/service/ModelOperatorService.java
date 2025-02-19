package com.ms.learning.conversations.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.learning.conversations.api.consuming.dto.BillableUnitFullGetDto;
import com.ms.learning.conversations.api.producing.dto.ConversationMessage;
import com.ms.learning.conversations.data.billingengine.client.BillableUnitRepository;
import com.ms.learning.conversations.data.billingengine.client.BillingEngineClient;
import jakarta.enterprise.context.ApplicationScoped;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class ModelOperatorService {

  private static final String NO_CONTEXT = "context is not provided";
  private static final Long STATIC_MEMORY_ID = 1L;

  private final ObjectMapper objectMapper;
  private final DeliveryExecutionHelperAiService deliveryExecutionHelperAiService;

  public ConversationMessage handleConversationMessage(ConversationMessage conversationMessage) {
    String context = NO_CONTEXT;
    if (conversationMessage.getContext() != null) {
      try {
        context = objectMapper.writeValueAsString(conversationMessage.getContext());
      } catch (JsonProcessingException e) {
        throw new RuntimeException("Failed to serialize context: %s".formatted(e.getMessage()), e);
      }
    }

    ModelResponse modelResponse =
        deliveryExecutionHelperAiService.processUserConversation(
            STATIC_MEMORY_ID, conversationMessage.getMessage(), context);

    return ConversationMessage.builder()
        .message(modelResponse.getResponseMessage())
        .context(modelResponse.getContextComments())
        .timestamp(OffsetDateTime.now(ZoneOffset.UTC))
        .build();
  }
}
