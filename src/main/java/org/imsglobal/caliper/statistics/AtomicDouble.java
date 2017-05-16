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

import java.util.concurrent.atomic.AtomicLong;

import static java.lang.Double.doubleToLongBits;

/**
 * A thread-safe and lock-free double implementation
 *
 */
class AtomicDouble extends Number {

    private static final long serialVersionUID = -2480549991498013056L;
    private AtomicLong bits;

    public AtomicDouble() {
        this(0);
    }

    public AtomicDouble(double initialValue) {
        bits = new AtomicLong(doubleToLongBits(initialValue));
    }

    public final boolean compareAndSet(double expect, double update) {
        return bits.compareAndSet(doubleToLongBits(expect), doubleToLongBits(update));
    }

    public final void set(double newValue) {
        bits.set(doubleToLongBits(newValue));
    }

    public final double get() {
        return Double.longBitsToDouble(bits.get());
    }

    public float floatValue() {
        return (float)get();
    }

    public final double addAndGet(double newValue) {
        return Double.longBitsToDouble(bits.addAndGet(doubleToLongBits(newValue)));
    }

    public final double getAndSet(double newValue) {
        return Double.longBitsToDouble(bits.getAndSet(doubleToLongBits(newValue)));
    }

    public final boolean weakCompareAndSet(float expect, float update) {
        return bits.weakCompareAndSet(doubleToLongBits(expect), doubleToLongBits(update));
    }

    public double doubleValue() {
        return (double) floatValue();
    }

    public int intValue() {
        return (int) get();
    }

    public long longValue() {
        return (long) get();
    }
}