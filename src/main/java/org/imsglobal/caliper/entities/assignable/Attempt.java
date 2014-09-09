/**
 * 
 */
package org.imsglobal.caliper.entities.assignable;

import org.imsglobal.caliper.entities.CaliperAgent;
import org.imsglobal.caliper.entities.CaliperEntity;

/**
 * @author pnayak
 * 
 *         Representation of an Attempt. Attempt's are generated as part of, or,
 *         are the object of an interaction represented by an AssignableEvent
 * 
 */
public class Attempt extends CaliperEntity {

	private int count;
	private Assignable assignable;
	private CaliperAgent actor;

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * @return the assignable
	 */
	public Assignable getAssignable() {
		return assignable;
	}

	/**
	 * @param assignable
	 *            the assignable to set
	 */
	public void setAssignable(Assignable assignable) {
		this.assignable = assignable;
	}

	/**
	 * @return the actor
	 */
	public CaliperAgent getActor() {
		return actor;
	}

	/**
	 * @param actor
	 *            the actor to set
	 */
	public void setActor(CaliperAgent actor) {
		this.actor = actor;
	}

}
