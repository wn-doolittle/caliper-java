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

package org.imsglobal.caliper.databind;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

public enum JxnFilters {
    SERIALIZE_ALL("serializeAll", SimpleBeanPropertyFilter.serializeAll()),
    SERIALIZE_ID_ONLY("filterOutAllExceptId", SimpleBeanPropertyFilter.filterOutAllExcept("id")),
    SERIALIZE_WITHOUT_CONTEXT("serializeAllExceptContext", SimpleBeanPropertyFilter.serializeAllExcept("@context"));

    private final String id;
    private final SimpleBeanPropertyFilter filter;
    private static final Map<String, SimpleBeanPropertyFilter> filters;

    static {
        Map<String, SimpleBeanPropertyFilter> map = new HashMap<>();
        for (JxnFilters constants : JxnFilters.values()) {
            map.put(constants.id(), constants.filter());
        }
        filters = ImmutableMap.copyOf(map);
    }

    /**
     * Private constructor
     * @param id
     */
    private JxnFilters(final String id, final SimpleBeanPropertyFilter filter) {
        this.id = id;
        this.filter = filter;
    }

    /**
     * Retrieve id.
     * @return id
     */
    public String id() {
        return id;
    }

    /**
     * Retrieve filter.
     * @return filter
     */
    public SimpleBeanPropertyFilter filter() {
        return filter;
    }

    /**
     * Retrieve filters map.
     * @return filters map
     */
    public static Map<String, SimpleBeanPropertyFilter> filters() {
        return filters;
    }

    /**
     * Retrieve PropertyFilter by id.
     * @param id
     * @return SimplePropertyFilter
     */
    public static SimpleBeanPropertyFilter getFilterById(String id) {
        return filters.get(id);
    }
}