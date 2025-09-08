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

import java.io.File;
import java.util.Iterator;
import java.util.Properties;

/**
 * 
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class SystemUtil {

	public static void printProperties() {
		System.out.println();
		System.out.println("System.getProperties()");
		System.out.println("----------------------------------------------------------------------");
		Properties props = System.getProperties();
		for (Iterator<Object> keyItor = props.keySet().iterator(); keyItor.hasNext();) {
			Object key = keyItor.next();
			String value = props.getProperty(key.toString());
			System.out.println(key + "=" + value);
		}
		System.out.println("----------------------------------------------------------------------");
	}

	public static File getUserDir() {
		File dir = null;
		try {
			String path = System.getProperty("user.dir");
			if (path != null) {
				dir = new File(path);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dir;
	}
}
