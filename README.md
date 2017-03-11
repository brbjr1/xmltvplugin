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

### unit tests
```bash
./gradlew test
```
needs some mockup files locally in order to run (see folder testdata)

## Install
http://forums.sagetv.com/forums/showthread.php?t=17363

### Manual steps
It is assumed that your SageTV server is up and running and that you configured a tuner but without EPG. You can
tune in and the tuner works, however you most likely miss the EPG data so far.

 * run ./gradlew assemble and copy build/libs/xmltv-1.0.xxx.jar to your sagetv server
 * transport the configs to your sagetv server (or extract them from the jar)
 * stop sagetv server
 * mv the xmltv-xxxx.jar to JARs/ directory on your sagetv server
 * edit Sage.properties and enable the plugin with: ``epg/epg_import_plugin=xmltv.XMLTVImportPlugin``
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

### Automatic installation
to be done :-)

## EPG XMLT guide grabber
See https://github.com/yafraorg/docker-yafraepg for a docker based running XMLTV generator.

## References
The source code goes back to the early days of SageTV and was developed by various persons also active on the
SageTV Forum. Some links shall show the references:

 * XMLTVImportPlugin from Demideus http://forums.sagetv.com/forums/showthread.php?t=12740
 * XMLTVImportPlugin from Demideus on Channel IDs http://forums.sagetv.com/forums/showthread.php?p=141039
 * XMLTV HowTo http://forums.sagetv.com/forums/showthread.php?t=17363
 * New Home on GitHub https://github.com/yafraorg/xmltvplugin.git

## Support
Log issues on GitHub.com or fork the repo and contribute if you like.
See as well Forum Thread http://forums.sagetv.com/forums/showthread.php?t=63249
