package com.steeplesoft.jooq.basic;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/")
public class DemoResource {
    @GET
    public String foo() {
        return "bar";
    }
}
