package org.imsglobal.caliper.entities.media;

import java.util.UUID;

import org.imsglobal.caliper.entities.CaliperEntity;

import com.google.common.base.Strings;

/**
 * Media Location
 */
public class MediaLocation extends CaliperEntity {

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
        private long currentTime;

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