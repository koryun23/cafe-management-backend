package com.cafe.mapper.table;

import com.cafe.dto.CafeTableAssignmentRequestDto;
import com.cafe.service.core.table.CafeTableAssignedToWaiterCreationParams;
import org.springframework.stereotype.Component;

@Component
public class CafeTableAssignedToWaiterCreationParamsMapperImpl implements CafeTableAssignedToWaiterCreationParamsMapper{
    @Override
    public CafeTableAssignedToWaiterCreationParams apply(CafeTableAssignmentRequestDto dto) {
        return new CafeTableAssignedToWaiterCreationParams(
                dto.getCafeTableId(),
                dto.getWaiterId()
        );
    }
}
