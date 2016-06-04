/*
 * Copyright 2016 The XMLTVImportPlugin for SageTV Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package xmltv;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import junit.framework.TestCase;

/**
 * Test the XMLInputStreamFilter;
 * 
 * @author Demideus
 */
public class XMLInputStreamFilterTest extends TestCase {

	/**
	 * Test filtering in the read method.
	 * 
	 * @throws IOException
	 */
	public void testRead() throws IOException {
		assertEquals("00 11 22", readAll(
				new XMLInputStreamFilter(new ByteArrayInputStream(
						"0\u00000 1\u00011 2\u00022".getBytes()))).toString());
		assertEquals("amp&amp;amp", readAll(
				new XMLInputStreamFilter(new ByteArrayInputStream("amp&amp;amp"
						.getBytes()))).toString());
		assertEquals("#40&#40;#40", readAll(
				new XMLInputStreamFilter(new ByteArrayInputStream("#40&#40;#40"
						.getBytes()))).toString());
		assertEquals("#30#30", readAll(
				new XMLInputStreamFilter(new ByteArrayInputStream("#30&#30;#30"
						.getBytes()))).toString());
		assertEquals("#1c&#1c;#1c", readAll(
				new XMLInputStreamFilter(new ByteArrayInputStream("#1c&#1c;#1c"
						.getBytes()))).toString());
		assertEquals("#x1c#x1c", readAll(
				new XMLInputStreamFilter(new ByteArrayInputStream(
						"#x1c&#x1c;#x1c".getBytes()))).toString());
		assertEquals("#xd&#xd;#xd", readAll(
				new XMLInputStreamFilter(new ByteArrayInputStream("#xd&#xd;#xd"
						.getBytes()))).toString());

	}

	/**
	 * Read all bytes in an InputStream.
	 * 
	 * @param inputStream the inputstream.
	 * @return the bytes.
	 * @throws IOException
	 */
	private ByteArrayOutputStream readAll(InputStream inputStream)
			throws IOException {
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		byte[] buffer = new byte[1000];
		int bytesRead = inputStream.read(buffer);
		while (bytesRead > -1) {
			bout.write(buffer, 0, bytesRead);
			bytesRead = inputStream.read(buffer);
		}
		return bout;
	}
}