package org.imsglobal.caliper.profiles;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({
    "name",
    "partOf",
    "objectType",
    "alignedLearningObjective",
    "keyword",
    "action",
    "target",
    "generated" })
public abstract class BaseProfile {

    @JsonProperty("name")
    private String name;

    @JsonProperty("partOf")
    private String partOf;

    @JsonProperty("objectType")
    private List<String> objectType = new ArrayList<String>();

    @JsonProperty("alignedLearningObjective")
    private List<String> alignedLearningObjective = new ArrayList<String>();

    @JsonProperty("keyword")
    private List<String> keyword = new ArrayList<String>();

    @JsonProperty("action")
    private String action;

    @JsonProperty("target")
    private Object target;

    @JsonProperty("generated")
    private Object generated;

    /**
     * @param builder apply builder object properties to the profile object.
     */
    protected BaseProfile(Builder<?> builder) {
        this.name = builder.name;
        this.partOf = builder.partOf;
        this.objectType = builder.objectType;
        this.alignedLearningObjective = builder.alignedLearningObjective;
        this.keyword = builder.keyword;
        this.action = builder.action;
        this.target = builder.target;
        this.generated = builder.generated;
    }

    /**
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return parent identifier.
     */
    public String getPartOf() {
        return partOf;
    }

    /**
     * @return objectType.
     */
    public List<String> getObjectType() {
        return objectType;
    }

    /**
     * @return learning objectives.
     */
    public List<String> getAlignedLearningObjective() {
        return alignedLearningObjective;
    }

    /**
     * @return topics.
     */
    public List<String> getKeyword() {
        return keyword;
    }

    /**
     * @return action.
     */
    public String getAction() {
        return action;
    }

    /**
     * @return target object, if exists.
     */
    public Object getTarget() {
        return target;
    }

    /**
     * @return generated object, if exists.
     */
    public Object getGenerated() {
        return generated;
    }

    /**
     * Initialize default parameter values in the builder (not in the outer profile class).  Given the abstract nature
     * of BaseProfile, the builder's .build() method is omitted.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> {
        private String name;
        private String partOf;
        private List<String> objectType;
        private List<String> alignedLearningObjective = new ArrayList<String>();
        private List<String> keyword = new ArrayList<String>();
        private String action;
        private Object target;
        private Object generated;

        protected abstract T self();

        /**
         * @param name
         * @return builder.
         */
        public T name(String name) {
            this.name = name;
            return self();
        }

        /**
         * @param partOf
         * @return builder.
         */
        public T partOf(String partOf) {
            this.partOf = partOf;
            return self();
        }

        /**
         * @param objectType
         * @return builder.
         */
        public T objectType(List<String> objectType) {
            this.objectType = objectType;
            return self();
        }

        /**
         * @param alignedLearningObjective
         * @return builder.
         */
        public T alignedLearningObjective(List<String> alignedLearningObjective) {
            this.alignedLearningObjective = alignedLearningObjective;
            return self();
        }

        /**
         * @param keyword
         * @return builder.
         */
        public T keyword(List<String> keyword) {
            this.keyword = keyword;
            return self();
        }

        /**
         * @param action
         * @return builder.
         */
        public T action(String action) {
            this.action = action;
            return self();
        }

        /**
         * @param target
         * @return builder.
         */
        public T target(Object target) {
            this.target = target;
            return self();
        }

        /**
         * @param generated
         * @return builder.
         */
        public T generated(Object generated) {
            this.generated = generated;
            return self();
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
}