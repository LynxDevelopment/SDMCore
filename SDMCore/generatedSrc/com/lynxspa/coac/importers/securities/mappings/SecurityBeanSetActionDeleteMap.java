// $-- src/com/lynxspa/coac/importers/securities/mappings/SecurityBeanSetActionDelete.fpmmap

package com.lynxspa.coac.importers.securities.mappings;

import java.util.*;
import com.lynxit.utils.beans.IteratorUtils;

import com.lynxit.fpm.admin.config.annotations.GeneratedType;
import com.lynxit.fpm.admin.config.ConfigurationException;
import com.lynxit.fpm.nodes.internal.MappingNode;

@GeneratedType(definitionFile = "src/com/lynxspa/coac/importers/securities/mappings/SecurityBeanSetActionDelete.fpmmap")
public class SecurityBeanSetActionDeleteMap
		extends
		MappingNode<com.lynxspa.sdm.importers.securities.beans.SecurityBean, com.lynxspa.sdm.importers.securities.beans.SecurityBean> {

	// function libraries declarations

	@Override
	public void init() throws Exception {
		super.init();

		// initialize libs
	}

	public com.lynxspa.sdm.importers.securities.beans.SecurityBean perform(
			com.lynxspa.sdm.importers.securities.beans.SecurityBean input)
			throws Exception {
		if (input == null)
			return null;

		/*
		 * declare the output message
		 */
		// $-- /mn:mapping-element/@outputBean
		com.lynxspa.sdm.importers.securities.beans.SecurityBean output;

		/*
		 * transformation
		 */

		// $-- /mn:mapping-element/inputField
		output = input;

		//setting field action

		// $-- /mn:mapping-element/field
		com.lynxspa.sdm.importers.securities.beans.SecurityBean.ActionsToDo outputAction;

		// $-- /mn:mapping-element/field/constant
		outputAction = com.lynxspa.sdm.importers.securities.beans.SecurityBean.ActionsToDo.DELETE;
		output.setAction(outputAction);

		return output;
	}

}