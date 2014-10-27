package org.imsglobal.caliper.profiles;

import com.google.common.collect.Lists;
import org.imsglobal.caliper.entities.media.MediaLocation;
import org.imsglobal.caliper.entities.schemadotorg.MediaObject;

import java.util.List;

public class MediaProfile extends Profile {

    private MediaObject mediaObject;
    private List<MediaLocation> mediaLocations = Lists.newArrayList();

    /**
     * @param builder apply builder object properties to the profile object.
     */
    protected MediaProfile(Builder<?> builder) {
        super(builder);
        this.mediaObject = builder.mediaObject;
        this.mediaLocations = builder.mediaLocations;
    }

    /**
     * @return media object
     */
    public MediaObject getMediaObject() {
       return mediaObject;
    }

    /**
     * @return list of media locations
     */
    public List<MediaLocation> getMediaLocations() {
        return mediaLocations;
    }

    /**
     * Initialize default parameter values in the builder (not in the outer profile class).
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends Profile.Builder<T>  {
        private MediaObject mediaObject;
        private List<MediaLocation> mediaLocations = Lists.newArrayList();

        /**
         * @param mediaObject
         * @return builder.
         */
        public T mediaObject(MediaObject mediaObject) {
            this.mediaObject = mediaObject;
            return self();
        }

        /**
         * @param mediaLocations
         * @return builder
         */
        public T mediaLocations(List<MediaLocation> mediaLocations) {
            this.mediaLocations = mediaLocations;
            return self();
        }

        /**
         * @param mediaLocation
         * @return builder
         */
        public T mediaLocation(MediaLocation mediaLocation) {
            this.mediaLocations.add(mediaLocation);
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