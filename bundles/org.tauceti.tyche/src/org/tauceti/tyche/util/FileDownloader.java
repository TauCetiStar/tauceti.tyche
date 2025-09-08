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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class FileDownloader {

	/**
	 * 
	 * @param urlStr
	 * @param targetDirectory
	 * @return
	 * @throws IOException
	 */
	public static boolean downloadFile(String urlStr, String targetDirectory) throws IOException {
		return downloadFile(urlStr, targetDirectory, (String) null);
	}

	/**
	 * 
	 * @param urlStr
	 * @param targetDirectory
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static boolean downloadFile(String urlStr, String targetDirectory, String fileName) throws IOException {
		boolean succeed = false;

		HttpURLConnection connection = null;
		try {
			URL url = new URL(urlStr);
			System.out.println("Target URL: " + url);

			// Extract filename from URL
			if (fileName == null) {
				fileName = urlStr.substring(urlStr.lastIndexOf("/") + 1);
			}

			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				// Create target file path
				File targetFile = new File(targetDirectory, fileName);
				if (targetFile.exists()) {
					System.out.println("Delete existing file.");
					targetFile.delete();
					System.out.println("Existing file is deleted.");
				}

				BufferedInputStream in = null;
				FileOutputStream out = null;
				try {
					in = new BufferedInputStream(connection.getInputStream());
					out = new FileOutputStream(targetFile);

					System.out.println("Start downloading file.");
					byte[] buffer = new byte[1024];
					int bytesRead;
					while ((bytesRead = in.read(buffer)) != -1) {
						out.write(buffer, 0, bytesRead);
					}

					succeed = true;

				} catch (IOException e) {
					throw e;
				} finally {
					if (out != null) {
						out.close();
					}
					if (in != null) {
						in.close();
					}
				}

				if (succeed) {
					System.out.println("File is successfully downloaded.");
				}

				if (targetFile.isFile()) {
					System.out.println("File is downloaded to: " + targetFile.getAbsolutePath());

				} else {
					System.out.println("Fails to download file to: " + targetFile.getAbsolutePath());
				}

			} else {
				throw new IOException("Failed to download file: HTTP response code " + connection.getResponseCode());
			}
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		return succeed;
	}
}
