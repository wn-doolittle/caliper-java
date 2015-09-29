/**
 * This file is part of IMS Caliper Analytics™ and is licensed to
 * IMS Global Learning Consortium, Inc. (http://www.imsglobal.org)
 * under one or more contributor license agreements.  See the NOTICE
 * file distributed with this work for additional information.
 *
 * IMS Caliper is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, version 3 of the License.
 *
 * IMS Caliper is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package org.imsglobal.caliper;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.imsglobal.caliper.entities.Entity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

public class TestFreeFormEntity implements Entity {

    @JsonProperty("@context")
    private String context;

    @JsonProperty("@id")
    private String id;

    @JsonProperty("@type")
    private String type;

    @JsonProperty("extensions")
    private Map<String, Object> extensions;

    // Constructor
    private TestFreeFormEntity(String context, String id, String type) {
        this.context = context;
        this.id = id;
        this.type = type;
    }

    /**
     * Returns the context that provides a mapping of resources.
     * @return the context IRI
     */
    @Nullable
    public String getContext() {
        return context;
    }

    /**
     * The JSON-LD context provides a mapping of terms to IRIs.  The identifier
     * should be expressed as a unique IRI in conformance with the JSON-LD specification.
     * @param context
     */
    public void setContext(@Nullable String context) {
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
    public static TestFreeFormEntity create(String context, String id, String type) {
        return new TestFreeFormEntity(context, id, type);
    }
}