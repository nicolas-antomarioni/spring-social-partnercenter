package org.springframework.social.partnercenter;

import org.junit.Test;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.billing.invoicing.Invoice;
import org.springframework.social.partnercenter.api.customer.Customer;
import org.springframework.social.partnercenter.api.customer.response.GetCompanyProfileResponse;
import org.springframework.social.partnercenter.api.customer.response.GetSubscriptionListResponse;
import org.springframework.social.partnercenter.api.order.Order;
import org.springframework.social.partnercenter.api.order.response.OfferListResponse;
import org.springframework.social.partnercenter.api.order.subscription.Subscription;
import org.springframework.social.partnercenter.connect.PartnerCenterConnectionFactory;
import org.springframework.social.partnercenter.http.logging.LogLevel;

public class PartnerCenterTest {

	@Test
	public void someTest(){
		PartnerCenterConnectionFactory factory = new PartnerCenterConnectionFactory("9193d755-e3fe-4510-a954-e1f5046fbab0",
				"DTW10c2hW8hllL5vqV8Pd2omzBKhWMmmaNQP1rcgiuw=",
				"testtestappdirecttip.onmicrosoft.com");
		PartnerCenter partnerCenter = factory.createConnection().getApi();
//		CustomerOperations customerOperations = partnerCenter.getCustomerOperations();
		PartnerCenterResponse<Customer> list = partnerCenter.getCustomerOperations().getList(100);
		Boolean domainAvailable = partnerCenter.getUtilityOperations().isDomainAvailable("stpitest2j5");
		PartnerCenterResponse<Customer> byCompanyNameOrDomain = partnerCenter.getCustomerOperations()
				.getCompanyByDomain(10, "stpitest2j5");
		PartnerCenterResponse<Order> aCustomersOrder = partnerCenter.getOrderOperations().getACustomersOrder(list.getItems().get(1).getId());
		GetCompanyProfileResponse customersCompanyProfile = partnerCenter.getCustomerOperations().getCustomersCompanyProfile(list.getItems().get(1).getId());
		Customer customer = list.getItems().get(1);
		PartnerCenterResponse<Invoice> invoices = partnerCenter.getInvoiceOperations().getInvoices();
//		CountryInformation us = partnerCenter.getUtilityOperations().getAddressFormattingRulesByMarket("us");
//		CustomerUsageSummary usageSummary = partnerCenter.getUsageOperations().getUsageSummary(customer.getId());
		GetSubscriptionListResponse customersSubscriptions = partnerCenter.getSubscriptionOperations().getCustomersSubscriptions(customer.getId());
		partnerCenter.enableSlf4j(LogLevel.INFO);
		Subscription byId = partnerCenter.getSubscriptionOperations().getById(customer.getId(), customersSubscriptions.getItems().get(0).getId());
		OfferListResponse addOnOffersForOffer = partnerCenter.getOfferOperations()
				.getAddOnOffersForOffer("1E51E973-0BB4-4358-8202-C417F8239BC0", "US");
	}
}