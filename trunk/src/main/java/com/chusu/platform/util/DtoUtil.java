package com.chusu.platform.util;

import java.beans.PropertyDescriptor;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.beanutils.converters.BigDecimalConverter;

public class DtoUtil {

	private static final PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();

	// private static final DateFormat dateDf = new
	// SimpleDateFormat("yyyy-MM-dd");
	//
	// private static final DateFormat datetimeDf = new
	// SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public void init() {
		BigDecimalConverter bd = new BigDecimalConverter(null);
		ConvertUtils.register(bd, java.math.BigDecimal.class);
	}

	/**
	 * 利用反射原理取得JAVABEAN的属性值,转换成STRING返回
	 * 
	 * @param bean
	 *            Object
	 * @param property
	 *            String
	 * @return String
	 */
	public static String getProperty(Object bean, String property) {
		String result = null;
		try {
			result = BeanUtils.getSimpleProperty(bean, property);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public static void copyProperties(Object orig, Object dest) {
		copyProperties(orig, dest, true);
	}

	public static void copyProperties(Object orig, Object dest,
			String[] ignalString) {
		copyProperties(orig, dest, true, ignalString);
	}

	public static void copyProperties(Object orig, Object dest,
			boolean ignalNull) {
		DateFormat dateDf = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat datetimeDf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			if (orig != null && dest != null) {
				PropertyDescriptor origDescriptors[] = propertyUtilsBean
						.getPropertyDescriptors(dest);
				for (int i = 0; i < origDescriptors.length; i++) {
					String name = origDescriptors[i].getName();
					if ("class".equals(name)) {
						continue;
					}
					if (propertyUtilsBean.isReadable(orig, name)
							&& propertyUtilsBean.isWriteable(dest, name)) {
						Object orig_value = propertyUtilsBean.getProperty(orig,
								name);
						Class<?> dest_type = propertyUtilsBean.getPropertyType(
								dest, name);
						if (orig_value == null && ignalNull) {
							continue;
						} else if ((dest_type == java.sql.Timestamp.class
								|| dest_type == java.sql.Date.class || dest_type == java.math.BigDecimal.class)
								&& "".equals(orig_value)) {
							propertyUtilsBean.setProperty(dest, name, null);
						} else if (dest_type == java.sql.Timestamp.class) {
							java.util.Date time = null;
							String value = orig_value.toString();
							if (value.indexOf("-") != -1) {
								if (value.length() > 10) {
									time = datetimeDf.parse(orig_value
											.toString());
								} else {
									time = dateDf.parse(orig_value.toString());
								}
								BeanUtils.copyProperty(dest, name,
										new Timestamp(time.getTime()));
							} else {
								BeanUtils.copyProperty(dest, name,
										new Timestamp(Long.valueOf(value)));
							}
						} else if (dest_type == java.sql.Date.class) {
							java.util.Date time = null;
							String value = orig_value.toString();
							if (value.indexOf("-") != -1) {
								if (orig_value.toString().length() > 10) {
									time = datetimeDf.parse(orig_value
											.toString());
								} else {
									time = dateDf.parse(orig_value.toString());
								}
								BeanUtils.copyProperty(dest, name, new Date(
										time.getTime()));
							} else {
								BeanUtils.copyProperty(dest, name, new Date(
										Long.valueOf(value)));
							}
						} else {
							try {
								BeanUtils.copyProperty(dest, name, orig_value);
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void copyProperties(Object orig, Object dest,
			boolean ignalNull, String[] ignalString) {
		DateFormat dateDf = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat datetimeDf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			if (orig != null && dest != null) {
				final PropertyDescriptor origDescriptors[] = propertyUtilsBean
						.getPropertyDescriptors(dest);
				for (final PropertyDescriptor descriptor : origDescriptors) {
					List<String> ignalList = Arrays.asList(ignalString);
					String name = descriptor.getName();
					if (propertyUtilsBean
							.isReadable(orig, descriptor.getName())
							&& propertyUtilsBean.isWriteable(dest,
									descriptor.getName())
							&& !ignalList.contains(descriptor.getName())) {
						final Object orig_value = propertyUtilsBean
								.getProperty(orig, descriptor.getName());
						final Class<?> dest_type = propertyUtilsBean
								.getPropertyType(dest, descriptor.getName());
						if (orig_value == null && ignalNull) {
							continue;
						} else if ((dest_type == java.sql.Timestamp.class
								|| dest_type == java.sql.Date.class || dest_type == java.math.BigDecimal.class)
								&& "".equals(orig_value)) {
							propertyUtilsBean.setProperty(dest, name, null);
						} else if (dest_type == java.sql.Timestamp.class) {
							java.util.Date time = null;
							String value = orig_value.toString();
							if (value.indexOf("-") != -1) {
								if (value.length() > 10) {
									time = datetimeDf.parse(orig_value
											.toString());
								} else {
									time = dateDf.parse(orig_value.toString());
								}
								BeanUtils.copyProperty(dest, name,
										new Timestamp(time.getTime()));
							} else {
								BeanUtils.copyProperty(dest, name,
										new Timestamp(Long.valueOf(value)));
							}
						} else if (dest_type == java.sql.Date.class) {
							java.util.Date time = null;
							String value = orig_value.toString();
							if (value.indexOf("-") != -1) {
								if (orig_value.toString().length() > 10) {
									time = datetimeDf.parse(orig_value
											.toString());
								} else {
									time = dateDf.parse(orig_value.toString());
								}
								BeanUtils.copyProperty(dest, name, new Date(
										time.getTime()));
							} else {
								BeanUtils.copyProperty(dest, name, new Date(
										Long.valueOf(value)));
							}
						} else {
							try {
								BeanUtils.copyProperty(dest, name, orig_value);
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void copyPropertiesToString(Object orig, Object dest,
			boolean ignalNull) {
		DateFormat dateDf = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat datetimeDf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			if (orig != null && dest != null) {
				PropertyDescriptor origDescriptors[] = propertyUtilsBean
						.getPropertyDescriptors(dest);
				for (int i = 0; i < origDescriptors.length; i++) {
					String name = origDescriptors[i].getName();
					if ("class".equals(name)) {
						continue;
					}
					if (propertyUtilsBean.isReadable(orig, name)
							&& propertyUtilsBean.isWriteable(dest, name)) {
						Object orig_value = propertyUtilsBean.getProperty(orig,
								name);
						String orig_type = propertyUtilsBean.getPropertyType(
								orig, name).getName();
						if (orig_value == null && ignalNull) {
							continue;
						} else if (orig_type.equals("java.sql.Timestamp")
								&& orig_value != null) {
							BeanUtils.copyProperty(dest, name,
									datetimeDf.format((Timestamp) orig_value));
						} else if (orig_type.equals("java.sql.Date")
								&& orig_value != null) {
							BeanUtils.copyProperty(dest, name,
									dateDf.format((Date) orig_value));
						} else {
							try {
								BeanUtils.copyProperty(dest, name, orig_value);
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 将属性的值装配到JAVABEAN中
	 * 
	 * @param bean
	 *            Object
	 * @param property
	 *            String
	 * @param value
	 *            Object
	 */
	public static void populateProperty(Object bean, String property,
			Object value) {
		try {
			String fieldtype = propertyUtilsBean
					.getPropertyType(bean, property).getName();
			if ((fieldtype.equals("java.sql.Timestamp")
					|| fieldtype.equals("java.sql.Date") || fieldtype
						.equals("java.math.BigDecimal"))
					&& (value == null || "".equals(value))) {
				if (propertyUtilsBean.isReadable(bean, "modifyStrs")
						&& propertyUtilsBean.isWriteable(bean, "modifyStrs")) {
					Object obj = propertyUtilsBean.getProperty(bean,
							"modifyStrs");
					if (obj instanceof List) {
						@SuppressWarnings("unchecked")
						List<String> list = (List<String>) obj;
						list.add(property);
						value = null;
					}
				}
			}
			BeanUtils.setProperty(bean, property, value);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 把fieldNames里指定的字段从from copy到to
	 * 
	 * @param orig
	 *            Object
	 * @param dest
	 *            Object
	 * @param fieldNames
	 *            String[]
	 */
	public static void copySpecifyPropertys(Object orig, Object dest,
			String[] fieldNames) {
		DateFormat dateDf = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat datetimeDf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			if (dest == null || orig == null) {
				return;
			}
			for (int i = 0; i < fieldNames.length; i++) {
				String name = fieldNames[i];
				final Object orig_value = propertyUtilsBean.getProperty(orig,
						name);
				final Class<?> dest_type = propertyUtilsBean.getPropertyType(
						dest, name);
				if (orig_value == null) {
					continue;
				} else if ((dest_type == java.sql.Timestamp.class
						|| dest_type == java.sql.Date.class || dest_type == java.math.BigDecimal.class)
						&& "".equals(orig_value)) {
					propertyUtilsBean.setProperty(dest, name, null);
				} else if (dest_type == java.sql.Timestamp.class) {
					java.util.Date time = null;
					String value = orig_value.toString();
					if (value.indexOf("-") != -1) {
						if (value.length() > 10) {
							time = datetimeDf.parse(orig_value.toString());
						} else {
							time = dateDf.parse(orig_value.toString());
						}
						BeanUtils.copyProperty(dest, name,
								new Timestamp(time.getTime()));
					} else {
						BeanUtils.copyProperty(dest, name,
								new Timestamp(Long.valueOf(value)));
					}
				} else if (dest_type == java.sql.Date.class) {
					java.util.Date time = null;
					String value = orig_value.toString();
					if (value.indexOf("-") != -1) {
						if (orig_value.toString().length() > 10) {
							time = datetimeDf.parse(orig_value.toString());
						} else {
							time = dateDf.parse(orig_value.toString());
						}
						BeanUtils.copyProperty(dest, name,
								new Date(time.getTime()));
					} else {
						BeanUtils.copyProperty(dest, name,
								new Date(Long.valueOf(value)));
					}
				} else {
					try {
						BeanUtils.copyProperty(dest, name, orig_value);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void resetDTO(Object dto) {
		if (dto == null)
			return;
		PropertyDescriptor origDescriptors[] = propertyUtilsBean
				.getPropertyDescriptors(dto);
		for (int i = 0; i < origDescriptors.length; i++) {
			String name = origDescriptors[i].getName();
			if (propertyUtilsBean.isWriteable(dto, name)
					&& !"class".equals(name)) {
				try {
					PropertyUtils.setSimpleProperty(dto, name, null);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	/**
	 * @category 拷贝List<Entity>到List<Dto>
	 * @param origList
	 * @param clazz
	 * @return List<Dto>
	 * @author zhaozhong
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> copyList(List<? extends Object> origList,
			Class<T> clazz) {
		List<T> list = new ArrayList<T>();
		if (null == origList || origList.isEmpty()) {
			return list;
		}
		for (Object o : origList) {
			try {
				Object d = clazz.newInstance();
				copyProperties(o, d);
				list.add((T) d);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * 把除了ignalString里指定的字段之外的字段从from copy到to
	 * 
	 * @param orig
	 *            Object
	 * @param dest
	 *            Object
	 * @param ignalString
	 *            String[]
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void copyPropertysIgnoreSpecify(Object orig, Object dest,
			String[] ignalString) {
		if (dest == null || orig == null) {
			return;
		}
		try {
			if (orig != null && dest != null) {
				PropertyDescriptor origDescriptors[] = propertyUtilsBean
						.getPropertyDescriptors(dest);
				List list = new ArrayList();
				for (int i = 0; i < origDescriptors.length; i++) {
					String name = origDescriptors[i].getName();
					if ("class".equals(name)) {
						continue;
					}
					if (!stringArrayContain(ignalString, name)
							&& propertyUtilsBean.isReadable(orig, name)
							&& propertyUtilsBean.isWriteable(dest, name)) {
						Object orig_value = propertyUtilsBean.getProperty(orig,
								name);
						String dest_type = propertyUtilsBean.getPropertyType(
								dest, name).getName();
						if (orig_value == null) {
							continue;
						} else if ((dest_type.equals("java.sql.Date") || dest_type
								.equals("java.math.BigDecimal"))
								&& "".equals(orig_value)) {
							BeanUtils.copyProperty(dest, name, null);
							list.add(name);
						} else {
							try {
								BeanUtils.copyProperty(dest, name, orig_value);
							} catch (Exception ex) {
								// logger.error(ex);
								// ex.printStackTrace();
							}
						}
					}
				}
				if (propertyUtilsBean.isReadable(dest, "modifyStrs")
						&& propertyUtilsBean.isWriteable(dest, "modifyStrs")) {
					BeanUtils.copyProperty(dest, "modifyStrs", list);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static boolean stringArrayContain(String[] array, String isContain) {
		for (String str : array) {
			if (str != null && str.equals(isContain)) {
				return true;
			}
		}
		return false;
	}
}
