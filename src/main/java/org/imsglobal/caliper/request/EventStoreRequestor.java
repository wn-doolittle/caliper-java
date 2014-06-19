package org.imsglobal.caliper.request;

import org.imsglobal.caliper.entities.CaliperEntity;
import org.imsglobal.caliper.events.CaliperEvent;

public interface EventStoreRequestor {

	public void send(CaliperEvent caliperEvent);

	public void send(CaliperEntity caliperEntity);

}
