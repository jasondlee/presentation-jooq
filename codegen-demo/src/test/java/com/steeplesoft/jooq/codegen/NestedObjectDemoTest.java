package com.steeplesoft.jooq.codegen;

import com.steeplesoft.jooq.codegen.model.AddressModel;
import com.steeplesoft.jooq.codegen.model.CityModel;
import com.steeplesoft.jooq.codegen.model.CustomerModel;
import org.jooq.DSLContext;
import org.jooq.SelectField;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static com.steeplesoft.jooq_demo.generated.tables.Address.ADDRESS;
import static com.steeplesoft.jooq_demo.generated.tables.City.CITY;
import static com.steeplesoft.jooq_demo.generated.tables.Customer.CUSTOMER;
import static org.jooq.Records.mapping;
import static org.jooq.impl.DSL.row;

public class NestedObjectDemoTest {
    private DSLContext dsl = DslContextProvider.getDslContext();

    @Test
    public void nestedDemo() {
        CustomerModel customer = dsl.select(
                        CUSTOMER.FIRST_NAME,
                        CUSTOMER.LAST_NAME,
                        CUSTOMER.EMAIL,
                        row(
                                ADDRESS.ADDRESS_,
                                ADDRESS.ADDRESS2,
                                ADDRESS.CITY_ID,
                                ADDRESS.POSTAL_CODE,
                                ADDRESS.PHONE
                        ).mapping((address, address2, cityId, postalCode, phone) -> new AddressModel()
                                .setAddress(address)
                                .setAddress2(address2)
                                .setPostalCode(postalCode)
                                .setPhone(phone)).as("address")
                )
                .from(CUSTOMER).join(ADDRESS).on(CUSTOMER.ADDRESS_ID.eq(ADDRESS.ADDRESS_ID))
                .where(CUSTOMER.CUSTOMER_ID.eq(1))
                .fetchOne(mapping((firstName, lastName, email, address) -> {
                                    return new CustomerModel()
                                            .setFirstName(firstName)
                                            .setLastName(lastName)
                                            .setEmail(email)
                                            .setAddress(address)
                                            ;
                                }
                        )
                );
        System.out.println(customer);
    }

    @Test
    public void nestedNestedDemo() {
        CustomerModel customer = dsl.select(
                        CUSTOMER.FIRST_NAME,
                        CUSTOMER.LAST_NAME,
                        CUSTOMER.EMAIL,
                        row(
                                ADDRESS.ADDRESS_,
                                ADDRESS.ADDRESS2,
                                row(
                                        CITY.CITY_ID,
                                        CITY.CITY_,
                                        CITY.LAST_UPDATE
                                ).mapping((id, city, lastUpdate) -> new CityModel()
                                        .setCityId(id)
                                        .setCity(city)
                                        .setLastUpdate(lastUpdate)
                                ).as("city"),
                                ADDRESS.POSTAL_CODE,
                                ADDRESS.PHONE
                        ).mapping((address, address2, city, postalCode, phone) -> new AddressModel()
                                .setAddress(address)
                                .setAddress2(address2)
                                .setCity(city)
                                .setPostalCode(postalCode)
                                .setPhone(phone)).as("address")
                )
                .from(CUSTOMER)
                    .join(ADDRESS).on(CUSTOMER.ADDRESS_ID.eq(ADDRESS.ADDRESS_ID))
                    .join(CITY).on(ADDRESS.CITY_ID.eq(CITY.CITY_ID))
                .where(CUSTOMER.CUSTOMER_ID.eq(1))
                .fetchOne(mapping((firstName, lastName, email, address) -> {
                                    return new CustomerModel()
                                            .setFirstName(firstName)
                                            .setLastName(lastName)
                                            .setEmail(email)
                                            .setAddress(address)
                                            ;
                                }
                        )
                );
        System.out.println(customer);
    }

    @Test
    public void nestedPlusImplictDemo() {
        CustomerModel customer = dsl.select(
                        CUSTOMER.FIRST_NAME,
                        CUSTOMER.LAST_NAME,
                        CUSTOMER.EMAIL,
                        row(
                                CUSTOMER.address().ADDRESS_,
                                CUSTOMER.address().ADDRESS2,
                                row(
                                        CUSTOMER.address().city().CITY_ID,
                                        CUSTOMER.address().city().CITY_,
                                        CUSTOMER.address().city().LAST_UPDATE
                                ).mapping((id, city, lastUpdate) -> new CityModel()
                                        .setCityId(id)
                                        .setCity(city)
                                        .setLastUpdate(lastUpdate)
                                ).as("city"),
                                CUSTOMER.address().POSTAL_CODE,
                                CUSTOMER.address().PHONE
                        ).mapping((address, address2, city, postalCode, phone) -> new AddressModel()
                                .setAddress(address)
                                .setAddress2(address2)
                                .setCity(city)
                                .setPostalCode(postalCode)
                                .setPhone(phone)).as("address")
                )
                .from(CUSTOMER)
                .where(CUSTOMER.CUSTOMER_ID.eq(1))
                .fetchOne(mapping((firstName, lastName, email, address) -> {
                                    return new CustomerModel()
                                            .setFirstName(firstName)
                                            .setLastName(lastName)
                                            .setEmail(email)
                                            .setAddress(address)
                                            ;
                                }
                        )
                );
        System.out.println(customer);
    }

    @Test
    public void nestedDemoPlusImplicitWithFunctions() {
        CustomerModel customer = dsl.select(
                        CUSTOMER.FIRST_NAME,
                        CUSTOMER.LAST_NAME,
                        CUSTOMER.EMAIL,
                        addressRow()
                )
                .from(CUSTOMER)
                .where(CUSTOMER.CUSTOMER_ID.eq(1))
                .fetchOne(mapping(this::mapCustomer));
        System.out.println(customer);
    }

    private CustomerModel mapCustomer(String firstName, String lastName, String email, AddressModel address) {
        return new CustomerModel()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setAddress(address);
    }

    private SelectField<AddressModel> addressRow() {
        return row(
                CUSTOMER.address().ADDRESS_,
                CUSTOMER.address().ADDRESS2,
                cityRow(),
                CUSTOMER.address().POSTAL_CODE,
                CUSTOMER.address().PHONE
        ).mapping(this::mapAddress).as("address");
    }

    private SelectField<CityModel> cityRow() {
        return row(
                CUSTOMER.address().city().CITY_ID,
                CUSTOMER.address().city().CITY_,
                CUSTOMER.address().city().LAST_UPDATE
        ).mapping(this::mapCity).as("city");
    }

    private AddressModel mapAddress(String address, String address2, CityModel city, String postalCode, String phone) {
        return new AddressModel()
                .setAddress(address)
                .setAddress2(address2)
                .setCity(city)
                .setPostalCode(postalCode)
                .setPhone(phone);
    }

    private CityModel mapCity(Integer id, String city, LocalDateTime updated) {
        return new CityModel()
                .setCityId(id)
                .setCity(city)
                .setLastUpdate(updated);
    }
}
