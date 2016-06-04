# xmltvplugin
XMLTVPlugin for SageTV V9 or higher based on the different java sources from the
SageTV Forums (http://forums.sagetv.com/forums/).

## Build
clone this repository and build the class files by using gradle.

```bash
git clone https://github.com/yafraorg/xmltvplugin.git
./gradlew assemble
```

a zip file will be under build/distribution including a classes and basic configuration files

## Install
http://forums.sagetv.com/forums/showthread.php?t=17363

### Manual steps
It is assumed that your SageTV server is up and running and that you configured a tuner but without EPG. You can
tune in and the tuner works, however you most likely miss the EPG data so far.

 * compile the XMLTVImportPlugin to class files
 * transport the classes and configs to your sagetv server/directory
 * stop sagetv server
 * edit Sage.properties and enable the plugin with: ``epg/epg_import_plugin=xmltv.XMLTVImportPlugin``
 * create the xmltv/ directory and copy the class files into it
 * copy the following files into the sagetv server/directory
   * xmltv.properties
   * epgdata.xml
   * lineupN.properties
   * lineupCommon.properties
 * edit those files for your setup
   * lineupN.properties XMLTV channel id mapping to SageTV channel id -> in order to get the mapping of the
     channel id/name for the xmltv file to the sagetv channel, lookup a file called "XXXX Tuner 0-0-DVB-C.frq"
     and you should be able to see the name of a channel (most likely equal or very close to the one
     of your EPG source) and the ID
   * lineupCommon.properties - change ratings and channel defaults
 * start sagetv server
 * go to the tuner setup and select XMLTV
 * as zip code enter "00000" (five zeros)
 * select the XMLTV Lineup
 * optionally you can upload an icon set which fits to the channel names

## EPG XMLT guide grabber
See https://github.com/yafraorg/docker-yafraepg for a docker based running XMLTV generator.
