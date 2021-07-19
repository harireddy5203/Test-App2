/*
 * Copyright (c) 2020-2021 Innominds inc. All Rights Reserved. This software is \n * confidential and proprietary information of Innominds inc. You shall not disclose \n * Confidential Information and shall use it only in accordance with the terms \n *
 */
package com.abc.features.platform.data.mapper;

import com.abc.features.platform.data.model.experience.tabl1.CreateTabl1Request;
import com.abc.features.platform.data.model.experience.tabl1.Tabl1;
import com.abc.features.platform.data.model.experience.tabl1.UpdateTabl1Request;
import com.abc.features.platform.data.model.persistence.Tabl1Entity;
import java.util.Collection;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper contract that maps / transforms data from an instance of type {@link Tabl1Entity to {@link Tabl1 and vice-versa.
 *
 * @author Admin
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface Tabl1Mapper {

    /**
     * This method transforms an instance of type {@link CreateTabl1Request} to an instance of type
     * {@link Tabl1Entity}.
     *
     * @param source Instance of type {@link CreateTabl1Request} that needs to be transformed to
     *     {@link Tabl1Entity}.
     * @return Instance of type {@link Tabl1Entity}.
     */
    Tabl1Entity transform(CreateTabl1Request source);

    /**
     * This method transforms an instance of type {@link Tabl1Entity} to an instance of type {@link
     * Tabl1}.
     *
     * @param source Instance of type {@link Tabl1Entity} that needs to be transformed to {@link
     *     Tabl1}.
     * @return Instance of type {@link Tabl1}.
     */
    Tabl1 transform(Tabl1Entity source);

    /**
     * This method converts / transforms the provided collection of {@link Tabl1Entity} instances to
     * a collection of instances of type {@link Tabl1}.
     *
     * @param source Instance of type {@link Tabl1Entity} that needs to be transformed to {@link
     *     Tabl1}.
     * @return Collection of instance of type {@link Tabl1}.
     */
    default Collection<Tabl1> transformListTo(Collection<Tabl1Entity> source) {
        return source.stream().map(this::transform).collect(Collectors.toSet());
    }
    /**
     * This method transforms an instance of type {@link UpdateTabl1Request} to an instance of type
     * {@link Tabl1Entity}.
     *
     * <p>The provided instance ({@code target}) will be updated instead of creating a new instance.
     *
     * @param source Instance of type {@link UpdateTabl1Request} that needs to be transformed to
     *     {@link Tabl1Entity}.
     * @param target Instance of type {@link Tabl1Entity} that will be updated instead of creating
     *     and returning a new instance.
     */
    void transform(UpdateTabl1Request source, @MappingTarget Tabl1Entity target);

    /**
     * This method transforms an instance of type {@link UpdateTabl1Request} to an instance of type
     * {@link Tabl1Entity}.
     *
     * @param source Instance of type {@link UpdateTabl1Request} that needs to be transformed to
     *     {@link Tabl1Entity}.
     * @return Instance of type {@link Tabl1Entity}.
     */
    Tabl1Entity transform(UpdateTabl1Request source);

    /**
     * This method converts / transforms the provided collection of {@link UpdateTabl1Request}
     * instances to a collection of instances of type {@link Tabl1Entity}.
     *
     * @param source Instance of type {@link UpdateTabl1Request} that needs to be transformed to
     *     {@link Tabl1Entity}.
     * @return Instance of type {@link Tabl1Entity}.
     */
    default Collection<Tabl1Entity> transformList(Collection<UpdateTabl1Request> source) {
        return source.stream().map(this::transform).collect(Collectors.toSet());
    }
}
