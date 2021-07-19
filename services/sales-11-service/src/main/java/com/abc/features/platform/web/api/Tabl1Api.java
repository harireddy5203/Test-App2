/*
 * Copyright (c) 2020-2021 Innominds inc. All Rights Reserved. This software is \n * confidential and proprietary information of Innominds inc. You shall not disclose \n * Confidential Information and shall use it only in accordance with the terms \n *
 */
package com.abc.features.platform.web.api;

import com.abc.commons.data.utils.PageUtils;
import com.abc.commons.web.api.AbstractApi;
import com.abc.commons.web.configuration.properties.ApiDocumentationSettings;
import com.abc.features.platform.data.model.experience.tabl1.CreateTabl1Request;
import com.abc.features.platform.data.model.experience.tabl1.Tabl1;
import com.abc.features.platform.data.model.experience.tabl1.UpdateTabl1Request;
import com.abc.features.platform.web.service.Tabl1Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implementation of APIs that provide CRUD (Create, Read, Update and Delete) functionality for
 * persistence models of type {@link com.abc.features.platform.data.model.persistence.Tabl1Entity}.
 *
 * @author Admin
 */
@Slf4j
@RestController
@RequestMapping(Tabl1Api.rootEndPoint)
public class Tabl1Api extends AbstractApi {
    /** Tag for this API. */
    public static final String API_TAG = "Tabl1s";

    /** Root end point. */
    public static final String rootEndPoint = "/sales-11";

    /** Service implementation of type {@link Tabl1Service}. */
    private final Tabl1Service tabl1Service;

    /**
     * Constructor.
     *
     * @param tabl1Service Service instance of type {@link Tabl1Service}.
     */
    public Tabl1Api(final Tabl1Service tabl1Service) {
        this.tabl1Service = tabl1Service;
    }

