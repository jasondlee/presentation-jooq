package com.steeplesoft.jooq.codegen;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.steeplesoft.jooq.codegen.model.AddressModel;
import com.steeplesoft.jooq.codegen.model.BaseModel;
import com.steeplesoft.jooq.codegen.model.CityModel;
import com.steeplesoft.jooq.codegen.model.StaffModel;
import com.steeplesoft.jooq.codegen.model.StoreModel;
import com.steeplesoft.jooq_demo.generated.tables.Address;
import org.jooq.DSLContext;
import org.jooq.SelectField;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static com.steeplesoft.jooq_demo.generated.tables.Staff.STAFF;
import static com.steeplesoft.jooq_demo.generated.tables.Store.STORE;
import static org.jooq.Records.mapping;
import static org.jooq.impl.DSL.multiset;
import static org.jooq.impl.DSL.row;

public class MultisetDemo {
    private DSLContext dsl = DslContextProvider.getDslContext();

    @Test
    public void multisetDemo() throws JsonProcessingException {
        List<StoreModel> stores = dsl.select(
                        STORE.STORE_ID,
                        addressRow(STORE.address()),
                        multiset(
                                dsl.select(
                                                STAFF.STAFF_ID,
                                                STAFF.FIRST_NAME,
                                                STAFF.LAST_NAME,
                                                addressRow(STAFF.address()),
                                                STAFF.EMAIL
                                        )
                                        .from(STAFF)
                                        .where(STAFF.STORE_ID.eq(STORE.STORE_ID))
                        ).as("staff").convertFrom(r -> r.map(mapping(this::mapStaff)))
                )
                .from(STORE)
                .fetchInto(StoreModel.class);

        System.out.println(BaseModel.mapper.writeValueAsString(stores));
    }

    private StaffModel mapStaff(Integer id, String firstName, String lastName, AddressModel address, String email) {
        return new StaffModel()
                .setStaffId(id)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setAddress(address)
                .setEmail(email);
    }

    private SelectField<AddressModel> addressRow(Address address) {
        return row(
                address.ADDRESS_,
                address.ADDRESS2,
                cityRow(address),
                address.POSTAL_CODE,
                address.PHONE
        ).mapping(this::mapAddress).as("address");
    }

    private SelectField<CityModel> cityRow(Address address) {
        return row(
                address.city().CITY_ID,
                address.city().CITY_,
                address.city().LAST_UPDATE
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
