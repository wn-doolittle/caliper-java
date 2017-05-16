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


public class Statistics extends StatisticsMap {

    private static final long serialVersionUID = 5469315718941515883L;

    private static String MEASURE_KEY = "Measure";
    private static String DESCRIBE_KEY = "Describe";

    private static String SUCCESSFUL_KEY = "Successful";
    private static String FAILED_KEY = "Failed";

    public Statistic getDescribes() {
        return ensure(DESCRIBE_KEY);
    }

    public void updateDescribes(double val) {
        update(DESCRIBE_KEY, val);
    }

    public Statistic getMeasures() {
        return ensure(MEASURE_KEY);
    }

    public void updateMeasures(double val) {
        update(MEASURE_KEY, val);
    }

    public Statistic getSuccessful() {
        return ensure(SUCCESSFUL_KEY);
    }

    public void updateSuccessful(double val) {
        update(SUCCESSFUL_KEY, val);
    }

    public Statistic getFailed() {
        return ensure(FAILED_KEY);
    }

    public void updateFailed(double val) {
        update(FAILED_KEY, val);
    }
}