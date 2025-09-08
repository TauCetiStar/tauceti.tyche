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
package org.tauceti.tyche;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * 
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class Activator implements BundleActivator {

	private static Activator instance;
	private static BundleContext context;

	public static Activator getDefault() {
		return Activator.instance;
	}

	public static BundleContext getContext() {
		return Activator.context;
	}

	public Activator() {
	}

	@Override
	public void start(BundleContext bundleContext) throws Exception {
		Activator.instance = this;
		Activator.context = bundleContext;
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.instance = null;
		Activator.context = null;
	}
}
