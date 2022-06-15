package com.cafe.facade.impl.table;

import com.cafe.dto.*;
import com.cafe.entity.table.CafeTable;
import com.cafe.entity.table.CafeTableAssignedToWaiter;
import com.cafe.entity.table.CafeTableStatusType;
import com.cafe.entity.user.User;
import com.cafe.entity.user.UserRoleType;
import com.cafe.facade.core.table.CafeTableFacade;
import com.cafe.service.core.table.CafeTableAssignedToWaiterCreationParams;
import com.cafe.service.core.table.CafeTableAssignedToWaiterService;
import com.cafe.service.core.table.CafeTableCreationParams;
import com.cafe.service.core.table.CafeTableService;
import com.cafe.service.core.user.UserRoleService;
import com.cafe.service.core.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class CafeTableFacadeImpl implements CafeTableFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(CafeTableFacadeImpl.class);
    private final CafeTableService cafeTableService;
    private final UserService userService;
    private final CafeTableAssignedToWaiterService cafeTableAssignedToWaiterService;
    private final UserRoleService userRoleService;

    public CafeTableFacadeImpl(CafeTableService cafeTableService,
                               UserService userService,
                               CafeTableAssignedToWaiterService cafeTableAssignedToWaiterService,
                               UserRoleService userRoleService) {
        this.cafeTableService = cafeTableService;
        this.userService = userService;
        this.cafeTableAssignedToWaiterService = cafeTableAssignedToWaiterService;
        this.userRoleService = userRoleService;
    }

    @Override
    public CafeTableRegistrationResponseDto register(CafeTableRegistrationRequestDto dto) {
        Assert.notNull(dto, "Cafe table registration response dto should not be null");
        LOGGER.info("Creating a new cafe table according to the cafe table registration request dto - {}", dto);
        if(cafeTableService.existsByCode(dto.getCode())) {
            return new CafeTableRegistrationResponseDto(
                    List.of(String.format("Cannot register a cafe table with a code of %s because that code is already taken", dto.getCode()))
            );
        }
        CafeTable cafeTable = cafeTableService.create(new CafeTableCreationParams(
                dto.getCafeTableStatusType(),
                dto.getNumberOfSeats(),
                dto.getCode()
        ));
        CafeTableRegistrationResponseDto responseDto = new CafeTableRegistrationResponseDto(
                cafeTable.getCafeTableStatusType(),
                cafeTable.getNumberOfSeats(),
                dto.getCode(),
                LocalDateTime.now()
        );
        LOGGER.info("Successfully created a new cafe table according to the cafe table registration request - {}, response - {}", dto, responseDto);
        return responseDto;
    }

    @Override
    public CafeTableAssignmentResponseDto assignTableToWaiter(CafeTableAssignmentRequestDto dto) {
        Assert.notNull(dto, "Cafe table assignment request dto should not be null");
        LOGGER.info("Assigning a table to a waiter according to the cafe table assignment request dto - {}", dto);
        Optional<User> userOptional = userService.findById(dto.getWaiterId());
        if (userOptional.isEmpty()) {
            return new CafeTableAssignmentResponseDto(
                    List.of(String.format("No user found with an id of %d", dto.getWaiterId()))
            );
        }
        User user = userOptional.get();
        if (userRoleService.getRoleType(user.getUsername()) != UserRoleType.WAITER) {
            return new CafeTableAssignmentResponseDto(
                    List.of("Cannot assign a cafe table to a user who is not a waiter")
            );
        }
        Optional<CafeTable> cafeTableOptional = cafeTableService.findById(dto.getTableId());
        if (cafeTableOptional.isEmpty()) {
            return new CafeTableAssignmentResponseDto(
                    List.of(String.format("No table found with an id of %d", dto.getTableId()))
            );
        }
        CafeTable cafeTable = cafeTableOptional.get();
        CafeTableStatusType cafeTableStatusType = cafeTable.getCafeTableStatusType();
        if(cafeTableStatusType != CafeTableStatusType.FREE) {
            return new CafeTableAssignmentResponseDto(
                    List.of(String.format("The table having an id of %d is not free, its status is %s", dto.getTableId(), cafeTableStatusType))
            );
        }
        CafeTableAssignedToWaiter cafeTableAssignedToWaiter = cafeTableAssignedToWaiterService.create(new CafeTableAssignedToWaiterCreationParams(
                dto.getTableId(),
                dto.getWaiterId()
        ));
        CafeTableAssignmentResponseDto responseDto = new CafeTableAssignmentResponseDto(
                cafeTableAssignedToWaiter.getCafeTable().getId(),
                cafeTableAssignedToWaiter.getWaiter().getId(),
                LocalDateTime.now()
        );
        LOGGER.info("Successfully assigned a table with an id of {} to a waiter with an id of {}, response - {}",
                dto.getTableId(), dto.getWaiterId(), responseDto);
        return responseDto;
    }

    @Override
    public CafeTablesAssignedToWaiterRetrievalResponseDto retrieveCafeTableList(CafeTablesAssignedToWaiterRetrievalRequestDto dto) {
        Assert.notNull(dto, "Cafe table list retrieval request dto should not be null");
        LOGGER.info("Retrieving a list of cafe tables according to the cafe table retrieval request dto - {}", dto);
        List<CafeTableAssignedToWaiter> allByWaiterId = cafeTableAssignedToWaiterService.findAllByWaiterId(dto.getWaiterId());
        CafeTablesAssignedToWaiterRetrievalResponseDto responseDto = new CafeTablesAssignedToWaiterRetrievalResponseDto(allByWaiterId);
        LOGGER.info("Successfully retrieved a list of cafe tables according to the cafe table retrieval request dto - {}, result - {}", dto, responseDto);
        return responseDto;
    }
}
