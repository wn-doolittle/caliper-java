/**
 * 
 */
package org.imsglobal.caliper.request;

import org.imsglobal.caliper.entities.Agent;
import org.imsglobal.caliper.entities.DigitalResource;
import org.imsglobal.caliper.events.CaliperEvent;

/**
 * @author pnayak
 *
 */
public class HttpRequestor implements EventStoreRequestor {

	/* (non-Javadoc)
	 * @see org.imsglobal.caliper.request.EventStoreRequestor#send(org.imsglobal.caliper.events.CaliperEvent)
	 */
	@Override
	public void send(CaliperEvent caliperEvent) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.imsglobal.caliper.request.EventStoreRequestor#send(org.imsglobal.caliper.entities.DigitalResource)
	 */
	@Override
	public void send(DigitalResource digitalResource) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.imsglobal.caliper.request.EventStoreRequestor#send(org.imsglobal.caliper.entities.Agent)
	 */
	@Override
	public void send(Agent agent) {
		// TODO Auto-generated method stub

	}

}
