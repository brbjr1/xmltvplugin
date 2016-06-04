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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

import junit.framework.TestCase;
import sage.EPGDBPublic;
import sage.MockEPGDBPublic;

/**
 * @author Demideus
 */
public class XMLTVImportPluginTest extends TestCase {

	/**
	 * The plugin.
	 */
	private XMLTVImportPlugin plugin = new XMLTVImportPlugin();

	/**
	 * The local markets.
	 */
	private static final String LOCAL_MARKETS = "{{3391600736, Lineup 2}, {867507149, XMLTV Lineup}}";

    /**
     * Test the algorithm that checks if something has been activated. 
     */
    public void testIsActivated() {
        List emptyList = Collections.EMPTY_LIST;
        List listAll = Arrays.asList(new String[] {"*"});
        List list1 = Arrays.asList(new String[] {"1"});
        List list2 = Arrays.asList(new String[] {"2"});
        List list12 = Arrays.asList(new String[] {"1", "2"});
        List list123 = Arrays.asList(new String[] {"1", "2", "3"});
        List list23 = Arrays.asList(new String[] {"2", "3"});
        
        assertFalse(XMLTVImportPlugin.isActivated(emptyList, emptyList));
        assertFalse(XMLTVImportPlugin.isActivated(list1, emptyList));
        assertFalse(XMLTVImportPlugin.isActivated(list2, emptyList));
        assertFalse(XMLTVImportPlugin.isActivated(list12, emptyList));
        assertFalse(XMLTVImportPlugin.isActivated(list123, emptyList));
        assertFalse(XMLTVImportPlugin.isActivated(list23, emptyList));
        
        assertTrue(XMLTVImportPlugin.isActivated(emptyList, listAll));
        assertTrue(XMLTVImportPlugin.isActivated(list1, listAll));
        assertTrue(XMLTVImportPlugin.isActivated(list2, listAll));
        assertTrue(XMLTVImportPlugin.isActivated(list12, listAll));
        assertTrue(XMLTVImportPlugin.isActivated(list123, listAll));
        assertTrue(XMLTVImportPlugin.isActivated(list23, listAll));
        
        assertFalse(XMLTVImportPlugin.isActivated(emptyList, list1));
        assertTrue(XMLTVImportPlugin.isActivated(list1, list1));
        assertFalse(XMLTVImportPlugin.isActivated(list2, list1));
        assertTrue(XMLTVImportPlugin.isActivated(list12, list1));
        assertTrue(XMLTVImportPlugin.isActivated(list123, list1));
        assertFalse(XMLTVImportPlugin.isActivated(list23, list1));
        
        assertFalse(XMLTVImportPlugin.isActivated(emptyList, list2));
        assertFalse(XMLTVImportPlugin.isActivated(list1, list2));
        assertTrue(XMLTVImportPlugin.isActivated(list2, list2));
        assertTrue(XMLTVImportPlugin.isActivated(list12, list2));
        assertTrue(XMLTVImportPlugin.isActivated(list123, list2));
        assertTrue(XMLTVImportPlugin.isActivated(list23, list2));
        
        assertFalse(XMLTVImportPlugin.isActivated(emptyList, list12));
        assertTrue(XMLTVImportPlugin.isActivated(list1, list12));
        assertTrue(XMLTVImportPlugin.isActivated(list2, list12));
        assertTrue(XMLTVImportPlugin.isActivated(list12, list12));
        assertTrue(XMLTVImportPlugin.isActivated(list123, list12));
        assertTrue(XMLTVImportPlugin.isActivated(list23, list12));
        
        assertFalse(XMLTVImportPlugin.isActivated(emptyList, list123));
        assertTrue(XMLTVImportPlugin.isActivated(list1, list123));
        assertTrue(XMLTVImportPlugin.isActivated(list2, list123));
        assertTrue(XMLTVImportPlugin.isActivated(list12, list123));
        assertTrue(XMLTVImportPlugin.isActivated(list123, list123));
        assertTrue(XMLTVImportPlugin.isActivated(list23, list123));
        
        assertFalse(XMLTVImportPlugin.isActivated(emptyList, list23));
        assertFalse(XMLTVImportPlugin.isActivated(list1, list23));
        assertTrue(XMLTVImportPlugin.isActivated(list2, list23));
        assertTrue(XMLTVImportPlugin.isActivated(list12, list23));
        assertTrue(XMLTVImportPlugin.isActivated(list123, list23));
        assertTrue(XMLTVImportPlugin.isActivated(list23, list23));
    }
    
