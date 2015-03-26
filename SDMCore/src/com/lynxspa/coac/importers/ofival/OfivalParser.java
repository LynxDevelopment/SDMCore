package com.lynxspa.coac.importers.ofival;

import java.text.DecimalFormat;
import java.text.ParseException;

import com.ibm.icu.util.StringTokenizer;
import com.lynxspa.coac.importers.EventMessageImportBean;
import com.lynxspa.coac.importers.ImportParserAdapter;


public class OfivalParser implements ImportParserAdapter {
	
	static enum ServiceBodyParser{
		
		AMS{
			public void parseBody(EventMessageImportBean _importedMessage, String _message) throws ParseException{
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMS:29-33:",29,33,"X(05)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMS:34-34:",34,34,"X(01)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMS:35-46:",35,46,"X(12)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMS:47-58:",47,58,"X(12)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMS:59-60:",59,60,"X(02)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMS:61-66:",61,66,"X(06)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMS:67-69:",67,69,"9(03)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMS:70-77:",70,77,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMS:78-80:",78,80,"X(03)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMS:81-88:",81,88,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMS:89-93:",89,93,"X(05)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMS:94-108:",94,108,"9(15)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMS:109-123:",109,123,"9(15)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMS:124-140:",124,140,"9(10)V9(07)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMS:141-156:",141,156,"9(08)V9(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMS:157-165:",157,165,"9(03)V9(06)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMS:166-181:",166,181,"9(08)V9(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMS:182-182:",182,182,"X(01)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMS:183-183:",183,183,"X(01)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMS:184-184:",184,184,"X(01)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMS:185-200:",185,200,"9(08)V9(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMS:201-216:",201,216,"9(08)V9(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMS:217-224:",217,224,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMS:225-232:",225,232,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMS:233-233:",233,233,"X(01)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMS:234-249:",234,249,"9(08)V9(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMS:250-266:",250,266,"9(10)V9(07)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMS:267-281:",267,281,"9(15)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMS:282-296:",282,296,"9(15)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMS:297-304:",297,304,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMS:305-312:",305,312,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMS:313-320:",313,320,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMS:321-400:",321,400,"X(80)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMS:401-403:",401,403,"X(03)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMS:404-415:",404,415,"X(12)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMS:416-423:",416,423,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMS:424-427:",424,427,"X(04)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMS:428-444:",428,444,"9(10)V9(07)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMS:445-452:",445,452,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMS:453-468:",453,468,"9(08)V9(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMS:469-484:",469,484,"8(08)V9(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMS:485-493:",485,493,"9(03)V9(06)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMS:494-502:",494,502,"9(03)V9(06)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMS:503-519:",503,519,"9(10)V9(07)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMS:520-536:",520,536,"9(10)V9(07)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMS:537-586:",537,586,"X(50)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMS:587-636:",587,636,"X(50)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMS:637-1028:",637,1028,"X(392)");
			}
		},
		AMP{
			public void parseBody(EventMessageImportBean _importedMessage, String _message) throws ParseException{
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:29-33:",29,33,"X(05)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:34-34:",34,34,"X(01)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:35-39:",35,39,"X(05)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:40-51:",40,51,"X(12)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:52-54:",52,54,"X(03)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:55-62:",55,62,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:63-65:",63,65,"9(03)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:66-73:",66,73,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:74-81:",74,81,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:82-89:",82,89,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:90-105:",90,105,"9(14)V9(2)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:106-122:",106,122,"9(10)V9(7)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:123-137:",123,137,"9(15)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:138-152:",138,152,"9(15)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:153-153:",153,153,"X(01)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:154-154:",154,154,"X(01)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:155-156:",155,156,"X(02)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:157-172:",157,172,"9(8)V9(8)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:173-189:",173,189,"9(10)V9(7)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:190-206:",190,206,"9(10)V9(7)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:207-207:",207,207,"X(01)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:208-210:",208,210,"9(03)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:211-226:",211,226,"9(8)V9(8)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:227-242:",227,242,"9(8)V9(8)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:243-243:",243,243,"X(01)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:244-259:",244,259,"9(8)V9(8)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:260-275:",260,275,"9(8)V9(8)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:276-291:",276,291,"9(8)V9(8)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:292-299:",292,299,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:300-315:",300,315,"9(8)V9(8)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:316-323:",316,323,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:324-339:",324,339,"9(8)V9(8)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:340-348:",340,348,"9(3)V9(6)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:349-356:",349,356,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:357-364:",357,364,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:365-372:",365,372,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:373-384:",373,384,"X(12)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:385-396:",385,396,"X(12)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:397-397:",397,397,"X(01)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:398-398:",398,398,"X(01)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:399-401:",399,401,"X(03)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:402-404:",402,404,"X(03)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:405-420:",405,420,"9(8)V9(8)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:421-421:",421,421,"X(01)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:422-429:",422,429,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:430-437:",430,437,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:438-441:",438,441,"X(04)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:442-443:",442,443,"X(02)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:444-444:",444,444,"X(01)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:445-445:",445,445,"X(01)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:446-453:",446,453,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:454-461:",454,461,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:462-469:",462,469,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:470-477:",470,477,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:478-485:",478,485,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:486-493:",486,493,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:494-494:",494,494,"X(01)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:495-511:",495,511,"9(10)V9(7)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:512-528:",512,528,"9(10)V9(7)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"AMP:529-1028:",529,1028,"X(500)");
			}
		},
		CVC{
			public void parseBody(EventMessageImportBean _importedMessage, String _message) throws ParseException{
				OfivalParser.setOfivalValue(_importedMessage,_message,"CVC:29-33:",29,33,"X(05)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"CVC:34-34:",34,34,"X(01)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"CVC:35-39:",35,39,"X(05)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"CVC:40-51:",40,51,"X(12)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"CVC:52-63:",52,63,"X(12)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"CVC:64-66:",64,66,"X(03)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"CVC:67-69:",67,69,"9(03)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"CVC:70-70:",70,70,"X(01)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"CVC:71-78:",71,78,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"CVC:79-86:",79,86,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"CVC:87-94:",87,94,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"CVC:95-102:",95,102,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"CVC:103-119:",103,119,"9(10)V9(07)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"CVC:120-122:",120,122,"X(03)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"CVC:123-138:",123,138,"9(8)V9(8)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"CVC:139-154:",139,154,"9(8)V9(8)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"CVC:155-188:",155,188,"X(34)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"CVC:189-190:",189,190,"X(02)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"CVC:191-220:",191,220,"X(30)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"CVC:221-225:",221,225,"9(05)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"CVC:226-228:",226,228,"9(03)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"CVC:229-229:",229,229,"X(01)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"CVC:230-259:",230,259,"X(30)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"CVC:260-264:",260,264,"9(05)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"CVC:265-344:",265,344,"X(80)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"CVC:345-346:",345,346,"X(02)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"CVC:347-349:",347,349,"X(03)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"CVC:350-357:",350,357,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"CVC:358-365:",358,365,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"CVC:366-369:",366,369,"X(04)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"CVC:370-378:",370,378,"9(03)V9(06)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"CVC:379-394:",379,394,"X(16)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"CVC:395-405:",395,405,"X(11)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"CVC:406-406:",406,406,"X(01)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"CVC:407-1028:",407,1028,"X(622)");
			}
		},
		THR{
			public void parseBody(EventMessageImportBean _importedMessage, String _message) throws ParseException{
				OfivalParser.replaceOfivalValue(_importedMessage,_message,"THR:29-33:",29,33,"X(05)");
				OfivalParser.replaceOfivalValue(_importedMessage,_message,"THR:34-34:",34,34,"X(01)");
				OfivalParser.replaceOfivalValue(_importedMessage,_message,"THR:35-39:",35,39,"X(05)");
				OfivalParser.replaceOfivalValue(_importedMessage,_message,"THR:40-47:",40,47,"X(08)");
				OfivalParser.replaceOfivalValue(_importedMessage,_message,"THR:48-50:",48,50,"X(03)");
				OfivalParser.replaceOfivalValue(_importedMessage,_message,"THR:51-53:",51,53,"9(03)");
				OfivalParser.replaceOfivalValue(_importedMessage,_message,"THR:54-65:",54,65,"X(12)");
				OfivalParser.replaceOfivalValue(_importedMessage,_message,"THR:66-68:",66,68,"9(03)");
				OfivalParser.accumulateOfivalValue(_importedMessage,_message,"THR:69-148:",69,148,"X(80)");
				OfivalParser.replaceOfivalValue(_importedMessage,_message,"THR:149-1028:",149,1028,"X(880)");
			}
		},
		CUP{
			public void parseBody(EventMessageImportBean _importedMessage, String _message) throws ParseException{
				OfivalParser.setOfivalValue(_importedMessage,_message,"CUP:29-33:",29,33,"X(05)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"CUP:34-34:",34,34,"X(01)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"CUP:35-46:",35,46,"X(12)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"CUP:47-58:",47,58,"X(12)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"CUP:59-60:",59,60,"X(02)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"CUP:61-66:",61,66,"X(06)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"CUP:67-69:",67,69,"9(03)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"CUP:70-77:",70,77,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"CUP:78-85:",78,85,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"CUP:86-88:",86,88,"X(03)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"CUP:89-96:",89,96,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"CUP:97-101:",97,101,"X(05)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"CUP:102-117:",102,117,"9(08)V9(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"CUP:118-133:",118,133,"9(08)V9(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"CUP:134-142:",134,142,"9(03)V9(06)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"CUP:143-151:",143,151,"9(03)V9(06)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"CUP:152-160:",152,160,"9(03)V9(06)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"CUP:161-161:",161,161,"X(01)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"CUP:162-191:",162,191,"X(30)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"CUP:192-200:",192,200,"9(03)V9(06)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"CUP:201-209:",201,209,"9(03)V9(06)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"CUP:210-212:",210,212,"X(03)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"CUP:213-220:",213,220,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"CUP:221-224:",221,224,"X(04)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"CUP:225-1028:",225,1028,"X(804)");
			}
		},
		DEV{
			public void parseBody(EventMessageImportBean _importedMessage, String _message) throws ParseException{
				OfivalParser.setOfivalValue(_importedMessage,_message,"DEV:29-33:",29,33,"X(05)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DEV:34-34:",34,34,"X(01)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DEV:35-39:",35,39,"X(05)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DEV:40-51:",40,51,"X(12)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DEV:52-54:",52,54,"X(03)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DEV:55-66:",55,66,"X(12)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DEV:67-78:",67,78,"X(12)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DEV:79-86:",79,86,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DEV:87-89:",87,89,"9(03)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DEV:90-90:",90,90,"X(01)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DEV:91-106:",91,106,"9(08)V9(8)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DEV:107-122:",107,122,"9(08)V9(8)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DEV:123-202:",123,202,"X(80)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DEV:203-211:",203,211,"9(03)V9(6)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DEV:212-214:",212,214,"X(03)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DEV:215-222:",215,222,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DEV:223-226:",223,226,"X(04)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DEV:227-230:",227,230,"X(04)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DEV:231-234:",231,234,"X(04)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DEV:235-238:",235,238,"X(04)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DEV:239-242:",239,242,"X(04)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DEV:243-246:",243,246,"X(04)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DEV:247-250:",247,250,"X(04)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DEV:251-254:",251,254,"X(04)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DEV:255-258:",255,258,"X(04)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DEV:259-262:",259,262,"X(04)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DEV:263-270:",263,270,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DEV:271-1028:",271,1028,"X(258)");
			}
		},
		DAC{
			public void parseBody(EventMessageImportBean _importedMessage, String _message) throws ParseException{
				OfivalParser.setOfivalValue(_importedMessage,_message,"DAC:29-33:",29,33,"X(05)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DAC:34-34:",34,34,"X(01)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DAC:35-39:",35,39,"X(05)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DAC:40-51:",40,51,"X(12)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DAC:52-63:",52,63,"X(12)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DAC:64-75:",64,75,"X(12)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DAC:76-83:",76,83,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DAC:84-86:",84,86,"9(03)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DAC:87-91:",87,91,"9(05)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DAC:92-92:",92,92,"X(01)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DAC:93-93:",93,93,"X(01)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DAC:94-96:",94,96,"9(03)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DAC:97-100:",97,100,"X(04)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DAC:101-116:",101,116,"9(08)V9(8)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DAC:117-132:",117,132,"9(08)V9(8)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DAC:133-212:",133,212,"X(80)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DAC:213-292:",213,292,"X(80)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DAC:293-372:",293,372,"X(80)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DAC:373-381:",373,381,"9(03)V9(6)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DAC:382-383:",382,383,"X(02)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DAC:384-387:",384,387,"X(04)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DAC:388-391:",388,391,"X(04)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DAC:392-395:",392,395,"X(04)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DAC:396-399:",396,399,"X(04)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DAC:400-403:",400,403,"X(04)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DAC:404-407:",404,407,"X(04)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DAC:408-411:",408,411,"X(04)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DAC:412-415:",412,415,"X(04)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DAC:416-419:",416,419,"X(04)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DAC:420-423:",420,423,"X(04)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DAC:424-425:",424,425,"X(02)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DAC:426-428:",426,428,"X(03)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DAC:429-444:",429,444,"9(08)V9(8)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DAC:445-460:",445,460,"9(08)V9(8)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DAC:461-468:",461,468,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DAC:469-485:",469,485,"9(10)V9(7)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DAC:486-501:",486,501,"9(14)V9(2)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DAC:502-508:",502,508,"9(07)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DAC:509-516:",509,516,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"DAC:517-1028:",517,1028,"X(512)");
			}
		},
		FUS{
			public void parseBody(EventMessageImportBean _importedMessage, String _message) throws ParseException{
				OfivalParser.setOfivalValue(_importedMessage,_message,"FUS:29-33:",29,33,"X(05)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"FUS:34-34:",34,34,"X(01)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"FUS:35-39:",35,39,"X(05)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"FUS:40-51:",40,51,"X(12)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"FUS:52-63:",52,63,"X(12)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"FUS:64-66:",64,66,"X(03)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"FUS:67-74:",67,74,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"FUS:75-77:",75,77,"9(03)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"FUS:78-78:",78,78,"X(01)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"FUS:79-81:",79,81,"9(03)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"FUS:82-89:",82,89,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"FUS:90-97:",90,97,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"FUS:98-497:",98,497,"X(80)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"FUS:498-505:",498,505,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"FUS:506-509:",506,509,"X(04)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"FUS:510-517:",510,517,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"FUS:518-525:",518,525,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"FUS:526-526:",526,526,"X(01)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"FUS:527-541:",527,541,"9(15)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"FUS:542-556:",542,556,"9(15)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"FUS:557-1028:",557,1028,"X(472)");
			}
		},
		FVL{
			public void parseBody(EventMessageImportBean _importedMessage, String _message) throws ParseException{
				OfivalParser.setOfivalValue(_importedMessage,_message,"FUS:FVL:29-33:",29,33,"X(05)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"FUS:FVL:34-34:",34,34,"X(01)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"FUS:FVL:35-39:",35,39,"X(5)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"FUS:FVL:40-51:",40,51,"X(12)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"FUS:FVL:52-63:",52,63,"X(12)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"FUS:FVL:64-66:",64,66,"X(3)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"FUS:FVL:67-74:",67,74,"X(8)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"FUS:FVL:75-77:",75,77,"9(03)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"FUS:FVL:78-82:",78,82,"X(05)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"FUS:FVL:83-94:",83,94,"X(12)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"FUS:FVL:95-111:",95,111,"9(10)V9(7)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"FUS:FVL:112-128:",112,128,"9(10)V9(7)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"FUS:FVL:129-144:",129,144,"9(08)V9(8)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"FUS:FVL:145-160:",145,160,"9(08)V9(8)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"FUS:FVL:161-163:",161,163,"X(03)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"FUS:FVL:164-483:",164,483,"X(80)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"FUS:FVL:484-484:",484,484,"X(01)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"FUS:FVL:485-500:",485,500,"9(08)V9(8)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"FUS:FVL:501-508:",501,508,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"FUS:FVL:509-516:",509,516,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"FUS:FVL:517-524:",517,524,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"FUS:FVL:525-525:",525,525,"X(01)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"FUS:FVL:526-540:",526,540,"9(15)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"FUS:FVL:541-555:",541,555,"9(15)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"FUS:FVL:556-563:",556,563,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"FUS:FVL:564-1028:",564,1028,"X(465)");
			}
		},
		OPA{
			public void parseBody(EventMessageImportBean _importedMessage, String _message) throws ParseException{
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPA:29-33:",29,33,"X(05)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPA:34-34:",34,34,"X(01)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPA:35-39:",35,39," X(05)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPA:40-51:",40,51," X(12)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPA:52-63:",52,63,"X(12)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPA:64-66:",64,66," 9(03)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPA:67-69:",67,69," X(03)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPA:70-77:",70,77,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPA:78-85:",78,85," X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPA:86-165:",86,165,"X(80)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPA:166-170:",166,170," X(05)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPA:171-210:",171,210,"X(40)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPA:211-227:",211,227,"9(10)V9(7)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPA:228-244:",228,244,"9(10)V9(7)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPA:245-260:",245,260,"9(8)V9(8)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPA:261-261:",261,261," X(01)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPA:262-278:",262,278,"9(10)V9(7)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPA:279-286:",279,286,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPA:287-294:",287,294," X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPA:295-454:",295,454,"X(80)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPA:455-462:",455,462,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPA:463-470:",463,470,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPA:471-471:",471,471,"X(01)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPA:472-475:",472,475,"X(04)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPA:476-476:",476,476,"X(01)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPA:477-477:",477,477,"X(01)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPA:478-480:",478,480,"X(03)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPA:481-492:",481,492,"X(12)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPA:493-509:",493,509,"9(10)V9(7)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPA:510-526:",510,526,"9(10)V9(7)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPA:527-527:",527,527,"X(01)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPA:528-543:",528,543,"9(08)V9(8)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPA:544-560:",544,560,"9(10)V9(7)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPA:561-569:",561,569,"9(03)V9(6)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPA:570-570:",570,570,"X(01)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPA:571-1028:",571,1028,"X(458)");
			}
		},
		OPV{
			public void parseBody(EventMessageImportBean _importedMessage, String _message) throws ParseException{
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPV:29-33:",29,33,"X(05)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPV:34-34:",34,34,"X(01)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPV:35-39:",35,39,"X(05)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPV:40-51:",40,51,"X(12)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPV:52-63:",52,63,"X(12)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPV:64-64:",64,64," X(01)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPV:65-67:",65,67,"9(03)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPV:68-70:",68,70,"X(03)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPV:71-78:",71,78," X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPV:79-86:",79,86,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPV:87-91:",87,91," X(05)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPV:92-131:",92,131,"X(40)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPV:132-139:",132,139," X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPV:140-147:",140,147," X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPV:148-164:",148,164,"9(10)V9(7)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPV:165-165:",165,165,"X(01)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPV:166-182:",166,182,"9(10)V9(7)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPV:183-190:",183,190,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPV:191-198:",191,198," X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPV:199-278:",199,278,"X(80)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPV:279-286:",279,286,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPV:287-301:",287,301,"9(15)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPV:302-316:",302,316,"9(15)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPV:317-332:",317,332,"9(08)V9(8)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPV:333-335:",333,335,"X(03)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"OPV:336-1028:",336,1028,"X(693)");
			}
		},
		RFJ{
			public void parseBody(EventMessageImportBean _importedMessage, String _message) throws ParseException{
				OfivalParser.setOfivalValue(_importedMessage,_message,"RFJ:29-33:",29,33,"X(05)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"RFJ:34-34:",34,34,"X(01)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"RFJ:35-46:",35,46,"X(12)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"RFJ:47-58:",47,58,"X(12)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"RFJ:59-60:",59,60,"X(02)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"RFJ:61-66:",61,66,"X(06)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"RFJ:67-67:",67,67,"X(01)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"RFJ:68-79:",68,79,"X(12)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"RFJ:80-159:",80,159,"X(80)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"RFJ:160-161:",160,161,"X(02)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"RFJ:162-177:",162,177,"9(8)V9(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"RFJ:178-182:",178,182,"X(05)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"RFJ:183-190:",183,190,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"RFJ:191-207:",191,207,"9(10)V9(07)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"RFJ:208-224:",208,224,"9(10)V9(07)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"RFJ:225-241:",225,241,"9(10)V9(07)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"RFJ:242-257:",242,257,"9(8)V9(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"RFJ:258-265:",258,265,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"RFJ:266-266:",266,266,"X(01)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"RFJ:267-275:",267,275,"9(3)V9(06)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"RFJ:276-284:",276,284,"9(3)V9(06)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"RFJ:285-292:",285,292,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"RFJ:293-293:",293,293,"X(01)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"RFJ:294-294:",294,294,"X(01)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"RFJ:295-295:",295,295,"X(01)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"RFJ:296-296:",296,296,"X(01)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"RFJ:297-297:",297,297,"X(01)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"RFJ:298-305:",298,305,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"RFJ:306-313:",306,313,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"RFJ:314-321:",314,321,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"RFJ:322-322:",322,322,"X(01)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"RFJ:323-330:",323,330,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"RFJ:331-338:",331,338,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"RFJ:339-346:",339,346,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"RFJ:347-347:",347,347,"X(01)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"RFJ:348-356:",348,356,"9(3)V9(06) ");
				OfivalParser.setOfivalValue(_importedMessage,_message,"RFJ:357-365:",357,365,"9(3)V9(06) ");
				OfivalParser.setOfivalValue(_importedMessage,_message,"RFJ:366-366:",366,366,"X(01)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"RFJ:367-369:",367,369,"X(03)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"RFJ:370-370:",370,370,"X(01)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"RFJ:371-371:",371,371,"X(01)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"RFJ:372-387:",372,387,"9(8)V9(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"RFJ:388-395:",388,395,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"RFJ:396-403:",396,403,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"RFJ:404-411:",404,411,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"RFJ:412-427:",412,427,"9(8)V9(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"RFJ:428-429:",428,429,"X(02)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"RFJ:430-437:",430,437,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"RFJ:438-441:",438,441,"X(04)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"RFJ:442-453:",442,453,"X(12)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"RFJ:454-470:",454,470,"9(10)V9(07)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"RFJ:471-487:",471,487,"9(10)V9(07)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"RFJ:488-492:",488,492,"X(05)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"RFJ:493-1028:",493,1028,"X(536)");
			}
		},
		SPL{
			public void parseBody(EventMessageImportBean _importedMessage, String _message) throws ParseException{
				OfivalParser.setOfivalValue(_importedMessage,_message,"SPL:29-33:",29,33,"X(05)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"SPL:34-34:",34,34,"X(01)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"SPL:35-39:",35,39,"X(05)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"SPL:40-51:",40,51,"X(12)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"SPL:52-63:",52,63,"X(12)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"SPL:64-66:",64,66,"X(03)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"SPL:67-74:",67,74,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"SPL:75-77:",75,77,"9(03)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"SPL:78-94:",78,94,"9(10)V9(7)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"SPL:95-111:",95,111,"9(10)V9(7)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"SPL:112-127:",112,127,"9(8)V9(8)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"SPL:128-143:",128,143,"9(8)V9(8)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"SPL:144-155:",144,155,"X(12)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"SPL:156-160:",156,160,"X(05)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"SPL:161-165:",161,165,"X(05)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"SPL:166-173:",166,173,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"SPL:174-181:",174,181,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"SPL:182-261:",182,261,"X(80)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"SPL:262-341:",262,341,"X(80)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"SPL:342-421:",342,421,"X(80)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"SPL:422-429:",422,429,"X(08)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"SPL:430-446:",430,446,"9(10)V9(7)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"SPL:447-463:",447,463,"9(10)V9(7)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"SPL:464-478:",464,478,"9(15)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"SPL:479-493:",479,493,"9(15)");
				OfivalParser.setOfivalValue(_importedMessage,_message,"SPL:494-1028:",494,1028,"X(535)");
			}
		},
		UNKNOWN{
			public void parseBody(EventMessageImportBean _importedMessage, String _message) throws ParseException{}
		};
		
		public abstract void parseBody(EventMessageImportBean _importedMessage, String _message) throws ParseException;
	} 
	
	public OfivalParser() {
		super();
	}

	private String parseHeader(EventMessageImportBean _importedMessage, String _message) throws ParseException{

		String reply=null;
		
		OfivalParser.setOfivalValue(_importedMessage,_message,":1-8:",1,8,"9(08)");
		reply=OfivalParser.setOfivalValue(_importedMessage,_message,":9-16:",9,16,"X(08)");
		OfivalParser.setOfivalValue(_importedMessage,_message,":17-24:",17,24,"X(08)");
		OfivalParser.setOfivalValue(_importedMessage,_message,":25-28:",25,28,"9(04)");
		
		return reply;
	}

	private void parseFooter(EventMessageImportBean _importedMessage, String _message) throws ParseException{

		OfivalParser.setOfivalValue(_importedMessage,_message,":1029-1036:",1029,1036,"X(08)");
		OfivalParser.setOfivalValue(_importedMessage,_message,":1037-1046:",1037,1046,"X(10)");
		OfivalParser.setOfivalValue(_importedMessage,_message,":1047-1054:",1047,1054,"X(08)");
		OfivalParser.setOfivalValue(_importedMessage,_message,":1055-1062:",1055,1062,"X(08)");
		OfivalParser.setOfivalValue(_importedMessage,_message,":1063-1072:",1063,1072,"X(10)");
		OfivalParser.setOfivalValue(_importedMessage,_message,":1073-1080:",1073,1080,"X(08)");
		OfivalParser.setOfivalValue(_importedMessage,_message,":1081-1100:",1081,1100,"X(19)");
	}
	
	public boolean parse(EventMessageImportBean _importedMessage, String _message,int _offset) throws ParseException {
		
		boolean reply=true;
		
		try{
			if(_message.length()>1100){
				parseMultiLine(_importedMessage,_message,_offset);
			}else{
				parseLine(_importedMessage,_message,_offset);
			}
		}catch (ParseException e) {
			throw e;
		}catch (Exception e) {
			throw new ParseException(e.getMessage(),0);
		}
	
		return reply;
	}
	public boolean parseMultiLine(EventMessageImportBean _importedMessage, String _message,int _offset) throws ParseException {
		
		boolean reply=true;
		String service="";
		String parsingLine=null;
		ServiceBodyParser bodyParser=null;
		StringTokenizer tokenizer=null;
		int lineNumber=0;
		
		try{
			tokenizer=new StringTokenizer(_message,"\n");
			while(tokenizer.hasMoreTokens()){
				parsingLine=tokenizer.nextToken();
				if(lineNumber==0){
					service=parseHeader(_importedMessage,parsingLine);
					parseFooter(_importedMessage,parsingLine);
					try{
						bodyParser=ServiceBodyParser.valueOf(service);
					}catch (Exception e) {
						bodyParser=ServiceBodyParser.UNKNOWN;
					}
				}
				bodyParser.parseBody(_importedMessage, parsingLine);
				lineNumber++;
			}
		}catch (ParseException e) {
			throw new ParseException(e.getMessage(),e.getErrorOffset()+(lineNumber*1100));
		}catch (Exception e) {
			throw new ParseException(e.getMessage(),0);
		}
	
		return reply;
	}

	public boolean parseLine(EventMessageImportBean _importedMessage, String _message,int _offset) throws ParseException {
		
		boolean reply=true;
		String service="";
		ServiceBodyParser bodyParser=null;
		
		try{
			service=parseHeader(_importedMessage,_message);
			try{
				bodyParser=ServiceBodyParser.valueOf(service);
			}catch (Exception e) {
				bodyParser=ServiceBodyParser.UNKNOWN;
			}
			bodyParser.parseBody(_importedMessage, _message);
			parseFooter(_importedMessage,_message);
		}catch (ParseException e) {
			throw e;
		}catch (Exception e) {
			throw new ParseException(e.getMessage(),0);
		}
	
		return reply;
	}

	public static String setOfivalValue(EventMessageImportBean _importedMessage,String _message,String _path,int _start,int _end,String _format) throws ParseException{
		
		String reply=null;
		
		try{
			reply=getOfivalValue(_message,_start,_end,_format);
			if(reply.length()!=0){
				_importedMessage.addField(_path,reply);
			}else{
				reply=null;
			}
		}catch(Exception e){
			throw new ParseException("Parsing exception: "+e.getMessage(),_start);
		}
		
		return reply;
	}

	public static String replaceOfivalValue(EventMessageImportBean _importedMessage,String _message,String _path,int _start,int _end,String _format) throws ParseException{
		
		String reply=null;
		
		try{
			reply=getOfivalValue(_message,_start,_end,_format);
			if(reply.length()!=0){
				if(_importedMessage.getField(_path)!=null){
					_importedMessage.replaceField(_path,reply);
				}else{
					_importedMessage.addField(_path,reply);
				}
			}else{
				reply=null;
			}
		}catch(Exception e){
			throw new ParseException("Parsing exception: "+e.getMessage(),_start);
		}
		
		return reply;
	}
	
	public static String accumulateOfivalValue(EventMessageImportBean _importedMessage,String _message,String _path,int _start,int _end,String _format) throws ParseException{
		
		String reply=null;
		String originalValue=null;
		
		try{
			reply=getOfivalValue(_message,_start,_end,_format);
			if(reply.length()!=0){
				if((originalValue=_importedMessage.getField(_path))!=null){
					_importedMessage.replaceField(_path,originalValue+' '+reply);
				}else{
					_importedMessage.addField(_path,reply);
				}
			}else{
				reply=null;
			}
		}catch(Exception e){
			throw new ParseException("Parsing exception: "+e.getMessage(),_start);
		}
		
		return reply;
	}

	public static String getOfivalValue(String _message,int _start,int _end,String _format){

		String reply=null;
		int integerEnd=0;
		DecimalFormat format=null;
		
		reply=_message.substring(_start-1,_end);
		switch(_format.charAt(0)){
			case 'X':	reply=reply.replace('','\n').trim();
						break;
			case '9':	if((integerEnd=_format.indexOf(')'))<_format.length()-1){
							integerEnd=Integer.parseInt(_format.substring(2,integerEnd));
							format=new DecimalFormat("##################0.0####################");
							reply=format.format(Double.parseDouble(reply.substring(0,integerEnd)+'.'+reply.substring(integerEnd)));
						}else{
							reply=String.valueOf(Long.parseLong(reply));
						}
						break;
		}
		
		return reply;
	}
}
