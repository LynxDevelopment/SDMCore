// $-- src/com/lynxspa/coac/plannings/maps/mapPlannings.fpmmap

package com.lynxspa.coac.plannings.maps;

import java.util.*;
import com.lynxit.utils.beans.IteratorUtils;

import com.lynxit.fpm.admin.config.annotations.GeneratedType;
import com.lynxit.fpm.admin.config.ConfigurationException;
import com.lynxit.fpm.nodes.internal.MappingNode;

@GeneratedType(definitionFile = "src/com/lynxspa/coac/plannings/maps/mapPlannings.fpmmap")
public class MapPlanningsMap
		extends
		MappingNode<com.lynxspa.entities.plannings.SPPlanningProcess, com.lynxspa.coac.plannings.beans.PlanningBean> {

	// function libraries declarations
	com.lynxspa.coac.plannings.maps.PlanningsFunctions planningsFunctions;
	com.lynxspa.fpm.functions.ObjectFunctions objectFunctions;
	com.lynxit.fpm.functionlibraries.StringFunctions stringFunctions;

	@Override
	public void init() throws Exception {
		super.init();

		// initialize libs
		planningsFunctions = new com.lynxspa.coac.plannings.maps.PlanningsFunctions();
		planningsFunctions.setNode(this);
		objectFunctions = new com.lynxspa.fpm.functions.ObjectFunctions();
		objectFunctions.setNode(this);
		stringFunctions = new com.lynxit.fpm.functionlibraries.StringFunctions();
		stringFunctions.setNode(this);
	}

	public com.lynxspa.coac.plannings.beans.PlanningBean perform(
			com.lynxspa.entities.plannings.SPPlanningProcess input)
			throws Exception {
		if (input == null)
			return null;

		/*
		 * declare the output message
		 */
		// $-- /mn:mapping-element/@outputBean
		com.lynxspa.coac.plannings.beans.PlanningBean output;

		/*
		 * transformation
		 */

		output = new com.lynxspa.coac.plannings.beans.PlanningBean();

		//setting field FTPBinary

		// $-- /mn:mapping-element/field[1]
		java.lang.Boolean outputFTPBinary;

		// $-- /mn:mapping-element/field[1]/function
		outputFTPBinary =
		// $-- /mn:mapping-element/field[1]/function
		planningsFunctions.isFTPBinary(input);
		output.setFTPBinary(outputFTPBinary);

		//setting field FTPExport

		// $-- /mn:mapping-element/field[2]
		java.lang.Boolean outputFTPExport;

		// $-- /mn:mapping-element/field[2]/function
		outputFTPExport =
		// $-- /mn:mapping-element/field[2]/function
		planningsFunctions.isFTPExportProcess(input);
		output.setFTPExport(outputFTPExport);

		//setting field FTPImport

		// $-- /mn:mapping-element/field[3]
		java.lang.Boolean outputFTPImport;

		// $-- /mn:mapping-element/field[3]/function
		outputFTPImport =
		// $-- /mn:mapping-element/field[3]/function
		planningsFunctions.isFTPImportProcess(input);
		output.setFTPImport(outputFTPImport);

		//setting field FTPPassive

		// $-- /mn:mapping-element/field[4]
		java.lang.Boolean outputFTPPassive;

		// $-- /mn:mapping-element/field[4]/function
		outputFTPPassive =
		// $-- /mn:mapping-element/field[4]/function
		planningsFunctions.isFTPPassive(input);
		output.setFTPPassive(outputFTPPassive);

		//setting field cronExpression

		// $-- /mn:mapping-element/field[5]
		java.lang.String outputCronExpression;

		// $-- /mn:mapping-element/field[5]/inputField
		outputCronExpression = in_cron(input);
		output.setCronExpression(outputCronExpression);

		//setting field ftpFile

		// $-- /mn:mapping-element/field[6]
		java.lang.String outputFtpFile;

		// $-- /mn:mapping-element/field[6]/function
		outputFtpFile =
		// $-- /mn:mapping-element/field[6]/function
		objectFunctions.stringValueOf(
		// $-- /mn:mapping-element/field[6]/function/function
				objectFunctions.condition(
				// $-- /mn:mapping-element/field[6]/function/function/function[1]
						planningsFunctions.isFTPProcess(input),
						// $-- /mn:mapping-element/field[6]/function/function/function[2]
						planningsFunctions.getFTPFile(input), null));
		output.setFtpFile(outputFtpFile);

		//setting field ftpPassword

		// $-- /mn:mapping-element/field[7]
		java.lang.String outputFtpPassword;

		// $-- /mn:mapping-element/field[7]/function
		outputFtpPassword =
		// $-- /mn:mapping-element/field[7]/function
		objectFunctions.stringValueOf(
		// $-- /mn:mapping-element/field[7]/function/function
				objectFunctions.condition(
				// $-- /mn:mapping-element/field[7]/function/function/function[1]
						planningsFunctions.isFTPProcess(input),
						// $-- /mn:mapping-element/field[7]/function/function/function[2]
						planningsFunctions.getFTPPassword(input), null));
		output.setFtpPassword(outputFtpPassword);

		//setting field ftpPath

		// $-- /mn:mapping-element/field[8]
		java.lang.String outputFtpPath;

		// $-- /mn:mapping-element/field[8]/function
		outputFtpPath =
		// $-- /mn:mapping-element/field[8]/function
		objectFunctions.stringValueOf(
		// $-- /mn:mapping-element/field[8]/function/function
				objectFunctions.condition(
				// $-- /mn:mapping-element/field[8]/function/function/function[1]
						planningsFunctions.isFTPProcess(input),
						// $-- /mn:mapping-element/field[8]/function/function/function[2]
						planningsFunctions.getFTPPath(input), null));
		output.setFtpPath(outputFtpPath);

		//setting field ftpPort

		// $-- /mn:mapping-element/field[9]
		java.lang.String outputFtpPort;

		// $-- /mn:mapping-element/field[9]/function
		outputFtpPort =
		// $-- /mn:mapping-element/field[9]/function
		objectFunctions.stringValueOf(
		// $-- /mn:mapping-element/field[9]/function/function
				objectFunctions.condition(
				// $-- /mn:mapping-element/field[9]/function/function/function[1]
						planningsFunctions.isFTPProcess(input),
						// $-- /mn:mapping-element/field[9]/function/function/function[2]
						planningsFunctions.getFTPPort(input), null));
		output.setFtpPort(outputFtpPort);

		//setting field ftpProxy

		// $-- /mn:mapping-element/field[10]
		java.lang.String outputFtpProxy;

		// $-- /mn:mapping-element/field[10]/function
		outputFtpProxy =
		// $-- /mn:mapping-element/field[10]/function
		objectFunctions.stringValueOf(
		// $-- /mn:mapping-element/field[10]/function/function
				objectFunctions.condition(
				// $-- /mn:mapping-element/field[10]/function/function/function[1]
						planningsFunctions.isFTPProcess(input),
						// $-- /mn:mapping-element/field[10]/function/function/function[2]
						planningsFunctions.getFTPProxy(input), null));
		output.setFtpProxy(outputFtpProxy);

		//setting field ftpProxyPassword

		// $-- /mn:mapping-element/field[11]
		java.lang.String outputFtpProxyPassword;

		// $-- /mn:mapping-element/field[11]/function
		outputFtpProxyPassword =
		// $-- /mn:mapping-element/field[11]/function
		objectFunctions.stringValueOf(
		// $-- /mn:mapping-element/field[11]/function/function
				objectFunctions.condition(
				// $-- /mn:mapping-element/field[11]/function/function/function[1]
						planningsFunctions.isFTPProcess(input),
						// $-- /mn:mapping-element/field[11]/function/function/function[2]
						planningsFunctions.getFTPProxyPassword(input), null));
		output.setFtpProxyPassword(outputFtpProxyPassword);

		//setting field ftpProxyUser

		// $-- /mn:mapping-element/field[12]
		java.lang.String outputFtpProxyUser;

		// $-- /mn:mapping-element/field[12]/function
		outputFtpProxyUser =
		// $-- /mn:mapping-element/field[12]/function
		objectFunctions.stringValueOf(
		// $-- /mn:mapping-element/field[12]/function/function
				objectFunctions.condition(
				// $-- /mn:mapping-element/field[12]/function/function/function[1]
						planningsFunctions.isFTPProcess(input),
						// $-- /mn:mapping-element/field[12]/function/function/function[2]
						planningsFunctions.getFTPProxyUser(input), null));
		output.setFtpProxyUser(outputFtpProxyUser);

		//setting field ftpServer

		// $-- /mn:mapping-element/field[13]
		java.lang.String outputFtpServer;

		// $-- /mn:mapping-element/field[13]/function
		outputFtpServer =
		// $-- /mn:mapping-element/field[13]/function
		objectFunctions.stringValueOf(
		// $-- /mn:mapping-element/field[13]/function/function
				objectFunctions.condition(
				// $-- /mn:mapping-element/field[13]/function/function/function[1]
						planningsFunctions.isFTPProcess(input),
						// $-- /mn:mapping-element/field[13]/function/function/function[2]
						planningsFunctions.getFTPServer(input), null));
		output.setFtpServer(outputFtpServer);

		//setting field ftpUser

		// $-- /mn:mapping-element/field[14]
		java.lang.String outputFtpUser;

		// $-- /mn:mapping-element/field[14]/function
		outputFtpUser =
		// $-- /mn:mapping-element/field[14]/function
		objectFunctions.stringValueOf(
		// $-- /mn:mapping-element/field[14]/function/function
				objectFunctions.condition(
				// $-- /mn:mapping-element/field[14]/function/function/function[1]
						planningsFunctions.isFTPProcess(input),
						// $-- /mn:mapping-element/field[14]/function/function/function[2]
						planningsFunctions.getFTPUser(input), null));
		output.setFtpUser(outputFtpUser);

		//setting field id

		// $-- /mn:mapping-element/field[15]
		java.lang.Long outputId;

		// $-- /mn:mapping-element/field[15]/inputField
		outputId = in_id(input);
		output.setId(outputId);

		//setting field objectTemplate

		// $-- /mn:mapping-element/field[16]
		java.lang.Byte[] outputObjectTemplate;

		// $-- /mn:mapping-element/field[16]/inputField
		outputObjectTemplate = in_objectTemplate(input);
		output.setObjectTemplate(outputObjectTemplate);

		//setting field outputFile

		// $-- /mn:mapping-element/field[17]
		java.lang.String outputOutputFile;

		// $-- /mn:mapping-element/field[17]/function
		outputOutputFile =
		// $-- /mn:mapping-element/field[17]/function
		stringFunctions.concat(
		// $-- /mn:mapping-element/field[17]/function/function[1]
				planningsFunctions.getPath(input),
				// $-- /mn:mapping-element/field[17]/function/function[2]
				stringFunctions.concat(
				// $-- /mn:mapping-element/field[17]/function/function[2]/function[1]
						planningsFunctions.getFileName(input),
						// $-- /mn:mapping-element/field[17]/function/function[2]/function[2]
						planningsFunctions.getExtension(input)));
		output.setOutputFile(outputOutputFile);

		//setting field outputFileName

		// $-- /mn:mapping-element/field[18]
		java.lang.String outputOutputFileName;

		// $-- /mn:mapping-element/field[18]/function
		outputOutputFileName =
		// $-- /mn:mapping-element/field[18]/function
		stringFunctions.concat(
		// $-- /mn:mapping-element/field[18]/function/function[1]
				planningsFunctions.getFileName(input),
				// $-- /mn:mapping-element/field[18]/function/function[2]
				planningsFunctions.getExtension(input));
		output.setOutputFileName(outputOutputFileName);

		//setting field overWrite

		// $-- /mn:mapping-element/field[19]
		java.lang.Boolean outputOverWrite;

		// $-- /mn:mapping-element/field[19]/function
		outputOverWrite =
		// $-- /mn:mapping-element/field[19]/function
		planningsFunctions.isOverwrite(input);
		output.setOverWrite(outputOverWrite);

		return output;
	}

	private java.lang.Byte[] in_objectTemplate(
			com.lynxspa.entities.plannings.SPPlanningProcess input) {
		try {
			return input.getObjectTemplate();
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

	private java.lang.Long in_id(
			com.lynxspa.entities.plannings.SPPlanningProcess input) {
		try {
			return input.getId();
		} catch (NullPointerException exc) {
			return null;
		}
	}

}