	/**
	 * Test the lineup.
	 */
	public void testGetLocalMarkets() {
		assertEquals(LOCAL_MARKETS, toString(this.plugin.getLocalMarkets()));
	}

	/**
	 * Test the lineup.
	 */
	public void testGetProviders() {
		assertEquals(LOCAL_MARKETS, toString(this.plugin.getProviders(null)));
		assertEquals(LOCAL_MARKETS, toString(this.plugin.getProviders("")));
		assertEquals(LOCAL_MARKETS, toString(this.plugin.getProviders("00000")));
		assertEquals(LOCAL_MARKETS, toString(this.plugin.getProviders("90210")));
	}

	/**
	 * Test the updateGuide method on the XMLTV Lineup.
	 */
	public void testUpdateGuideXMLTVLineup() {
		String[] stringsEmpty = {};
		byte[] bytesEmpty = {};
		MockEPGDBPublic guide = new MockEPGDBPublic();
		TreeMap lineup = new TreeMap();
		lineup.put(new Integer(18730), new String[] { "1" });

		guide.expectAddChannelPublic("Channel 1", "Channel 1", "", 18730, true);

		guide.expectAddShowPublic(null, "title 1", null, null, 0, null, null,
				stringsEmpty, bytesEmpty, null, stringsEmpty, null, null,
				stringsEmpty, "SH3gw4GY0000", null, 0, true);
		guide.expectAddAiringPublic("SH3gw4GY0000", 18730, 32535174420000L,
				60000L, true);

		guide.expectSetLineup(867507149, lineup);

		this.plugin.updateGuide("867507149", guide);

		guide.verify();
	}

