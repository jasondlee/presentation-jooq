package com.steeplesoft.jooq.codegen;

import com.steeplesoft.jooq.codegen.model.CustomerModel;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static com.steeplesoft.jooq_demo.generated.tables.Customer.CUSTOMER;

@Path("/nocondition")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ConditionResource {
    @Inject
    private DSLContext dsl;

    @GET
    public List<CustomerModel> noCondition(@QueryParam("lastName") String lastName) {
        return dsl.select()
                .from(CUSTOMER)
                .where(
                        CUSTOMER.FIRST_NAME.eq("MARION")
                                .and(
                                        (lastName != null ? CUSTOMER.LAST_NAME.eq(lastName) : DSL.noCondition())
                                )
                ).fetch().map(r -> CustomerModel.fromRecord(r));
    }

}