    /**
     * This API provides the capability to add a new instance of type {@link
     * com.abc.features.platform.data.model.persistence.Tabl1Entity} into the system.
     *
     * @param payload Payload containing the details required to create an instance of type {@link
     *     com.abc.features.platform.data.model.persistence.Tabl1Entity}.
     * @return Response of type {@link ResponseEntity} that wraps an instance of type {@link Tabl1}.
     */
    @Operation(
            method = "createTabl1",
            summary = "Create a new Tabl1.",
            description = "This API is used to create a new Tabl1 in the system.",
            tags = {Tabl1Api.API_TAG},
            security = {
                @SecurityRequirement(
                        name =
                                ApiDocumentationSettings.ApiSecurityScheme
                                        .DEFAULT_SECURITY_SCHEME_NAME)
            })
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "201",
                        description = "Successfully created a new Tabl1 in the system.",
                        content = @Content),
                @ApiResponse(
                        responseCode = "403",
                        description = "You do not have permissions to perform this operation.",
                        content = @Content)
            })
    @PreAuthorize(value = "isAuthenticated()")
    @PostMapping(value = "/tabl1s")
    public ResponseEntity<Tabl1> createTabl1(@Valid @RequestBody final CreateTabl1Request payload) {
        // Delegate to the service layer.
        final Tabl1 newInstance = tabl1Service.createTabl1(payload);

        // Build a response entity object and return it.
        return ResponseEntity.status(HttpStatus.CREATED).body(newInstance);
    }

    /**
     * This API provides the capability to update an existing instance of type {@link
     * com.abc.features.platform.data.model.persistence.Tabl1Entity} in the system.
     *
     * @param tabl1Id Unique identifier of Tabl1 in the system, which needs to be updated.
     * @param payload Request payload containing the details of an existing Tabl1, which needs to be
     *     updated in the system.
     * @return Response of type {@link ResponseEntity} that wraps an instance of type {@link Tabl1}.
     */
    @Operation(
            method = "updateTabl1",
            summary = "Update an existing Tabl1.",
            description = "This API is used to update an existing Tabl1 in the system.",
            tags = {Tabl1Api.API_TAG},
            security = {
                @SecurityRequirement(
                        name =
                                ApiDocumentationSettings.ApiSecurityScheme
                                        .DEFAULT_SECURITY_SCHEME_NAME)
            })
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Successfully updated an existing Tabl1 in the system.",
                        content = @Content),
                @ApiResponse(
                        responseCode = "403",
                        description = "You do not have permissions to perform this operation.",
                        content = @Content)
            })
    @PreAuthorize(value = "isAuthenticated()")
    @PutMapping(value = "/tabl1s/{tabl1Id}")
    public ResponseEntity<Tabl1> updateTabl1(
            @PathVariable(name = "tabl1Id") final Integer tabl1Id,
            @Valid @RequestBody final UpdateTabl1Request payload) {
        // Delegate to the service layer.
        final Tabl1 updatedInstance = tabl1Service.updateTabl1(tabl1Id, payload);

        // Build a response entity object and return it.
        return ResponseEntity.ok(updatedInstance);
    }

    /**
     * This API provides the capability to retrieve the details of an existing {@link
     * com.abc.features.platform.data.model.persistence.Tabl1Entity} in the system.
     *
     * @param tabl1Id Unique identifier of Tabl1 in the system, whose details have to be retrieved.
     * @return Response of type {@link ResponseEntity} that wraps an instance of type {@link Tabl1}.
     */
    @Operation(
            method = "findTabl1",
            summary = "Find an existing Tabl1.",
            description = "This API is used to find an existing Tabl1 in the system.",
            tags = {Tabl1Api.API_TAG},
            security = {
                @SecurityRequirement(
                        name =
                                ApiDocumentationSettings.ApiSecurityScheme
                                        .DEFAULT_SECURITY_SCHEME_NAME)
            })
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description =
                                "Successfully retrieved the details of an existing Tabl1 in the system.",
                        content = @Content),
                @ApiResponse(
                        responseCode = "403",
                        description = "You do not have permissions to perform this operation.",
                        content = @Content)
            })
    @PreAuthorize(value = "isAuthenticated()")
    @GetMapping(value = "/tabl1s/{tabl1Id}")
    public ResponseEntity<Tabl1> findTabl1(@PathVariable(name = "tabl1Id") final Integer tabl1Id) {
        // Delegate to the service layer.
        final Tabl1 matchingInstance = tabl1Service.findTabl1(tabl1Id);

        // Build a response entity object and return it.
        return ResponseEntity.ok(matchingInstance);
    }

    /**
     * This API provides the capability to retrieve all instances of type {@link
     * com.abc.features.platform.data.model.persistence.Tabl1Entity} in the system in a paginated
     * manner.
     *
     * @param page Page number.
     * @param size Page size.
     * @return Response of type {@link ResponseEntity} that holds a page of instances of type Tabl1
     *     based on the provided pagination settings.
     */
    @Operation(
            method = "findAllTabl1s",
            summary = "Find all Tabl1s.",
            description = "This API is used to find all Tabl1s in the system.",
            tags = {Tabl1Api.API_TAG},
            security = {
                @SecurityRequirement(
                        name =
                                ApiDocumentationSettings.ApiSecurityScheme
                                        .DEFAULT_SECURITY_SCHEME_NAME)
            })
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description =
                                "Successfully retrieved the Tabl1s in the system based on the provided pagination settings.",
                        content = @Content),
                @ApiResponse(
                        responseCode = "403",
                        description = "You do not have permissions to perform this operation.",
                        content = @Content)
            })
    @PreAuthorize(value = "isAuthenticated()")
    @GetMapping(value = "/tabl1s")
    public ResponseEntity<Page<Tabl1>> findAllTabl1s(
            @RequestParam(name = "page", required = false, defaultValue = "0") final Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "20")
                    final Integer size) {
        // Delegate to the service layer.
        final Pageable pageSettings = PageUtils.createPaginationConfiguration(page, size);
        final Page<Tabl1> matchingInstances = tabl1Service.findAllTabl1s(pageSettings);

        // Build a response entity object and return it.
        return ResponseEntity.ok(matchingInstances);
    }

    /**
     * This API provides the capability to delete an existing instance of type {@link
     * com.abc.features.platform.data.model.persistence.Tabl1Entity} in the system.
     *
     * @param tabl1Id Unique identifier of Tabl1 in the system, which needs to be deleted.
     * @return Response of type {@link ResponseEntity} that holds the unique identifier of the
     *     {@link com.abc.features.platform.data.model.persistence.Tabl1Entity} that was deleted
     *     from the system.
     */
    @Operation(
            method = "deleteTabl1",
            summary = "Delete an existing Tabl1.",
            description = "This API is used to delete an existing Tabl1 in the system.",
            tags = {Tabl1Api.API_TAG},
            security = {
                @SecurityRequirement(
                        name =
                                ApiDocumentationSettings.ApiSecurityScheme
                                        .DEFAULT_SECURITY_SCHEME_NAME)
            })
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Successfully deleted an existing Tabl1 in the system.",
                        content = @Content),
                @ApiResponse(
                        responseCode = "403",
                        description = "You do not have permissions to perform this operation.",
                        content = @Content)
            })
    @PreAuthorize(value = "isAuthenticated()")
    @DeleteMapping(value = "/tabl1s/{tabl1Id}")
    public ResponseEntity<Integer> deleteTabl1(
            @PathVariable(name = "tabl1Id") final Integer tabl1Id) {
        // Delegate to the service layer.
        final Integer deletedInstance = tabl1Service.deleteTabl1(tabl1Id);

        // Build a response entity object and return it.
        return ResponseEntity.ok(deletedInstance);
    }
}