	/**
	 * Test the updateGuide method on Lineup 2.
	 */
	public void testUpdateGuideLineup2() {
		String[] stringsEmpty = {};
		byte[] bytesEmpty = {};
		String[] people2 = { "Director 1", "Director 2", "Actor 1", "Actor 2",
				"Actor 3 as character 3", "Writer 1", "Writer 2", "Producer 1",
				"Producer 2", "Presenter 1", "Presenter 2", "Guest 1",
				"Guest 2", "Guest 3", "Guest 4" };
		byte[] roles2 = { EPGDBPublic.DIRECTOR_ROLE, EPGDBPublic.DIRECTOR_ROLE,
				EPGDBPublic.ACTOR_ROLE, EPGDBPublic.ACTOR_ROLE,
				EPGDBPublic.ACTOR_ROLE, EPGDBPublic.WRITER_ROLE,
				EPGDBPublic.WRITER_ROLE, EPGDBPublic.PRODUCER_ROLE,
				EPGDBPublic.PRODUCER_ROLE, EPGDBPublic.HOST_ROLE,
				EPGDBPublic.HOST_ROLE, EPGDBPublic.GUEST_ROLE,
				EPGDBPublic.GUEST_ROLE, EPGDBPublic.GUEST_ROLE,
				EPGDBPublic.GUEST_ROLE };
		MockEPGDBPublic guide = new MockEPGDBPublic();
		TreeMap lineup = new TreeMap();
		lineup.put(new Integer(53283), new String[] { "2", "102" });
		lineup.put(new Integer(2627), new String[] { "2" });
		lineup.put(new Integer(32068), new String[] { "4" });
		lineup.put(new Integer(42788), new String[] { "3" });

      
        guide.expectAddChannelPublic("Ch2", "Channel 2", "Network 1", 53283,
                true);

        
		guide.expectAddShowPublic("HD - Titel 2 the Quick Brown Fox (0003)", null,
				"1-3. SubTitel (5/6)", "Description", 0, "Movie",
				"Actie / Avontuur / 2 / News", people2, roles2, "TVM",
				new String[] { "Graphic Violence" }, "0003", null,
				new String[] { "Omschrijving", "*", "14:9", "HDTV", "Mono",
						"US/Nederland", "Original language: Engels",
						"Season 1/2", "Episode 3/4", "Part 5/6" },
				"EP1hi1YA0003-5", "English", 0, true);
		guide.expectAddAiringPublic("EP1hi1YA0003-5", 53283, 32569478880000L,
				60000L, true);

		guide.expectAddShowPublic(null, "Test Single Category (01-02-3000)", "3.", null, 0,
				"Movie", "Single", stringsEmpty, bytesEmpty, "NC-17",
				new String[] { "Adult Situations" }, "30000201", null, new String[] {
						"*", "Episode 3", "6 parts" }, "EP20W7k#0003", null, 0,
				true);
		guide.expectAddAiringPublic("EP20W7k#0003", 53283, 32569478940000L,
				60000L, true);

		guide.expectAddShowPublic(null, "Test Valid Char \u00c9", "(1)", null,
				0, "Movie",
                "Actie / Avontuur / 2", stringsEmpty, bytesEmpty, "TVY7", new String[] {
						"Violence", "Language" }, null, null, new String[] {
						"*****", "Part 1" }, "SH2FOhM00000-1", null, 0, true);
		guide.expectAddAiringPublic("SH2FOhM00000-1", 53283, 32569478940000L,
				60000L, true);

		guide.expectAddShowPublic("Test Invalid Char  (01-02-3000)", null, "SubTitle", null, 0,
				"Series", "5", stringsEmpty, bytesEmpty, null, stringsEmpty, "30000201",
				null, stringsEmpty, "SH0DiSg90000", null, 0, true);
		guide.expectAddAiringPublic("SH0DiSg90000", 53283, 32569478940000L,
				60000L, true);

        guide.expectAddShowPublic(null, "Test Subcategory Without Category",
                null, null, 0, "Drama", null, stringsEmpty, bytesEmpty, null,
                stringsEmpty, null, null, stringsEmpty, "SH0ggY120000", null,
                0, true);
        guide.expectAddAiringPublic("SH0ggY120000", 53283, 32569478940000L,
                61000L, true);

        guide.expectAddShowPublic("Test No-episode", null, 
                null, null, 0, "Series", "5", stringsEmpty, bytesEmpty, null,
                stringsEmpty, null, null, stringsEmpty, "SH0Mggl#0000", null,
                0, true);
        guide.expectAddAiringPublic("SH0Mggl#0000", 53283, 32569478940000L,
                60000L, true);

        guide.expectAddShowPublic("Test No-date", null, 
                null, null, 0, "News", "4", stringsEmpty, bytesEmpty, null,
                stringsEmpty, null, null, stringsEmpty, "SH3EdpXJ0000", null,
                0, true);
        guide.expectAddAiringPublic("SH3EdpXJ0000", 53283, 32569478940000L,
                60000L, true);

        guide.expectAddShowPublic(null, "Test Dd_progid", "2-11.", 
                null, 0, null, null, stringsEmpty, bytesEmpty, null,
                stringsEmpty, null, null, new String[]{"Season 2/5", "Episode 11/20"}, "EP8084030006", null,
                0, true);
        guide.expectAddAiringPublic("EP8084030006", 53283, 32569478940000L,
                60000L, true);

		guide.expectAddChannelPublic("Channel 3", "Channel 3", "", 42788, true);

		guide.expectAddShowPublic(null, "Title on channel 3", null, null, 0,
				null, null, stringsEmpty, bytesEmpty, null, stringsEmpty, null,
				null, stringsEmpty, "SH0nW2ou0000", null, 0, true);
		guide.expectAddAiringPublic("SH0nW2ou0000", 42788, 32535174420000L,
				60000L, true);

		guide.expectAddChannelPublic("chX", "chX", "", 32068, true);
        guide.expectAddChannelPublic("Y", "IJ", "", 2627, true);

        guide.expectSetLineup(3391600736L, lineup);

		this.plugin.updateGuide("3391600736", guide);

		guide.verify();
	}

	/**
	 * Test the updateGuide method with an invalid parameter.
	 */
	public void testUpdateGuideNull() {
		MockEPGDBPublic guide = new MockEPGDBPublic();
		this.plugin.updateGuide(null, guide);
		guide.verify();
	}

	/**
	 * Returns an array of string arrays as a string.
	 * 
	 * @param aArray
	 *            the array.
	 * @return the string representation.
	 */
	private String toString(String[][] aArray) {
		StringBuffer sb = new StringBuffer();
		if (aArray.length > 0) {
			sb.append(toString(aArray[0]));
		}
		for (int i = 1; i < aArray.length; ++i) {
			sb.append(", " + toString(aArray[i]));
		}
		return "{" + sb + "}";
	}

	/**
	 * Returns a string array as a string.
	 * 
	 * @param aArray
	 *            the array.
	 * @return the string representation.
	 */
	private String toString(String[] aArray) {
		StringBuffer sb = new StringBuffer();
		if (aArray.length > 0) {
			sb.append(aArray[0]);
		}
		for (int i = 1; i < aArray.length; ++i) {
			sb.append(", " + aArray[i]);
		}
		return "{" + sb + "}";
	}
}