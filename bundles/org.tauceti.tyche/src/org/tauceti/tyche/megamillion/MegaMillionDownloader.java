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
package org.tauceti.tyche.megamillion;

import java.io.IOException;

import org.tauceti.tyche.util.FileDownloader;

/**
 * 
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class MegaMillionDownloader {

	public static void main(String[] args) {
		MegaMillionDownloader downloader = new MegaMillionDownloader();
		downloader.run();
	}

	public MegaMillionDownloader() {
	}

	public void run() {
		try {
			String fileUrl = "https://www.texaslottery.com/export/sites/lottery/Games/Mega_Millions/Winning_Numbers/megamillions.csv"; // Replace with your file URL
			String targetDirectory = System.getProperty("user.dir") + "/data"; // Replace with your target directory

			boolean succeed = FileDownloader.downloadFile(fileUrl, targetDirectory);
			if (succeed) {
				System.out.println("File downloaded successfully!");
			}
		} catch (IOException e) {
			System.err.println("Error downloading file: " + e.getMessage());
		}
	}
}
