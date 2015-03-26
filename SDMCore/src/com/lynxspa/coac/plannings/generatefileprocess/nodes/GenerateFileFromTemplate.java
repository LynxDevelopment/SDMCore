package com.lynxspa.coac.plannings.generatefileprocess.nodes;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.lynxit.fpm.FileProcessingComponent;
import com.lynxit.fpm.admin.config.ConfigurationException;
import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.admin.config.annotations.ConfigParam;
import com.lynxit.fpm.nodes.OutputConnectable;
import com.lynxit.fpm.nodes.output.TextFileOutputNode;
import com.lynxit.fpm.nodes.support.InternalNodeSupport;
import com.lynxit.fpm.resources.Resource;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.coac.plannings.beans.PlanningBean;
import com.lynxspa.coac.plannings.logs.LogPlanningError;
import com.lynxspa.coac.plannings.logs.LogPlanningInfo;
import com.lynxspa.entities.application.logs.utils.LogUtils;
import com.lynxspa.exception.FPMException;
import com.lynxspa.exception.dict.BasicErrorDict;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateHashModel;

@SuppressWarnings("unchecked")
@NodeBeautifier(description="GenerateFileFromTemplate", category="Corporateactionscore", smallIcon = "../../../../../lynxit/fpm/nodes/icons/certificate_16.gif", largeIcon = "../../../../../lynxit/fpm/nodes/icons/certificate_32.gif")
public class GenerateFileFromTemplate <T extends PlanningBean> extends InternalNodeSupport <T> implements FileProcessingComponent {
	
	private final Logger logger = Logger.getLogger(GenerateFileFromTemplate.class);
	private boolean overwrite;
	private Template template_;
	private String templateName_;
	private String outputPathFile_;
	private Configuration configuration_;
	private String[] staticsNames_;
	private Class[] staticsClasses_;
	private File lastProcessedFile_;
	private OutputConnectable<? super T> nodeConnectedToOut_;
	protected String locale=null;
	protected String user=null;
	protected Resource<Session> resource=null;
	
	public GenerateFileFromTemplate (){
	}

	
	@Override
	public void init() throws Exception
	{
		super.init();

		configuration_ = new Configuration();
		FileTemplateLoader fileTemplateLoader = new FileTemplateLoader();
		ClassTemplateLoader classTemplateLoader = new ClassTemplateLoader(TextFileOutputNode.class, "/");
		MultiTemplateLoader templateLoader = new MultiTemplateLoader(new TemplateLoader[] { fileTemplateLoader, classTemplateLoader });
		configuration_.setTemplateLoader(templateLoader);


		if (!((staticsClasses_ == null && staticsNames_ == null) || ((staticsClasses_ != null) && (staticsNames_ != null))))
		{
			throw new ConfigurationException("Each static class must have a corresponding name");
		}
		if ((staticsClasses_ != null && staticsNames_ != null) && staticsClasses_.length != staticsNames_.length)
		{
			throw new ConfigurationException("Classes and names array length are different");
		}
	}
	
