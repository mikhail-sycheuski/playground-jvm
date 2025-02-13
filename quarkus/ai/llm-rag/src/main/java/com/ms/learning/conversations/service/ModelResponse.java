package com.ms.learning.conversations.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModelResponse {
  private String responseMessage;
  private String contextComments;
}
