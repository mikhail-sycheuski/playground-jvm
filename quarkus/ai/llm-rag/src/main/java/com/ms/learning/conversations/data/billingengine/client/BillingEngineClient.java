package com.ms.learning.conversations.data.billingengine.client;

import com.ms.learning.conversations.api.consuming.V2Api;
import io.quarkus.oidc.client.reactive.filter.OidcClientRequestReactiveFilter;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.annotation.RegisterProviders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@ApplicationScoped
@RegisterRestClient(configKey = "billing-engine-api")
@RegisterProviders({
  @RegisterProvider(BillingEngineApiExceptionMapper.class),
  @RegisterProvider(OidcClientRequestReactiveFilter.class)
})
public interface BillingEngineClient extends V2Api {}
