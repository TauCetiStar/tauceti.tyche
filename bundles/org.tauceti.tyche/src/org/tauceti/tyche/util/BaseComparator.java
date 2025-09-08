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

import java.util.Comparator;

/**
 * 
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public abstract class BaseComparator<T> implements Comparator<T> {

	public static final String ASC = "asc"; //$NON-NLS-1$
	public static final String DESC = "desc"; //$NON-NLS-1$

	protected String sort;

	public BaseComparator() {
		this(ASC);
	}

	public BaseComparator(String sort) {
		this.sort = check(sort);
	}

	protected String check(String sort) {
		if (!ASC.equalsIgnoreCase(sort) && !DESC.equalsIgnoreCase(sort)) {
			return ASC;
		}
		return sort;
	}

	protected boolean asc() {
		return ASC.equalsIgnoreCase(sort);
	}

	protected boolean desc() {
		return DESC.equalsIgnoreCase(sort);
	}
}