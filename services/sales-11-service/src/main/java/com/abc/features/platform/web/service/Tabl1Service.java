/*
 * Copyright (c) 2020-2021 Innominds inc. All Rights Reserved. This software is \n * confidential and proprietary information of Innominds inc. You shall not disclose \n * Confidential Information and shall use it only in accordance with the terms \n *
 */
package com.abc.features.platform.web.service;

import com.abc.commons.data.utils.PageUtils;
import com.abc.commons.instrumentation.Instrument;
import com.abc.features.platform.data.mapper.Tabl1Mapper;
import com.abc.features.platform.data.model.experience.tabl1.CreateTabl1Request;
import com.abc.features.platform.data.model.experience.tabl1.Tabl1;
import com.abc.features.platform.data.model.experience.tabl1.UpdateTabl1Request;
import com.abc.features.platform.data.model.persistence.Tabl1Entity;
import com.abc.features.platform.data.repository.Tabl1Repository;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

/**
 * Service implementation that provides CRUD (Create, Read, Update, Delete) capabilities for
 * entities of type {@link Tabl1Entity}.
 *
 * @author Admin
 */
@Slf4j
@Validated
@Service
public class Tabl1Service {
    /** Repository implementation of type {@link Tabl1Repository}. */
    private final Tabl1Repository tabl1Repository;

    /** Mapper implementation of type {@link Tabl1Mapper} to transform between different types. */
    private final Tabl1Mapper tabl1Mapper;

    /**
     * Constructor.
     *
     * @param tabl1Repository Repository instance of type {@link Tabl1Repository}.
     * @param tabl1Mapper Mapper instance of type {@link Tabl1Mapper}.
     */
    public Tabl1Service(final Tabl1Repository tabl1Repository, final Tabl1Mapper tabl1Mapper) {
        this.tabl1Repository = tabl1Repository;
        this.tabl1Mapper = tabl1Mapper;
    }

    /**
     * This method attempts to create an instance of type {@link Tabl1Entity} in the system based on
     * the provided payload.
     *
     * @param payload Payload containing the details required to create an instance of type {@link
     *     Tabl1Entity}.
     * @return An experience model of type {@link Tabl1} that represents the newly created entity of
     *     type {@link Tabl1Entity}.
     */
    @Instrument
    @Transactional
    public Tabl1 createTabl1(@Valid final CreateTabl1Request payload) {
        // 1. Transform the experience model to a persistence model.
        final Tabl1Entity tabl1Entity = tabl1Mapper.transform(payload);

        // 2. Save the entity.
        Tabl1Service.LOGGER.debug("Saving a new instance of type - Tabl1Entity");
        final Tabl1Entity newInstance = tabl1Repository.save(tabl1Entity);

        // 3. Transform the created entity to an experience model and return it.
        return tabl1Mapper.transform(newInstance);
    }

    /**
     * This method attempts to update an existing instance of type {@link Tabl1Entity} using the
     * details from the provided input, which is an instance of type {@link UpdateTabl1Request}.
     *
     * @param tabl1Id Unique identifier of Tabl1 in the system, which needs to be updated.
     * @param payload Request payload containing the details of an existing Tabl1, which needs to be
     *     updated in the system.
     * @return A instance of type {@link Tabl1} containing the updated details.
     */
    @Instrument
    @Transactional
    public Tabl1 updateTabl1(final Integer tabl1Id, @Valid final UpdateTabl1Request payload) {
        // 1. Verify that the entity being updated truly exists.
        final Tabl1Entity matchingInstance = tabl1Repository.findByIdOrThrow(tabl1Id);

        // 2. Transform the experience model to a persistence model and delegate to the save()
        // method.
        tabl1Mapper.transform(payload, matchingInstance);

        // 3. Save the entity
        Tabl1Service.LOGGER.debug("Saving the updated entity - Tabl1Entity");
        final Tabl1Entity updatedInstance = tabl1Repository.save(matchingInstance);

        // 4. Transform updated entity to output object
        return tabl1Mapper.transform(updatedInstance);
    }

    /**
     * This method attempts to find a {@link Tabl1Entity} whose unique identifier matches the
     * provided identifier.
     *
     * @param tabl1Id Unique identifier of Tabl1 in the system, whose details have to be retrieved.
     * @return Matching entity of type {@link Tabl1} if found, else returns null.
     */
    @Instrument
    @Transactional(readOnly = true)
    public Tabl1 findTabl1(final Integer tabl1Id) {
        // 1. Find a matching entity and throw an exception if not found.
        final Tabl1Entity matchingInstance = tabl1Repository.findByIdOrThrow(tabl1Id);

        // 2. Transform the matching entity to the desired output.
        return tabl1Mapper.transform(matchingInstance);
    }

    /**
     * This method attempts to find instances of type Tabl1Entity based on the provided page
     * definition. If the page definition is null or contains invalid values, this method attempts
     * to return the data for the first page (i.e. page index is 0) with a default page size as 20.
     *
     * @return Returns a page of objects based on the provided page definition. Each object in the
     *     returned page is an instance of type {@link Tabl1}.
     */
    @Instrument
    @Transactional(readOnly = true)
    public Page<Tabl1> findAllTabl1s(final Pageable page) {
        // 1. Validate the provided pagination settings.
        final Pageable pageSettings = PageUtils.validateAndUpdatePaginationConfiguration(page);
        Tabl1Service.LOGGER.debug(
                "Page settings: page number {}, page size {}",
                pageSettings.getPageNumber(),
                pageSettings.getPageSize());

        // 2. Delegate to the super class method to find the data (page settings are verified in
        // that method).
        final Page<Tabl1Entity> pageData = tabl1Repository.findAll(pageSettings);

        // 3. If the page has data, transform each element into target type.
        if (pageData.hasContent()) {
            final List<Tabl1> dataToReturn =
                    pageData.getContent().stream()
                            .map(tabl1Mapper::transform)
                            .collect(Collectors.toList());

            return PageUtils.createPage(dataToReturn, pageSettings, pageData.getTotalElements());
        }

        // Return empty page.
        return PageUtils.emptyPage(pageSettings);
    }

    /**
     * This method attempts to delete an existing instance of type {@link Tabl1Entity} whose unique
     * identifier matches the provided identifier.
     *
     * @param tabl1Id Unique identifier of Tabl1 in the system, which needs to be deleted.
     * @return Unique identifier of the instance of type Tabl1Entity that was deleted.
     */
    @Instrument
    @Transactional
    public Integer deleteTabl1(final Integer tabl1Id) {
        // 1. Delegate to our repository method to handle the deletion.
        return tabl1Repository.deleteOne(tabl1Id);
    }
}
