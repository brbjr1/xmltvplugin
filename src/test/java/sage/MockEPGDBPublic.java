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
// MockCreator v2.6.4 build 2769; HashCode:2140696729; sage.EPGDBPublic
package sage;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import de.abstrakt.mock.MockCore;

public class MockEPGDBPublic implements sage.EPGDBPublic,
		de.abstrakt.mock.MockBase {

	/**
	 * The logger for this class.
	 */
	private static final Logger LOG = Logger.getLogger(MockEPGDBPublic.class);

	public static class PreMock implements de.abstrakt.mock.MockBase {

		/**
		 * The logger for this class.
		 */
		private static final Logger LOG = Logger.getLogger(PreMock.class);

		public PreMock(String id) {
			this.id = id;
		}

		public PreMock() {
			this.id = (String) MockCore.generateId(null, null);
		}

		public sage.MockEPGDBPublic.PreMock expectAnyCtor() {
			if (LOG.isDebugEnabled()) {
				LOG.debug("expectAnyCtor()");
			}

			id = MockCore.addExpectedAnyCtor(__id(), "sage.MockEPGDBPublic",
					null);
			return this;
		}

		public sage.MockEPGDBPublic.PreMock expectAnyCtor(Throwable th) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("expectAnyCtor(" + th + ")");
			}

			id = MockCore
					.addExpectedAnyCtor(__id(), "sage.MockEPGDBPublic", th);
			return this;
		}

		public void expectAddChannelPublic(java.lang.String p0_String,
				java.lang.String p1_String, java.lang.String p2_String,
				int p3_int, boolean valueToReturn) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("expectAddChannelPublic("
						+ p0_String
						+ ", "
						+ p1_String
						+ ", "
						+ p2_String
						+ ", "
						+ p3_int
						+ ", "
						+ valueToReturn
						+ ")");
			}

			java.util.List params = new java.util.ArrayList();
			params.add(p0_String);
			params.add(p1_String);
			params.add(p2_String);
			params.add(new Integer(p3_int));

			MockCore
					.addExpectedMethodCall(
							getClassObjectMethodSignature("addChannelPublic(String,String,String,int)"),
							params, new Boolean(valueToReturn));
		}

		public void expectAddChannelPublic(java.lang.String p0_String,
				java.lang.String p1_String, java.lang.String p2_String,
				int p3_int, java.lang.Throwable toThrow) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("expectAddChannelPublic("
						+ p0_String
						+ ", "
						+ p1_String
						+ ", "
						+ p2_String
						+ ", "
						+ p3_int
						+ ", "
						+ toThrow
						+ ")");
			}

			java.util.List params = new java.util.ArrayList();
			params.add(p0_String);
			params.add(p1_String);
			params.add(p2_String);
			params.add(new Integer(p3_int));

			MockCore
					.addExpectedMethodCall(
							getClassObjectMethodSignature("addChannelPublic(String,String,String,int)"),
							params, toThrow);
		}

		public void acceptAddChannelPublic(Object p0_String, Object p1_String,
				Object p2_String, Object p3_int, boolean valueToReturn) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("acceptAddChannelPublic("
						+ p0_String
						+ ", "
						+ p1_String
						+ ", "
						+ p2_String
						+ ", "
						+ p3_int
						+ ", "
						+ valueToReturn
						+ ")");
			}

			java.util.List params = new java.util.ArrayList();
			params.add(p0_String);
			params.add(p1_String);
			params.add(p2_String);
			params.add(p3_int);

			MockCore
					.addExpectedMethodCall(
							getClassObjectMethodSignature("addChannelPublic(String,String,String,int)"),
							params, new Boolean(valueToReturn));
		}

		public void acceptAddChannelPublic(Object p0_String, Object p1_String,
				Object p2_String, Object p3_int, java.lang.Throwable toThrow) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("acceptAddChannelPublic("
						+ p0_String
						+ ", "
						+ p1_String
						+ ", "
						+ p2_String
						+ ", "
						+ p3_int
						+ ", "
						+ toThrow
						+ ")");
			}

			java.util.List params = new java.util.ArrayList();
			params.add(p0_String);
			params.add(p1_String);
			params.add(p2_String);
			params.add(p3_int);

			MockCore
					.addExpectedMethodCall(
							getClassObjectMethodSignature("addChannelPublic(String,String,String,int)"),
							params, toThrow);
		}

		public void setAddChannelPublicDummy(boolean toReturn) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("setAddChannelPublicDummy(" + toReturn + ")");
			}

			MockCore
					.setDummy(
							getClassObjectMethodSignature("addChannelPublic(String,String,String,int)"),
							null, new Boolean(toReturn));
		}

		public void setAddChannelPublicDummy(java.lang.Throwable toThrow) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("setAddChannelPublicDummy(" + toThrow + ")");
			}

			MockCore
					.setDummy(
							getClassObjectMethodSignature("addChannelPublic(String,String,String,int)"),
							null, toThrow);
		}

		public void setAddChannelPublicDummy(java.lang.String p0_String,
				java.lang.String p1_String, java.lang.String p2_String,
				int p3_int, java.lang.Throwable toThrow) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("setAddChannelPublicDummy("
						+ p0_String
						+ ", "
						+ p1_String
						+ ", "
						+ p2_String
						+ ", "
						+ p3_int
						+ ", "
						+ toThrow
						+ ")");
			}

			java.util.List params = new java.util.ArrayList();
			params.add(p0_String);
			params.add(p1_String);
			params.add(p2_String);
			params.add(new Integer(p3_int));

			MockCore
					.setDummy(
							getClassObjectMethodSignature("addChannelPublic(String,String,String,int)"),
							params, toThrow);
		}

		public void setAddChannelPublicDummy(java.lang.String p0_String,
				java.lang.String p1_String, java.lang.String p2_String,
				int p3_int, boolean toReturn) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("setAddChannelPublicDummy("
						+ p0_String
						+ ", "
						+ p1_String
						+ ", "
						+ p2_String
						+ ", "
						+ p3_int
						+ ", "
						+ toReturn
						+ ")");
			}

			java.util.List params = new java.util.ArrayList();
			params.add(p0_String);
			params.add(p1_String);
			params.add(p2_String);
			params.add(new Integer(p3_int));

			MockCore
					.setDummy(
							getClassObjectMethodSignature("addChannelPublic(String,String,String,int)"),
							params, new Boolean(toReturn));
		}

		public void acceptAddChannelPublicDummy(Object p0_String,
				Object p1_String, Object p2_String, Object p3_int,
				boolean valueToReturn) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("acceptAddChannelPublicDummy("
						+ p0_String
						+ ", "
						+ p1_String
						+ ", "
						+ p2_String
						+ ", "
						+ p3_int
						+ ", "
						+ valueToReturn
						+ ")");
			}

			java.util.List params = new java.util.ArrayList();
			params.add(p0_String);
			params.add(p1_String);
			params.add(p2_String);
			params.add(p3_int);

			MockCore
					.setDummy(
							getClassObjectMethodSignature("addChannelPublic(String,String,String,int)"),
							params, new Boolean(valueToReturn));
		}

		public void acceptAddChannelPublicDummy(Object p0_String,
				Object p1_String, Object p2_String, Object p3_int,
				java.lang.Throwable valueToThrow) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("acceptAddChannelPublicDummy("
						+ p0_String
						+ ", "
						+ p1_String
						+ ", "
						+ p2_String
						+ ", "
						+ p3_int
						+ ", "
						+ valueToThrow
						+ ")");
			}

			java.util.List params = new java.util.ArrayList();
			params.add(p0_String);
			params.add(p1_String);
			params.add(p2_String);
			params.add(p3_int);

			MockCore
					.setDummy(
							getClassObjectMethodSignature("addChannelPublic(String,String,String,int)"),
							params, valueToThrow);
		}

		public void expectSetLineup(long p0_long, java.util.Map p1_Map) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("expectSetLineup(" + p0_long + ", " + mapToString(p1_Map) + ")");
			}

			java.util.List params = new java.util.ArrayList();
			params.add(new Long(p0_long));
			params.add(mapToString(p1_Map));

			MockCore.addExpectedMethodCall(
					getClassObjectMethodSignature("setLineup(long,Map)"),
					params);
		}

		public void expectSetLineup(long p0_long, java.util.Map p1_Map,
				java.lang.Throwable toThrow) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("expectSetLineup("
						+ p0_long
						+ ", "
						+ mapToString(p1_Map)
						+ ", "
						+ toThrow
						+ ")");
			}

			java.util.List params = new java.util.ArrayList();
			params.add(new Long(p0_long));
			params.add(mapToString(p1_Map));

			MockCore.addExpectedMethodCall(
					getClassObjectMethodSignature("setLineup(long,Map)"),
					params, toThrow);
		}

		public void acceptSetLineup(Object p0_long, Object p1_Map) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("acceptSetLineup(" + p0_long + ", " + mapToString(p1_Map) + ")");
			}

			java.util.List params = new java.util.ArrayList();
			params.add(p0_long);
			params.add(mapToString(p1_Map));

			MockCore.addExpectedMethodCall(
					getClassObjectMethodSignature("setLineup(long,Map)"),
					params);
		}

		public void acceptSetLineup(Object p0_long, Object p1_Map,
				java.lang.Throwable toThrow) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("acceptSetLineup("
						+ p0_long
						+ ", "
						+ mapToString(p1_Map)
						+ ", "
						+ toThrow
						+ ")");
			}

			java.util.List params = new java.util.ArrayList();
			params.add(p0_long);
			params.add(mapToString(p1_Map));

			MockCore.addExpectedMethodCall(
					getClassObjectMethodSignature("setLineup(long,Map)"),
					params, toThrow);
		}

		public void setSetLineupDummy() {
			if (LOG.isDebugEnabled()) {
				LOG.debug("setSetLineupDummy()");
			}

			MockCore.setDummy(
					getClassObjectMethodSignature("setLineup(long,Map)"), null,
					null);
		}

		public void setSetLineupDummy(java.lang.Throwable toThrow) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("setSetLineupDummy(" + toThrow + ")");
			}

			MockCore.setDummy(
					getClassObjectMethodSignature("setLineup(long,Map)"), null,
					toThrow);
		}

		public void setSetLineupDummy(long p0_long, java.util.Map p1_Map,
				java.lang.Throwable toThrow) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("setSetLineupDummy("
						+ p0_long
						+ ", "
						+ mapToString(p1_Map)
						+ ", "
						+ toThrow
						+ ")");
			}

			java.util.List params = new java.util.ArrayList();
			params.add(new Long(p0_long));
			params.add(mapToString(p1_Map));

			MockCore.setDummy(
					getClassObjectMethodSignature("setLineup(long,Map)"),
					params, toThrow);
		}

		public void acceptSetLineupDummy(Object p0_long, Object p1_Map,
				java.lang.Throwable valueToThrow) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("acceptSetLineupDummy("
						+ p0_long
						+ ", "
						+ mapToString(p1_Map)
						+ ", "
						+ valueToThrow
						+ ")");
			}

			java.util.List params = new java.util.ArrayList();
			params.add(p0_long);
			params.add(mapToString(p1_Map));

			MockCore.setDummy(
					getClassObjectMethodSignature("setLineup(long,Map)"),
					params, valueToThrow);
		}

		public void expectAddShowPublic(java.lang.String p0_String,
				java.lang.String p1_String, java.lang.String p2_String,
				java.lang.String p3_String, long p4_long,
				java.lang.String p5_String, java.lang.String p6_String,
				java.lang.String[] p7_String1D, byte[] p8_byte1D,
				java.lang.String p9_String, java.lang.String[] p10_String1D,
				java.lang.String p11_String, java.lang.String p12_String,
				java.lang.String[] p13_String1D, java.lang.String p14_String,
				java.lang.String p15_String, long p16_long,
				boolean valueToReturn) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("expectAddShowPublic("
						+ p0_String
						+ ", "
						+ p1_String
						+ ", "
						+ p2_String
						+ ", "
						+ p3_String
						+ ", "
						+ p4_long
						+ ", "
						+ p5_String
						+ ", "
						+ p6_String
						+ ", "
						+ p7_String1D
						+ ", "
						+ p8_byte1D
						+ ", "
						+ p9_String
						+ ", "
						+ p10_String1D
						+ ", "
						+ p11_String
						+ ", "
						+ p12_String
						+ ", "
						+ p13_String1D
						+ ", "
						+ p14_String
						+ ", "
						+ p15_String
						+ ", "
						+ p16_long
						+ ", "
						+ valueToReturn
						+ ")");
			}

			java.util.List params = new java.util.ArrayList();
			params.add(p0_String);
			params.add(p1_String);
			params.add(p2_String);
			params.add(p3_String);
			params.add(new Long(p4_long));
			params.add(p5_String);
			params.add(p6_String);
			params.add(p7_String1D);
			params.add(p8_byte1D);
			params.add(p9_String);
			params.add(p10_String1D);
			params.add(p11_String);
			params.add(p12_String);
			params.add(p13_String1D);
			params.add(p14_String);
			params.add(p15_String);
			params.add(new Long(p16_long));

			MockCore
					.addExpectedMethodCall(
							getClassObjectMethodSignature("addShowPublic(String,String,String,String,long,String,String,String1D,byte1D,String,String1D,String,String,String1D,String,String,long)"),
							params, new Boolean(valueToReturn));
		}

		public void expectAddShowPublic(java.lang.String p0_String,
				java.lang.String p1_String, java.lang.String p2_String,
				java.lang.String p3_String, long p4_long,
				java.lang.String p5_String, java.lang.String p6_String,
				java.lang.String[] p7_String1D, byte[] p8_byte1D,
				java.lang.String p9_String, java.lang.String[] p10_String1D,
				java.lang.String p11_String, java.lang.String p12_String,
				java.lang.String[] p13_String1D, java.lang.String p14_String,
				java.lang.String p15_String, long p16_long,
				java.lang.Throwable toThrow) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("expectAddShowPublic("
						+ p0_String
						+ ", "
						+ p1_String
						+ ", "
						+ p2_String
						+ ", "
						+ p3_String
						+ ", "
						+ p4_long
						+ ", "
						+ p5_String
						+ ", "
						+ p6_String
						+ ", "
						+ p7_String1D
						+ ", "
						+ p8_byte1D
						+ ", "
						+ p9_String
						+ ", "
						+ p10_String1D
						+ ", "
						+ p11_String
						+ ", "
						+ p12_String
						+ ", "
						+ p13_String1D
						+ ", "
						+ p14_String
						+ ", "
						+ p15_String
						+ ", "
						+ p16_long
						+ ", "
						+ toThrow
						+ ")");
			}

			java.util.List params = new java.util.ArrayList();
			params.add(p0_String);
			params.add(p1_String);
			params.add(p2_String);
			params.add(p3_String);
			params.add(new Long(p4_long));
			params.add(p5_String);
			params.add(p6_String);
			params.add(p7_String1D);
			params.add(p8_byte1D);
			params.add(p9_String);
			params.add(p10_String1D);
			params.add(p11_String);
			params.add(p12_String);
			params.add(p13_String1D);
			params.add(p14_String);
			params.add(p15_String);
			params.add(new Long(p16_long));

			MockCore
					.addExpectedMethodCall(
							getClassObjectMethodSignature("addShowPublic(String,String,String,String,long,String,String,String1D,byte1D,String,String1D,String,String,String1D,String,String,long)"),
							params, toThrow);
		}

		public void acceptAddShowPublic(Object p0_String, Object p1_String,
				Object p2_String, Object p3_String, Object p4_long,
				Object p5_String, Object p6_String, Object p7_String1D,
				Object p8_byte1D, Object p9_String, Object p10_String1D,
				Object p11_String, Object p12_String, Object p13_String1D,
				Object p14_String, Object p15_String, Object p16_long,
				boolean valueToReturn) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("acceptAddShowPublic("
						+ p0_String
						+ ", "
						+ p1_String
						+ ", "
						+ p2_String
						+ ", "
						+ p3_String
						+ ", "
						+ p4_long
						+ ", "
						+ p5_String
						+ ", "
						+ p6_String
						+ ", "
						+ p7_String1D
						+ ", "
						+ p8_byte1D
						+ ", "
						+ p9_String
						+ ", "
						+ p10_String1D
						+ ", "
						+ p11_String
						+ ", "
						+ p12_String
						+ ", "
						+ p13_String1D
						+ ", "
						+ p14_String
						+ ", "
						+ p15_String
						+ ", "
						+ p16_long
						+ ", "
						+ valueToReturn
						+ ")");
			}

			java.util.List params = new java.util.ArrayList();
			params.add(p0_String);
			params.add(p1_String);
			params.add(p2_String);
			params.add(p3_String);
			params.add(p4_long);
			params.add(p5_String);
			params.add(p6_String);
			params.add(p7_String1D);
			params.add(p8_byte1D);
			params.add(p9_String);
			params.add(p10_String1D);
			params.add(p11_String);
			params.add(p12_String);
			params.add(p13_String1D);
			params.add(p14_String);
			params.add(p15_String);
			params.add(p16_long);

			MockCore
					.addExpectedMethodCall(
							getClassObjectMethodSignature("addShowPublic(String,String,String,String,long,String,String,String1D,byte1D,String,String1D,String,String,String1D,String,String,long)"),
							params, new Boolean(valueToReturn));
		}

		public void acceptAddShowPublic(Object p0_String, Object p1_String,
				Object p2_String, Object p3_String, Object p4_long,
				Object p5_String, Object p6_String, Object p7_String1D,
				Object p8_byte1D, Object p9_String, Object p10_String1D,
				Object p11_String, Object p12_String, Object p13_String1D,
				Object p14_String, Object p15_String, Object p16_long,
				java.lang.Throwable toThrow) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("acceptAddShowPublic("
						+ p0_String
						+ ", "
						+ p1_String
						+ ", "
						+ p2_String
						+ ", "
						+ p3_String
						+ ", "
						+ p4_long
						+ ", "
						+ p5_String
						+ ", "
						+ p6_String
						+ ", "
						+ p7_String1D
						+ ", "
						+ p8_byte1D
						+ ", "
						+ p9_String
						+ ", "
						+ p10_String1D
						+ ", "
						+ p11_String
						+ ", "
						+ p12_String
						+ ", "
						+ p13_String1D
						+ ", "
						+ p14_String
						+ ", "
						+ p15_String
						+ ", "
						+ p16_long
						+ ", "
						+ toThrow
						+ ")");
			}

			java.util.List params = new java.util.ArrayList();
			params.add(p0_String);
			params.add(p1_String);
			params.add(p2_String);
			params.add(p3_String);
			params.add(p4_long);
			params.add(p5_String);
			params.add(p6_String);
			params.add(p7_String1D);
			params.add(p8_byte1D);
			params.add(p9_String);
			params.add(p10_String1D);
			params.add(p11_String);
			params.add(p12_String);
			params.add(p13_String1D);
			params.add(p14_String);
			params.add(p15_String);
			params.add(p16_long);

			MockCore
					.addExpectedMethodCall(
							getClassObjectMethodSignature("addShowPublic(String,String,String,String,long,String,String,String1D,byte1D,String,String1D,String,String,String1D,String,String,long)"),
							params, toThrow);
		}

		public void setAddShowPublicDummy(boolean toReturn) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("setAddShowPublicDummy(" + toReturn + ")");
			}

			MockCore
					.setDummy(
							getClassObjectMethodSignature("addShowPublic(String,String,String,String,long,String,String,String1D,byte1D,String,String1D,String,String,String1D,String,String,long)"),
							null, new Boolean(toReturn));
		}

		public void setAddShowPublicDummy(java.lang.Throwable toThrow) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("setAddShowPublicDummy(" + toThrow + ")");
			}

			MockCore
					.setDummy(
							getClassObjectMethodSignature("addShowPublic(String,String,String,String,long,String,String,String1D,byte1D,String,String1D,String,String,String1D,String,String,long)"),
							null, toThrow);
		}

		public void setAddShowPublicDummy(java.lang.String p0_String,
				java.lang.String p1_String, java.lang.String p2_String,
				java.lang.String p3_String, long p4_long,
				java.lang.String p5_String, java.lang.String p6_String,
				java.lang.String[] p7_String1D, byte[] p8_byte1D,
				java.lang.String p9_String, java.lang.String[] p10_String1D,
				java.lang.String p11_String, java.lang.String p12_String,
				java.lang.String[] p13_String1D, java.lang.String p14_String,
				java.lang.String p15_String, long p16_long,
				java.lang.Throwable toThrow) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("setAddShowPublicDummy("
						+ p0_String
						+ ", "
						+ p1_String
						+ ", "
						+ p2_String
						+ ", "
						+ p3_String
						+ ", "
						+ p4_long
						+ ", "
						+ p5_String
						+ ", "
						+ p6_String
						+ ", "
						+ p7_String1D
						+ ", "
						+ p8_byte1D
						+ ", "
						+ p9_String
						+ ", "
						+ p10_String1D
						+ ", "
						+ p11_String
						+ ", "
						+ p12_String
						+ ", "
						+ p13_String1D
						+ ", "
						+ p14_String
						+ ", "
						+ p15_String
						+ ", "
						+ p16_long
						+ ", "
						+ toThrow
						+ ")");
			}

			java.util.List params = new java.util.ArrayList();
			params.add(p0_String);
			params.add(p1_String);
			params.add(p2_String);
			params.add(p3_String);
			params.add(new Long(p4_long));
			params.add(p5_String);
			params.add(p6_String);
			params.add(p7_String1D);
			params.add(p8_byte1D);
			params.add(p9_String);
			params.add(p10_String1D);
			params.add(p11_String);
			params.add(p12_String);
			params.add(p13_String1D);
			params.add(p14_String);
			params.add(p15_String);
			params.add(new Long(p16_long));

			MockCore
					.setDummy(
							getClassObjectMethodSignature("addShowPublic(String,String,String,String,long,String,String,String1D,byte1D,String,String1D,String,String,String1D,String,String,long)"),
							params, toThrow);
		}

		public void setAddShowPublicDummy(java.lang.String p0_String,
				java.lang.String p1_String, java.lang.String p2_String,
				java.lang.String p3_String, long p4_long,
				java.lang.String p5_String, java.lang.String p6_String,
				java.lang.String[] p7_String1D, byte[] p8_byte1D,
				java.lang.String p9_String, java.lang.String[] p10_String1D,
				java.lang.String p11_String, java.lang.String p12_String,
				java.lang.String[] p13_String1D, java.lang.String p14_String,
				java.lang.String p15_String, long p16_long, boolean toReturn) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("setAddShowPublicDummy("
						+ p0_String
						+ ", "
						+ p1_String
						+ ", "
						+ p2_String
						+ ", "
						+ p3_String
						+ ", "
						+ p4_long
						+ ", "
						+ p5_String
						+ ", "
						+ p6_String
						+ ", "
						+ p7_String1D
						+ ", "
						+ p8_byte1D
						+ ", "
						+ p9_String
						+ ", "
						+ p10_String1D
						+ ", "
						+ p11_String
						+ ", "
						+ p12_String
						+ ", "
						+ p13_String1D
						+ ", "
						+ p14_String
						+ ", "
						+ p15_String
						+ ", "
						+ p16_long
						+ ", "
						+ toReturn
						+ ")");
			}

			java.util.List params = new java.util.ArrayList();
			params.add(p0_String);
			params.add(p1_String);
			params.add(p2_String);
			params.add(p3_String);
			params.add(new Long(p4_long));
			params.add(p5_String);
			params.add(p6_String);
			params.add(p7_String1D);
			params.add(p8_byte1D);
			params.add(p9_String);
			params.add(p10_String1D);
			params.add(p11_String);
			params.add(p12_String);
			params.add(p13_String1D);
			params.add(p14_String);
			params.add(p15_String);
			params.add(new Long(p16_long));

			MockCore
					.setDummy(
							getClassObjectMethodSignature("addShowPublic(String,String,String,String,long,String,String,String1D,byte1D,String,String1D,String,String,String1D,String,String,long)"),
							params, new Boolean(toReturn));
		}

		public void acceptAddShowPublicDummy(Object p0_String,
				Object p1_String, Object p2_String, Object p3_String,
				Object p4_long, Object p5_String, Object p6_String,
				Object p7_String1D, Object p8_byte1D, Object p9_String,
				Object p10_String1D, Object p11_String, Object p12_String,
				Object p13_String1D, Object p14_String, Object p15_String,
				Object p16_long, boolean valueToReturn) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("acceptAddShowPublicDummy("
						+ p0_String
						+ ", "
						+ p1_String
						+ ", "
						+ p2_String
						+ ", "
						+ p3_String
						+ ", "
						+ p4_long
						+ ", "
						+ p5_String
						+ ", "
						+ p6_String
						+ ", "
						+ p7_String1D
						+ ", "
						+ p8_byte1D
						+ ", "
						+ p9_String
						+ ", "
						+ p10_String1D
						+ ", "
						+ p11_String
						+ ", "
						+ p12_String
						+ ", "
						+ p13_String1D
						+ ", "
						+ p14_String
						+ ", "
						+ p15_String
						+ ", "
						+ p16_long
						+ ", "
						+ valueToReturn
						+ ")");
			}

			java.util.List params = new java.util.ArrayList();
			params.add(p0_String);
			params.add(p1_String);
			params.add(p2_String);
			params.add(p3_String);
			params.add(p4_long);
			params.add(p5_String);
			params.add(p6_String);
			params.add(p7_String1D);
			params.add(p8_byte1D);
			params.add(p9_String);
			params.add(p10_String1D);
			params.add(p11_String);
			params.add(p12_String);
			params.add(p13_String1D);
			params.add(p14_String);
			params.add(p15_String);
			params.add(p16_long);

			MockCore
					.setDummy(
							getClassObjectMethodSignature("addShowPublic(String,String,String,String,long,String,String,String1D,byte1D,String,String1D,String,String,String1D,String,String,long)"),
							params, new Boolean(valueToReturn));
		}

		public void acceptAddShowPublicDummy(Object p0_String,
				Object p1_String, Object p2_String, Object p3_String,
				Object p4_long, Object p5_String, Object p6_String,
				Object p7_String1D, Object p8_byte1D, Object p9_String,
				Object p10_String1D, Object p11_String, Object p12_String,
				Object p13_String1D, Object p14_String, Object p15_String,
				Object p16_long, java.lang.Throwable valueToThrow) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("acceptAddShowPublicDummy("
						+ p0_String
						+ ", "
						+ p1_String
						+ ", "
						+ p2_String
						+ ", "
						+ p3_String
						+ ", "
						+ p4_long
						+ ", "
						+ p5_String
						+ ", "
						+ p6_String
						+ ", "
						+ p7_String1D
						+ ", "
						+ p8_byte1D
						+ ", "
						+ p9_String
						+ ", "
						+ p10_String1D
						+ ", "
						+ p11_String
						+ ", "
						+ p12_String
						+ ", "
						+ p13_String1D
						+ ", "
						+ p14_String
						+ ", "
						+ p15_String
						+ ", "
						+ p16_long
						+ ", "
						+ valueToThrow
						+ ")");
			}

			java.util.List params = new java.util.ArrayList();
			params.add(p0_String);
			params.add(p1_String);
			params.add(p2_String);
			params.add(p3_String);
			params.add(p4_long);
			params.add(p5_String);
			params.add(p6_String);
			params.add(p7_String1D);
			params.add(p8_byte1D);
			params.add(p9_String);
			params.add(p10_String1D);
			params.add(p11_String);
			params.add(p12_String);
			params.add(p13_String1D);
			params.add(p14_String);
			params.add(p15_String);
			params.add(p16_long);

			MockCore
					.setDummy(
							getClassObjectMethodSignature("addShowPublic(String,String,String,String,long,String,String,String1D,byte1D,String,String1D,String,String,String1D,String,String,long)"),
							params, valueToThrow);
		}

		public void expectAddAiringPublic(java.lang.String p0_String,
				int p1_int, long p2_long, long p3_long, boolean valueToReturn) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("expectAddAiringPublic("
						+ p0_String
						+ ", "
						+ p1_int
						+ ", "
						+ p2_long
						+ ", "
						+ p3_long
						+ ", "
						+ valueToReturn
						+ ")");
			}

			java.util.List params = new java.util.ArrayList();
			params.add(p0_String);
			params.add(new Integer(p1_int));
			params.add(new Long(p2_long));
			params.add(new Long(p3_long));

			MockCore
					.addExpectedMethodCall(
							getClassObjectMethodSignature("addAiringPublic(String,int,long,long)"),
							params, new Boolean(valueToReturn));
		}

		public void expectAddAiringPublic(java.lang.String p0_String,
				int p1_int, long p2_long, long p3_long,
				java.lang.Throwable toThrow) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("expectAddAiringPublic("
						+ p0_String
						+ ", "
						+ p1_int
						+ ", "
						+ p2_long
						+ ", "
						+ p3_long
						+ ", "
						+ toThrow
						+ ")");
			}

			java.util.List params = new java.util.ArrayList();
			params.add(p0_String);
			params.add(new Integer(p1_int));
			params.add(new Long(p2_long));
			params.add(new Long(p3_long));

			MockCore
					.addExpectedMethodCall(
							getClassObjectMethodSignature("addAiringPublic(String,int,long,long)"),
							params, toThrow);
		}

		public void acceptAddAiringPublic(Object p0_String, Object p1_int,
				Object p2_long, Object p3_long, boolean valueToReturn) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("acceptAddAiringPublic("
						+ p0_String
						+ ", "
						+ p1_int
						+ ", "
						+ p2_long
						+ ", "
						+ p3_long
						+ ", "
						+ valueToReturn
						+ ")");
			}

			java.util.List params = new java.util.ArrayList();
			params.add(p0_String);
			params.add(p1_int);
			params.add(p2_long);
			params.add(p3_long);

			MockCore
					.addExpectedMethodCall(
							getClassObjectMethodSignature("addAiringPublic(String,int,long,long)"),
							params, new Boolean(valueToReturn));
		}

		public void acceptAddAiringPublic(Object p0_String, Object p1_int,
				Object p2_long, Object p3_long, java.lang.Throwable toThrow) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("acceptAddAiringPublic("
						+ p0_String
						+ ", "
						+ p1_int
						+ ", "
						+ p2_long
						+ ", "
						+ p3_long
						+ ", "
						+ toThrow
						+ ")");
			}

			java.util.List params = new java.util.ArrayList();
			params.add(p0_String);
			params.add(p1_int);
			params.add(p2_long);
			params.add(p3_long);

			MockCore
					.addExpectedMethodCall(
							getClassObjectMethodSignature("addAiringPublic(String,int,long,long)"),
							params, toThrow);
		}

		public void setAddAiringPublicDummy(boolean toReturn) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("setAddAiringPublicDummy("
						+ toReturn
						+ ")");
			}

			MockCore
					.setDummy(
							getClassObjectMethodSignature("addAiringPublic(String,int,long,long)"),
							null, new Boolean(toReturn));
		}

		public void setAddAiringPublicDummy(java.lang.Throwable toThrow) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("setAddAiringPublicDummy(" + toThrow + ")");
			}

			MockCore
					.setDummy(
							getClassObjectMethodSignature("addAiringPublic(String,int,long,long)"),
							null, toThrow);
		}

		public void setAddAiringPublicDummy(java.lang.String p0_String,
				int p1_int, long p2_long, long p3_long,
				java.lang.Throwable toThrow) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("setAddAiringPublicDummy("
						+ p0_String
						+ ", "
						+ p1_int
						+ ", "
						+ p2_long
						+ ", "
						+ p3_long
						+ ", "
						+ toThrow
						+ ")");
			}

			java.util.List params = new java.util.ArrayList();
			params.add(p0_String);
			params.add(new Integer(p1_int));
			params.add(new Long(p2_long));
			params.add(new Long(p3_long));

			MockCore
					.setDummy(
							getClassObjectMethodSignature("addAiringPublic(String,int,long,long)"),
							params, toThrow);
		}

		public void setAddAiringPublicDummy(java.lang.String p0_String,
				int p1_int, long p2_long, long p3_long, boolean toReturn) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("setAddAiringPublicDummy("
						+ p0_String
						+ ", "
						+ p1_int
						+ ", "
						+ p2_long
						+ ", "
						+ p3_long
						+ ", "
						+ toReturn
						+ ")");
			}

			java.util.List params = new java.util.ArrayList();
			params.add(p0_String);
			params.add(new Integer(p1_int));
			params.add(new Long(p2_long));
			params.add(new Long(p3_long));

			MockCore
					.setDummy(
							getClassObjectMethodSignature("addAiringPublic(String,int,long,long)"),
							params, new Boolean(toReturn));
		}

		public void acceptAddAiringPublicDummy(Object p0_String, Object p1_int,
				Object p2_long, Object p3_long, boolean valueToReturn) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("acceptAddAiringPublicDummy("
						+ p0_String
						+ ", "
						+ p1_int
						+ ", "
						+ p2_long
						+ ", "
						+ p3_long
						+ ", "
						+ valueToReturn
						+ ")");
			}

			java.util.List params = new java.util.ArrayList();
			params.add(p0_String);
			params.add(p1_int);
			params.add(p2_long);
			params.add(p3_long);

			MockCore
					.setDummy(
							getClassObjectMethodSignature("addAiringPublic(String,int,long,long)"),
							params, new Boolean(valueToReturn));
		}

		public void acceptAddAiringPublicDummy(Object p0_String, Object p1_int,
				Object p2_long, Object p3_long, java.lang.Throwable valueToThrow) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("acceptAddAiringPublicDummy("
						+ p0_String
						+ ", "
						+ p1_int
						+ ", "
						+ p2_long
						+ ", "
						+ p3_long
						+ ", "
						+ valueToThrow
						+ ")");
			}

			java.util.List params = new java.util.ArrayList();
			params.add(p0_String);
			params.add(p1_int);
			params.add(p2_long);
			params.add(p3_long);

			MockCore
					.setDummy(
							getClassObjectMethodSignature("addAiringPublic(String,int,long,long)"),
							params, valueToThrow);
		}

		public void verify() {
			if (LOG.isDebugEnabled()) {
				LOG.debug("verify()");
			}
			MockCore.verify();
		}

		public void startBlock() {
			if (LOG.isDebugEnabled()) {
				LOG.debug("startBlock()");
			}
			MockCore.startBlock();
		}

		public void endBlock() {
			if (LOG.isDebugEnabled()) {
				LOG.debug("endBlock()");
			}
			MockCore.endBlock();
		}

		private String getClassObjectMethodSignature(String method) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("getClassObjectMethodSignature("
						+ method
						+ ")");
			}
			return "sage.MockEPGDBPublic" + "#" + __id() + "#" + method;
		}

		private String id;

		public String __id() {
			if (LOG.isDebugEnabled()) {
				LOG.debug("__id()");
			}
			return id;
		}

		public static final String MOCK_CREATOR_BASE_TYPE = "sage.EPGDBPublic";
	}

	public MockEPGDBPublic() {
		java.util.List params = new java.util.ArrayList();
		Object idex = MockCore.generateId("sage.MockEPGDBPublic", params);
		if (idex instanceof Throwable) {
		}
		if (id != null && !id.equals(idex))
			throw new RuntimeException("expected construction of mock#"
					+ id
					+ " but constructed #"
					+ idex);
		id = (String) idex;
	}

	public static sage.MockEPGDBPublic.PreMock expectCtor() {
		if (LOG.isDebugEnabled()) {
			LOG.debug("expectCtor()");
		}

		java.util.List params = new java.util.ArrayList();
		String id = MockCore.addExpectedCtor(null, "sage.MockEPGDBPublic",
				params);
		return new sage.MockEPGDBPublic.PreMock(id);
	}

	public static sage.MockEPGDBPublic.PreMock expectAnyCtor() {
		if (LOG.isDebugEnabled()) {
			LOG.debug("expectAnyCtor()");
		}

		String id = MockCore.addExpectedAnyCtor(null, "sage.MockEPGDBPublic",
				null);
		return new sage.MockEPGDBPublic.PreMock(id);
	}

	public static sage.MockEPGDBPublic.PreMock expectAnyCtor(Throwable th) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("expectAnyCtor(" + th + ")");
		}

		String id = MockCore.addExpectedAnyCtor(null, "sage.MockEPGDBPublic",
				th);
		return new sage.MockEPGDBPublic.PreMock(id);
	}

	public boolean addChannelPublic(java.lang.String p0_String,
			java.lang.String p1_String, java.lang.String p2_String, int p3_int) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("addChannelPublic("
					+ p0_String
					+ ", "
					+ p1_String
					+ ", "
					+ p2_String
					+ ", "
					+ p3_int
					+ ")");
		}

		this.id = MockCore.enteredMethodBody(this.id);
		Object returnValue = null;
		java.util.List params = new java.util.ArrayList();
		params.add(p0_String);
		params.add(p1_String);
		params.add(p2_String);
		params.add(new Integer(p3_int));

		returnValue = MockCore
				.getReturnValue(
						getClassObjectMethodSignature("addChannelPublic(String,String,String,int)"),
						params, MockCore.OPT_DEFAULT);
		if (returnValue instanceof RuntimeException) {
			throw (RuntimeException) returnValue;
		}
		if (returnValue instanceof Error) {
			throw (Error) returnValue;
		}
		if (returnValue == null)
			throw MockCore
					.makeException("addChannelPublic: called but not prepared");
		return ((Boolean) returnValue).booleanValue();
	}

	public void expectAddChannelPublic(java.lang.String p0_String,
			java.lang.String p1_String, java.lang.String p2_String, int p3_int,
			boolean valueToReturn) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("expectAddChannelPublic("
					+ p0_String
					+ ", "
					+ p1_String
					+ ", "
					+ p2_String
					+ ", "
					+ p3_int
					+ ", "
					+ valueToReturn
					+ ")");
		}

		java.util.List params = new java.util.ArrayList();
		params.add(p0_String);
		params.add(p1_String);
		params.add(p2_String);
		params.add(new Integer(p3_int));

		MockCore
				.addExpectedMethodCall(
						getClassObjectMethodSignature("addChannelPublic(String,String,String,int)"),
						params, new Boolean(valueToReturn));
	}

	public void expectAddChannelPublic(java.lang.String p0_String,
			java.lang.String p1_String, java.lang.String p2_String, int p3_int,
			java.lang.Throwable toThrow) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("expectAddChannelPublic("
					+ p0_String
					+ ", "
					+ p1_String
					+ ", "
					+ p2_String
					+ ", "
					+ p3_int
					+ ", "
					+ toThrow
					+ ")");
		}

		java.util.List params = new java.util.ArrayList();
		params.add(p0_String);
		params.add(p1_String);
		params.add(p2_String);
		params.add(new Integer(p3_int));

		MockCore
				.addExpectedMethodCall(
						getClassObjectMethodSignature("addChannelPublic(String,String,String,int)"),
						params, toThrow);
	}

	public void acceptAddChannelPublic(Object p0_String, Object p1_String,
			Object p2_String, Object p3_int, boolean valueToReturn) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("acceptAddChannelPublic("
					+ p0_String
					+ ", "
					+ p1_String
					+ ", "
					+ p2_String
					+ ", "
					+ p3_int
					+ ", "
					+ valueToReturn
					+ ")");
		}

		java.util.List params = new java.util.ArrayList();
		params.add(p0_String);
		params.add(p1_String);
		params.add(p2_String);
		params.add(p3_int);

		MockCore
				.addExpectedMethodCall(
						getClassObjectMethodSignature("addChannelPublic(String,String,String,int)"),
						params, new Boolean(valueToReturn));
	}

	public void acceptAddChannelPublic(Object p0_String, Object p1_String,
			Object p2_String, Object p3_int, java.lang.Throwable toThrow) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("acceptAddChannelPublic("
					+ p0_String
					+ ", "
					+ p1_String
					+ ", "
					+ p2_String
					+ ", "
					+ p3_int
					+ ", "
					+ toThrow
					+ ")");
		}

		java.util.List params = new java.util.ArrayList();
		params.add(p0_String);
		params.add(p1_String);
		params.add(p2_String);
		params.add(p3_int);

		MockCore
				.addExpectedMethodCall(
						getClassObjectMethodSignature("addChannelPublic(String,String,String,int)"),
						params, toThrow);
	}

	public void setAddChannelPublicDummy(boolean toReturn) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("setAddChannelPublicDummy(" + toReturn + ")");
		}

		MockCore
				.setDummy(
						getClassObjectMethodSignature("addChannelPublic(String,String,String,int)"),
						null, new Boolean(toReturn));
	}

	public void setAddChannelPublicDummy(java.lang.Throwable toThrow) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("setAddChannelPublicDummy(" + toThrow + ")");
		}

		MockCore
				.setDummy(
						getClassObjectMethodSignature("addChannelPublic(String,String,String,int)"),
						null, toThrow);
	}

	public void setAddChannelPublicDummy(java.lang.String p0_String,
			java.lang.String p1_String, java.lang.String p2_String, int p3_int,
			java.lang.Throwable toThrow) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("setAddChannelPublicDummy("
					+ p0_String
					+ ", "
					+ p1_String
					+ ", "
					+ p2_String
					+ ", "
					+ p3_int
					+ ", "
					+ toThrow
					+ ")");
		}

		java.util.List params = new java.util.ArrayList();
		params.add(p0_String);
		params.add(p1_String);
		params.add(p2_String);
		params.add(new Integer(p3_int));

		MockCore
				.setDummy(
						getClassObjectMethodSignature("addChannelPublic(String,String,String,int)"),
						params, toThrow);
	}

	public void setAddChannelPublicDummy(java.lang.String p0_String,
			java.lang.String p1_String, java.lang.String p2_String, int p3_int,
			boolean toReturn) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("setAddChannelPublicDummy("
					+ p0_String
					+ ", "
					+ p1_String
					+ ", "
					+ p2_String
					+ ", "
					+ p3_int
					+ ", "
					+ toReturn
					+ ")");
		}

		java.util.List params = new java.util.ArrayList();
		params.add(p0_String);
		params.add(p1_String);
		params.add(p2_String);
		params.add(new Integer(p3_int));

		MockCore
				.setDummy(
						getClassObjectMethodSignature("addChannelPublic(String,String,String,int)"),
						params, new Boolean(toReturn));
	}

	public void acceptAddChannelPublicDummy(Object p0_String, Object p1_String,
			Object p2_String, Object p3_int, boolean valueToReturn) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("acceptAddChannelPublicDummy("
					+ p0_String
					+ ", "
					+ p1_String
					+ ", "
					+ p2_String
					+ ", "
					+ p3_int
					+ ", "
					+ valueToReturn
					+ ")");
		}

		java.util.List params = new java.util.ArrayList();
		params.add(p0_String);
		params.add(p1_String);
		params.add(p2_String);
		params.add(p3_int);

		MockCore
				.setDummy(
						getClassObjectMethodSignature("addChannelPublic(String,String,String,int)"),
						params, new Boolean(valueToReturn));
	}

	public void acceptAddChannelPublicDummy(Object p0_String, Object p1_String,
			Object p2_String, Object p3_int, java.lang.Throwable valueToThrow) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("acceptAddChannelPublicDummy("
					+ p0_String
					+ ", "
					+ p1_String
					+ ", "
					+ p2_String
					+ ", "
					+ p3_int
					+ ", "
					+ valueToThrow
					+ ")");
		}

		java.util.List params = new java.util.ArrayList();
		params.add(p0_String);
		params.add(p1_String);
		params.add(p2_String);
		params.add(p3_int);

		MockCore
				.setDummy(
						getClassObjectMethodSignature("addChannelPublic(String,String,String,int)"),
						params, valueToThrow);
	}

	public void setLineup(long p0_long, java.util.Map p1_Map) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("setLineup("
					+ p0_long
					+ ", "
					+ mapToString(p1_Map)
					+ ")");
		}

		this.id = MockCore.enteredMethodBody(this.id);
		Object returnValue = null;
		java.util.List params = new java.util.ArrayList();
		params.add(new Long(p0_long));
		params.add(mapToString(p1_Map));

		returnValue = MockCore.getReturnValue(
				getClassObjectMethodSignature("setLineup(long,Map)"), params,
				MockCore.OPT_UNEXPECTED_VOID_OKAY);
		if (returnValue instanceof RuntimeException) {
			throw (RuntimeException) returnValue;
		}
		if (returnValue instanceof Error) {
			throw (Error) returnValue;
		}
	}

    private static String mapToString(Object aMap) {
    	return mapToString((Map) aMap);
    }
    
	private static String mapToString(Map aMap) {
		StringBuffer sb = new StringBuffer();
		Iterator it = aMap.entrySet().iterator();
		sb.append("{");
		if (it.hasNext()) {
			Map.Entry entry = (Entry) it.next();
			sb.append(entry.getKey());
			sb.append("=");
			sb.append(toString(entry.getValue()));
			while (it.hasNext()) {
				sb.append(", ");
				entry = (Entry) it.next();
				sb.append(entry.getKey());
				sb.append("=");
				sb.append(toString(entry.getValue()));
			}
		}
		sb.append("}");
		return sb.toString();
	}

	private static String toString(Object aValue) {
		if (aValue == null) {
			return null;
		}
		if (aValue.getClass().isArray()) {
			return arrayToString((Object[]) aValue);
		}
		return aValue.toString();
	}

	private static String arrayToString(Object[] aObjects) {
		if (aObjects == null) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		if (aObjects.length > 0) {
			sb.append(toString(aObjects[0]));
			for (int i = 1; i < aObjects.length; ++i) {
				sb.append(", ");
				sb.append(toString(aObjects[i]));
			}
		}
		sb.append("]");
		return sb.toString();
	}

	private static String arrayToString(byte[] aBytes) {
		if (aBytes == null) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		if (aBytes.length > 0) {
			sb.append(aBytes[0]);
			for (int i = 1; i < aBytes.length; ++i) {
				sb.append(", ");
				sb.append(aBytes[i]);
			}
		}
		sb.append("]");
		return sb.toString();
	}

	public void expectSetLineup(long p0_long, java.util.Map p1_Map) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("expectSetLineup("
					+ p0_long
					+ ", "
					+ mapToString(p1_Map)
					+ ")");
		}

		java.util.List params = new java.util.ArrayList();
		params.add(new Long(p0_long));
		params.add(mapToString(p1_Map));

		MockCore.addExpectedMethodCall(
				getClassObjectMethodSignature("setLineup(long,Map)"), params);
	}

	public void expectSetLineup(long p0_long, java.util.Map p1_Map,
			java.lang.Throwable toThrow) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("expectSetLineup("
					+ p0_long
					+ ", "
					+ mapToString(p1_Map)
					+ ", "
					+ toThrow
					+ ")");
		}

		java.util.List params = new java.util.ArrayList();
		params.add(new Long(p0_long));
		params.add(mapToString(p1_Map));

		MockCore.addExpectedMethodCall(
				getClassObjectMethodSignature("setLineup(long,Map)"), params,
				toThrow);
	}

	public void acceptSetLineup(Object p0_long, Object p1_Map) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("acceptSetLineup("
					+ p0_long
					+ ", "
					+ mapToString(p1_Map)
					+ ")");
		}

		java.util.List params = new java.util.ArrayList();
		params.add(p0_long);
		params.add(mapToString(p1_Map));

		MockCore.addExpectedMethodCall(
				getClassObjectMethodSignature("setLineup(long,Map)"), params);
	}

	public void acceptSetLineup(Object p0_long, Object p1_Map,
			java.lang.Throwable toThrow) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("acceptSetLineup("
					+ p0_long
					+ ", "
					+ mapToString(p1_Map)
					+ ", "
					+ toThrow
					+ ")");
		}

		java.util.List params = new java.util.ArrayList();
		params.add(p0_long);
		params.add(mapToString(p1_Map));

		MockCore.addExpectedMethodCall(
				getClassObjectMethodSignature("setLineup(long,Map)"), params,
				toThrow);
	}

	public void setSetLineupDummy() {
		if (LOG.isDebugEnabled()) {
			LOG.debug("setSetLineupDummy()");
		}

		MockCore.setDummy(getClassObjectMethodSignature("setLineup(long,Map)"),
				null, null);
	}

	public void setSetLineupDummy(java.lang.Throwable toThrow) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("setSetLineupDummy(" + toThrow + ")");
		}

		MockCore.setDummy(getClassObjectMethodSignature("setLineup(long,Map)"),
				null, toThrow);
	}

	public void setSetLineupDummy(long p0_long, java.util.Map p1_Map,
			java.lang.Throwable toThrow) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("setSetLineupDummy("
					+ p0_long
					+ ", "
					+ mapToString(p1_Map)
					+ ", "
					+ toThrow
					+ ")");
		}

		java.util.List params = new java.util.ArrayList();
		params.add(new Long(p0_long));
		params.add(mapToString(p1_Map));

		MockCore.setDummy(getClassObjectMethodSignature("setLineup(long,Map)"),
				params, toThrow);
	}

	public void acceptSetLineupDummy(Object p0_long, Object p1_Map,
			java.lang.Throwable valueToThrow) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("acceptSetLineupDummy("
					+ p0_long
					+ ", "
					+ mapToString(p1_Map)
					+ ", "
					+ valueToThrow
					+ ")");
		}

		java.util.List params = new java.util.ArrayList();
		params.add(p0_long);
		params.add(mapToString(p1_Map));

		MockCore.setDummy(getClassObjectMethodSignature("setLineup(long,Map)"),
				params, valueToThrow);
	}

	public boolean addShowPublic(java.lang.String p0_String,
			java.lang.String p1_String, java.lang.String p2_String,
			java.lang.String p3_String, long p4_long,
			java.lang.String p5_String, java.lang.String p6_String,
			java.lang.String[] p7_String1D, byte[] p8_byte1D,
			java.lang.String p9_String, java.lang.String[] p10_String1D,
			java.lang.String p11_String, java.lang.String p12_String,
			java.lang.String[] p13_String1D, java.lang.String p14_String,
			java.lang.String p15_String, long p16_long) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("addShowPublic("
					+ p0_String
					+ ", "
					+ p1_String
					+ ", "
					+ p2_String
					+ ", "
					+ p3_String
					+ ", "
					+ p4_long
					+ ", "
					+ p5_String
					+ ", "
					+ p6_String
					+ ", "
					+ arrayToString(p7_String1D)
					+ ", "
					+ arrayToString(p8_byte1D)
					+ ", "
					+ p9_String
					+ ", "
					+ arrayToString(p10_String1D)
					+ ", "
					+ p11_String
					+ ", "
					+ p12_String
					+ ", "
					+ arrayToString(p13_String1D)
					+ ", "
					+ p14_String
					+ ", "
					+ p15_String
					+ ", "
					+ p16_long
					+ ")");
		}

		id = MockCore.enteredMethodBody(id);
		Object returnValue = null;
		java.util.List params = new java.util.ArrayList();
		params.add(p0_String);
		params.add(p1_String);
		params.add(p2_String);
		params.add(p3_String);
		params.add(new Long(p4_long));
		params.add(p5_String);
		params.add(p6_String);
		params.add(arrayToString(p7_String1D));
		params.add(arrayToString(p8_byte1D));
		params.add(p9_String);
		params.add(arrayToString(p10_String1D));
		params.add(p11_String);
		params.add(p12_String);
		params.add(arrayToString(p13_String1D));
		params.add(p14_String);
		params.add(p15_String);
		params.add(new Long(p16_long));

		returnValue = MockCore
				.getReturnValue(
						getClassObjectMethodSignature("addShowPublic(String,String,String,String,long,String,String,String1D,byte1D,String,String1D,String,String,String1D,String,String,long)"),
						params, MockCore.OPT_DEFAULT);
		if (returnValue instanceof RuntimeException) {
			throw (RuntimeException) returnValue;
		}
		if (returnValue instanceof Error) {
			throw (Error) returnValue;
		}
		if (returnValue == null)
			throw MockCore
					.makeException("addShowPublic: called but not prepared");
		return ((Boolean) returnValue).booleanValue();
	}

	public void expectAddShowPublic(java.lang.String p0_String,
			java.lang.String p1_String, java.lang.String p2_String,
			java.lang.String p3_String, long p4_long,
			java.lang.String p5_String, java.lang.String p6_String,
			java.lang.String[] p7_String1D, byte[] p8_byte1D,
			java.lang.String p9_String, java.lang.String[] p10_String1D,
			java.lang.String p11_String, java.lang.String p12_String,
			java.lang.String[] p13_String1D, java.lang.String p14_String,
			java.lang.String p15_String, long p16_long, boolean valueToReturn) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("expectAddShowPublic("
					+ p0_String
					+ ", "
					+ p1_String
					+ ", "
					+ p2_String
					+ ", "
					+ p3_String
					+ ", "
					+ p4_long
					+ ", "
					+ p5_String
					+ ", "
					+ p6_String
					+ ", "
					+ arrayToString(p7_String1D)
					+ ", "
					+ arrayToString(p8_byte1D)
					+ ", "
					+ p9_String
					+ ", "
					+ arrayToString(p10_String1D)
					+ ", "
					+ p11_String
					+ ", "
					+ p12_String
					+ ", "
					+ arrayToString(p13_String1D)
					+ ", "
					+ p14_String
					+ ", "
					+ p15_String
					+ ", "
					+ p16_long
					+ ", "
					+ valueToReturn
					+ ")");
		}

		java.util.List params = new java.util.ArrayList();
		params.add(p0_String);
		params.add(p1_String);
		params.add(p2_String);
		params.add(p3_String);
		params.add(new Long(p4_long));
		params.add(p5_String);
		params.add(p6_String);
		params.add(arrayToString(p7_String1D));
		params.add(arrayToString(p8_byte1D));
		params.add(p9_String);
		params.add(arrayToString(p10_String1D));
		params.add(p11_String);
		params.add(p12_String);
		params.add(arrayToString(p13_String1D));
		params.add(p14_String);
		params.add(p15_String);
		params.add(new Long(p16_long));

		MockCore
				.addExpectedMethodCall(
						getClassObjectMethodSignature("addShowPublic(String,String,String,String,long,String,String,String1D,byte1D,String,String1D,String,String,String1D,String,String,long)"),
						params, new Boolean(valueToReturn));
	}

	public void expectAddShowPublic(java.lang.String p0_String,
			java.lang.String p1_String, java.lang.String p2_String,
			java.lang.String p3_String, long p4_long,
			java.lang.String p5_String, java.lang.String p6_String,
			java.lang.String[] p7_String1D, byte[] p8_byte1D,
			java.lang.String p9_String, java.lang.String[] p10_String1D,
			java.lang.String p11_String, java.lang.String p12_String,
			java.lang.String[] p13_String1D, java.lang.String p14_String,
			java.lang.String p15_String, long p16_long,
			java.lang.Throwable toThrow) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("expectAddShowPublic("
					+ p0_String
					+ ", "
					+ p1_String
					+ ", "
					+ p2_String
					+ ", "
					+ p3_String
					+ ", "
					+ p4_long
					+ ", "
					+ p5_String
					+ ", "
					+ p6_String
					+ ", "
					+ p7_String1D
					+ ", "
					+ p8_byte1D
					+ ", "
					+ p9_String
					+ ", "
					+ p10_String1D
					+ ", "
					+ p11_String
					+ ", "
					+ p12_String
					+ ", "
					+ p13_String1D
					+ ", "
					+ p14_String
					+ ", "
					+ p15_String
					+ ", "
					+ p16_long
					+ ", "
					+ toThrow
					+ ")");
		}

		java.util.List params = new java.util.ArrayList();
		params.add(p0_String);
		params.add(p1_String);
		params.add(p2_String);
		params.add(p3_String);
		params.add(new Long(p4_long));
		params.add(p5_String);
		params.add(p6_String);
		params.add(p7_String1D);
		params.add(p8_byte1D);
		params.add(p9_String);
		params.add(p10_String1D);
		params.add(p11_String);
		params.add(p12_String);
		params.add(p13_String1D);
		params.add(p14_String);
		params.add(p15_String);
		params.add(new Long(p16_long));

		MockCore
				.addExpectedMethodCall(
						getClassObjectMethodSignature("addShowPublic(String,String,String,String,long,String,String,String1D,byte1D,String,String1D,String,String,String1D,String,String,long)"),
						params, toThrow);
	}

	public void acceptAddShowPublic(Object p0_String, Object p1_String,
			Object p2_String, Object p3_String, Object p4_long,
			Object p5_String, Object p6_String, Object p7_String1D,
			Object p8_byte1D, Object p9_String, Object p10_String1D,
			Object p11_String, Object p12_String, Object p13_String1D,
			Object p14_String, Object p15_String, Object p16_long,
			boolean valueToReturn) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("acceptAddShowPublic("
					+ p0_String
					+ ", "
					+ p1_String
					+ ", "
					+ p2_String
					+ ", "
					+ p3_String
					+ ", "
					+ p4_long
					+ ", "
					+ p5_String
					+ ", "
					+ p6_String
					+ ", "
					+ p7_String1D
					+ ", "
					+ p8_byte1D
					+ ", "
					+ p9_String
					+ ", "
					+ p10_String1D
					+ ", "
					+ p11_String
					+ ", "
					+ p12_String
					+ ", "
					+ p13_String1D
					+ ", "
					+ p14_String
					+ ", "
					+ p15_String
					+ ", "
					+ p16_long
					+ ", "
					+ valueToReturn
					+ ")");
		}

		java.util.List params = new java.util.ArrayList();
		params.add(p0_String);
		params.add(p1_String);
		params.add(p2_String);
		params.add(p3_String);
		params.add(p4_long);
		params.add(p5_String);
		params.add(p6_String);
		params.add(p7_String1D);
		params.add(p8_byte1D);
		params.add(p9_String);
		params.add(p10_String1D);
		params.add(p11_String);
		params.add(p12_String);
		params.add(p13_String1D);
		params.add(p14_String);
		params.add(p15_String);
		params.add(p16_long);

		MockCore
				.addExpectedMethodCall(
						getClassObjectMethodSignature("addShowPublic(String,String,String,String,long,String,String,String1D,byte1D,String,String1D,String,String,String1D,String,String,long)"),
						params, new Boolean(valueToReturn));
	}

	public void acceptAddShowPublic(Object p0_String, Object p1_String,
			Object p2_String, Object p3_String, Object p4_long,
			Object p5_String, Object p6_String, Object p7_String1D,
			Object p8_byte1D, Object p9_String, Object p10_String1D,
			Object p11_String, Object p12_String, Object p13_String1D,
			Object p14_String, Object p15_String, Object p16_long,
			java.lang.Throwable toThrow) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("acceptAddShowPublic("
					+ p0_String
					+ ", "
					+ p1_String
					+ ", "
					+ p2_String
					+ ", "
					+ p3_String
					+ ", "
					+ p4_long
					+ ", "
					+ p5_String
					+ ", "
					+ p6_String
					+ ", "
					+ p7_String1D
					+ ", "
					+ p8_byte1D
					+ ", "
					+ p9_String
					+ ", "
					+ p10_String1D
					+ ", "
					+ p11_String
					+ ", "
					+ p12_String
					+ ", "
					+ p13_String1D
					+ ", "
					+ p14_String
					+ ", "
					+ p15_String
					+ ", "
					+ p16_long
					+ ", "
					+ toThrow
					+ ")");
		}

		java.util.List params = new java.util.ArrayList();
		params.add(p0_String);
		params.add(p1_String);
		params.add(p2_String);
		params.add(p3_String);
		params.add(p4_long);
		params.add(p5_String);
		params.add(p6_String);
		params.add(p7_String1D);
		params.add(p8_byte1D);
		params.add(p9_String);
		params.add(p10_String1D);
		params.add(p11_String);
		params.add(p12_String);
		params.add(p13_String1D);
		params.add(p14_String);
		params.add(p15_String);
		params.add(p16_long);

		MockCore
				.addExpectedMethodCall(
						getClassObjectMethodSignature("addShowPublic(String,String,String,String,long,String,String,String1D,byte1D,String,String1D,String,String,String1D,String,String,long)"),
						params, toThrow);
	}

	public void setAddShowPublicDummy(boolean toReturn) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("setAddShowPublicDummy(" + toReturn + ")");
		}

		MockCore
				.setDummy(
						getClassObjectMethodSignature("addShowPublic(String,String,String,String,long,String,String,String1D,byte1D,String,String1D,String,String,String1D,String,String,long)"),
						null, new Boolean(toReturn));
	}

	public void setAddShowPublicDummy(java.lang.Throwable toThrow) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("setAddShowPublicDummy(" + toThrow + ")");
		}

		MockCore
				.setDummy(
						getClassObjectMethodSignature("addShowPublic(String,String,String,String,long,String,String,String1D,byte1D,String,String1D,String,String,String1D,String,String,long)"),
						null, toThrow);
	}

	public void setAddShowPublicDummy(java.lang.String p0_String,
			java.lang.String p1_String, java.lang.String p2_String,
			java.lang.String p3_String, long p4_long,
			java.lang.String p5_String, java.lang.String p6_String,
			java.lang.String[] p7_String1D, byte[] p8_byte1D,
			java.lang.String p9_String, java.lang.String[] p10_String1D,
			java.lang.String p11_String, java.lang.String p12_String,
			java.lang.String[] p13_String1D, java.lang.String p14_String,
			java.lang.String p15_String, long p16_long,
			java.lang.Throwable toThrow) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("setAddShowPublicDummy("
					+ p0_String
					+ ", "
					+ p1_String
					+ ", "
					+ p2_String
					+ ", "
					+ p3_String
					+ ", "
					+ p4_long
					+ ", "
					+ p5_String
					+ ", "
					+ p6_String
					+ ", "
					+ p7_String1D
					+ ", "
					+ p8_byte1D
					+ ", "
					+ p9_String
					+ ", "
					+ p10_String1D
					+ ", "
					+ p11_String
					+ ", "
					+ p12_String
					+ ", "
					+ p13_String1D
					+ ", "
					+ p14_String
					+ ", "
					+ p15_String
					+ ", "
					+ p16_long
					+ ", "
					+ toThrow
					+ ")");
		}

		java.util.List params = new java.util.ArrayList();
		params.add(p0_String);
		params.add(p1_String);
		params.add(p2_String);
		params.add(p3_String);
		params.add(new Long(p4_long));
		params.add(p5_String);
		params.add(p6_String);
		params.add(p7_String1D);
		params.add(p8_byte1D);
		params.add(p9_String);
		params.add(p10_String1D);
		params.add(p11_String);
		params.add(p12_String);
		params.add(p13_String1D);
		params.add(p14_String);
		params.add(p15_String);
		params.add(new Long(p16_long));

		MockCore
				.setDummy(
						getClassObjectMethodSignature("addShowPublic(String,String,String,String,long,String,String,String1D,byte1D,String,String1D,String,String,String1D,String,String,long)"),
						params, toThrow);
	}

	public void setAddShowPublicDummy(java.lang.String p0_String,
			java.lang.String p1_String, java.lang.String p2_String,
			java.lang.String p3_String, long p4_long,
			java.lang.String p5_String, java.lang.String p6_String,
			java.lang.String[] p7_String1D, byte[] p8_byte1D,
			java.lang.String p9_String, java.lang.String[] p10_String1D,
			java.lang.String p11_String, java.lang.String p12_String,
			java.lang.String[] p13_String1D, java.lang.String p14_String,
			java.lang.String p15_String, long p16_long, boolean toReturn) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("setAddShowPublicDummy("
					+ p0_String
					+ ", "
					+ p1_String
					+ ", "
					+ p2_String
					+ ", "
					+ p3_String
					+ ", "
					+ p4_long
					+ ", "
					+ p5_String
					+ ", "
					+ p6_String
					+ ", "
					+ p7_String1D
					+ ", "
					+ p8_byte1D
					+ ", "
					+ p9_String
					+ ", "
					+ p10_String1D
					+ ", "
					+ p11_String
					+ ", "
					+ p12_String
					+ ", "
					+ p13_String1D
					+ ", "
					+ p14_String
					+ ", "
					+ p15_String
					+ ", "
					+ p16_long
					+ ", "
					+ toReturn
					+ ")");
		}

		java.util.List params = new java.util.ArrayList();
		params.add(p0_String);
		params.add(p1_String);
		params.add(p2_String);
		params.add(p3_String);
		params.add(new Long(p4_long));
		params.add(p5_String);
		params.add(p6_String);
		params.add(p7_String1D);
		params.add(p8_byte1D);
		params.add(p9_String);
		params.add(p10_String1D);
		params.add(p11_String);
		params.add(p12_String);
		params.add(p13_String1D);
		params.add(p14_String);
		params.add(p15_String);
		params.add(new Long(p16_long));

		MockCore
				.setDummy(
						getClassObjectMethodSignature("addShowPublic(String,String,String,String,long,String,String,String1D,byte1D,String,String1D,String,String,String1D,String,String,long)"),
						params, new Boolean(toReturn));
	}

	public void acceptAddShowPublicDummy(Object p0_String, Object p1_String,
			Object p2_String, Object p3_String, Object p4_long,
			Object p5_String, Object p6_String, Object p7_String1D,
			Object p8_byte1D, Object p9_String, Object p10_String1D,
			Object p11_String, Object p12_String, Object p13_String1D,
			Object p14_String, Object p15_String, Object p16_long,
			boolean valueToReturn) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("acceptAddShowPublicDummy("
					+ p0_String
					+ ", "
					+ p1_String
					+ ", "
					+ p2_String
					+ ", "
					+ p3_String
					+ ", "
					+ p4_long
					+ ", "
					+ p5_String
					+ ", "
					+ p6_String
					+ ", "
					+ p7_String1D
					+ ", "
					+ p8_byte1D
					+ ", "
					+ p9_String
					+ ", "
					+ p10_String1D
					+ ", "
					+ p11_String
					+ ", "
					+ p12_String
					+ ", "
					+ p13_String1D
					+ ", "
					+ p14_String
					+ ", "
					+ p15_String
					+ ", "
					+ p16_long
					+ ", "
					+ valueToReturn
					+ ")");
		}

		java.util.List params = new java.util.ArrayList();
		params.add(p0_String);
		params.add(p1_String);
		params.add(p2_String);
		params.add(p3_String);
		params.add(p4_long);
		params.add(p5_String);
		params.add(p6_String);
		params.add(p7_String1D);
		params.add(p8_byte1D);
		params.add(p9_String);
		params.add(p10_String1D);
		params.add(p11_String);
		params.add(p12_String);
		params.add(p13_String1D);
		params.add(p14_String);
		params.add(p15_String);
		params.add(p16_long);

		MockCore
				.setDummy(
						getClassObjectMethodSignature("addShowPublic(String,String,String,String,long,String,String,String1D,byte1D,String,String1D,String,String,String1D,String,String,long)"),
						params, new Boolean(valueToReturn));
	}

	public void acceptAddShowPublicDummy(Object p0_String, Object p1_String,
			Object p2_String, Object p3_String, Object p4_long,
			Object p5_String, Object p6_String, Object p7_String1D,
			Object p8_byte1D, Object p9_String, Object p10_String1D,
			Object p11_String, Object p12_String, Object p13_String1D,
			Object p14_String, Object p15_String, Object p16_long,
			java.lang.Throwable valueToThrow) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("acceptAddShowPublicDummy("
					+ p0_String
					+ ", "
					+ p1_String
					+ ", "
					+ p2_String
					+ ", "
					+ p3_String
					+ ", "
					+ p4_long
					+ ", "
					+ p5_String
					+ ", "
					+ p6_String
					+ ", "
					+ p7_String1D
					+ ", "
					+ p8_byte1D
					+ ", "
					+ p9_String
					+ ", "
					+ p10_String1D
					+ ", "
					+ p11_String
					+ ", "
					+ p12_String
					+ ", "
					+ p13_String1D
					+ ", "
					+ p14_String
					+ ", "
					+ p15_String
					+ ", "
					+ p16_long
					+ ", "
					+ valueToThrow
					+ ")");
		}

		java.util.List params = new java.util.ArrayList();
		params.add(p0_String);
		params.add(p1_String);
		params.add(p2_String);
		params.add(p3_String);
		params.add(p4_long);
		params.add(p5_String);
		params.add(p6_String);
		params.add(p7_String1D);
		params.add(p8_byte1D);
		params.add(p9_String);
		params.add(p10_String1D);
		params.add(p11_String);
		params.add(p12_String);
		params.add(p13_String1D);
		params.add(p14_String);
		params.add(p15_String);
		params.add(p16_long);

		MockCore
				.setDummy(
						getClassObjectMethodSignature("addShowPublic(String,String,String,String,long,String,String,String1D,byte1D,String,String1D,String,String,String1D,String,String,long)"),
						params, valueToThrow);
	}

	public boolean addAiringPublic(java.lang.String p0_String, int p1_int,
			long p2_long, long p3_long) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("addAiringPublic("
					+ p0_String
					+ ", "
					+ p1_int
					+ ", "
					+ p2_long
					+ ", "
					+ p3_long
					+ ")");
		}

		id = MockCore.enteredMethodBody(id);
		Object returnValue = null;
		java.util.List params = new java.util.ArrayList();
		params.add(p0_String);
		params.add(new Integer(p1_int));
		params.add(new Long(p2_long));
		params.add(new Long(p3_long));

		returnValue = MockCore
				.getReturnValue(
						getClassObjectMethodSignature("addAiringPublic(String,int,long,long)"),
						params, MockCore.OPT_DEFAULT);
		if (returnValue instanceof RuntimeException) {
			throw (RuntimeException) returnValue;
		}
		if (returnValue instanceof Error) {
			throw (Error) returnValue;
		}
		if (returnValue == null)
			throw MockCore
					.makeException("addAiringPublic: called but not prepared");
		return ((Boolean) returnValue).booleanValue();
	}

    @Override
    public boolean addAiringDetailedPublic(String extID, int stationID, long startTime, long duration, int partNumber, int totalParts, String parentalRating, boolean hdtv, boolean stereo, boolean closedCaptioning, boolean sap, boolean subtitled, String premierFinale) {
        return false;
    }

    @Override
    public boolean addSeriesInfoPublic(int seriesID, String title, String network, String description, String history, String premiereDate, String finaleDate, String airDOW, String airHrMin, String imageURL, String[] people, String[] characters) {
        return false;
    }

    public void expectAddAiringPublic(java.lang.String p0_String, int p1_int,
			long p2_long, long p3_long, boolean valueToReturn) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("expectAddAiringPublic("
					+ p0_String
					+ ", "
					+ p1_int
					+ ", "
					+ p2_long
					+ ", "
					+ p3_long
					+ ", "
					+ valueToReturn
					+ ")");
		}

		java.util.List params = new java.util.ArrayList();
		params.add(p0_String);
		params.add(new Integer(p1_int));
		params.add(new Long(p2_long));
		params.add(new Long(p3_long));

		MockCore
				.addExpectedMethodCall(
						getClassObjectMethodSignature("addAiringPublic(String,int,long,long)"),
						params, new Boolean(valueToReturn));
	}

	public void expectAddAiringPublic(java.lang.String p0_String, int p1_int,
			long p2_long, long p3_long, java.lang.Throwable toThrow) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("expectAddAiringPublic("
					+ p0_String
					+ ", "
					+ p1_int
					+ ", "
					+ p2_long
					+ ", "
					+ p3_long
					+ ", "
					+ toThrow
					+ ")");
		}

		java.util.List params = new java.util.ArrayList();
		params.add(p0_String);
		params.add(new Integer(p1_int));
		params.add(new Long(p2_long));
		params.add(new Long(p3_long));

		MockCore
				.addExpectedMethodCall(
						getClassObjectMethodSignature("addAiringPublic(String,int,long,long)"),
						params, toThrow);
	}

	public void acceptAddAiringPublic(Object p0_String, Object p1_int,
			Object p2_long, Object p3_long, boolean valueToReturn) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("acceptAddAiringPublic("
					+ p0_String
					+ ", "
					+ p1_int
					+ ", "
					+ p2_long
					+ ", "
					+ p3_long
					+ ", "
					+ valueToReturn
					+ ")");
		}

		java.util.List params = new java.util.ArrayList();
		params.add(p0_String);
		params.add(p1_int);
		params.add(p2_long);
		params.add(p3_long);

		MockCore
				.addExpectedMethodCall(
						getClassObjectMethodSignature("addAiringPublic(String,int,long,long)"),
						params, new Boolean(valueToReturn));
	}

	public void acceptAddAiringPublic(Object p0_String, Object p1_int,
			Object p2_long, Object p3_long, java.lang.Throwable toThrow) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("acceptAddAiringPublic("
					+ p0_String
					+ ", "
					+ p1_int
					+ ", "
					+ p2_long
					+ ", "
					+ p3_long
					+ ", "
					+ toThrow
					+ ")");
		}

		java.util.List params = new java.util.ArrayList();
		params.add(p0_String);
		params.add(p1_int);
		params.add(p2_long);
		params.add(p3_long);

		MockCore
				.addExpectedMethodCall(
						getClassObjectMethodSignature("addAiringPublic(String,int,long,long)"),
						params, toThrow);
	}

	public void setAddAiringPublicDummy(boolean toReturn) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("setAddAiringPublicDummy(" + toReturn + ")");
		}

		MockCore
				.setDummy(
						getClassObjectMethodSignature("addAiringPublic(String,int,long,long)"),
						null, new Boolean(toReturn));
	}

	public void setAddAiringPublicDummy(java.lang.Throwable toThrow) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("setAddAiringPublicDummy(" + toThrow + ")");
		}

		MockCore
				.setDummy(
						getClassObjectMethodSignature("addAiringPublic(String,int,long,long)"),
						null, toThrow);
	}

	public void setAddAiringPublicDummy(java.lang.String p0_String, int p1_int,
			long p2_long, long p3_long, java.lang.Throwable toThrow) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("setAddAiringPublicDummy("
					+ p0_String
					+ ", "
					+ p1_int
					+ ", "
					+ p2_long
					+ ", "
					+ p3_long
					+ ", "
					+ toThrow
					+ ")");
		}

		java.util.List params = new java.util.ArrayList();
		params.add(p0_String);
		params.add(new Integer(p1_int));
		params.add(new Long(p2_long));
		params.add(new Long(p3_long));

		MockCore
				.setDummy(
						getClassObjectMethodSignature("addAiringPublic(String,int,long,long)"),
						params, toThrow);
	}

	public void setAddAiringPublicDummy(java.lang.String p0_String, int p1_int,
			long p2_long, long p3_long, boolean toReturn) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("setAddAiringPublicDummy("
					+ p0_String
					+ ", "
					+ p1_int
					+ ", "
					+ p2_long
					+ ", "
					+ p3_long
					+ ", "
					+ toReturn
					+ ")");
		}

		java.util.List params = new java.util.ArrayList();
		params.add(p0_String);
		params.add(new Integer(p1_int));
		params.add(new Long(p2_long));
		params.add(new Long(p3_long));

		MockCore
				.setDummy(
						getClassObjectMethodSignature("addAiringPublic(String,int,long,long)"),
						params, new Boolean(toReturn));
	}

	public void acceptAddAiringPublicDummy(Object p0_String, Object p1_int,
			Object p2_long, Object p3_long, boolean valueToReturn) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("acceptAddAiringPublicDummy("
					+ p0_String
					+ ", "
					+ p1_int
					+ ", "
					+ p2_long
					+ ", "
					+ p3_long
					+ ", "
					+ valueToReturn
					+ ")");
		}

		java.util.List params = new java.util.ArrayList();
		params.add(p0_String);
		params.add(p1_int);
		params.add(p2_long);
		params.add(p3_long);

		MockCore
				.setDummy(
						getClassObjectMethodSignature("addAiringPublic(String,int,long,long)"),
						params, new Boolean(valueToReturn));
	}

	public void acceptAddAiringPublicDummy(Object p0_String, Object p1_int,
			Object p2_long, Object p3_long, java.lang.Throwable valueToThrow) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("acceptAddAiringPublicDummy("
					+ p0_String
					+ ", "
					+ p1_int
					+ ", "
					+ p2_long
					+ ", "
					+ p3_long
					+ ", "
					+ valueToThrow
					+ ")");
		}

		java.util.List params = new java.util.ArrayList();
		params.add(p0_String);
		params.add(p1_int);
		params.add(p2_long);
		params.add(p3_long);

		MockCore
				.setDummy(
						getClassObjectMethodSignature("addAiringPublic(String,int,long,long)"),
						params, valueToThrow);
	}

	public void verify() {
		if (LOG.isDebugEnabled()) {
			LOG.debug("verify()");
		}
		MockCore.verify();
	}

	public void startBlock() {
		if (LOG.isDebugEnabled()) {
			LOG.debug("startBlock()");
		}
		MockCore.startBlock();
	}

	public void endBlock() {
		if (LOG.isDebugEnabled()) {
			LOG.debug("endBlock()");
		}
		MockCore.endBlock();
	}

	private String getClassObjectMethodSignature(String method) {
		return "sage.MockEPGDBPublic" + "#" + __id() + "#" + method;
	}

	private String id;

	public String __id() {
		return id;
	}

	public static final String MOCK_CREATOR_BASE_TYPE = "sage.EPGDBPublic";
}