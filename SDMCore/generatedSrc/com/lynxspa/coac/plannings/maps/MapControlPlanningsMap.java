// $-- src/com/lynxspa/coac/plannings/maps/mapControlPlannings.fpmmap

package com.lynxspa.coac.plannings.maps;

import java.util.*;
import com.lynxit.utils.beans.IteratorUtils;

import com.lynxit.fpm.admin.config.annotations.GeneratedType;
import com.lynxit.fpm.admin.config.ConfigurationException;
import com.lynxit.fpm.nodes.internal.MappingNode;

@GeneratedType(definitionFile = "src/com/lynxspa/coac/plannings/maps/mapControlPlannings.fpmmap")
public class MapControlPlanningsMap
		extends
		MappingNode<com.lynxspa.entities.plannings.SPPlanningProcess, com.lynxspa.coac.plannings.beans.ControlPlanningsBean> {

	// function libraries declarations
	com.lynxit.fpm.functionlibraries.StringFunctions stringFunctions;
	com.lynxspa.coac.plannings.maps.PlanningsFunctions planningsFunctions;

	@Override
	public void init() throws Exception {
		super.init();

		// initialize libs
		stringFunctions = new com.lynxit.fpm.functionlibraries.StringFunctions();
		stringFunctions.setNode(this);
		planningsFunctions = new com.lynxspa.coac.plannings.maps.PlanningsFunctions();
		planningsFunctions.setNode(this);
	}

	public com.lynxspa.coac.plannings.beans.ControlPlanningsBean perform(
			com.lynxspa.entities.plannings.SPPlanningProcess input)
			throws Exception {
		if (input == null)
			return null;

		/*
		 * declare the output message
		 */
		// $-- /mn:mapping-element/@outputBean
		com.lynxspa.coac.plannings.beans.ControlPlanningsBean output;

		/*
		 * transformation
		 */

		output = new com.lynxspa.coac.plannings.beans.ControlPlanningsBean();

		//setting field FTPExport

		// $-- /mn:mapping-element/field[1]
		java.lang.Boolean outputFTPExport;

		// $-- /mn:mapping-element/field[1]/function
		outputFTPExport =
		// $-- /mn:mapping-element/field[1]/function
		planningsFunctions.isFTPExportProcess(input);
		output.setFTPExport(outputFTPExport);

		//setting field FTPImport

		// $-- /mn:mapping-element/field[2]
		java.lang.Boolean outputFTPImport;

		// $-- /mn:mapping-element/field[2]/function
		outputFTPImport =
		// $-- /mn:mapping-element/field[2]/function
		planningsFunctions.isFTPImportProcess(input);
		output.setFTPImport(outputFTPImport);

		//setting field cronn

		// $-- /mn:mapping-element/field[3]
		java.lang.String outputCronn;

		// $-- /mn:mapping-element/field[3]/inputField
		outputCronn = in_cron(input);
		output.setCronn(outputCronn);

		//setting field executeShell

		// $-- /mn:mapping-element/field[4]
		java.lang.Boolean outputExecuteShell;

		// $-- /mn:mapping-element/field[4]/function
		outputExecuteShell =
		// $-- /mn:mapping-element/field[4]/function
		planningsFunctions.isExecuteShellProcess(input);
		output.setExecuteShell(outputExecuteShell);

		//setting field fileName

		// $-- /mn:mapping-element/field[5]
		java.lang.String outputFileName;

		// $-- /mn:mapping-element/field[5]/function
		outputFileName =
		// $-- /mn:mapping-element/field[5]/function
		stringFunctions.concat(
		// $-- /mn:mapping-element/field[5]/function/function[1]
				planningsFunctions.getPath(input),
				// $-- /mn:mapping-element/field[5]/function/function[2]
				stringFunctions.concat(
				// $-- /mn:mapping-element/field[5]/function/function[2]/function[1]
						planningsFunctions.getFileName(input),
						// $-- /mn:mapping-element/field[5]/function/function[2]/function[2]
						planningsFunctions.getExtension(input)));
		output.setFileName(outputFileName);

		//setting field id

		// $-- /mn:mapping-element/field[6]
		java.lang.Long outputId;

		// $-- /mn:mapping-element/field[6]/inputField
		outputId = in_id(input);
		output.setId(outputId);

		//setting field lastExecutionDate

		// $-- /mn:mapping-element/field[7]
		java.util.Date outputLastExecutionDate;

		// $-- /mn:mapping-element/field[7]/inputField
		outputLastExecutionDate = in_executionDate(input);
		output.setLastExecutionDate(outputLastExecutionDate);

		//setting field manual

		// $-- /mn:mapping-element/field[8]
		java.lang.Boolean outputManual;

		// $-- /mn:mapping-element/field[8]/inputField
		outputManual = in_manual(input);
		output.setManual(outputManual);

		//setting field name

		// $-- /mn:mapping-element/field[9]
		java.lang.String outputName;

		// $-- /mn:mapping-element/field[9]/inputField
		outputName = in_name(input);
		output.setName(outputName);

		return output;
	}

	private java.lang.String in_name(
			com.lynxspa.entities.plannings.SPPlanningProcess input) {
		try {
			return input.getName();
		} catch (NullPointerException exc) {
			return null;
		}
	}

	private java.lang.Boolean in_manual(
			com.lynxspa.entities.plannings.SPPlanningProcess input) {
		try {
			return input.isManual();
		} catch (NullPointerException exc) {
			return null;
		}
	}

	private java.lang.Long in_id(
			com.lynxspa.entities.plannings.SPPlanningProcess input) {
		try {
			return input.getId();
		} catch (NullPointerException exc) {
			return null;
		}
	}

	private java.lang.String in_cron(
			com.lynxspa.entities.plannings.SPPlanningProcess input) {
		try {
			return input.getCron();
		} catch (NullPointerException exc) {
			return null;
		}
	}

	private java.util.Date in_executionDate(
			com.lynxspa.entities.plannings.SPPlanningProcess input) {
		try {
			return input.getExecutionDate();
		} catch (NullPointerException exc) {
			return null;
		}
	}

}