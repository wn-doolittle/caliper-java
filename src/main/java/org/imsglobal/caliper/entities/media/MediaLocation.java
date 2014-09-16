package org.imsglobal.caliper.entities.media;

import java.util.UUID;

import org.imsglobal.caliper.entities.CaliperEntity;

import com.google.common.base.Strings;

/**
 * Media Location
 */
public class MediaLocation extends CaliperEntity {

    private final String id;
    private final String type;
	/**
	 * @param currentTime
	 */
	public MediaLocation(String id, long currentTime) {
		
		super();
		
		// Set id and JSON-LD @type
		if (Strings.isNullOrEmpty(id)) {
			// auto generate a UUID
			setId("http://purl.imsglobal.org/caliper/v1/medialocation/" + new UUID(1000l, 500l));
		} else {
			setId(id);
		}
		setType("http://purl.imsglobal.org/caliper/v1/MediaLocation");
		
		this.currentTime = currentTime;
	}

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
        this.id = builder.id;
        this.type = builder.type;
        this.currentTime = builder.currentTime;
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
        private static final String MEDIALOCATION_TYPE = "http://purl.imsglobal.org/caliper/v1/MediaLocation";
        private String id;
        private String type;
        private long currentTime;
        private UUID uuid = new UUID(1000l, 500l);

        /**
         * Initialize type with default values.  Required if builder().id() and/or .type() is not set by user.
         */
        public Builder() {
            id(MEDIALOCATION_TYPE + "/" + uuid);
            type(MEDIALOCATION_TYPE);
        }

        /**
         * DO WE NEED/WANT TO DO THIS?
         * Initialize Id
         * @param id
         */
        public Builder(String id) {
            if (Strings.isNullOrEmpty(id)) {
                id(MEDIALOCATION_TYPE + "/" + uuid);
            } else {
                id(id);
            }
            type(MEDIALOCATION_TYPE);
        }

        /**
         * @param id
         * @return builder
         */
        @Override
        public T id(String id) {
            if (Strings.isNullOrEmpty(id)) {
                this.id = MEDIALOCATION_TYPE + "/" + uuid;
            } else {
                this.id = id;
            }
            return self();
        }

        /**
         * @param type
         * @return builder
         */
        @Override
        public T type(String type) {
            if (type.equals(MEDIALOCATION_TYPE)) {
                this.type = type;
            } else {
                this.type = MEDIALOCATION_TYPE;
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