package com.steeplesoft.jooq.codegen;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.steeplesoft.jooq.codegen.model.AddressModel;
import com.steeplesoft.jooq.codegen.model.CityModel;
import com.steeplesoft.jooq.codegen.model.CountryModel;
import com.steeplesoft.jooq.codegen.model.StaffModel;
import com.steeplesoft.jooq.codegen.model.StoreModel;
import org.jooq.DSLContext;
import org.jooq.Field;

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
                        row(
                                STORE.address().ADDRESS_ID,
                                STORE.address().ADDRESS_,
                                STORE.address().ADDRESS2,
                                STORE.address().DISTRICT,
                                row(
                                        STORE.address().city().CITY_ID,
                                        STORE.address().city().CITY_,
                                        STORE.address().city().LAST_UPDATE
                                ).mapping(CityModel::new).as("city"),
                                STORE.address().POSTAL_CODE,
                                STORE.address().PHONE,
                                STORE.address().LAST_UPDATE
                        ).mapping(AddressModel::new).as("address"),
                        multiset(
                                select(
                                        STORE.staff().STAFF_ID,
                                        STORE.staff().FIRST_NAME,
                                        STORE.staff().LAST_NAME,
                                        STORE.staff().USERNAME,
                                        STORE.staff().EMAIL,
                                        STORE.staff().LAST_UPDATE
                                )
                                        .from(STAFF)
                                        .where(STORE.staff().STORE_ID.eq(STORE.STORE_ID))
                        ).as("staff").convertFrom(r -> r.map(mapping(StaffModel::new)))
                )
                .from(STORE)
                .fetch()
                .map(r -> new StoreModel(r.get(STORE.STORE_ID),
                        (AddressModel) r.get("address"),
                        (List<StaffModel>) r.get("staff")));
        System.out.println(mapper.writeValueAsString(stores));
        return stores;
    }
}
