package com.ms.learning.conversations.data.billingengine.client;

import com.ms.learning.conversations.api.consuming.dto.BillableUnitFullGetDto;
import com.ms.learning.conversations.api.consuming.dto.BillableUnitGetDto;
import com.ms.learning.conversations.api.consuming.dto.BillableUnitSearchDto;
import com.ms.learning.conversations.api.consuming.dto.IncomingBillableUnitType;
import dev.langchain4j.agent.tool.Tool;
import io.smallrye.common.annotation.Blocking;
import jakarta.enterprise.context.ApplicationScoped;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Slf4j
@ApplicationScoped
public class BillableUnitRepository {

  private final BillingEngineClient billingEngineClient;

  public BillableUnitRepository(@RestClient BillingEngineClient billingEngineClient) {
    this.billingEngineClient = billingEngineClient;
  }

  @Tool(
      """
        Retrieves detailed information about a single billable unit using its unique string ID.
        This function is ideal when you have the specific billableUnitId you need to investigate.
      """)
  @Blocking
  public BillableUnitFullGetDto getBillableUnitById(String billableUnitId) {
    return billingEngineClient.getBillableUnitById(billableUnitId);
  }

  @Tool(
      """
        Retrieves a list of billable units based on their type (ROUTE, WORK_ORDER, SERVICE), the country where the delivery occurred (ISO 3166 code), the supplier's car ID, and a list of reference keys.
        This function is useful when you need to find multiple billable units that match these specific criteria.
      """)
  @Blocking
  public List<BillableUnitGetDto> getBillableUnitsByTypeCountrySupplierReferenceKeys(
      String type, String country, String supplier, List<String> referenceKeys) {
    IncomingBillableUnitType incomingBillableUnitType = IncomingBillableUnitType.fromString(type);

    return billingEngineClient
        .searchBillableUnits(
            BillableUnitSearchDto.builder()
                .type(incomingBillableUnitType)
                .country(country)
                .supplierCarId(supplier)
                .referenceKeys(referenceKeys)
                .build())
        .getContent();
  }

  @Tool("Get billable units by type, country, supplier and the revisionTimestamp date range")
  @Blocking
  public List<BillableUnitGetDto> getBillableUnitsByTypeCountrySupplierDateRange(
      String type,
      String country,
      String supplier,
      OffsetDateTime startDate,
      OffsetDateTime endDate) {
    IncomingBillableUnitType incomingBillableUnitType = IncomingBillableUnitType.fromString(type);
    return billingEngineClient
        .searchBillableUnits(
            BillableUnitSearchDto.builder()
                .type(incomingBillableUnitType)
                .country(country)
                .supplierCarId(supplier)
                .referenceDateTimeRangeStart(startDate)
                .referenceDateTimeRangeEnd(endDate)
                .build())
        .getContent();
  }
}
