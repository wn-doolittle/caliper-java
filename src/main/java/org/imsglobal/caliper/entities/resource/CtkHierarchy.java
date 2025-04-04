package org.imsglobal.caliper.entities.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.imsglobal.caliper.entities.CaliperCollection;
import org.imsglobal.caliper.entities.EntityType;

import javax.annotation.Nullable;
import java.util.List;

public class CtkHierarchy extends DigitalResource implements CaliperCollection<CaliperDigitalResource> {

    /**
     * Collection of Hierarchy children.
     */
    @JsonProperty("items")
    private final ImmutableList<CaliperDigitalResource> items;

    /**
     * Type of the hierarchy.
     */
    @JsonProperty("hierarchyType")
    private final String hierarchyType;

    /**
     * @param builder apply builder object properties to the object.
     */
    protected CtkHierarchy(Builder<?> builder) {
        super(builder);
        this.items = ImmutableList.copyOf(builder.items);
        this.hierarchyType = builder.hierarchyType;
    }

    /**
     * Return an immutable list of the Collection's items.
     * @return the items
     */
    @Override
    @Nullable
    public ImmutableList<CaliperDigitalResource> getItems() {
        return items;
    }

    /**
     * Return the type of the hierarchy.
     * @return the hierarchyType
     */
    @Nullable
    public String getHierarchyType() {
        return hierarchyType;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder.
     */
    public static abstract class Builder<T extends Builder<T>> extends DigitalResource.Builder<T> {
        private List<CaliperDigitalResource> items = Lists.newArrayList();
        private String hierarchyType;

        /**
         * Constructor
         */
        public Builder() {
            super.type(EntityType.CTK_HIERARCHY);
        }

        /**
         * @param items
         * @return builder.
         */
        public T items(List<CaliperDigitalResource> items) {
            if(items != null) {
                this.items.addAll(items);
            }
            return self();
        }

        /**
         * @param item
         * @return builder.
         */
        public T item(CaliperDigitalResource item) {
            this.items.add(item);
            return self();
        }

        /**
         * @param hierarchyType
         * @return builder.
         */
        public T hierarchyType(String hierarchyType) {
            this.hierarchyType = hierarchyType;
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of the DigitalResourceCollection.
         */
        public CtkHierarchy build() {
            return new CtkHierarchy(this);
        }
    }

    /**
     *
     */
    private static class Builder2 extends Builder<Builder2> {
        @Override
        protected Builder2 self() {
            return this;
        }
    }

    /**
     * Static factory method.
     * @return a new instance of the builder.
     */
    public static Builder<?> builder() {
        return new Builder2();
    }
}