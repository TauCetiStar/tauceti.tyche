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

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.List;

/**
 * 
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class IOUtils {

	public static final int EOF = -1;
	public static final int DEFAULT_BUFFER_SIZE = 8192;

	/**
	 * Internal byte array buffer. Give one to each thread
	 */
	private static final ThreadLocal<char[]> SKIP_CHAR_BUFFER = new ThreadLocal<char[]>() {
		@Override
		protected char[] initialValue() {
			return charArray();
		}
	};

	private static char[] charArray() {
		return charArray(DEFAULT_BUFFER_SIZE);
	}

	private static char[] charArray(final int size) {
		return new char[size];
	}

	static char[] getCharArray() {
		return SKIP_CHAR_BUFFER.get();
	}

	/**
	 * 
	 * @param input
	 * @return
	 * @throws IOException
	 */
	public static String toString(final InputStream input) throws IOException {
		// make explicit the use of the default charset
		return toString(input, Charset.defaultCharset());
	}

	/**
	 * 
	 * @param input
	 * @param charset
	 * @return
	 * @throws IOException
	 */
	public static String toString(final InputStream input, final Charset charset) throws IOException {
		try (final StringBuilderWriter sw = new StringBuilderWriter()) {
			copy(input, sw, charset);
			return sw.toString();
		}
	}

	/**
	 * 
	 * @param input
	 * @param charsetName
	 * @return
	 * @throws IOException
	 */
	public static List<String> readLines(InputStream input, String charsetName) throws IOException {
		return org.apache.commons.io.IOUtils.readLines(input, charsetName);
	}

	/**
	 * 
	 * @param input
	 * @param writer
	 * @param inputCharset
	 * @throws IOException
	 */
	public static void copy(final InputStream input, final Writer writer, final Charset charset) throws IOException {
		final InputStreamReader reader = new InputStreamReader(input, (charset == null ? Charset.defaultCharset() : charset));
		copy(reader, writer);
	}

	/**
	 * 
	 * @param reader
	 * @param writer
	 * @return
	 * @throws IOException
	 */
	public static int copy(final Reader reader, final Writer writer) throws IOException {
		final long count = copyLarge(reader, writer);
		if (count > Integer.MAX_VALUE) {
			return EOF;
		}
		return (int) count;
	}

	/**
	 * 
	 * @param reader
	 * @param writer
	 * @return
	 * @throws IOException
	 */
	public static long copyLarge(final Reader reader, final Writer writer) throws IOException {
		return copyLarge(reader, writer, getCharArray());
	}

	/**
	 * 
	 * @param reader
	 * @param writer
	 * @param buffer
	 * @return
	 * @throws IOException
	 */
	public static long copyLarge(final Reader reader, final Writer writer, final char[] buffer) throws IOException {
		long count = 0;
		int n;
		while (EOF != (n = reader.read(buffer))) {
			writer.write(buffer, 0, n);
			count += n;
		}
		return count;
	}

	public static void closeQuietly(final Closeable closeable) {
		// closeQuietly(closeable, (Consumer<IOException>) null);
		if (closeable != null) {
			try {
				closeable.close();
			} catch (final IOException e) {
			}
		}
	}

	public static void closeQuietly(final Closeable... closeables) {
		if (closeables == null) {
			return;
		}
		for (final Closeable closeable : closeables) {
			closeQuietly(closeable);
		}
	}

	/*-
	 * 
	 * @param closeable
	 * @param consumer
	 *
	public static void closeQuietly(final Closeable closeable, final Consumer<IOException> consumer) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (final IOException e) {
				if (consumer != null) {
					consumer.accept(e);
				}
			}
		}
	}
	*/

	public static void closeQuietly(final InputStream input) {
		closeQuietly((Closeable) input);
	}

	public static void closeQuietly(final OutputStream output) {
		closeQuietly((Closeable) output);
	}

	public static void closeQuietly(final Reader reader) {
		closeQuietly((Closeable) reader);
	}

	public static void closeQuietly(final Writer writer) {
		closeQuietly((Closeable) writer);
	}
}
