package org.imsglobal.caliper.request;

import org.imsglobal.caliper.entities.Agent;
import org.imsglobal.caliper.entities.DigitalResource;
import org.imsglobal.caliper.events.CaliperEvent;

public interface EventStoreRequestor {

	public void send(CaliperEvent caliperEvent);
	public void send(DigitalResource digitalResource);
	public void send(Agent agent);
	
}
