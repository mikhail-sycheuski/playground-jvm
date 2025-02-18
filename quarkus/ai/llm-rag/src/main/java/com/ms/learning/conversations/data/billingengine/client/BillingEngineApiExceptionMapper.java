package com.ms.learning.conversations.data.billingengine.client;

import io.netty.handler.codec.http.HttpResponseStatus;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

@Slf4j
public class BillingEngineApiExceptionMapper
    implements ResponseExceptionMapper<BillingEngineApiException> {

  @Override
  public BillingEngineApiException toThrowable(Response response) {
    var responseAsString = response.readEntity(String.class);
    return new BillingEngineApiException(
        HttpResponseStatus.valueOf(response.getStatus()),
        String.format(
            "Unsuccessful response from Billing Engine API. " + "Response: status %s, body %s.",
            response.getStatus(), responseAsString));
  }
}
