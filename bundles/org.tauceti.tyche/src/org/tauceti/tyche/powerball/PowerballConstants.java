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
package org.tauceti.tyche.powerball;

import java.io.File;

/**
 * 
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class PowerballConstants {

	public static int NONE = 0;

	public static int DRAW_AVG = 1 << 21;

	public static int DRAW_ROUND = 1 << 31;
	public static int DRAW_SQUARE_01_PER_ROW = 1 << 32;
	public static int DRAW_SQUARE_10_PER_ROW = 1 << 33;
	public static int DRAW_SQUARE_14_PER_ROW = 1 << 34;

	public static int PB_ROUND = 1 << 41;
	public static int PB_SQUARE_01_PER_ROW = 1 << 42;

	public static int WHITE_BALL_MAX = 69;
	public static int POWER_BALL_MAX = 26;

	public static File REPORTS_FOLDER = new File("C:/dev/reports");
	public static String REPORTS_FOLDER_PATH = "C:/dev/reports";
}
