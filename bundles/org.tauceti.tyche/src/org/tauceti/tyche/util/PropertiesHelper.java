/*
 * Copyright (c) 2025 TauCeti.org
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * Contributors:
 *     Yang Yang - initial API and implementation
 */
package org.tauceti.tyche.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.osgi.framework.BundleContext;

/**
 * 
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class PropertiesHelper {

	public static PropertiesHelper INSTANCE = new PropertiesHelper();

	public static SimpleDateFormat SIMPLE_DATE_FORMAT1 = new SimpleDateFormat();

	/**
	 * 
	 * @param bundleContext
	 * @param propName
	 * @return
	 */
	public boolean isEnabled(BundleContext bundleContext, String propName) {
		if (propName != null) {
			if ((bundleContext != null && "true".equals(bundleContext.getProperty(propName))) || "true".equals(System.getProperty(propName)) || "true".equals(System.getenv(propName))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param bundleContext
	 * @param propName
	 * @return
	 */
	public boolean hasSystemProperty(BundleContext bundleContext, String propName) {
		boolean hasRawValue = false;
		if (propName != null) {
			if (hasRawValue == false) {
				String value = bundleContext.getProperty(propName);
				if (value != null) {
					hasRawValue = true;
				}
			}
			if (hasRawValue == false) {
				String value = System.getProperty(propName);
				if (value != null) {
					hasRawValue = true;
				}
			}
			if (hasRawValue == false) {
				String value = System.getenv(propName);
				if (value != null) {
					hasRawValue = true;
				}
			}
		}
		return hasRawValue;
	}

	/**
	 * 
	 * @param bundleContext
	 * @param propName
	 * @return
	 */
	public String getSystemPropertyRawValue(BundleContext bundleContext, String propName) {
		String rawValue = null;
		if (propName != null) {
			if (bundleContext != null) {
				String value = bundleContext.getProperty(propName);
				if (value != null) {
					rawValue = value;
				}
			}
			if (rawValue == null) {
				String value = System.getProperty(propName);
				if (value != null) {
					rawValue = value;
				}
			}
			if (rawValue == null) {
				String value = System.getenv(propName);
				if (value != null) {
					rawValue = value;
				}
			}
		}
		return rawValue;
	}

	/**
	 * 
	 * @param bundleContext
	 * @param propName
	 * @param defaultValue
	 * @return
	 */
	public String getSystemPropertyStringValue(BundleContext bundleContext, String propName, String defaultValue) {
		String rawValue = getSystemPropertyRawValue(bundleContext, propName);
		if (rawValue == null) {
			if (defaultValue != null) {
				rawValue = defaultValue;
			}
		}
		return rawValue;
	}

	/**
	 * 
	 * @param bundleContext
	 * @param propName
	 * @param defaultValue
	 * @return
	 */
	public Boolean getSystemPropertyBooleanValue(BundleContext bundleContext, String propName, Boolean defaultValue) {
		Boolean value = null;
		String rawValue = getSystemPropertyRawValue(bundleContext, propName);
		if (rawValue != null) {
			try {
				value = Boolean.valueOf(rawValue);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (value == null) {
			if (defaultValue != null) {
				value = defaultValue;
			}
		}
		return value;
	}

	/**
	 * 
	 * @param bundleContext
	 * @param propName
	 * @param defaultValue
	 * @return
	 */
	public Integer getSystemPropertyIntegerValue(BundleContext bundleContext, String propName, Integer defaultValue) {
		Integer value = null;
		String rawValue = getSystemPropertyRawValue(bundleContext, propName);
		if (rawValue != null) {
			try {
				value = Integer.valueOf(rawValue);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (value == null) {
			if (defaultValue != null) {
				value = defaultValue;
			}
		}
		return value;
	}

	/**
	 * 
	 * @param bundleContext
	 * @param propName
	 * @param defaultValue
	 * @return
	 */
	public Long getSystemPropertyLongValue(BundleContext bundleContext, String propName, Long defaultValue) {
		Long value = null;
		String rawValue = getSystemPropertyRawValue(bundleContext, propName);
		if (rawValue != null) {
			try {
				value = Long.valueOf(rawValue);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (value == null) {
			if (defaultValue != null) {
				value = defaultValue;
			}
		}
		return value;
	}

	/**
	 * 
	 * @param bundleContext
	 * @param propName
	 * @param defaultValue
	 * @return
	 */
	public Float getSystemPropertyFloatValue(BundleContext bundleContext, String propName, Float defaultValue) {
		Float value = null;
		String rawValue = getSystemPropertyRawValue(bundleContext, propName);
		if (rawValue != null) {
			try {
				value = Float.valueOf(rawValue);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (value == null) {
			if (defaultValue != null) {
				value = defaultValue;
			}
		}
		return value;
	}

	/**
	 * 
	 * @param bundleContext
	 * @param propName
	 * @param defaultValue
	 * @return
	 */
	public Double getSystemPropertyDoubleValue(BundleContext bundleContext, String propName, Double defaultValue) {
		Double value = null;
		String rawValue = getSystemPropertyRawValue(bundleContext, propName);
		if (rawValue != null) {
			try {
				value = Double.valueOf(rawValue);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (value == null) {
			if (defaultValue != null) {
				value = defaultValue;
			}
		}
		return value;
	}

	/**
	 * 
	 * @param bundleContext
	 * @param propName
	 * @param dateFormat
	 * @param defaultValue
	 * @return
	 */
	public Date getSystemPropertyDateValue(BundleContext bundleContext, String propName, DateFormat dateFormat, Date defaultValue) {
		Date value = null;
		String rawValue = getSystemPropertyRawValue(bundleContext, propName);
		if (rawValue != null) {
			try {
				if (dateFormat == null) {
					dateFormat = SIMPLE_DATE_FORMAT1;
				}
				value = dateFormat.parse(rawValue);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (value == null) {
			if (defaultValue != null) {
				value = defaultValue;
			}
		}
		return value;
	}

	/**
	 * 
	 * @param properties
	 * @param propName
	 * @param defaultValue
	 * @return
	 */
	public String getStringValue(Map<String, Object> properties, String propName, String defaultValue) {
		String stringValue = null;
		if (properties != null) {
			Object valueObj = properties.get(propName);
			if (valueObj != null) {
				try {
					stringValue = valueObj.toString();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		if (stringValue == null && defaultValue != null) {
			stringValue = defaultValue;
		}
		return stringValue;
	}

	/**
	 * 
	 * @param properties
	 * @param propName
	 * @param defaultValue
	 * @return
	 */
	public boolean getBooleanValue(Map<String, Object> properties, String propName, Boolean defaultValue) {
		Boolean booleanValue = null;
		if (properties != null) {
			Object valueObj = properties.get(propName);
			if (valueObj != null) {
				if (valueObj instanceof Boolean) {
					booleanValue = (Boolean) valueObj;
				} else {
					try {
						booleanValue = Boolean.valueOf(valueObj.toString());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		if (booleanValue == null && defaultValue != null) {
			booleanValue = defaultValue;
		}
		if (booleanValue == null) {
			return false;
		}
		return booleanValue;
	}

	/**
	 * 
	 * @param properties
	 * @param propName
	 * @param defaultValue
	 * @return
	 */
	public int getIntValue(Map<String, Object> properties, String propName, Integer defaultValue) {
		Integer intValue = null;
		if (properties != null) {
			Object valueObj = properties.get(propName);
			if (valueObj != null) {
				if (valueObj instanceof Integer) {
					intValue = (Integer) valueObj;
				} else {
					try {
						intValue = Integer.valueOf(valueObj.toString());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		if (intValue == null && defaultValue != null) {
			intValue = defaultValue;
		}
		if (intValue == null) {
			return 0;
		}
		return intValue;
	}

	/**
	 * 
	 * @param properties
	 * @param propName
	 * @param defaultValue
	 * @return
	 */
	public long getLongValue(Map<String, Object> properties, String propName, Long defaultValue) {
		Long longValue = null;
		if (properties != null) {
			Object valueObj = properties.get(propName);
			if (valueObj != null) {
				if (valueObj instanceof Long) {
					longValue = (Long) valueObj;
				} else {
					try {
						longValue = Long.valueOf(valueObj.toString());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		if (longValue == null && defaultValue != null) {
			longValue = defaultValue;
		}
		if (longValue == null) {
			return 0;
		}
		return longValue;
	}

	/**
	 * 
	 * @param properties
	 * @param propName
	 * @param defaultValue
	 * @return
	 */
	public double getDoubleValue(Map<String, Object> properties, String propName, Double defaultValue) {
		Double doubleValue = null;
		if (properties != null) {
			Object valueObj = properties.get(propName);
			if (valueObj != null) {
				if (valueObj instanceof Double) {
					doubleValue = (Double) valueObj;
				} else {
					try {
						doubleValue = Double.valueOf(valueObj.toString());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		if (doubleValue == null && defaultValue != null) {
			doubleValue = defaultValue;
		}
		if (doubleValue == null) {
			return 0;
		}
		return doubleValue;
	}

	/**
	 * 
	 * @param properties
	 * @param propName
	 * @param dateFormat
	 * @param defaultValue
	 * @return
	 */
	public Date getDateValue(Map<String, Object> properties, String propName, DateFormat dateFormat, Date defaultValue) {
		Date date = null;
		if (properties != null) {
			Object obj = properties.get(propName);
			if (obj != null) {
				if (obj instanceof Date) {
					date = (Date) obj;
				} else {
					String string = obj.toString();
					if (dateFormat != null) {
						try {
							date = dateFormat.parse(string);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		if (date == null) {
			if (defaultValue != null) {
				date = defaultValue;
			}
		}
		return date;
	}

	/**
	 * 
	 * @param properties
	 * @param propClass
	 * @param propName
	 * @param defaultValue
	 * @param throwExceptionIfNull
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T getValue(Map<String, Object> properties, Class<T> propClass, String propName, T defaultValue, boolean throwRuntimeExceptionIfNull) {
		T value = null;
		if (properties != null && propClass != null && propName != null) {
			Object valueObj = properties.get(propName);
			if (valueObj != null) {
				if (propClass.isAssignableFrom(valueObj.getClass())) {
					value = (T) valueObj;
				}
			}
		}
		if (value == null && defaultValue != null) {
			value = defaultValue;
		}
		if (value == null && throwRuntimeExceptionIfNull) {
			throw new RuntimeException("Propert '" + propName + "' value is null.");
		}
		return value;
	}

	/**
	 * 
	 * @param valueObj
	 * @return
	 */
	public String toStringValue(Object valueObj) {
		String stringValue = null;
		if (valueObj != null) {
			if (valueObj instanceof String) {
				stringValue = (String) valueObj;
			} else {
				try {
					stringValue = valueObj.toString();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return stringValue;
	}

	/**
	 * 
	 * @param valueObj
	 * @return
	 */
	public Boolean toBooleanValue(Object valueObj) {
		Boolean booleanValue = null;
		if (valueObj != null) {
			if (valueObj instanceof Boolean) {
				booleanValue = (Boolean) valueObj;
			} else {
				try {
					booleanValue = Boolean.valueOf(valueObj.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return booleanValue;
	}

	/**
	 * 
	 * @param valueObj
	 * @return
	 */
	public Integer toIntegerValue(Object valueObj) {
		Integer intValue = null;
		if (valueObj != null) {
			if (valueObj instanceof Integer) {
				intValue = (Integer) valueObj;
			} else {
				try {
					intValue = Integer.valueOf(valueObj.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return intValue;
	}

	/**
	 * 
	 * @param valueObj
	 * @return
	 */
	public Long toLongValue(Object valueObj) {
		Long longValue = null;
		if (valueObj != null) {
			if (valueObj instanceof Long) {
				longValue = (Long) valueObj;
			} else {
				try {
					longValue = Long.valueOf(valueObj.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return longValue;
	}

	/**
	 * 
	 * @param valueObj
	 * @return
	 */
	public Float toFloatValue(Object valueObj) {
		Float floatValue = null;
		if (valueObj != null) {
			if (valueObj instanceof Float) {
				floatValue = (Float) valueObj;
			} else {
				try {
					floatValue = Float.valueOf(valueObj.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return floatValue;
	}

	/**
	 * 
	 * @param valueObj
	 * @return
	 */
	public Double toDoubleValue(Object valueObj) {
		Double doubleValue = null;
		if (valueObj != null) {
			if (valueObj instanceof Double) {
				doubleValue = (Double) valueObj;
			} else {
				try {
					doubleValue = Double.valueOf(valueObj.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return doubleValue;
	}

	/**
	 * 
	 * @param dateFormat
	 * @param valueObj
	 * @return
	 */
	public Date toDateValue(DateFormat dateFormat, Object valueObj) {
		Date date = null;
		if (valueObj != null) {
			if (valueObj instanceof Date) {
				date = (Date) valueObj;
			} else {
				if (dateFormat != null) {
					try {
						date = dateFormat.parse(valueObj.toString());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return date;
	}

}
