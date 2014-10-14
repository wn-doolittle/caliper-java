package org.imsglobal.caliper.entities.media;

import java.util.UUID;

import org.imsglobal.caliper.entities.CaliperEntity;

import com.google.common.base.Strings;

/**
 * Media Location
 */
public class MediaLocation extends CaliperEntity {

    private final String type;
    private final String id;

    /**
     * The time value (from beginning of media) that indicates the current
     * location
     */
    private long currentTime;

    /**
     * @param builder apply builder object properties to the MediaLocation object.
     */
    protected MediaLocation(Builder<?> builder) {
        super(builder);
        this.type = builder.type;
        this.id = builder.id;
        this.currentTime = builder.currentTime;
    }

    /**
     * @return the type
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * @return the currentTime
     */
    public long getCurrentTime() {
        return currentTime;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends CaliperEntity.Builder<T>  {
        private String type;
        private String id;
        private long currentTime;
        private UUID uuid = new UUID(1000l, 500l);

        /**
         * Initialize type with default values.
         */
        public Builder() {
            id(CaliperEntity.Type.MEDIA_LOCATION.uri() + "/" + uuid);
            type(CaliperEntity.Type.MEDIA_LOCATION.uri());
        }

        /**
         * @param type
         * @return builder.
         */
        private T type(String type) {
            this.type = type;
            return self();
        }

        /**
         * @param id
         * @return builder
         */
        @Override
        public T id(String id) {
            if (Strings.isNullOrEmpty(id)) {
                this.id = CaliperEntity.Type.MEDIA_LOCATION.uri() + "/" + uuid;
            } else {
                this.id = id;
            }
            return self();
        }

        /**
         * @param currentTime
         * @return builder
         */
        public T currentTime(long currentTime) {
            this.currentTime = currentTime;
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of MediaLocation.
         */
        public MediaLocation build() {
            return new MediaLocation(this);
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