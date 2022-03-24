package com.steeplesoft.jooq.codegen.mapper;

import com.steeplesoft.jooq.codegen.model.CustomerModel;
import com.steeplesoft.jooq_demo.generated.tables.records.CustomerRecord;
import org.jetbrains.annotations.Nullable;
import org.jooq.RecordMapper;

public class CustomerMapper implements RecordMapper<CustomerRecord, CustomerModel>  {
    @Override
    public @Nullable CustomerModel map(CustomerRecord customerRecord) {
        return new CustomerModel()
                .setFirstName(customerRecord.getFirstName())
                .setLastName(customerRecord.getLastName());

    }
}
