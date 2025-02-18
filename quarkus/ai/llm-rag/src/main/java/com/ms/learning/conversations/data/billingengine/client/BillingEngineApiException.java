package com.ms.learning.conversations.data.billingengine.client;

import io.netty.handler.codec.http.HttpResponseStatus;
import lombok.Data;

@Data
public class BillingEngineApiException extends RuntimeException {

  private final HttpResponseStatus statusCode;

  /**
   * A constructor for {@link BillingEngineApiException}.
   *
   * @param message Message to be shown in the API response.
   */
  public BillingEngineApiException(HttpResponseStatus statusCode, String message) {
    super(message);
    this.statusCode = statusCode;
  }
}
