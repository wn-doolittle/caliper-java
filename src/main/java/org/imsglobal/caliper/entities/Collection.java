package org.imsglobal.caliper.entities;

import com.google.common.collect.ImmutableList;

/**
 * The Collection interface marks an object type that can serve as a container of other entities.
 */
public interface Collection<T extends Entity> extends Entity {

    Entity getIsPartOf();

    ImmutableList<T> getItems();
}