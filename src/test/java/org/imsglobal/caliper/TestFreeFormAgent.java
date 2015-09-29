package org.imsglobal.caliper;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.imsglobal.caliper.entities.Entity;
import org.imsglobal.caliper.entities.foaf.Agent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

public class TestFreeFormAgent implements Agent, Entity {

    @JsonProperty("@context")
    private String context;

    @JsonProperty("@id")
    private String id;

    @JsonProperty("@type")
    private String type;

    @JsonProperty("extensions")
    private Map<String, Object> extensions;

    // Constructor
    private TestFreeFormAgent(String context, String id, String type) {
        this.context = context;
        this.id = id;
        this.type = type;
    }

    /**
     * Returns the context that provides a mapping of resources.
     * @return the context IRI
     */
    @Nonnull
    public String getContext() {
        return context;
    }

    /**
     * The JSON-LD context provides a mapping of terms to IRIs.  The identifier
     * should be expressed as a unique IRI in conformance with the JSON-LD specification.
     * @param context
     */
    public void setContext(String context) {
        this.context = context;
    }

    /**
     * Returns the id used to identify the entity.
     * @return the identifier IRI
     */
    @Nonnull
    public String getId() {
        return id;
    }

    /**
     * Each entity (or node in the graph as defined by JSON-LD) requires an identifier.  The identifier should be
     * expressed as a unique IRI in conformance with the JSON-LD specification.
     * @param id IRI
     */
    public void setId(@Nonnull String id) {
        this.id = id;
    }

    /**
     * Returns the entity type.  The type should be expressed as a unique IRI in conformance with
     * the JSON-LD specification.
     * @return the thin entity type.
     */
    @Nonnull
    public String getType() {
        return type;
    }

    /**
     * Specifies the type of entity (or node as defined by JSON-LD).  The type should be expressed as a unique IRI
     * in conformance with the JSON-LD specification.
     * @param type IRI
     */
    public void setType(@Nonnull String type) {
        this.type = type;
    }

    /**
     * Returns a map of additional custom attributes.
     * @return custom extensions (key/value pairs).
     */
    @Nullable
    public Map<String, Object> getExtensions() {
        return extensions;
    }

    /**
     * Specify a map of additional custom properties
     * @param extensions
     */
    public void setExtensions(@Nullable Map<String, Object> extensions) {
        this.extensions = extensions;
    }

    /**
     * Static factory method that is used to create instances of ThinEntity.
     * @param id
     * @param type
     * @return ThinEntity
     */
    public static TestFreeFormAgent create(String context, String id, String type) {
        return new TestFreeFormAgent(context, id, type);
    }
}