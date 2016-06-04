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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.lang.ref.SoftReference;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.CRC32;

//import org.apache.xerces.parsers.SAXParser;
import javax.xml.parsers.*;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;

import org.xml.sax.helpers.XMLReaderFactory;
import sage.EPGDBPublic;

/**
 * More information at: http://mta.techno.org/epg/
 * 
 * @author mta
 */
public final class XMLTVImportPlugin implements sage.EPGImportPlugin,
        ContentHandler, ErrorHandler {

    /**
     * The newline sequence.
     */
    private static final String NEWLINE = System.getProperty("line.separator");

    /**
     * The date format used for logging.
     */
    private static final SimpleDateFormat DF_LOG = new SimpleDateFormat(
            "dd-MM-yyyy HH:mm:ss,SSS ");

    /**
     * The exceptions logging file.
     */
    private static final File ERROR_LOG = new File("xmltv.error.log");

    /**
     * The logging file.
     */
    private static final File LOG = new File("xmltv.log");

    /**
     * The error log writer.
     */
    private static PrintStream sErrorLogPrinter;

    /**
     * The log writer.
     */
    private static PrintStream sLogPrinter;

    /**
     * The number of milliseconds that a log should be kept. 
     * 
     * 16 hours.  
     */
    private static final long LOG_TIMEOUT = 1000 * 60 * 60 * 16;

    /**
     * Characters used to encode a 32 bit checksum into 6 bytes.
     */
    private static final char[] SHOWID_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz@#"
            .toCharArray();

    /**
     * Help field for translating collections to arrays.
     */
    private static final String[] DUMMY_STRING_ARRAY = new String[0];

    /**
     * The default properties.
     */
    private static final Properties DEFAULTS = createDefaults();

    /**
     * <code>true</code> if we are in a JUnit test.
     */
    private static final boolean IS_JUNIT_TEST = null != Package
            .getPackage("junit.framework");

    /**
     * The dateformat for parsing XMLTV dates with seconds.
     */
    private static final SimpleDateFormat DF_SECONDS = new SimpleDateFormat(
            "yyyyMMddHHmmss Z");

    /**
     * The dateformat for parsing XMLTV dates without seconds.
     */
    private static final SimpleDateFormat DF_MINUTES = new SimpleDateFormat(
            "yyyyMMddHHmm Z");

    /**
     * The dateformat for parsing dates without time.
     */
    private static final SimpleDateFormat DF_DAY = new SimpleDateFormat(
            "yyyyMMdd");

    /**
     * Formatting help.
     */
    private static final DecimalFormat FORMAT_00 = new DecimalFormat("00");

    /**
     * The regular expression for lowercase words <br>
     * (Words containing only lowercase unicode characters).
     */
    private static final Pattern LOWERCASE_WORDS_PATTERN = Pattern
            .compile(" ([\\p{Ll}]+)");

    /**
     * The regular expression for an XMLTV numbering system number.
     */
    private static final String RE_NS_PATTERN = "\\s*([0-9]+)?\\s*(?:/([0-9]+)?)?\\s*";

    /**
     * The regular expression for the XMLTV numbering system.
     */
    private static final Pattern RE_EPISODE_NUMBER_PATTERN = Pattern
            .compile(RE_NS_PATTERN
                    + "\\."
                    + RE_NS_PATTERN
                    + "\\."
                    + RE_NS_PATTERN);

    /**
     * The ratings in order of restrictiveness.
     */
    private static final List RATINGS = Arrays.asList(new String[] {"AO",
            "TVM", "NC-17", "R", "TV14", "PG-13", "PG", "TVPG", "G", "TVG",
            "TVY7", "TVY", "NR"});

    /**
     * The advisory content strings.
     */
    private static final List ADVISORY_CONTENT_STRINGS = Arrays
            .asList(new String[] {"Graphic Violence", "Violence",
                    "Mild Violence", "Graphic Language", "Language",
                    "Adult Situations", "Strong Sexual Content", "Nudity",
                    "Brief Nudity", "Rape"});

    /**
     * The maximum number of stars.
     */
    private int maxStars;

    /**
     * The providers (map of provider-id's to a list of configurations).
     */
    private static Map sProviders;

    /**
     * The time that the configurations have been read.
     */
    private static long sConfigurationsTimestamp;

    /**
     * The properties files.
     */
    private static Map sPropertiesFiles;

    /**
     * The channels and programmes that are added to the guide can be limited to 
     * specified channel id's (comma separated list).
     *
     * If this setting is empty all channels and programs will be added.
     * NOTE: filtered channels will still be parsed, they will merely not be 
     * added to the guide.
     *
     * The default is:
     *	 channel.ids=
     */
    private List channelIds;

    /**
     * Titles on dutch channels are capitalized differently from titles on
     * english channels. This would be ok if sage's favorites weren't
     * case-sensitive. To solve this the parser can capitalize the initial
     * letter of each word in a title. Since in english not all words are
     * capitalized there is also a list of words that can be excluded from this.
     * This also helps in IMDB lookups. If initcap.channel.ids is empty it is
     * used for all channels.
     * 
     * The defaults for are: initcap.title=false initcap.episodename=false
     * initcap.channel.ids= initcap.skip.words=the, a, an, at, in, on, and, of,
     * from, to, is, with, en, de, der, het, op, voor
     */
    private boolean initcapTitle;

    /**
     * @see #initcapTitle
     */
    private boolean initcapEpisodeName;

    /**
     * @see #initcapTitle
     */
    private List initcapChannelIds;

    /**
     * Flag to indicate that all channel ids should be initcapped.
     */
    private boolean initcapAllChannelIds;

    /**
     * @see #initcapTitle
     */
    private List initcapSkipWords;

    /**
     * The year of the show is added to the title between braces. This should
     * help in IMDB lookups, but it doesn't (it doesn't hurt tough). NOTE: This
     * does not affect the generated show-id. If title.add.year.categories is
     * empty it is used for all categories.
     * 
     * The defaults for are: title.add.year=true title.add.year.categories=Movie
     */
    private boolean titleAddYear;

    /**
     * @see #titleAddYear
     */
    private List titleAddYearCategories;

    /**
     * The HD title decoration.
     */
    private MessageFormat hdTitleDecoration;

    /**
     * The channels for which the titles can be HD decorated.
     */
    private List hdTitleDecorationChannels;

    /**
     * The date title decoration.
     */
    private MessageFormat dateTitleDecoration;

    /**
     * The categories for which the titles can be date decorated.
     */
    private List dateTitleDecorationCategories;

    /**
     * The episode number is prepended to the episode name. The part number of
     * the episode is added to the episode name NOTE: This does not affect the
     * generated show-id.
     * 
     * The default is: episodename.add.episode.number=true
     * episodename.add.part.number=true
     */
    private boolean episodeNameAddEpisodeNumber;

    /**
     * @see #episodeNameAddEpisodeNumber
     */
    private boolean episodeNameAddPartNumber;

    /**
     * The "split.movie.detect.time" setting controls the maximum time (in
     * milliseconds) that two parts can be apart for them to be considered split
     * movie parts. Otherwise they are considered reruns of the same show. The
     * default value is 4 hours (14400000 milliseconds).
     */
    private long splitMovieDetectTime;

    /**
     * All credit roles known to Sage.
     */
    private static final List ROLES = Arrays.asList(new String[] {null,
            "actor", "actor.lead", "actor.support", "actress", "actress.lead",
            "actress.support", "guest", "guest.star", "director", "producer",
            "writer", "choreographer", "sports.figure", "coach", "host",
            "producer.executive"});

    /**
     * The sage role for a director.
     */
    private byte directorRole;

    /**
     * The sage role for an actor.
     */
    private byte actorRole;

    /**
     * The sage role for a writer.
     */
    private byte writerRole;

    /**
     * The sage role for an adapter.
     */
    private byte adapterRole;

    /**
     * The sage role for a producer.
     */
    private byte producerRole;

    /**
     * The sage role for a presenter.
     */
    private byte presenterRole;

    /**
     * The sage role for a commentator.
     */
    private byte commentatorRole;

    /**
     * The sage role for a guest.
     */
    private byte guestRole;

    /**
     * The category translations.
     */
    private Map categoryTranslations;

    /**
     * The maximum number of category combinations that are present in the rules.
     */
    private int maxCategoryTranslationLength;

    /**
     * The maximum number of subcategories passed to Sage.
     */
    private int maxSubcategories;

    /**
     * <code>true</code> if missing translate.category entries are detected.
     */
    private boolean reportMissingTranslateCategory;

    /**
     * The number of days that should have passed since the show.date to mark 
     * the airing as a rerun.
     */
    private int rerunAfterDate;

    /**
     * The categories for which rerunAfterDate should be applied.
     */
    private List rerunAfterDateCategories;

    /**
     * The categories for which a rerun is assumed if there is no episode 
     * information.
     */
    private List rerunNoEpisodeCategories;

    /**
     * The categories for which a rerun is assumed if there is no date 
     * information.
     */
    private List rerunNoDateCategories;

    /**
     * The categories are not always enough to determine if something is a movie.
     * This option allows you to add a category (or categories) if a star-rating 
     * exists. 
     */
    private List categoriesForStarRating;

    /**
     * The date year decoration.
     */
    private MessageFormat dateYearDecoration;

    ////////////////////////////////////////////////////////////////////////////

    /**
     * The EPG database.
     */
    private sage.EPGDBPublic guide;

    /**
     * The current configuration.
     */
    private Properties configuration;

    /**
     * The currently parsed channel.
     */
    private Channel channel;

    /**
     * The currently parsed text.
     */
    private StringBuffer text;

    /**
     * All parsed channels.
     */
    private Map channels;

    /**
     * The currently parsed show.
     */
    private Show show;

    /**
     * The numbering system that is being parsed.
     */
    private String system;

    /**
     * Intermediate field for storing a value element.
     */
    private String value;

    /**
     * The rating system.
     */
    private String ratingSystem;

    /**
     * Creates the default properties.
     * 
     * @return the created default properties.
     */
    private static final Properties createDefaults() {
        Properties defaults = new Properties();
        defaults.put("configurations", "xmltv.properties");
        defaults.put("provider.name", "XMLTV Lineup");
        defaults.put("xmltv.files", "epgdata.xml");
        defaults.put("inputstream.filter", "false");
        defaults.put("channel.ids", "*");
        defaults.put("initcap.title", "false");
        defaults.put("initcap.episode.name", "false");
        defaults.put("initcap.channel.ids", "*");
        defaults.put("initcap.skip.words", "the, a, an, at, in, on, and, "
                + "of, from, to, is, with, not, "
                + "en, de, der, het, op, voor, uit, van");
        defaults.put("title.add.year", "true");
        defaults.put("title.add.year.categories", "Movie");
        defaults.put("episode.name.add.episode.number", "true");
        defaults.put("episode.name.add.part.number", "true");
        defaults.put("split.movie.detect.time", "14400000");
        defaults.put("credits.director", "director");
        defaults.put("credits.actor", "actor");
        defaults.put("credits.writer", "writer");
        defaults.put("credits.producer", "producer");
        defaults.put("credits.presenter", "host");
        defaults.put("credits.guest", "guest");
        defaults.put("rating.*.NC-17", "NC-17");
        defaults.put("rating.*.X", "NC-17");
        defaults.put("rating.*.R", "R");
        defaults.put("rating.*.PG-13", "PG-13");
        defaults.put("rating.*.PG", "PG");
        defaults.put("rating.*.G", "G");
        defaults.put("rating.*.TV-MA", "TVM");
        defaults.put("rating.*.TV-MA-V", "TVM, Graphic Violence");
        defaults.put("rating.*.TV-MA-S", "TVM, Strong Sexual Content");
        defaults.put("rating.*.TV-MA-L", "TVM, Language");
        defaults.put("rating.*.TV-14", "TV14");
        defaults.put("rating.*.TV-14-V", "TV14, Violence");
        defaults.put("rating.*.TV-14-S", "TV14, Adult Situations");
        defaults.put("rating.*.TV-14-L", "TV14, Language");
        defaults.put("rating.*.TV-14-D", "TV14, Graphic Language");
        defaults.put("rating.*.TV-PG", "TVPG");
        defaults.put("rating.*.TV-PG-V", "TVPG, Mild Violence");
        defaults.put("rating.*.TV-PG-S", "TVPG, Adult Situations");
        defaults.put("rating.*.TV-PG-L", "TVPG, Language");
        defaults.put("rating.*.TV-PG-D", "TVPG, Graphic Language");
        defaults.put("rating.*.TV-G", "TVG");
        defaults.put("rating.*.TV-Y7", "TVY7");
        defaults.put("rating.*.TV-Y", "TVY");
        defaults.put("max.stars", "4");
        defaults.put("max.subcategories", Integer.toString(Integer.MAX_VALUE));
        defaults.put("report.missing.translate.category", "false");
        defaults.put("rerun.after.date", Integer.toString(Integer.MIN_VALUE));
        defaults.put("rerun.after.date.categories", "Series");
        defaults.put("hd.title.decoration", "HD - {0}");
        defaults.put("date.title.decoration", "{0} ({1,date,dd-MM-yyyy})");

        // Log the defaults.
        StringBuffer sb = new StringBuffer();
        TreeMap map = new TreeMap();
        Enumeration names = defaults.propertyNames();
        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            map.put(name, defaults.getProperty(name));
        }
        for (Iterator it = map.entrySet().iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            sb.append(NEWLINE + entry.getKey() + "=" + entry.getValue());
        }
        log("Defaults:" + sb);

        return defaults;
    }

    /**
     * Init for a configuration.
     * 
     * @param aConfiguration the configuration.
     */
    private final void initConfiguration(Properties aConfiguration) {
        // Log the defaults.
        StringBuffer sb = new StringBuffer();
        TreeMap map = new TreeMap();
        Enumeration names = aConfiguration.propertyNames();
        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            map.put(name, aConfiguration.getProperty(name));
        }
        for (Iterator it = map.entrySet().iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            sb.append(NEWLINE + entry.getKey() + "=" + entry.getValue());
        }
        log("Configuration:" + sb);

        this.configuration = aConfiguration;

        this.channelIds = Arrays.asList(getStrings(getProperty(aConfiguration,
                "channel.ids")));

        this.initcapTitle = isTrue(getProperty(aConfiguration, "initcap.title"));
        this.initcapEpisodeName = isTrue(getProperty(aConfiguration,
                "initcap.episode.name"));
        this.initcapChannelIds = Arrays.asList(getStrings(getProperty(
                aConfiguration, "initcap.channel.ids")));
        this.initcapAllChannelIds = this.initcapChannelIds.contains("*");
        this.initcapSkipWords = Arrays.asList(getStrings(getProperty(
                aConfiguration, "initcap.skip.words")));

        this.titleAddYear = isTrue(getProperty(aConfiguration, "title.add.year"));
        this.titleAddYearCategories = Arrays.asList(getStrings(getProperty(
                aConfiguration, "title.add.year.categories")));

        this.episodeNameAddEpisodeNumber = isTrue(getProperty(aConfiguration,
                "episode.name.add.episode.number"));
        this.episodeNameAddPartNumber = isTrue(getProperty(aConfiguration,
                "episode.name.add.part.number"));

        this.directorRole = (byte) ROLES.indexOf(getProperty(aConfiguration,
                "credits.director"));
        this.actorRole = (byte) ROLES.indexOf(getProperty(aConfiguration,
                "credits.actor"));
        this.writerRole = (byte) ROLES.indexOf(getProperty(aConfiguration,
                "credits.writer"));
        this.adapterRole = (byte) ROLES.indexOf(getProperty(aConfiguration,
                "credits.adapter"));
        this.producerRole = (byte) ROLES.indexOf(getProperty(aConfiguration,
                "credits.producer"));
        this.presenterRole = (byte) ROLES.indexOf(getProperty(aConfiguration,
                "credits.presenter"));
        this.commentatorRole = (byte) ROLES.indexOf(getProperty(aConfiguration,
                "credits.commentator"));
        this.guestRole = (byte) ROLES.indexOf(getProperty(aConfiguration,
                "credits.guest"));

        this.splitMovieDetectTime = Long.parseLong(getProperty(aConfiguration,
                "split.movie.detect.time"));

        this.maxStars = getIntProperty(aConfiguration, "max.stars");

        this.categoryTranslations = new HashMap();
        this.maxCategoryTranslationLength = 0;
        names = aConfiguration.propertyNames();
        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            if (name.startsWith("translate.category.")) {
                String translation = aConfiguration.getProperty(name);
                List from = Arrays.asList(name.substring(19).split(" */ *"));
                List to = translation.length() > 0 ? Arrays.asList(translation
                        .split(" */ *")) : Collections.EMPTY_LIST;
                this.categoryTranslations.put(from, to);
                if (this.maxCategoryTranslationLength < from.size()) {
                    this.maxCategoryTranslationLength = from.size();
                }
            }
        }
        this.reportMissingTranslateCategory = isTrue(getProperty(
                aConfiguration, "report.missing.translate.category"));

        this.maxSubcategories = getIntProperty(aConfiguration,
                "max.subcategories");

        this.rerunAfterDate = getIntProperty(aConfiguration, "rerun.after.date");
        this.rerunAfterDateCategories = Arrays.asList(getStrings(getProperty(
                aConfiguration, "rerun.after.date.categories")));

        this.rerunNoEpisodeCategories = Arrays.asList(getStrings(getProperty(
                aConfiguration, "rerun.no-episode.categories")));
        this.rerunNoDateCategories = Arrays.asList(getStrings(getProperty(
                aConfiguration, "rerun.no-date.categories")));

        String decoration = getProperty(aConfiguration, "hd.title.decoration");
        if (decoration != null && decoration.length() > 0) {
            this.hdTitleDecoration = new MessageFormat(decoration);
            this.hdTitleDecorationChannels = Arrays
                    .asList(getStrings(getProperty(aConfiguration,
                            "hd.title.decoration.channels")));
        }

        decoration = getProperty(aConfiguration, "date.title.decoration");
        if (decoration != null && decoration.length() > 0) {
            this.dateTitleDecoration = new MessageFormat(decoration);
            this.dateTitleDecorationCategories = Arrays
                    .asList(getStrings(getProperty(aConfiguration,
                            "date.title.decoration.categories")));
        }

        this.categoriesForStarRating = Arrays.asList(getStrings(getProperty(
                aConfiguration, "categories.for.star-rating")));

        decoration = getProperty(aConfiguration, "date.year.decoration");
        if (decoration != null && decoration.length() > 0) {
            this.dateYearDecoration = new MessageFormat(decoration);
        }
    }

    /**
     * Exit for a configuration.
     */
    private final void exitConfiguration() {
        // Resetting these objects prevents use of them out of context.
        // (also prevents a minor memory leak).
        this.configuration = null;
        this.channelIds = null;
        this.initcapChannelIds = null;
        this.initcapSkipWords = null;
        this.titleAddYearCategories = null;
        this.categoryTranslations = null;
        this.rerunAfterDateCategories = null;
        this.rerunNoEpisodeCategories = null;
        this.rerunNoDateCategories = null;
        this.hdTitleDecoration = null;
        this.hdTitleDecorationChannels = null;
        this.dateTitleDecoration = null;
        this.dateTitleDecorationCategories = null;
        this.categoriesForStarRating = null;
        this.dateYearDecoration = null;
    }

    /**
     * Init for a provider.
     */
    private final void initProvider() {
        this.channels = new HashMap();
    }

    /**
     * Exit for a provider.
     */
    private final void exitProvider() {
        // Resetting this object prevents use of it out of context.
        // (also prevents a minor memory leak).
        this.channels = null;
    }

    /**
     * Gets the configurations.
     * 
     * If the configuration files have changed they will be reread.
     * @return the list of configurations.
     */
    private synchronized static final Map getProviders() {
        if (sProviders == null) {
            // First time.
            readConfigurations();
        } else {
            for (Iterator it = sPropertiesFiles.keySet().iterator(); it
                    .hasNext();) {
                File propertiesFile = (File) it.next();
                if (propertiesFile.lastModified() > sConfigurationsTimestamp) {
                    // At least one configuration file has changed.
                    readConfigurations();
                    break;
                }
            }
        }
        return sProviders;
    }

    /**
     * Reads the configurations.
     */
    private synchronized static final void readConfigurations() {
        // Reset the entire configuration.
        sConfigurationsTimestamp = System.currentTimeMillis();
        sPropertiesFiles = new HashMap();
        sProviders = new TreeMap();

        Properties xmltvProperties = new Properties(DEFAULTS);
        try {
            FileInputStream inputStream = new FileInputStream(
                    "xmltv.properties");
            xmltvProperties.load(inputStream);
            inputStream.close();
        } catch (Exception e) {
            log(e);
        }
        String[] configurationFileNames = getStrings(getProperty(
                xmltvProperties, "configurations"));
        for (int i = 0; i < configurationFileNames.length; ++i) {
            Properties configuration = readConfiguration(new File(
                    configurationFileNames[i]));
            String providerId = getProperty(configuration, "provider.id");
            if (providerId == null) {
                String providerName = getProperty(configuration,
                        "provider.name");
                if ("XMLTV Lineup".equals(providerName)) {
                    providerId = "867507149";
                } else {
                    CRC32 crc32 = new CRC32();
                    try {
                        crc32.update(providerName.toLowerCase().getBytes(
                                "UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        log(e);
                        crc32.update(providerName.getBytes());
                    }
                    providerId = Long.toString(crc32.getValue());
                }
            }
            List configurations = (List) sProviders.get(providerId);
            if (configurations == null) {
                configurations = new LinkedList();
                sProviders.put(providerId, configurations);
            }
            configurations.add(configuration);
        }
    }

    /**
     * Reads a single configuration.
     * 
     * @param aConfigurationFile the configuration file to read.
     * @return the configuration.
     */
    private static final Properties readConfiguration(File aConfigurationFile) {
        List propertiesList = new LinkedList();
        includeInPropertiesList(propertiesList, aConfigurationFile);
        Properties configuration = DEFAULTS;
        for (int i = propertiesList.size() - 1; i >= 0; --i) {
            Properties properties = getProperties((File) propertiesList.get(i));
            configuration = new Properties(configuration);
            try {
                // Copy the content of the properties in the new configuration node.
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                properties.store(outputStream, null);
                ByteArrayInputStream inputStream = new ByteArrayInputStream(
                        outputStream.toByteArray());
                configuration.load(inputStream);
            } catch (IOException e) {
                log(e);
            }
        }
        return configuration;
    }

    /**
     * Add a properties file to a list of properties files and include all 
     * includes. 
     * 
     * @param aPropertiesList the list of properties files.
     * @param aPropertiesFile the properties file to add.
     */
    private static final void includeInPropertiesList(List aPropertiesList,
            File aPropertiesFile) {
        aPropertiesList.add(aPropertiesFile);
        String[] includes = getStrings(getProperty(
                getProperties(aPropertiesFile), "include"));
        for (int i = 0; i < includes.length; ++i) {
            File includeFile = new File(includes[i]);
            if (!aPropertiesList.contains(includeFile)) {
                includeInPropertiesList(aPropertiesList, includeFile);
            }
        }
    }

    /**
     * Gets (cached) Properties.
     * 
     * @param aPropertiesFile the file object for the properties file.
     * @return the Properties.
     */
    private synchronized static final Properties getProperties(
            File aPropertiesFile) {
        Properties properties = null;
        SoftReference ref = (SoftReference) sPropertiesFiles
                .get(aPropertiesFile);
        if (ref != null) {
            properties = (Properties) ref.get();
        }
        if (properties == null) {
            properties = new Properties();
            try {
                FileInputStream inputStream = new FileInputStream(
                        aPropertiesFile);
                properties.load(inputStream);
                inputStream.close();
            } catch (Exception e) {
                log(e);
            }
            sPropertiesFiles
                    .put(aPropertiesFile, new SoftReference(properties));
        }
        return properties;
    }

    /**
     * Return a string array.
     * @param aCommaSeparatedList the list of comma separated values (the list 
     * is already assumed to be trimmed).
     * @return the string array.
     */
    private static final String[] getStrings(String aCommaSeparatedList) {
        if (aCommaSeparatedList != null && aCommaSeparatedList.length() > 0) {
            return aCommaSeparatedList.trim().split(" *, *");
        }
        return new String[0];
    }

    /**
     * Return <code>true</code> if a string denotes a true value.
     * 
     * @param aValue the string. 
     * @return true if a aValue denotes a true value..
     */
    private static final boolean isTrue(String aValue) {
        if (aValue == null) {
            return false;
        }
        String value = aValue.toLowerCase();
        return "true".equals(value) || "yes".equals(value) || "1".equals(value);
    }

    /**
     * Returns a property from properties.
     * 
     * @param aProperties the properties (or configuration) from which the 
     * value should be retrieved.
     * @param aKey the key of the property.
     * @return the value; <code>null</code> if the property is not present or
     * is empty. 
     */
    private static final String getProperty(Properties aProperties, String aKey) {
        String value = aProperties.getProperty(aKey);
        if (value != null) {
            value = value.trim();
            if (value.length() == 0) {
                return null;
            }
        }
        return value;
    }

    /**
     * Logs an exception.
     * 
     * @param aException the exception. 
     */
    private static synchronized final void log(Throwable aException) {
        PrintStream log = getErrorLogPrinter();
        log.print(DF_LOG.format(new Date()));
        aException.printStackTrace(log);
        log.flush();

        // Log in the normal log file as well.
        log = getLogPrinter();
        log.println(DF_LOG.format(new Date()) + aException);
    }

    /**
     * Gets an active error log printer.
     * 
     * @return the error log printer.
     */
    private static final PrintStream getErrorLogPrinter() {
        if (sErrorLogPrinter == null
                || (System.currentTimeMillis() - ERROR_LOG.lastModified()) > LOG_TIMEOUT) {
            if (IS_JUNIT_TEST || (ERROR_LOG.exists() && !ERROR_LOG.canWrite())) {
                sErrorLogPrinter = System.err;
            } else {
                try {
                    // Remove the old error log.
                    try {
                        ERROR_LOG.delete();
                    } catch (Exception e) {
                        PrintStream errorLog = (sErrorLogPrinter == null
                                ? System.err : sErrorLogPrinter);
                        e.printStackTrace(errorLog);
                        PrintStream log = (sLogPrinter == null ? System.out
                                : sLogPrinter);
                        log.print(e);
                    }
                    sErrorLogPrinter = new PrintStream(new FileOutputStream(
                            ERROR_LOG, true));
                } catch (Exception e) {
                    sErrorLogPrinter = System.err;
                }
            }
        }
        return sErrorLogPrinter;
    }

    /**
     * Logs a message.
     * 
     * @param aMessage The message.
     */
    private static synchronized final void log(String aMessage) {
        PrintStream log = getLogPrinter();
        log.println(DF_LOG.format(new Date()) + aMessage);
    }

    /**
     * Logs an object.
     * 
     * @param aObject the object to log.
     */
    static final void log(Object aObject) {
        if (aObject == null) {
            log("null");
        } else if (aObject instanceof Throwable) {
            log((Throwable) aObject);
        } else {
            log(aObject.toString());
        }
    }

    /**
     * Logs a error message.
     * 
     * @param aMessage The error message.
     */
    private static synchronized final void logError(String aMessage) {
        PrintStream log = getErrorLogPrinter();
        log.println(DF_LOG.format(new Date()) + aMessage);
    }

    /**
     * Logs the contents of a stream.
     * @param aInputStream the stream.
     */
    private static synchronized final void log(InputStream aInputStream) {
        PrintStream log = getLogPrinter();
        byte[] buffer = new byte[1000];
        try {
            int bytesRead = aInputStream.read(buffer);
            while (bytesRead > 0) {
                log.print(new String(buffer, 0, bytesRead));
                bytesRead = aInputStream.read(buffer);
            }
        } catch (IOException e) {
            log(e);
        }
    }

    /**
     * Logs the contents of a stream to both the error and the regular log-file.
     * @param aInputStream the stream.
     */
    private static synchronized final void logError(InputStream aInputStream) {
        PrintStream errorLog = getErrorLogPrinter();
        PrintStream log = getLogPrinter();
        byte[] buffer = new byte[1000];
        try {
            int bytesRead = aInputStream.read(buffer);
            while (bytesRead > 0) {
                String text = new String(buffer, 0, bytesRead);
                errorLog.print(text);
                log.print(text);
                bytesRead = aInputStream.read(buffer);
            }
        } catch (IOException e) {
            log(e);
        }
        errorLog.flush();
    }

    /**
     * Gets an active log printer.
     * 
     * @return the log printer.
     */
    private static final PrintStream getLogPrinter() {
        if (sLogPrinter == null
                || (System.currentTimeMillis() - LOG.lastModified()) > LOG_TIMEOUT) {
            if (IS_JUNIT_TEST || (LOG.exists() && !LOG.canWrite())) {
                sLogPrinter = System.out;
            } else {
                try {
                    // Remove the old log.
                    try {
                        LOG.delete();
                    } catch (Exception e) {
                        PrintStream errorLog = (sErrorLogPrinter == null
                                ? System.err : sErrorLogPrinter);
                        e.printStackTrace(errorLog);
                        PrintStream log = (sLogPrinter == null ? System.out
                                : sLogPrinter);
                        log.print(e);
                    }
                    sLogPrinter = new PrintStream(new FileOutputStream(LOG,
                            true));
                } catch (Exception e) {
                    sLogPrinter = System.out;
                }
            }
        }
        return sLogPrinter;
    }

    /**
     * Close the error log.
     */
    private synchronized static final void closeErrorLog() {
        if (sErrorLogPrinter != null) {
            if (sErrorLogPrinter != System.err) {
                sErrorLogPrinter.close();
            }
            sErrorLogPrinter = null;
        }
        if (ERROR_LOG.exists() && ERROR_LOG.length() == 0) {
            // If there is nothing to report then don't alarm the user
            // with the presence of an error log.
            ERROR_LOG.delete();
        }
    }

    /**
     * Close the log.
     */
    private synchronized static final void closeLog() {
        if (sLogPrinter != null) {
            if (sLogPrinter != System.out) {
                sLogPrinter.close();
            }
            sLogPrinter = null;
        }
    }

    /**
     * @see sage.EPGImportPlugin#getLocalMarkets()
     */
    public final String[][] getLocalMarkets() {
        try {
            log("getLocalMarkets()");

            return getProviderArray();

        } catch (Throwable t) {
            log(t);
            throw (Error) t;
        } finally {
            cleanup();
        }
    }

    /**
     * Gets the array of providers.
     * 
     * @return the array.
     */
    private static final String[][] getProviderArray() {
        Map providers = getProviders();
        String[][] localMarkets = new String[providers.size()][2];
        int i = 0;
        for (Iterator it = providers.entrySet().iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            localMarkets[i][0] = (String) entry.getKey();
            List configurations = (List) entry.getValue();
            Properties configuration = (Properties) configurations.get(0);
            localMarkets[i][1] = getProperty(configuration, "provider.name");
            ++i;
        }
        return localMarkets;
    }

    /**
     * Gets an integer property.
     * @param aProperties the properties (or configuration) from which the 
     * value should be retrieved.
     * @param aKey the key of the property.
     * @return the value 
     */
    private final int getIntProperty(Properties aProperties, String aKey) {
        String string = getProperty(aProperties, aKey);
        if (string == null) {
            // The user has provided an empty value.
            string = getProperty(DEFAULTS, aKey);
        }
        if (string == null) {
            // The programmer has not provided a default value.
            return 0;
        }
        return Integer.parseInt(string);
    }

    /**
     * Exit the plugin.
     */
    private final void cleanup() {
        closeLog();
        closeErrorLog();
    }

    /**
     * @see sage.EPGImportPlugin#getProviders(java.lang.String)
     */
    public final String[][] getProviders(String aZipCode) {
        try {
            log("getProviders(" + aZipCode + ")");

            // Ignore the zipcode
            return getProviderArray();

        } catch (Throwable t) {
            log(t);
            throw (Error) t;
        } finally {
            cleanup();
        }
    }

    /**
     * @see sage.EPGImportPlugin#updateGuide(String, EPGDBPublic)
     */
    public final boolean updateGuide(String aProviderId, EPGDBPublic aGuide) {
        try {
            log("updateGuide(" + aProviderId + ", " + aGuide + ")");

            initProvider();

            try {
                this.guide = aGuide;

                if (aProviderId != null) {
                    List configurations = getConfigurations(aProviderId);
                    if (configurations.size() > 0) {
                        String providerName = getProperty(
                                (Properties) configurations.get(0),
                                "provider.name");
                        log("Provider name = " + providerName);
                        int i = 0;
                        for (Iterator it = configurations.iterator(); it
                                .hasNext();) {
                            Properties config = (Properties) it.next();
                            try {
                                log("Configuration "
                                        + providerName
                                        + "["
                                        + i++
                                        + "]");
                                updateGuide(config);
                            } catch (Throwable t) {
                                log(t);
                                // Continue with the next configuration.
                            }
                        }
                    }

                    TreeMap lineup = new TreeMap();
                    int nextChannelNumber = 2;
                    for (Iterator i = this.channels.values().iterator(); i
                            .hasNext();) {
                        this.channel = (Channel) i.next();
                        if (this.channel.numbers.size() == 0) {
                            lineup.put(this.channel.id, new String[] {Integer
                                    .toString(nextChannelNumber++)});
                        } else {
                            lineup.put(this.channel.id, this.channel.numbers
                                    .toArray(new String[0]));
                        }
                        this.channel = null;
                    }
                    aGuide.setLineup(Long.parseLong(aProviderId), lineup);
                }

            } finally {
                exitProvider();
            }

        } catch (Throwable t) {
            log(t);
            if (t.getClass().getName().equals(
                    "junit.framework.ComparisonFailure")) {
                // For ease of testing...
                throw (Error) t;
            }
        } finally {
            cleanup();
        }

        // Never return false. Even if something has gone wrong, a lot of things 
        // might have gone right. If something is really wrong it will be 
        // apparent through missing sections in the guide. Errors can be found
        // in the exceptions log.
        return true;
    }

    /**
     * Gets the configuration for a specific provider.
     * 
     * @param aProviderId the provider.
     * @return the list of configurations for that provider.
     */
    private static synchronized final List getConfigurations(String aProviderId) {
        List configurations = null;
        if (aProviderId != null) {
            Map providers = getProviders();
            if (providers != null) {
                configurations = (List) providers.get(aProviderId);
            }
        }
        return configurations == null ? Collections.EMPTY_LIST : configurations;
    }

    /**
     * Update the guide for a specific configuration.
     * 
     * @param aConfiguration the configuration that is used for the update.
     */
    private final void updateGuide(Properties aConfiguration) {
        initConfiguration(aConfiguration);
        try {
            waitForTimeslot(aConfiguration);
            executeRunBefore(aConfiguration);

            try {
                XMLReader xmlReader = XMLReaderFactory.createXMLReader();
                xmlReader
                        .setFeature(
                                "http://apache.org/xml/features/nonvalidating/load-external-dtd",
                                false);
                xmlReader.setContentHandler(this);
                xmlReader.setErrorHandler(this);

                String[] xmltvFiles = getStrings(getProperty(aConfiguration,
                        "xmltv.files"));
                boolean inputStreamFilter = isTrue(getProperty(aConfiguration,
                        "inputstream.filter"));
                for (int i = 0; i < xmltvFiles.length; ++i) {
                    try {
                        log("Parseing " + xmltvFiles[i]);
                        InputStream in = new FileInputStream(xmltvFiles[i]);
                        if (inputStreamFilter) {
                            in = new XMLInputStreamFilter(in);
                        }
                        xmlReader.parse(new InputSource(in));
                        in.close();
                    } catch (Throwable t) {
                        log(t);
                        // Continue with the next file.
                    }
                }

                for (Iterator it = this.channelIds.iterator(); it.hasNext();) {
                    // Add missing channels.
                    String xmltvId = (String) it.next();
                    if (!this.channels.containsKey(xmltvId)) {
                        this.channel = new Channel(xmltvId);
                        addChannelToGuide();
                    }
                }
                this.channel = null;

            } catch (Throwable t) {
                log(t);
            }
        } finally {
            exitConfiguration();
        }
    }

    /**
     * Lets the current thread wait for a specific timeslot before starting.
     * 
     * @param aConfiguration the configuration.
     */
    private void waitForTimeslot(Properties aConfiguration) {
        String timeslot = aConfiguration.getProperty("timeslot");
        if (timeslot == null) {
            return;
        }
        if (timeslot.length() < 9) {
            log("Invalid timeslot: " + timeslot);
            log("timeslot should adhere to the format: hhmm-hhmm");
            return;
        }

        log("Checking timeslot");
        Calendar now = Calendar.getInstance();
        Calendar from = (Calendar) now.clone();
        from.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeslot
                .substring(0, 2)));
        from.set(Calendar.MINUTE, Integer.parseInt(timeslot.substring(2, 4)));
        from.set(Calendar.SECOND, 0);
        from.set(Calendar.MILLISECOND, 0);
        Calendar until = (Calendar) now.clone();
        until.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeslot.substring(5,
                7)));
        until.set(Calendar.MINUTE, Integer.parseInt(timeslot.substring(7, 9)));
        until.set(Calendar.SECOND, 0);
        until.set(Calendar.MILLISECOND, 0);
        if (from.after(until)) {
            from.add(Calendar.DATE, -1);
        }

        while (now.after(until)) {
            from.add(Calendar.DATE, 1);
            until.add(Calendar.DATE, 1);
        }
        log(" from  = " + from.getTime());
        log(" until = " + until.getTime());

        if (now.after(from)) {
            log("We're in the correct timeslot.");
            return;
        }

        log("Waiting for " + from.getTime());
        try {
            Thread.sleep(from.getTimeInMillis() - now.getTimeInMillis());
        } catch (InterruptedException e) {
            log(e);
        }
        log("Waited for timeslot since " + now.getTime());
        log("Continuing update.");
    }

    /**
     * Run the program that has been configured to run before parseing.
     * 
     * @param aConfiguration the configuration.
     */
    private final void executeRunBefore(Properties aConfiguration) {
        try {
            String runBefore = getProperty(aConfiguration, "run.before");
            if (null != runBefore) {
                log("Executing: \"" + runBefore + "\"");
                Process process = Runtime.getRuntime().exec(runBefore);

                log("stdout:");
                log(process.getInputStream());

                log("stderr:");
                logError(process.getErrorStream());

                process.waitFor();
                log("Execution complete.");
            }
        } catch (Throwable t) {
            log(t);
            // We don't know if this is fatal for the update, so just carry on...
        }
    }

    /* XML Parsing */

    public final void characters(char[] aCh, int aStart, int aLength)
            throws SAXException {
        if (this.text != null) {
            this.text.append(aCh, aStart, aLength);
        }
    }

    public final void startDocument() throws SAXException {
    // noop
    }

    public final void endDocument() throws SAXException {
    // noop
    }

    public final void startElement(String aNamespaceURI, String aLocalName,
            String aQName, Attributes aAttributes) throws SAXException {
        if (aQName.equals("channel")) {
            String xmltvId = aAttributes.getValue("id");
            this.channel = (Channel) this.channels.get(xmltvId);
            if (this.channel == null) {
                this.channel = new Channel(xmltvId);
            }
        } else if (aQName.equals("programme")) {
            this.show = new Show(parseXmltvDate(aAttributes.getValue("start")),
                    parseXmltvDate(aAttributes.getValue("stop")));
            String channelId = aAttributes.getValue("channel");
            if (channelId != null
                    && (this.channelIds.contains("*") || this.channelIds
                            .contains(channelId))) {
                this.channel = (Channel) this.channels.get(channelId);
                if (this.channel == null) {
                    // never seen before channel, create it
                    this.channel = new Channel(channelId);
                    addChannelToGuide();
                }
            }
        } else if (aQName.equals("episode-num")) {
            this.system = aAttributes.getValue("system");
            this.text = new StringBuffer();
        } else if ((aQName.equals("title")
                || aQName.equals("sub-title")
                || aQName.equals("desc")
                || aQName.equals("director")
                || aQName.equals("actor")
                || aQName.equals("writer")
                || aQName.equals("adapter")
                || aQName.equals("producer")
                || aQName.equals("presenter")
                || aQName.equals("commentator")
                || aQName.equals("guest")
                || aQName.equals("date")
                || aQName.equals("category")
                || aQName.equals("language")
                || aQName.equals("orig-language")
                || aQName.equals("country")
                || aQName.equals("colour")
                || aQName.equals("aspect")
                || aQName.equals("quality")
                || aQName.equals("stereo")
                || aQName.equals("subtitles") || aQName.equals("value"))
                && this.show != null) {
            this.text = new StringBuffer();
        } else if (aQName.equals("display-name")) {
            this.text = new StringBuffer();
        } else if (aQName.equals("previously-shown")) {
            this.show.rerun = true;
        } else if (aQName.equals("premiere")) {
            this.show.premiere = true;
        } else if (aQName.equals("new")) {
            // The first showing of the first episode of a new show?
            // Sounds like a premiere to me...
            this.show.premiere = true;
        } else if (aQName.equals("subtitles")) {
            this.show.subtitles = aAttributes.getValue("type");
            if (this.show.subtitles == null) {
                this.show.subtitles = "yes";
            }
        } else if (aQName.equals("rating")) {
            this.ratingSystem = aAttributes.getValue("system");
            if (this.ratingSystem == null) {
                this.ratingSystem = "";
            }
        } else if (aQName.equals("star-rating")) {
            // noop
        } else if (IS_JUNIT_TEST && !ignoreQName(aQName)) {
            logError("<" + aQName + ">");
        }
    }

    /**
     * Tests if a QName should be ignored.
     * 
     * @param aQName the QName that should be examined.
     * @return <code>true</code> if the QName should be ignored.
     */
    private static final boolean ignoreQName(String aQName) {
        return aQName.equals("tv")
                || aQName.equals("icon")
                || aQName.equals("url")
                || aQName.equals("credits")
                || aQName.equals("length")
                || aQName.equals("video")
                || aQName.equals("present")
                || aQName.equals("audio")
                || aQName.equals("last-chance");
    }

    public final void endElement(String aNamespaceURI, String aLocalName,
            String aQName) throws SAXException {
        //		boolean isShow = (this.show != null && this.text != null);
        if (aQName.equals("channel")) {
            if ((this.channelIds.contains("*") || this.channelIds
                    .contains(this.channel.xmltvId))
                    && !this.channels.containsKey(this.channel.xmltvId)) {
                addChannelToGuide();
            }
            this.channel = null;
        } else if (aQName.equals("programme")) {
            addShowToGuide();
            this.show = null;
            this.channel = null;
        } else if (aQName.equals("display-name") && this.channel != null) {
            this.channel.names.add(this.text.toString());
        } else if (aQName.equals("title")) {
            this.show.title = this.text.toString();
        } else if (aQName.equals("sub-title")) {
            this.show.episodeName = this.text.toString();
        } else if (aQName.equals("desc")) {
            this.show.descriptions.add(this.text.toString());
        } else if (aQName.equals("director")) {
            addPersonToShow(this.text.toString(), this.directorRole);
        } else if (aQName.equals("actor")) {
            addPersonToShow(this.text.toString(), this.actorRole);
        } else if (aQName.equals("writer")) {
            addPersonToShow(this.text.toString(), this.writerRole);
        } else if (aQName.equals("adapter")) {
            addPersonToShow(this.text.toString(), this.adapterRole);
        } else if (aQName.equals("producer")) {
            addPersonToShow(this.text.toString(), this.producerRole);
        } else if (aQName.equals("presenter")) {
            addPersonToShow(this.text.toString(), this.presenterRole);
        } else if (aQName.equals("commentator")) {
            addPersonToShow(this.text.toString(), this.commentatorRole);
        } else if (aQName.equals("guest")) {
            addPersonToShow(this.text.toString(), this.guestRole);
        } else if (aQName.equals("date")) {
            String date = this.text.toString();
            if (date.length() >= 4) {
                this.show.year = date.substring(0, 4);
                if (date.length() >= 8) {
                    try {
                        this.show.date = DF_DAY.parse(date.substring(0, 8));
                    } catch (ParseException e) {
                        log(e);
                    }
                }
            }
        } else if (aQName.equals("category")) {
            String[] categories = this.text.toString().split("/");
            for (int i = 0; i < categories.length; ++i) {
                this.show.categories.add(categories[i]);
            }
        } else if (aQName.equals("language")) {
            if (this.show.subtitles == null) {
                // Subtitles seem to have a language element as well.
                this.show.language = this.text.toString();
            }
        } else if (aQName.equals("orig-language")) {
            this.show.originalLanguage = this.text.toString();
        } else if (aQName.equals("country")) {
            this.show.countries.add(this.text.toString());
        } else if (aQName.equals("episode-num")) {
            parseEpisodeNumber(this.text.toString());
        } else if (aQName.equals("previously-shown")) {
            // noop
        } else if (aQName.equals("colour")) {
            this.show.colour = this.text.toString();
        } else if (aQName.equals("aspect")) {
            this.show.aspect = this.text.toString();
        } else if (aQName.equals("quality")) {
            this.show.quality = this.text.toString();
        } else if (aQName.equals("stereo")) {
            this.show.audio = this.text.toString();
        } else if (aQName.equals("premiere")) {
            // noop
        } else if (aQName.equals("new")) {
            // noop
        } else if (aQName.equals("subtitles")) {
            // noop
        } else if (aQName.equals("value")) {
            this.value = this.text.toString();
        } else if (aQName.equals("rating")) {
            addRatingToShow();
            this.ratingSystem = null;
            this.value = null;
        } else if (aQName.equals("star-rating")) {
            addStarRatingToShow();
            this.value = null;
        } else if (IS_JUNIT_TEST && !ignoreQName(aQName)) {
            logError("</" + aQName + ">");
        }
        this.text = null;
    }

    /**
     * Adds the star rating to the show.
     */
    private final void addStarRatingToShow() {
        String[] strings = this.value.split(" */ *");
        if (strings.length > 1) {
            this.show.stars = (int) Math.round(Double.parseDouble(strings[0])
                    * this.maxStars
                    / Double.parseDouble(strings[1]));
        }
    }

    /**
     * Adds the rating data to the show.
     */
    private final void addRatingToShow() {
        String property = getProperty(this.configuration, "rating."
                + this.ratingSystem
                + "."
                + this.value);
        if (property == null) {
            property = getProperty(this.configuration, "rating.*." + this.value);
        }
        if (property == null) {
            logError("Missing configuration: rating."
                    + this.ratingSystem
                    + "."
                    + this.value
                    + "=");
        } else {
            String[] ratings = getStrings(property);
            if (ratings.length > 0) {
                if (ratings[0].length() > 0) {
                    if (this.show.rating == null
                            || RATINGS.indexOf(this.show.rating) > RATINGS
                                    .indexOf(ratings[0])) {
                        // A rating can only be overridden by a more restrictive 
                        // rating.
                        this.show.rating = ratings[0];
                    }
                }
                for (int i = 1; i < ratings.length; ++i) {
                    if (ADVISORY_CONTENT_STRINGS.contains(ratings[i])) {
                        this.show.expandedRatings.add(ratings[i]);
                    }
                }
            }
        }
    }

    /**
     * Sets the XMLTV numbering system number on the show.
     * 
     * @param aEpisodeNumber the XMLTV numbering system number.
     */
    private final void parseEpisodeNumber(String aEpisodeNumber) {
        if ("xmltv_ns".equals(this.system)) {
            Matcher matcher = RE_EPISODE_NUMBER_PATTERN.matcher(aEpisodeNumber);
            if (matcher.find()) {
                this.show.season = toInt(matcher.group(1), -1) + 1; // 0-based
                this.show.seasons = toInt(matcher.group(2), 0);
                this.show.episode = toInt(matcher.group(3), -1) + 1; // 0-based
                this.show.episodes = toInt(matcher.group(4), 0);
                this.show.part = toInt(matcher.group(5), -1) + 1; // 0-based
                this.show.parts = toInt(matcher.group(6), 0);
                if (this.show.season > 0 || this.show.episode > 0) {
                    // Create a free-form version of the episode number.
                    StringBuffer sb = new StringBuffer();
                    if (this.show.season > 0) {
                        sb.append(this.show.season + "-");
                    }
                    if (this.show.episode > 0) {
                        sb.append(this.show.episode);
                    }
                    this.show.freeFormEpisodeNumber = sb.toString();
                }
            } else {
                this.show.freeFormEpisodeNumber = aEpisodeNumber;
            }
        } else if ("dd_progid".equals(this.system)) {
            this.show.showId = aEpisodeNumber.replaceFirst("\\.", "");
        } else {
            this.show.freeFormEpisodeNumber = aEpisodeNumber;
        }
    }

    /**
     * Parses a string for an int.
     * <br>If the string is a <code>null</code> the default value will be used.
     * 
     * @param aString the string containing the int.
     * @param aDefault the default value.
     * @return the resulting int.
     */
    private static final int toInt(String aString, int aDefault) {
        return aString == null ? aDefault : Integer.parseInt(aString);
    }

    /**
     * Adds a person to the show.
     * 
     * @param aName the name of the person.
     * @param aRole the role.
     */
    private final void addPersonToShow(String aName, byte aRole) {
        if (aRole > 0 && aName.length() > 0) {
            String[] names = aName
                    .split(" *(,|;| and | en | e(\\.a)??\\.??( |$)| \\([^\\)]*\\)) *");
            for (int i = 0; i < names.length; ++i) {
                if (names[i].length() > 0) {
                    this.show.people.add(names[i]);
                    this.show.roles.write(aRole);
                }
            }
        }
    }

    /**
     * Adds the current channel.
     */
    private final void addChannelToGuide() {
        this.channels.put(this.channel.xmltvId, this.channel);

        // Determine the channel.[id] prefix.
        String prefix = "channel." + this.channel.xmltvId + ".";

        // Add the 'channel.[id].numbers'
        String[] numbers = getStrings(getProperty(this.configuration, prefix
                + "numbers"));
        for (int i = 0; i < numbers.length; ++i) {
            this.channel.numbers.add(numbers[i]);
        }

        // Override the names with 'channel.[id].names'
        String[] names = getStrings(getProperty(this.configuration, prefix
                + "names"));
        if (names.length > 0) {
            this.channel.names.clear();
            for (int i = 0; i < names.length; ++i) {
                this.channel.names.add(names[i]);
            }
        }

        // Set the network to 'channel.[id].network'
        String network = getProperty(this.configuration, prefix + "network");
        if (network != null) {
            this.channel.network = network;
        }

        // Calculate the Sage ID for the channel.
        CRC32 crc = new CRC32();
        crc.update(this.channel.xmltvId.getBytes());
        this.channel.id = new Integer((int) (crc.getValue() >> 16 & 0xFFFFFFFF));

        // Determine the long and short names for the channel.
        String shortName = null;
        String longName = null;
        Iterator it = this.channel.names.iterator();
        if (it.hasNext()) {
            shortName = longName = (String) it.next();
            while (it.hasNext()) {
                String name = (String) it.next();
                if (name.length() < shortName.length()) {
                    shortName = name;
                }
                if (name.length() > longName.length()) {
                    longName = name;
                }
            }
        } else {
            shortName = longName = this.channel.xmltvId;
        }

        log(this.channel.toString());
        try {
            this.guide.addChannelPublic(shortName, longName,
                    this.channel.network, this.channel.id.intValue());
        } catch (Throwable t) {
            log(t);
        }
    }

    // TODO check to put the right title to SageTV correctly handling the rerun shows
    /**
     * Adds the current show.
     */
    private final void addShowToGuide() {
        if (this.channel != null
                && this.show.start.getTime() > System.currentTimeMillis()
                && this.show.end != null
                && this.show.start.before(this.show.end)) {
            log(this.show.toString());
            try {
                LinkedList categories = translateCategories(this.show.categories);
                if (!this.categoriesForStarRating.isEmpty()
                        && this.show.stars >= 0) {
                    // Prevent duplication of categories.
                    categories.removeAll(this.categoriesForStarRating);
                    // Prepend all the categories that should be added for a 
                    // star-rating element. 
                    categories.addAll(0, this.categoriesForStarRating);
                }
                if (makeFirst(categories, "Movie")
                        || makeFirst(categories, "Series")
                        || makeFirst(categories, "News")
                        || makeFirst(categories, "Sports event")
                        || makeFirst(categories, "Sports non-event")
                        || makeFirst(categories, "Sports talk")) {
                    // The show is now colored.
                }

                // Check for rerun
                if (!this.show.rerun) {
                    if (this.rerunAfterDate >= 0
                            && this.show.date != null
                            && isActivated(categories,
                                    this.rerunAfterDateCategories)) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(this.show.date);
                        calendar.add(Calendar.DATE, this.rerunAfterDate);
                        if (this.show.start.after(calendar.getTime())) {
                            this.show.rerun = true;
                        }
                    } else if (!this.show.rerun) {
                        if (this.show.episodeName == null
                                && this.show.episode <= 0
                                && this.show.freeFormEpisodeNumber == null
                                && isActivated(categories,
                                        this.rerunNoEpisodeCategories)) {
                            this.show.rerun = true;
                        } else if (this.show.date == null
                                && isActivated(categories,
                                        this.rerunNoDateCategories)) {
                            this.show.rerun = true;
                        }
                    }
                }

                String title = this.show.title;
                // FIXME handling rerun shows correctly with SageTV 9
                // String rerunTitle = null;
                String rerunTitle = this.show.title;
                String episodeName = this.show.episodeName;
                if (this.initcapAllChannelIds
                        || this.initcapChannelIds
                                .contains(this.channel.xmltvId)) {
                    if (this.initcapTitle) {
                        title = initcap(title);
                    }
                    if (this.initcapEpisodeName) {
                        episodeName = initcap(episodeName);
                    }
                }
                if (title != null) {
                    if (this.show.date != null
                            && isActivated(categories,
                                    this.dateTitleDecorationCategories)) {
                        title = this.dateTitleDecoration.format(
                                new Object[] {title, this.show.date},
                                new StringBuffer(), null).toString();
                    } else if (this.titleAddYear
                            && this.show.year != null
                            && isActivated(categories,
                                    this.titleAddYearCategories)) {
                        title += " (" + this.show.year + ")";
                    }
                    if (this.show.quality != null
                            && this.show.quality.startsWith("HD")
                            && isActivated(this.channel.id,
                                    this.hdTitleDecorationChannels)) {
                        title = this.hdTitleDecoration.format(
                                new Object[] {title}, new StringBuffer(), null)
                                .toString();
                    }
                }
                if (this.show.rerun) {
                    rerunTitle = title;
                    // FIXME handling rerun shows correctly with SageTV 9
                    //title = null;
                }
                if (this.episodeNameAddEpisodeNumber
                        && this.show.freeFormEpisodeNumber != null) {
                    if (episodeName == null) {
                        episodeName = this.show.freeFormEpisodeNumber + ".";
                    } else {
                        episodeName = this.show.freeFormEpisodeNumber
                                + ". "
                                + episodeName;
                    }
                }
                if (this.episodeNameAddPartNumber && this.show.part > 0) {
                    StringBuffer sb = new StringBuffer();
                    if (episodeName == null) {
                        sb.append("(" + this.show.part);
                    } else {
                        sb.append(episodeName + " (" + this.show.part);
                    }
                    if (this.show.parts > 0) {
                        sb.append("/" + this.show.parts);
                    }
                    sb.append(")");
                    episodeName = sb.toString();
                }

                String desc = this.show.descriptions.size() > 0
                        ? (String) this.show.descriptions.get(0) : null;
                String category = categories.size() > 0 ? (String) categories
                        .get(0) : null;
                String subCategory = null;
                if (categories.size() > 1) {
                    StringBuffer sb = new StringBuffer((String) categories
                            .get(1));
                    for (int i = 2; i < categories.size()
                            && i <= this.maxSubcategories; ++i) {
                        sb.append(" / " + categories.get(i));
                    }
                    subCategory = sb.toString();
                }

                List bonus = new LinkedList();
                if (this.show.descriptions.size() > 1) {
                    bonus.addAll(this.show.descriptions.subList(1,
                            this.show.descriptions.size()));
                }
                if (this.show.stars > 0) {
                    StringBuffer sb = new StringBuffer();
                    for (int i = 0; i < this.show.stars; ++i) {
                        sb.append('*');
                    }
                    bonus.add(sb.toString());
                }
                if (this.show.aspect != null) {
                    bonus.add(this.show.aspect);
                }
                if ("no".equals(this.show.colour)) {
                    bonus.add("B&W");
                }
                if (this.show.quality != null) {
                    bonus.add(this.show.quality);
                }
                if (this.show.audio != null) {
                    bonus.add(initcap(this.show.audio));
                }
                if (!this.show.countries.isEmpty()) {
                    StringBuffer sb = new StringBuffer();
                    Iterator it = this.show.countries.iterator();
                    if (it.hasNext()) {
                        sb.append(it.next());
                        while (it.hasNext()) {
                            sb.append("/" + it.next());
                        }
                    }
                    bonus.add(sb.toString());
                }
                if (this.show.originalLanguage != null
                        && !this.show.originalLanguage
                                .equals(this.show.language)) {
                    bonus.add("Original language: "
                            + this.show.originalLanguage);
                }
                if (this.show.season > 0) {
                    bonus.add("Season "
                            + this.show.season
                            + (this.show.seasons > 0 ? "/" + this.show.seasons
                                    : ""));
                } else if (this.show.seasons > 0) {
                    bonus.add(this.show.seasons + " seasons");
                }
                if (this.show.episode > 0) {
                    bonus.add("Episode "
                            + this.show.episode
                            + (this.show.episodes > 0 ? "/"
                                    + this.show.episodes : ""));
                } else if (this.show.episodes > 0) {
                    bonus.add(this.show.episodes + " episodes");
                }
                if (this.show.part > 0) {
                    bonus
                            .add("Part "
                                    + this.show.part
                                    + (this.show.parts > 0 ? "/"
                                            + this.show.parts : ""));
                } else if (this.show.parts > 0) {
                    bonus.add(this.show.parts + " parts");
                }

                String year = this.show.year;
                if (this.dateYearDecoration != null && this.show.date != null) {
                    year = this.dateYearDecoration.format(
                            new Object[] {this.show.date}, new StringBuffer(),
                            null).toString();
                }

                String showId = generateShowId(category);

                /*
                 * Add the show now to SageTV EPG
                 * rerunTitle and title meaning is not fully clear needs more documentation or links
                 * @see EPGDBPublic.addShowPublic()
                  */
                if (!this.guide.addShowPublic(rerunTitle, title, episodeName,
                        desc, 0L, category, subCategory,
                        toStringArray(this.show.people), this.show.roles
                                .toByteArray(), this.show.rating,
                        toStringArray(this.show.expandedRatings), year, null,
                        toStringArray(bonus), showId, this.show.language, 0L)) {
                    throw new RuntimeException("Add show failed.");
                }

                int stationId = this.channel.id.intValue();
                long start = this.show.start.getTime();
                long duration = this.show.end.getTime() - start;
                if (!this.guide.addAiringPublic(showId, stationId, start,
                        duration)) {
                    throw new RuntimeException("Add airing failed.");
                }
            } catch (Throwable t) {
                log(t);
            }
        }
    }

    /**
     * Routine to examine if a configuration item is activated.
     * 
     * @param aList the list of properties that is checked.
     * @param aActivatedBy the list of properties that activates the configuration item.
     * @return <code>true</code> if the configuration item is activated.
     */
    static final boolean isActivated(List aList, List aActivatedBy) {
        if (aActivatedBy.contains("*")) {
            return true;
        }
        for (Iterator it = aActivatedBy.iterator(); it.hasNext();) {
            if (aList.contains(it.next())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Routine to examine if a configuration item is activated.
     * 
     * @param aItem the property that is checked.
     * @param aActivatedBy the list of properties that activates the configuration item.
     * @return <code>true</code> if the configuration item is activated.
     */
    static final boolean isActivated(Object aItem, List aActivatedBy) {
        return aActivatedBy.contains(aItem.toString())
                || aActivatedBy.contains("*");
    }

    /**
     * Generates a more or less unique show id in the Zap2it format.
     *  
     * @param aCategory the primary category after translation.
     * @return the showId.
     */
    private final String generateShowId(String aCategory) {
        if (this.show.showId != null) {
            return this.show.showId;
        }
        String episodeSuffix;
        String showId = null;
        if (this.show.season > 0 || this.show.episode > 0) {
            episodeSuffix = this.show.season > 0 ? FORMAT_00
                    .format(this.show.season - 1) : "00";
            episodeSuffix += this.show.episode > 0 ? FORMAT_00
                    .format(this.show.episode) : "00";
            // Season 1 episode 1 should render into "0001" allowing 
            // Malore's series Premieres & Specials to trigger on 
            // the episode ID.
            showId = "EP";
        } else if (this.show.freeFormEpisodeNumber != null) {
            episodeSuffix = this.show.freeFormEpisodeNumber;
            // Make certain that we have at least 4 characters, so 
            // Cayar's episode number in episode name feature 
            // doesn't barf.
            for (int i = this.show.freeFormEpisodeNumber.length(); i < 4; ++i) {
                episodeSuffix += ' ';
            }
            showId = "SH";
        } else {
            episodeSuffix = "0000";
            showId = "SH";
        }
        if (this.show.part > 0) {
            episodeSuffix += "-" + this.show.part;
        }

        // Now generate a number that is unique to the show.
        CRC32 crc32 = new CRC32();
        if (this.show.title != null) {
            crc32.update(this.show.title.toLowerCase().getBytes());
        }
        boolean uidGenerated = !episodeSuffix.equals("0000");
        if (this.show.episodeName != null) {
            crc32.update(this.show.episodeName.toLowerCase().getBytes());
            uidGenerated = true;
        } else if ("Movie".equals(aCategory)) {
            String director = this.show.getDirector();
            if (director != null) {
                // For a movie without an episode name the director
                // name can be enough to identify the show.
                crc32.update(director.toLowerCase().getBytes());
                uidGenerated = true;
            } else if (this.show.year != null) {
                // The year might be enough to identify the movie.
                crc32.update(this.show.year.toLowerCase().getBytes());
                uidGenerated = true;
            } else {
                // The first two actors might be enough to identify 
                // the movie.
                List actors = this.show.getLeadActors();
                if (!actors.isEmpty()) {
                    crc32.update(((String) actors.get(0)).toLowerCase()
                            .getBytes());
                    uidGenerated = true;
                    if (actors.size() > 0) {
                        crc32.update(((String) actors.get(1)).toLowerCase()
                                .getBytes());
                    }
                }
            }
        }
        if (!uidGenerated) {
            crc32.update(DF_SECONDS.format(this.show.start).getBytes());
        }
        showId += checksumToShowId(crc32.getValue()) + episodeSuffix;

        // Add a sequence number to the id if the show is the second part of a 
        // movie.
        if (this.splitMovieDetectTime > 0 && "Movie".equals(aCategory)) {
            String baseId = showId;
            int count = 0;
            while (this.channel.movieIds.containsKey(showId)
                    && this.show.start.getTime()
                            - ((Date) this.channel.movieIds.get(showId))
                                    .getTime() < this.splitMovieDetectTime) {
                log("diff = "
                        + (this.show.start.getTime() - ((Date) this.channel.movieIds
                                .get(showId)).getTime()));
                showId = baseId + "-" + ++count;
            }
            this.channel.movieIds.put(showId, this.show.start);
        }

        return showId;
    }

    /**
     * Creates a string array from a collection.
     * 
     * @param aCollection the collection that should be converted.
     * @return the string array.
     */
    private static final String[] toStringArray(Collection aCollection) {
        return (String[]) aCollection.toArray(DUMMY_STRING_ARRAY);
    }

    /**
     * Initcaps a string.
     * 
     * @param aString the string to process.
     * @return the processed string.
     */
    private final String initcap(String aString) {
        Matcher lowercaseMatcher = LOWERCASE_WORDS_PATTERN.matcher(aString);
        char[] chars = aString.toCharArray();
        chars[0] = Character.toUpperCase(chars[0]);
        int i;
        while (lowercaseMatcher.find()) {
            if (!this.initcapSkipWords.contains(lowercaseMatcher.group(1))) {
                i = lowercaseMatcher.start(1);
                chars[i] = Character.toUpperCase(chars[i]);
            }
        }
        return new String(chars);
    }

    public final void endPrefixMapping(String aPrefix) throws SAXException {
    // noop
    }

    public final void ignorableWhitespace(char[] aCh, int aStart, int aLength)
            throws SAXException {
    // noop
    }

    public final void setDocumentLocator(Locator aLocator) {
    // noop
    }

    public final void skippedEntity(String aName) throws SAXException {
    // noop
    }

    /**
     * Parse an XMLTV date.
     * 
     * @param aXmltvDate the XMLTV formatted date
     * @return the parsed date.
     */
    private static final Date parseXmltvDate(String aXmltvDate) {
        if (aXmltvDate != null) {
            try {
                switch (aXmltvDate.indexOf(" ")) {
                case 12:
                    return DF_MINUTES.parse(aXmltvDate);
                case 14:
                    return DF_SECONDS.parse(aXmltvDate);
                }
                throw new ParseException("Unknown date format: " + aXmltvDate,
                        0);
            } catch (ParseException e) {
                log(e);
            }
        }
        return null;
    }

    public final void startPrefixMapping(String aPrefix, String aUri)
            throws SAXException {
    // noop
    }

    public final void processingInstruction(String aTarget, String aData)
            throws SAXException {
    // noop
    }

    public final void error(SAXParseException aException) throws SAXException {
        log(aException);
    }

    public final void fatalError(SAXParseException aException)
            throws SAXException {
        log(aException);
    }

    public final void warning(SAXParseException aException) throws SAXException {
        log(aException);
    }

    /**
     * Encode a 32 bit checksum into 6 bytes.
     * 
     * @param checksum
     *            the checksum that should be encoded.
     *            <p>
     *            This is a long since Java doesn't have unsigned integers.
     * @return the encoded checksum (always 6 characters wide).
     */
    private static final String checksumToShowId(long checksum) {
        char[] chs = new char[6];
        for (int i = 0; i < 6; ++i) {
            chs[i] = SHOWID_CHARS[(int) ((checksum >> ((5 - i) * 6)) & 0x3fL)];
        }
        return new String(chs);
    }

    /**
     * Translate a set of categories.
     * 
     * @param aCategories the categories that should be translated.
     * @return the translated categories.
     */
    public final LinkedList translateCategories(Set aCategories) {
        LinkedList translatedCategories = new LinkedList();
        if (!aCategories.isEmpty()) {
            LinkedList notYetTranslated = new LinkedList();
            notYetTranslated.addAll(aCategories);

            // Process the largest number of categories first.
            translation: while (notYetTranslated.size() > 0) {
                for (int length = Math.min(this.maxCategoryTranslationLength,
                        notYetTranslated.size()); length > 0; --length) {
                    List subList = notYetTranslated.subList(0, length);
                    List translations = (List) this.categoryTranslations
                            .get(subList);
                    if (translations != null) {
                        for (Iterator it = translations.iterator(); it
                                .hasNext();) {
                            String category = (String) it.next();
                            if (!translatedCategories.contains(category)) {
                                translatedCategories.add(category);
                            }
                        }
                        subList.clear();
                        continue translation;
                    }
                }
                String category = (String) notYetTranslated.removeFirst();
                if (!translatedCategories.contains(category)) {
                    translatedCategories.add(category);
                }
                if (this.reportMissingTranslateCategory) {
                    logError("Missing configuration: translate.category."
                            + category.replaceAll(" ", "\\\\ ").replaceAll("'",
                                    "\\\\'")
                            + "=");
                    // Only report each category once.
                    List list = new LinkedList();
                    list.add(category);
                    this.categoryTranslations.put(list, list);
                }
            }
        }
        return translatedCategories;
    }

    /**
     * Make a category the first category in the list.
     * 
     * @param aList the list of categories.
     * @param aCategory the category.
     * @return true if the category is first in the list.
     */
    private static final boolean makeFirst(LinkedList aList, String aCategory) {
        if (aList.contains(aCategory)) {
            if (aList.indexOf(aCategory) > 0) {
                aList.remove(aCategory);
                aList.addFirst(aCategory);
            }
            return true;
        }
        return false;
    }
}