// $-- src/com/lynxspa/coac/normalizer/mappings/CAEventMessageToMessageNormalizeBean.fpmmap

package com.lynxspa.coac.normalizer.mappings;

import java.util.*;
import com.lynxit.utils.beans.IteratorUtils;

import com.lynxit.fpm.admin.config.annotations.GeneratedType;
import com.lynxit.fpm.admin.config.ConfigurationException;
import com.lynxit.fpm.nodes.internal.MappingNode;

@GeneratedType(definitionFile = "src/com/lynxspa/coac/normalizer/mappings/CAEventMessageToMessageNormalizeBean.fpmmap")
public class CAEventMessageToMessageNormalizeBeanMap
		extends
		MappingNode<com.lynxspa.sdm.entities.events.messages.CAEventMessage, com.lynxspa.coac.normalizer.beans.MessageNormalizeBean> {

	// function libraries declarations

	@Override
	public void init() throws Exception {
		super.init();

		// initialize libs
	}

	public com.lynxspa.coac.normalizer.beans.MessageNormalizeBean perform(
			com.lynxspa.sdm.entities.events.messages.CAEventMessage input)
			throws Exception {
		if (input == null)
			return null;

		/*
		 * declare the output message
		 */
		// $-- /mn:mapping-element/@outputBean
		com.lynxspa.coac.normalizer.beans.MessageNormalizeBean output;

		/*
		 * transformation
		 */

		output = new com.lynxspa.coac.normalizer.beans.MessageNormalizeBean();

		//setting field message

		// $-- /mn:mapping-element/field
		com.lynxspa.sdm.entities.events.messages.CAEventMessage outputMessage;

		// $-- /mn:mapping-element/field/inputField
		outputMessage = input;
		output.setMessage(outputMessage);

		return output;
	}

}