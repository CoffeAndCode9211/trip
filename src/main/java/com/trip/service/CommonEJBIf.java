package com.trip.service;

import com.trip.dto.ActivityLogTO;
import com.trip.model.OptionConfig;

public interface CommonEJBIf {

	public void activityLogIt(ActivityLogTO activityLogTO);
	
	public OptionConfig getOptionConfig(String optionkey);
}
