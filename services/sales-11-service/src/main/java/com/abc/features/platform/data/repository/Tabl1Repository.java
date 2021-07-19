/*
 * Copyright (c) 2020-2021 Innominds inc. All Rights Reserved. This software is \n * confidential and proprietary information of Innominds inc. You shall not disclose \n * Confidential Information and shall use it only in accordance with the terms \n *
 */
package com.abc.features.platform.data.repository;

import com.abc.commons.data.jpa.repository.ExtendedJpaRepository;
import com.abc.features.platform.data.model.persistence.Tabl1Entity;
import org.springframework.stereotype.Repository;

/**
 * Repository interface to handle the operations pertaining to domain models of type "Tabl1Entity".
 *
 * @author Admin
 */
@Repository
public interface Tabl1Repository extends ExtendedJpaRepository<Tabl1Entity, Integer> {
    // Any custom methods can be added here.
}
