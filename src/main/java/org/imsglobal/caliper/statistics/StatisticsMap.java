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

package org.imsglobal.caliper.statistics;

import java.util.concurrent.ConcurrentHashMap;

public class StatisticsMap extends ConcurrentHashMap<String, Statistic> {

    private static final long serialVersionUID = -8837006750327885446L;

    public Statistic ensure(String key) {
        if (this.containsKey(key)) return this.get(key);

        Statistic statistic = new Statistic();
        this.put(key, statistic);
        return statistic;
    }

    public void update(String operation, double val) {
        if (!this.containsKey(operation)) {
            this.putIfAbsent(operation, new Statistic());
        }

        this.get(operation).update(val);
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();

        builder.append("\n-------- Caliper Java Statistics --------\n");

        for (Entry<String, Statistic> entry : entrySet()) {

            String operation = entry.getKey();
            Statistic statistic = entry.getValue();

            builder.append(String.format("%s : %s\n", operation, statistic.toString()));
        }

        builder.append("----------------------------------------\n");

        return builder.toString();
    }
}