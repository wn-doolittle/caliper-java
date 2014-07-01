package org.imsglobal.caliper.request;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.entity.StringEntity;
import org.imsglobal.caliper.entities.CaliperEntity;
import org.imsglobal.caliper.events.CaliperEvent;
import org.joda.time.DateTime;

import com.google.common.collect.Lists;
import com.google.gson.Gson;

public abstract class EventStoreRequestor {

	public abstract boolean send(CaliperEvent caliperEvent);

	public abstract boolean send(CaliperEntity caliperEntity);
	
	/**
	 * @param caliperEvent
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	protected StringEntity generatePayload(CaliperEvent caliperEvent)
			throws UnsupportedEncodingException {
		
		List<EventStoreEnvelope> listPayload = Lists.newArrayList();
		EventStoreEnvelope envelope = new EventStoreEnvelope();
		envelope.setType("caliperEvent");
		envelope.setTime(DateTime.now().toString());
		envelope.setData(caliperEvent);
		listPayload.add(envelope);
				
		Gson gson = new Gson();
		String jsonPayload = gson.toJson(listPayload);
//		LOG.debug("Sending: " + jsonPayload);
		StringEntity payLoad = new StringEntity(jsonPayload);
		payLoad.setContentType("application/json");
		
		return payLoad;
	}

}
