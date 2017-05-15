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
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import java.util.Map;

public class JxnFilterProvider {

    /**
     * Constructor
     */
    private JxnFilterProvider() {

    }

    /**
     * Factory method that returns a Caliper-friendly SimpleFilterProvider instance.
     * @return SimpleFilterProvider
     */
    public static SimpleFilterProvider create() {
        return createBaseSimpleFilterProvider();
    }

    /**
     * Factory method that returns a Caliper-friendly JsonSimpleFilterProvider instance
     * provisioned with a single filter.
     * @return JsonSimpleFilterProvider
     */
    public static SimpleFilterProvider create(String id, SimpleBeanPropertyFilter filter) {
        SimpleFilterProvider provider = createBaseSimpleFilterProvider();
        provider.addFilter(id, filter);

        return provider;
    }

    /**
     * Factory method that returns a Caliper-friendly JsonSimpleFilterProvider instance
     * provisioned with multiple filters.
     * @return JsonSimpleFilterProvider
     */
    public static SimpleFilterProvider create(Map<String, SimpleBeanPropertyFilter> filters) {
        SimpleFilterProvider provider = createBaseSimpleFilterProvider();
        for (Map.Entry<String, SimpleBeanPropertyFilter> entry : filters.entrySet()) {
            provider.addFilter(entry.getKey(), entry.getValue());
        }

        return provider;
    }

    /**
     * Factory method that provisions a Caliper-friendly SimpleFilterProvider as a prelude to adding Filters.
     * @return SimpleFilterProvider
     */
    private static SimpleFilterProvider createBaseSimpleFilterProvider() {
        SimpleFilterProvider provider = new SimpleFilterProvider();
        provider.setDefaultFilter(JxnFilters.EXCLUDE_CONTEXT.filter());
        provider.setFailOnUnknownId(true);

        return provider;
    }
}