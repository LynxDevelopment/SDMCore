// $-- src/com/lynxspa/coac/normalizer/operationnew/mappings/GetEventDetailsFromNormalizableBean.fpmmap

package com.lynxspa.coac.normalizer.operationnew.mappings;

import java.util.*;
import com.lynxit.utils.beans.IteratorUtils;

import com.lynxit.fpm.admin.config.annotations.GeneratedType;
import com.lynxit.fpm.admin.config.ConfigurationException;
import com.lynxit.fpm.nodes.internal.MappingNode;

@GeneratedType(definitionFile = "src/com/lynxspa/coac/normalizer/operationnew/mappings/GetEventDetailsFromNormalizableBean.fpmmap")
public class GetEventDetailsFromNormalizableBeanMap
		extends
		MappingNode<com.lynxspa.coac.normalizer.adapters.NormalizableAdapter, com.lynxspa.sdm.entities.events.details.CAEventDetail> {

	// function libraries declarations

	@Override
	public void init() throws Exception {
		super.init();

		// initialize libs
	}

	public com.lynxspa.sdm.entities.events.details.CAEventDetail perform(
			com.lynxspa.coac.normalizer.adapters.NormalizableAdapter input)
			throws Exception {
		if (input == null)
			return null;

		/*
		 * declare the output message
		 */
		// $-- /mn:mapping-element/@outputBean
		com.lynxspa.sdm.entities.events.details.CAEventDetail output;

		/*
		 * transformation
		 */

		// $-- /mn:mapping-element/inputField
		output = in_eventDetail(input);

		return output;
	}

	private com.lynxspa.sdm.entities.events.details.CAEventDetail in_eventDetail(
			com.lynxspa.coac.normalizer.adapters.NormalizableAdapter input) {
		try {
			return input.getEventDetail();
		} catch (NullPointerException exc) {
			return null;
		}
	}

}