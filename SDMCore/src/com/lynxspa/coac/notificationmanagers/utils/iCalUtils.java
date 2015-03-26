package com.lynxspa.coac.notificationmanagers.utils;

import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.Parameter;
import net.fortuna.ical4j.model.ParameterList;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyList;

public class iCalUtils {
	 /**
	   * @param cal
	   * @param comp
	   */
	  public static void addComponent(final Calendar cal, final Component comp) {
	    cal.getComponents().add(comp);
	  }

	  /**
	   * @param comp
	   * @param val
	   */
	  public static void addProperty(final Component comp, final Property val) {
	    PropertyList props =  comp.getProperties();

	    props.add(val);
	  }

	  /**
	   * @param prop
	   * @param val
	   */
	  public static void addParameter(final Property prop, final Parameter val) {
	    ParameterList parl =  prop.getParameters();

	    parl.add(val);
	  }

	  /**
	   * @param prop
	   * @param name
	   * @return Parameter
	   */
	  public static Parameter getParameter(final Property prop, final String name) {
	    ParameterList parl =  prop.getParameters();

	    if (parl == null) {
	      return null;
	    }

	    return parl.getParameter(name);
	  }

	  /**
	   * @param prop
	   * @param name
	   * @return Parameter value
	   */
	  public static String getParameterVal(final Property prop, final String name) {
	    ParameterList parl =  prop.getParameters();

	    if (parl == null) {
	      return null;
	    }

	    Parameter par = parl.getParameter(name);

	    if (par == null) {
	      return null;
	    }

	    return par.getValue();
	  }

	  /**
	   * @param comp
	   * @param name
	   * @return Property
	   */
	  public static Property getProperty(final Component comp, final String name) {
	    PropertyList props =  comp.getProperties();

	    return props.getProperty(name);
	  }

	  /**
	   * @param comp
	   * @param name
	   * @return PropertyList
	   */
	  public static PropertyList getProperties(final Component comp, final String name) {
	    PropertyList props =  comp.getProperties();

	    props = props.getProperties(name);
	    if ((props != null) && (props.size() == 0)) {
	      return null;
	    }

	    return props;
	  }
}
