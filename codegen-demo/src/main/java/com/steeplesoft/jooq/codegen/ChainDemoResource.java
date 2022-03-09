package com.steeplesoft.jooq.codegen;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.steeplesoft.jooq.codegen.model.AddressModel;
import com.steeplesoft.jooq.codegen.model.CityModel;
import com.steeplesoft.jooq.codegen.model.StaffModel;
import com.steeplesoft.jooq.codegen.model.StoreModel;
import com.steeplesoft.jooq_demo.generated.tables.Address;
import com.steeplesoft.jooq_demo.generated.tables.City;
import com.steeplesoft.jooq_demo.generated.tables.Staff;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.SelectField;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static com.steeplesoft.jooq_demo.generated.tables.Staff.STAFF;
import static com.steeplesoft.jooq_demo.generated.tables.Store.STORE;
import static org.jooq.Records.mapping;
import static org.jooq.impl.DSL.multiset;
import static org.jooq.impl.DSL.row;
import static org.jooq.impl.DSL.select;

@Path("/chain")
@Produces(MediaType.APPLICATION_JSON)
public class ChainDemoResource {
    @Inject
    private ObjectMapper mapper;

    @Inject
    private DSLContext dsl;

    @GET
    public List<StoreModel> chainedStoresAndStaff() throws JsonProcessingException {
        List<StoreModel> stores = dsl.select(
                        STORE.STORE_ID,
                        addressRow(STORE.address()),
                        staffMultiset(STORE.staff())
                )
                .from(STORE)
                .fetch()
                .map(r -> new StoreModel(r.get(STORE.STORE_ID),
                        (AddressModel) r.get("address"),
                        (List<StaffModel>) r.get("staff")));
        System.out.println(mapper.writeValueAsString(stores));
        return stores;
    }

    private SelectField<List<StaffModel>> staffMultiset(Staff staff) {
        return multiset(
                select(
                        staff.STAFF_ID,
                        staff.FIRST_NAME,
                        staff.LAST_NAME,
                        staff.USERNAME,
                        staff.EMAIL,
                        staff.LAST_UPDATE
                )
                        .from(STAFF)
                        .where(staff.STORE_ID.eq(STORE.STORE_ID))
        ).as("staff").convertFrom(r -> r.map(mapping(StaffModel::new)));
    }

    private SelectField<AddressModel> addressRow(Address address) {
        return row(
                address.ADDRESS_ID,
                address.ADDRESS_,
                address.ADDRESS2,
                address.DISTRICT,
                cityRow(address.city()),
                address.POSTAL_CODE,
                address.PHONE,
                address.LAST_UPDATE
        ).mapping(AddressModel::new).as("address");
    }

    private SelectField<CityModel> cityRow(City city) {
        return row(
                city.CITY_ID,
                city.CITY_,
                city.LAST_UPDATE
        ).mapping(CityModel::new).as("city");
    }
}
