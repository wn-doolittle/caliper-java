package org.imsglobal.caliper.entities;

import com.google.common.collect.ImmutableList;

public interface Collection<T extends Entity> extends Entity {

    Entity getIsPartOf();

    ImmutableList<T> getItems();
}
