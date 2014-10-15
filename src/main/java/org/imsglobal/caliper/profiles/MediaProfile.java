package org.imsglobal.caliper.profiles;

import org.imsglobal.caliper.entities.schemadotorg.MediaObject;

public class MediaProfile extends BaseProfile {

    private MediaObject mediaObject;

    /**
     * @param builder apply builder object properties to the profile object.
     */
    protected MediaProfile(Builder<?> builder) {
        super(builder);
        this.mediaObject = builder.mediaObject;
    }

    /**
     * @return media object
     */
   public MediaObject getMediaObject() {
       return mediaObject;
   }

    /**
     * Initialize default parameter values in the builder (not in the outer profile class).
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends BaseProfile.Builder<T>  {
        private MediaObject mediaObject;

        /**
         * @param mediaObject
         * @return builder.
         */
        public T mediaObject(MediaObject mediaObject) {
            this.mediaObject = mediaObject;
            return self();
        }

        /**
         * Client invokes the build method in order to create an immutable profile object.
         * @return a new instance of MediaProfile.
         */
        public MediaProfile build() {
            return new MediaProfile(this);
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