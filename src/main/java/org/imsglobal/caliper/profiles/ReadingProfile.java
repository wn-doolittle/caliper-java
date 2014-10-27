package org.imsglobal.caliper.profiles;

import com.google.common.collect.Lists;
import org.imsglobal.caliper.entities.DigitalResource;
import org.imsglobal.caliper.entities.reading.View;
import java.util.List;

public class ReadingProfile extends org.imsglobal.caliper.profiles.Profile {

    private DigitalResource reading;
    private List<View> views = Lists.newArrayList();

    /**
     * Constructor
     * @param builder apply builder object properties to the profile object.
     */
    protected ReadingProfile(Builder<?> builder) {
        super(builder);
        this.reading = builder.reading;
        this.views = builder.views;
    }

    /**
     * @return reading
     */
    public DigitalResource getReading () {
        return reading;
    }

    /**
     * @return views
     */
    public List<View> getViews() {
        return views;
    }

    /**
     * Initialize default parameter values in the builder (not in the outer profile class).
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends Profile.Builder<T>  {

        private DigitalResource reading;
        private List<View> views = Lists.newArrayList();

        /**
         * @param reading
         * @return builder
         */
        public T reading(DigitalResource reading) {
            this.reading = reading;
            return self();
        }

        /**
         * @param views
         * @return builder
         */
        public T views(List<View> views) {
            this.views = views;
            return self();
        }

        /**
         * @param view
         * @return builder
         */
        public T view(View view) {
            this.views.add(view);
            return self();
        }

        /**
         * Client invokes the build method in order to create an immutable profile object.
         * @return a new instance of MediaProfile.
         */
        public ReadingProfile build() {
            return new ReadingProfile(this);
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