	/**
	 * @param message
	 * @throws Exception
	 * @see com.lynxit.fpm.nodes.OutputConnectable#process(IN)
	 */
	@Override
	public void processMessage(T message) throws Exception
	{
		SDMConfigManager manager=null;
		Session session=null;
		boolean appending = false;
		FileWriter out = null;
		try
		{
			session=this.resource.getCurrentInstance();
			manager = SDMConfigManager.getInstance();
			
			LogUtils.createLog(session,this.user,manager.getBundleName(),new Locale(this.locale), manager.getApplication(resource.getCurrentInstance()), LogPlanningInfo.PLANNINGS_FILE_FROM_TEMPLATE, new Object[]{outputPathFile_, templateName_},null);
			logger.debug("Generating File " + outputPathFile_ + " with template "+templateName_);
			
			lastProcessedFile_ = new File(outputPathFile_);
			if (lastProcessedFile_.exists() && !overwrite)
				appending = true;

			if (!lastProcessedFile_.getParentFile().exists())
				lastProcessedFile_.getParentFile().mkdirs();

			out = new FileWriter(outputPathFile_, !overwrite);

			template_ = configuration_.getTemplate(templateName_);

			try
			{
				Map<String, Object> context = new HashMap<String, Object>();

				if (staticsClasses_ != null && staticsNames_ != null)
				{
					BeansWrapper wrapper = BeansWrapper.getDefaultInstance();
					TemplateHashModel staticModels = wrapper.getStaticModels();
					for (int i = 0; i < staticsNames_.length; i++)
					{
						TemplateHashModel statics = (TemplateHashModel) staticModels.get(staticsClasses_[i].getName());
						context.put(staticsNames_[i], statics);
					}
				}

				context.put("process", getBusinessProcess());
				context.put("message", message);
				context.put("appending", new Boolean(appending));

				template_.process(context, out);
				out.flush();
				
				LogUtils.createLog(session,this.user,manager.getBundleName(),new Locale(this.locale), manager.getApplication(resource.getCurrentInstance()), LogPlanningInfo.PLANNINGS_FILE_FROM_TEMPLATE_GENERATED, new Object[]{outputPathFile_},null);
				logger.debug("File " + outputPathFile_ + " generated correctly");
				getNodeConnectedToOut().process(message);
			}
			finally
			{
				out.close();
			}
		}
		catch (Exception exc)
		{
			LogUtils.createLog(session,this.user,manager.getBundleName(),new Locale(this.locale), 
					manager.getApplication(session), LogPlanningError.PLANNINGS_FILE_FROM_TEMPLATE_ERROR, 
					new Object[]{outputPathFile_,templateName_},exc);
			logger.error("Error generating file " + outputPathFile_ + ": "+exc);
			throw new FPMException(BasicErrorDict.UNEXPECTED_ERROR,exc);
		}
	}


	public void connectNodeToOut(OutputConnectable<? super T> node){
        nodeConnectedToOut_ = node;
    }

	public OutputConnectable<? super T> getNodeConnectedToOut(){
        return nodeConnectedToOut_;
    }
	
	/**
	 * @return the append.
	 */
	@ConfigParam(description = "the writing mode", required = false, defaultValue = "false", dynamic = true)
	public boolean isOverwrite()
	{
		return overwrite;
	}

	/**
	 * @param overwrite
	 *            the overwrite to set.
	 */
	public void setOverwrite(boolean _overwrite)
	{
		overwrite = _overwrite;
	}

	/**
	 * @return the template.
	 */
	@ConfigParam(description = "The name of the template to use", required = true, dynamic = true)
	public String getTemplate()
	{
		return templateName_;
	}

	/**
	 * @param template
	 *            the template to set.
	 */
	public void setTemplate(String template)
	{
		templateName_ = template;
	}

	/**
	 * @return the outputFile.
	 */
	@ConfigParam(description = "The output file", required = true, dynamic = true)
	public String getOutputPathFile()
	{
		return outputPathFile_;
	}

	/**
	 * @param outputFile
	 *            the outputFile to set.
	 */
	public void setOutputPathFile(String outputPathFile)
	{
		outputPathFile_ = outputPathFile;
	}

	@ConfigParam(required = false)
	public String[] getStaticsNames()
	{
		return staticsNames_;
	}

	public void setStaticsNames(String[] staticsNames)
	{
		staticsNames_ = staticsNames;
	}

	@ConfigParam(required = false)
	public Class[] getStaticsClasses()
	{
		return staticsClasses_;
	}

	public void setStaticsClasses(Class[] staticsClasses)
	{
		staticsClasses_ = staticsClasses;
	}

	public File getLastProcessedFile()
	{
		return lastProcessedFile_;
	}


	@ConfigParam(required=true,description="Locale", group="config")
	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	@ConfigParam(required=true,description="User", group="config")
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@ConfigParam(required=true,description="Resource", group="config")
	public Resource<Session> getResource() {
		return resource;
	}

	public void setResource(Resource<Session> resource) {
		this.resource = resource;
	}
	
}
