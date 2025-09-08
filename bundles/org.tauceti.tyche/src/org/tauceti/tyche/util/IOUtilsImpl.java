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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URI;
import java.net.URLConnection;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.output.NullOutputStream;

/**
 * 
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class IOUtilsImpl {

	public IOUtilsImpl() {
	}

	public byte[] toByteArray(InputStream input) throws IOException {
		return org.apache.commons.io.IOUtils.toByteArray(input);
	}

	public byte[] toByteArray(URI uri) throws IOException {
		return org.apache.commons.io.IOUtils.toByteArray(uri);
	}

	public byte[] toByteArray(URLConnection urlConn) throws IOException {
		return org.apache.commons.io.IOUtils.toByteArray(urlConn);
	}

	public byte[] toByteArray(Reader input, String charsetName) throws IOException {
		return org.apache.commons.io.IOUtils.toByteArray(input, charsetName);
	}

	public List<String> readLines(InputStream input, String charsetName) throws IOException {
		return org.apache.commons.io.IOUtils.readLines(input, charsetName);
	}

	public List<String> readLines(Reader input) throws IOException {
		return org.apache.commons.io.IOUtils.readLines(input);
	}

	public void writeLines(Collection<?> lines, String lineEnding, OutputStream output, String charsetName) throws IOException {
		org.apache.commons.io.IOUtils.writeLines(lines, lineEnding, output, charsetName);
	}

	public void writeLines(Collection<?> lines, String lineEnding, Writer writer) throws IOException {
		org.apache.commons.io.IOUtils.writeLines(lines, lineEnding, writer);
	}

	public String toString(InputStream input, String charsetName) throws IOException {
		return org.apache.commons.io.IOUtils.toString(input, charsetName);
	}

	public String toString(byte[] input, String charsetName) throws IOException {
		return org.apache.commons.io.IOUtils.toString(input, charsetName);
	}

	public int copy(InputStream input, OutputStream output) throws IOException {
		return org.apache.commons.io.IOUtils.copy(input, output);
	}

	public int copyToNullOutputStream(InputStream input) throws IOException {
		return org.apache.commons.io.IOUtils.copy(input, new NullOutputStream());
	}

	public long copyLarge(InputStream input, OutputStream output) throws IOException {
		return org.apache.commons.io.IOUtils.copyLarge(input, output);
	}

	public int copy(Reader input, Writer output) throws IOException {
		return org.apache.commons.io.IOUtils.copy(input, output);
	}

	public long copyLarge(Reader input, Writer output) throws IOException {
		return org.apache.commons.io.IOUtils.copyLarge(input, output);
	}

	public void copy(InputStream input, Writer output, String inputEncoding) throws IOException {
		org.apache.commons.io.IOUtils.copy(input, output, inputEncoding);
	}

	public void copy(Reader input, OutputStream output, String outputEncoding) throws IOException {
		org.apache.commons.io.IOUtils.copy(input, output, outputEncoding);
	}
}
