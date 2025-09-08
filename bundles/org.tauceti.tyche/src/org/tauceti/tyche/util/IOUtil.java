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

import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.nio.charset.Charset;

/**
 * 
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class IOUtil {

	/**
	 * Represents the end-of-file (or stream).
	 * 
	 * @since 2.5 (made public)
	 */
	public static final int EOF = -1;

	/**
	 * The Unix directory separator character.
	 */
	public static final char DIR_SEPARATOR_UNIX = '/';

	/**
	 * The Windows directory separator character.
	 */
	public static final char DIR_SEPARATOR_WINDOWS = '\\';

	/**
	 * The system directory separator character.
	 */
	public static final char DIR_SEPARATOR = File.separatorChar;

	/**
	 * The Unix line separator string.
	 */
	public static final String LINE_SEPARATOR_UNIX = "\n";

	/**
	 * The Windows line separator string.
	 */
	public static final String LINE_SEPARATOR_WINDOWS = "\r\n";

	/**
	 * The system line separator string.
	 */
	public static final String LINE_SEPARATOR;

	static {
		// avoid security issues
		final StringBuilderWriter buf = new StringBuilderWriter(4);
		final PrintWriter out = new PrintWriter(buf);
		out.println();
		LINE_SEPARATOR = buf.toString();
		out.close();
	}

	public static final Charset DEFAULT_CHARSET = Charset.defaultCharset();
	public static final String DEFAULT_CHARSET_NAME = Charset.defaultCharset().name();

	// ------------------------------------------------------------------------------------
	// Close
	// ------------------------------------------------------------------------------------
	/**
	 * Closes a Closeable unconditionally.
	 * 
	 * Equivalent to Closeable#close(), except any exceptions will be ignored. This is typically used in finally blocks.
	 * 
	 * @param closeable       a Closeable to be closed.
	 * @param printStackTrace if printStackTrace is false, any exceptions will be ignored. if printStackTrace is true, if any exceptions occurs, it will be printed out.
	 */
	public static void closeQuietly(Closeable closeable, boolean printStackTrace) {
		try {
			if (closeable != null) {
				closeable.close();
			}
		} catch (IOException ioe) {
			if (printStackTrace) {
				ioe.printStackTrace();
			} else {
				// ignore
			}
		}
	}

	/**
	 * Close the Closeable objects and <b>ignore</b> any {@link IOException} or null pointers. Must only be used for cleanup in exception handlers.
	 * 
	 * @param printStackTrace if printStackTrace is false, any exceptions will be ignored. if printStackTrace is true, if any exceptions occurs, it will be printed out.
	 * @param closeable       a Closeable to be closed.
	 */
	public static void closeQuietly(boolean printStackTrace, Closeable... closeables) {
		for (java.io.Closeable c : closeables) {
			if (c != null) {
				try {
					c.close();
				} catch (IOException e) {
					if (printStackTrace) {
						e.printStackTrace();
					} else {
						// ignore
					}
				}
			}
		}
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

	// ------------------------------------------------------------------------------------
	// Convert
	// ------------------------------------------------------------------------------------
	/**
	 * Converts the specified string to an input stream, encoded as bytes using the specified character encoding.
	 * 
	 * Character encoding names can be found at http://www.iana.org/assignments/character-sets.
	 *
	 * @param input    the string to convert
	 * @param encoding the encoding to use, null means platform default
	 * @return an input stream
	 * @throws IOException                                  if the encoding is invalid
	 * @throws java.nio.charset.UnsupportedCharsetException thrown if the encoding is not supported.
	 */
	public static InputStream toInputStream(String input, String encoding) throws IOException {
		// return org.apache.commons.io.IOUtils.toInputStream(input, encoding);

		byte[] bytes = input.getBytes(Charsets.toCharset(encoding));
		return new ByteArrayInputStream(bytes);
	}

	/**
	 * Converts the specified bytes[] to an input stream.
	 * 
	 * @param bytes the bytes[] to convert
	 * @return
	 */
	public static InputStream toInputStream(byte[] bytes) {
		return new ByteArrayInputStream(bytes);
	}

	private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

	/**
	 * Copies some or all chars from a large (over 2GB) <code>InputStream</code> to an <code>OutputStream</code>, optionally skipping input chars.
	 * <p>
	 * This method buffers the input internally, so there is no need to use a <code>BufferedReader</code>.
	 * <p>
	 * The buffer size is given by {@link #DEFAULT_BUFFER_SIZE}.
	 *
	 * @param input       the <code>Reader</code> to read from
	 * @param output      the <code>Writer</code> to write to
	 * @param inputOffset : number of chars to skip from input before copying -ve values are ignored
	 * @param length      : number of chars to copy. -ve means all
	 * @return the number of chars copied
	 * @throws NullPointerException if the input or output is null
	 * @throws IOException          if an I/O error occurs
	 */
	public static long copyLarge(final InputStream input, final OutputStream output, final long inputOffset, final long length) throws IOException {
		return copyLarge(input, output, inputOffset, length, new byte[DEFAULT_BUFFER_SIZE]);
	}

	/**
	 * 
	 * @param input
	 * @param output
	 * @param inputOffset
	 * @param length
	 * @param buffer
	 * @return
	 * @throws IOException
	 */
	public static long copyLarge(final InputStream input, final OutputStream output, final long inputOffset, final long length, final byte[] buffer) throws IOException {
		if (inputOffset > 0) {
			skipFully(input, inputOffset);
		}
		if (length == 0) {
			return 0;
		}
		final int bufferLength = buffer.length;
		int bytesToRead = bufferLength;
		if (length > 0 && length < bufferLength) {
			bytesToRead = (int) length;
		}
		int read;
		long totalRead = 0;
		while (bytesToRead > 0 && EOF != (read = input.read(buffer, 0, bytesToRead))) {
			output.write(buffer, 0, read);
			totalRead += read;
			if (length > 0) {
				// only adjust length if not reading to the end
				// Note the cast must work because buffer.length is an integer
				bytesToRead = (int) Math.min(length - totalRead, bufferLength);
			}
		}
		return totalRead;
	}

	private static byte[] SKIP_BYTE_BUFFER;
	/**
	 * The default buffer size to use for the skip() methods.
	 */
	private static final int SKIP_BUFFER_SIZE = 2048;

	/**
	 * 
	 * @param input
	 * @param toSkip
	 * @throws IOException
	 */
	public static void skipFully(final InputStream input, final long toSkip) throws IOException {
		if (toSkip < 0) {
			throw new IllegalArgumentException("Bytes to skip must not be negative: " + toSkip);
		}
		final long skipped = skip(input, toSkip);
		if (skipped != toSkip) {
			throw new EOFException("Bytes to skip: " + toSkip + " actual: " + skipped);
		}
	}

	/**
	 * 
	 * @param input
	 * @param toSkip
	 * @return
	 * @throws IOException
	 */
	public static long skip(final InputStream input, final long toSkip) throws IOException {
		if (toSkip < 0) {
			throw new IllegalArgumentException("Skip count must be non-negative, actual: " + toSkip);
		}
		/*
		 * N.B. no need to synchronize this because: - we don't care if the buffer is created multiple times (the data is ignored) - we always use the same size buffer, so if it it is recreated it will still
		 * be OK (if the buffer size were variable, we would need to synch. to ensure some other thread did not create a smaller one)
		 */
		if (SKIP_BYTE_BUFFER == null) {
			SKIP_BYTE_BUFFER = new byte[SKIP_BUFFER_SIZE];
		}
		long remain = toSkip;
		while (remain > 0) {
			// See https://issues.apache.org/jira/browse/IO-203 for why we use read() rather than delegating to skip()
			final long n = input.read(SKIP_BYTE_BUFFER, 0, (int) Math.min(remain, SKIP_BUFFER_SIZE));
			if (n < 0) { // EOF
				break;
			}
			remain -= n;
		}
		return toSkip - remain;
	}

	/**
	 * @see org.apache.commons.io.output.StringBuilderWriter
	 *
	 */
	public static class StringBuilderWriter extends Writer implements Serializable {

		private static final long serialVersionUID = 5122517056858241663L;
		private final StringBuilder builder;

		public StringBuilderWriter() {
			this.builder = new StringBuilder();
		}

		public StringBuilderWriter(final int capacity) {
			this.builder = new StringBuilder(capacity);
		}

		/**
		 * Constructs a new instance with the specified {@link StringBuilder}.
		 * 
		 * <p>
		 * If {@code builder} is null a new instance with default capacity will be created.
		 * </p>
		 *
		 * @param builder The String builder. May be null.
		 */
		public StringBuilderWriter(final StringBuilder builder) {
			this.builder = builder != null ? builder : new StringBuilder();
		}

		/**
		 * Appends a single character to this Writer.
		 *
		 * @param value The character to append
		 * @return This writer instance
		 */
		@Override
		public Writer append(final char value) {
			builder.append(value);
			return this;
		}

		/**
		 * Appends a character sequence to this Writer.
		 *
		 * @param value The character to append
		 * @return This writer instance
		 */
		@Override
		public Writer append(final CharSequence value) {
			builder.append(value);
			return this;
		}

		/**
		 * Appends a portion of a character sequence to the {@link StringBuilder}.
		 *
		 * @param value The character to append
		 * @param start The index of the first character
		 * @param end   The index of the last character + 1
		 * @return This writer instance
		 */
		@Override
		public Writer append(final CharSequence value, final int start, final int end) {
			builder.append(value, start, end);
			return this;
		}

		/**
		 * Closing this writer has no effect.
		 */
		@Override
		public void close() {
		}

		/**
		 * Flushing this writer has no effect.
		 */
		@Override
		public void flush() {
		}

		/**
		 * Writes a String to the {@link StringBuilder}.
		 * 
		 * @param value The value to write
		 */
		@Override
		public void write(final String value) {
			if (value != null) {
				builder.append(value);
			}
		}

		/**
		 * Writes a portion of a character array to the {@link StringBuilder}.
		 *
		 * @param value  The value to write
		 * @param offset The index of the first character
		 * @param length The number of characters to write
		 */
		@Override
		public void write(final char[] value, final int offset, final int length) {
			if (value != null) {
				builder.append(value, offset, length);
			}
		}

		/**
		 * @return The underlying builder.
		 */
		public StringBuilder getBuilder() {
			return builder;
		}

		/**
		 * @return The contents of the String builder.
		 */
		@Override
		public String toString() {
			return builder.toString();
		}
	}

	/**
	 * @see org.apache.commons.io.Charsets
	 * 
	 */
	public static class Charsets {
		/**
		 * Returns the given Charset or the default Charset if the given Charset is null.
		 * 
		 * @param charset A charset or null.
		 * @return the given Charset or the default Charset if the given Charset is null
		 */
		public static Charset toCharset(final Charset charset) {
			return charset == null ? Charset.defaultCharset() : charset;
		}

		/**
		 * Returns a Charset for the named charset. If the name is null, return the default Charset.
		 * 
		 * @param charset The name of the requested charset, may be null.
		 * @return a Charset for the named charset
		 * @throws java.nio.charset.UnsupportedCharsetException If the named charset is unavailable
		 */
		public static Charset toCharset(final String charset) {
			return charset == null ? Charset.defaultCharset() : Charset.forName(charset);
		}
	}
}
