package com.steeplesoft.jooq.codegen;

import static com.steeplesoft.jooq_demo.generated.tables.Actor.ACTOR;
import static com.steeplesoft.jooq_demo.generated.tables.Customer.CUSTOMER;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.steeplesoft.jooq.codegen.model.ActorModel;
import com.steeplesoft.jooq.codegen.model.CustomerModel;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

@Path("/")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DummyResource {
    @Inject
    private DSLContext dsl;

    @GET
    public List<ActorModel> hello() {
        return dsl.fetch(ACTOR)
                .map(r -> ActorModel.fromRecord(r));
    }

    @GET
    @Path("/nocondition")
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
