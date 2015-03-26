// $-- src/com/lynxspa/coac/normalizer/operationnew/mappings/GetEventDetailsExtendedFromNormalizableBean.fpmmap

package com.lynxspa.coac.normalizer.operationnew.mappings;

import java.util.*;
import com.lynxit.utils.beans.IteratorUtils;

import com.lynxit.fpm.admin.config.annotations.GeneratedType;
import com.lynxit.fpm.admin.config.ConfigurationException;
import com.lynxit.fpm.nodes.internal.MappingNode;

@GeneratedType(definitionFile = "src/com/lynxspa/coac/normalizer/operationnew/mappings/GetEventDetailsExtendedFromNormalizableBean.fpmmap")
public class GetEventDetailsExtendedFromNormalizableBeanMap
		extends
		MappingNode<com.lynxspa.coac.normalizer.adapters.NormalizableAdapter, java.util.List<java.lang.Object>> {

	// function libraries declarations

	@Override
	public void init() throws Exception {
		super.init();

		// initialize libs
	}

	public java.util.List<java.lang.Object> perform(
			com.lynxspa.coac.normalizer.adapters.NormalizableAdapter input)
			throws Exception {
		if (input == null)
			return null;

		/*
		 * declare the output message
		 */
		// $-- /mn:mapping-element/@outputBean
		java.util.List<java.lang.Object> output;

		/*
		 * transformation
		 */

		// $-- /mn:mapping-element/inputField
		output = in_eventDetailExtended(input);

		return output;
	}

	private java.util.List in_eventDetailExtended(
			com.lynxspa.coac.normalizer.adapters.NormalizableAdapter input) {
		try {
			return input.getEventDetailExtended();
		} catch (NullPointerException exc) {
			return null;
		}
	}

}