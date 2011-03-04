// --------------------------------------------------------------------------
// The jSyncManager Project -- Source File.
// Copyright (c) 1998 - 2004 Brad BARCLAY <bbarclay@jsyncmanager.org>
// --------------------------------------------------------------------------
// OSI Certified Open Source Software
// --------------------------------------------------------------------------
//
// This library is free software; you can redistribute it and/or modify it
// under the terms of the GNU Lesser General Public License as published 
// by the Free Software Foundation; either version 2.1 of the License, or 
// (at your option) any later version.
//
// This library is distributed in the hope that it will be useful, but
// WITHOUT ANY WARRANTY; without even the implied warranty of 
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU 
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public 
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
//
// --------------------------------------------------------------------------
// $Id: DLPDatabase.java,v 3.13 2005/02/11 20:11:08 yaztromo Exp $
// --------------------------------------------------------------------------

package org.jSyncManager.API.Protocol.Util;

import org.jSyncManager.API.Tools.*;
import java.io.*;
import java.net.URL;
import java.util.Vector;

// ==========================================================================

/** A class representing an entire handheld database.
  * This class holds a single database.  Note that this construct is not directly 
  * readable or writable to or from a handheld device -- instead it's a container class 
  * that can hold all of the other elements that can be read or written to the handheld device.
  * @author Brad BARCLAY &lt;bbarclay@jsyncmanager.org&gt;
  * <br>Last modified by: $Author: yaztromo $ on $Date: 2005/02/11 20:11:08 $.
  * @version $Revision: 3.13 $
  */
public class DLPDatabase implements Serializable {
   private boolean isResourceDB = false;
   protected Vector data = new Vector();
   private DLPDatabaseInfo databaseInfo = null;
   private DLPBlock applicationBlock = null;
   private DLPBlock sortBlock = null;

   /** A flag to denate that we want to query RAM-based databases.            */
   public static final byte SEARCH_RAM_BASED_DB_FLAG = (byte)0x80;

   /** A flag to denate that we want to query ROM-based databases.            */
   public static final byte SEARCH_ROM_BASED_DB_FLAG = (byte)0x40;

   /** A flag to denote that during a call to read the database list, multiple
     * entries are allowed in the response.
     */
   public static final byte READ_MULTI_FLAG = (byte)0x20;

   /** A flag to denote that this is a resource (application) database.       */
   public static final char RESOURCE_DATABASE = 0x0001;

   /** A flag to denote that this database is flagged as read-only.           */
   public static final char READ_ONLY = 0x0002;

   /** A flag to denote that the app info block is dirty (modified).          */
   public static final char APP_INFO_DIRTY = 0x0004;

   /** A flag to denote that this database should be backed up at sync time.  */
   public static final char BACKUP_FLAG = 0x0008;

   /** A flag to denote that it's okay to write a newer version of this database
     * with a different name if this database is currently open.
     */
   public static final char INSTALL_NEWER = 0x0010;

   /** A flag to denote that the handheld should be reset after the installation of this database.
     */
   public static final char RESET_AFTER_INSTALL = 0x0020;

   /** A flag to denote that this database should employ copy protection.
     * Copy protection is typically used to prevent IR beaming of the database.
     */
   public static final char COPY_PREVENTION = 0x0040;

   /** A flag to denote that the database is open.                            */
   public static final char DB_OPEN = 0x8000;

   /** A flag to denote that the database should be opened in read mode.      */
   public static final byte READ_MODE = (byte)0x80;

   /** A flag to denote that the database should be opened in write mode.     */
   public static final byte WRITE_MODE = (byte)0x40;

   /** A flag to denote that the database should be opened in exclusive mode. */
   public static final byte EXCLUSIVE_MODE = (byte)0x20;

   /** A flag to denote that secret records should be shown.                  */
   public static final byte SHOW_SECRET = (byte)0x10;

   /** A flag used when calling JHotSync.setDatabaseInfo() to denote that no version change should be made.
     */
   public static final char NO_VERSION_CHANGE = 0xFFFF;

   private static final long serialVersionUID = 7721245540424622393L;

// ==========================================================================

/** DLPDatabase default constructor
 */
protected DLPDatabase() {
} // end-constructor

// --------------------------------------------------------------------------

/** DLPDatabase constructor, with a flag denoting whether this is a resource DB 
  * or not, and the DLPDatabaseInfo block.
  * @param flag <b>true</b> if this is a resource (application) database, <b>false</b> otherwise.
  * @param dlpdatabaseinfo DLPDatabaseInfo block.
  */
public DLPDatabase(boolean flag, DLPDatabaseInfo dlpdatabaseinfo) {
   setIsResourceDB(flag);
   setDatabaseInfo(dlpdatabaseinfo);
} // end-constructor

// ==========================================================================

/** Add an element in the form of a DLPRecord to the database.
  * @param dlprecord DLPRecord to be inserted.
  */
public void addElement(DLPRecord dlprecord) {
   if (isResourceDB() || dlprecord==null) return;
   data.addElement(dlprecord);
} // end-method

// --------------------------------------------------------------------------

/** Add an element in the form of a DLPResource to the database.
  * @param dlpresource DLPResource to be inserted.
  */
public void addElement(DLPResource dlpresource) {
   if (!isResourceDB() || dlpresource==null) return;
   data.addElement(dlpresource);
} // end-method

// --------------------------------------------------------------------------

/** Export this database to a File.
  * @param s path and name of file to export to.
  */
public void exportDatabase(String s) {
   java.io.File file=new java.io.File(s);
   if (file.isFile()) file.delete();
   if(!file.exists()) file.mkdirs();
   
   if(isResourceDB()) exportPRC(s);
   else exportPDB(s);
} // end-method

// --------------------------------------------------------------------------

/** Export a database to a PDB-format file.
  * @param s String name of file to export to.
  */
private void exportPDB(String s) {
   int numElements=getElements();
   int outputSize = 80 + 8 * numElements;
   if(getApplicationBlock() != null) outputSize += getApplicationBlock().getData().length;
   if(getSortBlock() != null) outputSize += getSortBlock().getData().length;
   for(int j = 0; j < numElements; j++) outputSize += ((DLPRecord)getElement(j)).getData().length;

   byte output[] = new byte[outputSize];
   int k = 0;
   byte dbName[] = getDatabaseInfo().getName().getBytes();

   System.arraycopy(dbName, 0, output, k, dbName.length);
   k+=dbName.length;

   for(int i1 = dbName.length; i1 < 32; i1++) output[k++] = 0;

   char c = getDatabaseInfo().getDatabaseFlags();
   k = UnsignedByte.copyCharToByteArray(c, output, k);
   c = (char)getDatabaseInfo().getVersion();
   k = UnsignedByte.copyCharToByteArray(c, output, k);
   
   int temp;
   k = UnsignedByte.copyLongToByteArray4(getDatabaseInfo().getCreationTime().convertToSeconds(), output, k);
   k = UnsignedByte.copyLongToByteArray4(getDatabaseInfo().getModificationTime().convertToSeconds(), output, k);
   k = UnsignedByte.copyLongToByteArray4(getDatabaseInfo().getBackupTime().convertToSeconds(), output, k);

   temp = getDatabaseInfo().getModificationNumber();
   k = UnsignedByte.copyIntToByteArray(temp, output, k);

   int nextPtr = 80 + numElements * 8;

   if(getApplicationBlock() != null) {
      temp = nextPtr;
      k = UnsignedByte.copyIntToByteArray(temp, output, k);
      nextPtr += getApplicationBlock().getData().length;
   } else {
    for (int z=0;z<4;z++) output[k++]=0;
   } /* endif */

   if(getSortBlock() != null) {
      temp = nextPtr;
      k = UnsignedByte.copyIntToByteArray(temp, output, k);
      nextPtr += getSortBlock().getData().length;
   } else {
    for (int z=0;z<4;z++) output[k++]=0;
   } /* endif */

   temp = getDatabaseInfo().getType();
   k = UnsignedByte.copyIntToByteArray(temp, output, k);

   temp = getDatabaseInfo().getCreator();
   k = UnsignedByte.copyIntToByteArray(temp, output, k);

   for(int j1 = 0; j1 < 8; j1++) output[k++] = 0;

   c = (char)numElements;
   k = UnsignedByte.copyCharToByteArray(c, output, k);

   for(int k1 = 0; k1 < numElements; k1++) {
      DLPRecord dlprecord = (DLPRecord)getElement(k1);
      int j3 = nextPtr;
      k = UnsignedByte.copyIntToByteArray(j3, output, k);

      // There's no archived flag in PDB, it's deleted and has data
      byte attr = dlprecord.getAttributes();
      if ((attr & DLPRecord.ARCHIVED) != 0) attr |= DLPRecord.DELETED;
      output[k++] = (byte)((attr & 0xf0) | (dlprecord.getCategory() & 0x0f));      

      int uniqueID = dlprecord.getRecordID();
      output[k++] = (byte)((uniqueID & 0x00FF0000) >>16);
      output[k++] = (byte)((uniqueID & 0x0000FF00) >> 8);
      output[k++] = (byte)((uniqueID & 0x000000FF));
      nextPtr += dlprecord.getData().length;
   }

   // Insert two null bytes.
   output[k++]=0;
   output[k++]=0;

   if(getApplicationBlock() != null) {
      byte output0[] = getApplicationBlock().getData();
      System.arraycopy(output0, 0, output, k, output0.length);
      k+=output0.length;
   }

   if(getSortBlock() != null) {
      byte output1[] = getSortBlock().getData();
      System.arraycopy(output1, 0, output, k, output1.length);
      k+=output1.length;
   }

   for(int j2 = 0; j2 < numElements; j2++) {
      DLPRecord rec = (DLPRecord)getElement(j2);
      byte recBytes[] = rec.getData();
      System.arraycopy(recBytes, 0, output, k, recBytes.length);
      k+=recBytes.length;
   }

   String s1 = System.getProperty("file.separator");
   String s2;
   String outFileName = StringManipulator.generateValidFilename(getDatabaseInfo().getName());

   if(s.endsWith(s1)) s2 = s + outFileName + ".PDB";
   else s2 = s + s1 + outFileName + ".PDB";

   try {
      FileOutputStream fos = new FileOutputStream(s2);
      fos.write(output);
      fos.close();
   } catch(Exception exception) {
      System.err.println("Unable to write database due to exception: " + exception.toString());
   }
} // end-method

// --------------------------------------------------------------------------

/** Export a PRC-format file.
  * @param s String name of file to export to.
  */
private void exportPRC(String s) {
   int numElements=getElements();

   int outputSize = 80 + 10 * numElements;
   for(int j = 0; j < numElements; j++) outputSize += ((DLPResource)getElement(j)).getData().length;

   byte output[] = new byte[outputSize];
   int k = 0;
   byte dbName[] = getDatabaseInfo().getName().getBytes();

   System.arraycopy(dbName, 0, output, k, dbName.length);
   k+=dbName.length;
   
   for(int i1 = dbName.length; i1 < 32; i1++) output[k++] = 0;

   char c = getDatabaseInfo().getDatabaseFlags();
   k = UnsignedByte.copyCharToByteArray(c, output, k);

   c = (char)getDatabaseInfo().getVersion();
   k = UnsignedByte.copyCharToByteArray(c, output, k);

   int l2;
   k = UnsignedByte.copyLongToByteArray4(getDatabaseInfo().getCreationTime().convertToSeconds(), output, k);
   k = UnsignedByte.copyLongToByteArray4(getDatabaseInfo().getModificationTime().convertToSeconds(), output, k);
   k = UnsignedByte.copyLongToByteArray4(getDatabaseInfo().getBackupTime().convertToSeconds(), output, k);

   l2 = getDatabaseInfo().getModificationNumber();
   k = UnsignedByte.copyIntToByteArray(l2, output, k);

   for(int j1 = 0; j1 < 8; j1++) output[k++] = 0;

   l2 = getDatabaseInfo().getType();
   k = UnsignedByte.copyIntToByteArray(l2, output, k);

   l2 = getDatabaseInfo().getCreator();
   k = UnsignedByte.copyIntToByteArray(l2, output, k);

   for(int k1 = 0; k1 < 8; k1++) output[k++] = 0;

   c = (char)numElements;
   k = UnsignedByte.copyCharToByteArray(c, output, k);
   int nextPtr = k + 2 + numElements * 10;

   for(int l1 = 0; l1 < numElements; l1++) {
      DLPResource dlpresource = (DLPResource)getElement(l1);
      int i3 = dlpresource.getResourceType();
      k = UnsignedByte.copyIntToByteArray(i3, output, k);

      char c1 = dlpresource.getResourceID();
      k = UnsignedByte.copyCharToByteArray(c1, output, k);

      i3 = nextPtr;
      k = UnsignedByte.copyIntToByteArray(i3, output, k);
      nextPtr += dlpresource.getData().length;
   }

   // Insert two null bytes.
   output[k++]=0;
   output[k++]=0;

   for(int i2 = 0; i2 < numElements; i2++) {
      DLPResource res = (DLPResource)getElement(i2);
      byte resBytes[] = res.getData();
      System.arraycopy(resBytes, 0, output, k, resBytes.length);
      k+=resBytes.length;
   }

   String s1 = System.getProperty("file.separator");
   String s2;

   if(s.endsWith(s1)) s2 = s + getDatabaseInfo().getName() + ".PRC";
   else s2 = s + s1 + getDatabaseInfo().getName() + ".PRC";
   
   try {
      FileOutputStream fos = new FileOutputStream(s2);
      fos.write(output);
      fos.close();
   } catch(Exception exception) {
      System.err.println("Unable to write database due to exception: " + exception.toString());
   }
} // end-method

// --------------------------------------------------------------------------

/** Get this databases application block.
  * @return this databases application block, or null if one is not present.
  */
public DLPBlock getApplicationBlock() {
   return applicationBlock;
} // end-method

// --------------------------------------------------------------------------

/** Get this database information object.
  * @return this database information object.
  */
public DLPDatabaseInfo getDatabaseInfo() {
   return databaseInfo;
} // end-method

// --------------------------------------------------------------------------

/** Get an element from the database by index.
  * @param i index of the element requested.
  * @return the selected element of data.
  */
public Object getElement(int i) {
   if(i >= data.size()) return null;
   else return data.elementAt(i);
} // end-method

// --------------------------------------------------------------------------

/** Get the number of elements in the database.
  * @return the number of elements in the database.
  */
public int getElements() {
   return data.size();
} // end-method

// --------------------------------------------------------------------------

/** Get the sort block for this database.
  * @return the sort block for this database, or null if one isn't present.
  */
public DLPBlock getSortBlock() {
   return sortBlock;
} // end-method

// --------------------------------------------------------------------------

/** Get the unique DB ID for this database.
  * @return the unique DB ID for this database.
  */
String getUniqueDBID() {
   return getDatabaseInfo().getUniqueDBID();
} // end-method

// --------------------------------------------------------------------------

/** Import a database from an InputStream.
  * @param inputStream InputStream to read for import.
  * @exception DatabaseFormatException if the data isn't in the correct serialized database format.
  * @return DLPDatabase the database object read from the input stream.
  */
private static DLPDatabase importDB(InputStream inputStream) throws DatabaseFormatException {
   DLPDatabase db = new DLPDatabase();
   DLPDatabaseInfo dbinfo = new DLPDatabaseInfo();
   int i = 0;

   // Read the stream into a buffer
   try {
      byte buf[] = readStream(inputStream);

      // Read the database name
      byte temp[] = new byte[32];
      while (buf[i] != 0) temp[i] = buf[i++];
      dbinfo.setName(new String(temp, 0, i));

      // Read the database header
      i = 32;
      dbinfo.setDatabaseFlags(UnsignedByte.unsignedBytes2Char(buf[i++], buf[i++]));
      dbinfo.setVersion(UnsignedByte.unsignedBytes2Char(buf[i++], buf[i++])); //34

      dbinfo.setCreationTime(new DLP_Date(DLP_Date.seconds2Calendar(UnsignedByte.unsignedBytes2Long4(buf[i++], buf[i++], buf[i++], buf[i++])))); //36
      dbinfo.setModificationTime(new DLP_Date(DLP_Date.seconds2Calendar(UnsignedByte.unsignedBytes2Long4(buf[i++], buf[i++], buf[i++], buf[i++])))); //40
      dbinfo.setBackupTime(new DLP_Date(DLP_Date.seconds2Calendar(UnsignedByte.unsignedBytes2Long4(buf[i++], buf[i++], buf[i++], buf[i++])))); //44
      dbinfo.setModificationNumber(UnsignedByte.unsignedBytes2Int(buf[i++], buf[i++], buf[i++], buf[i++])); //48

      int appInfoOffset = UnsignedByte.unsignedBytes2Int(buf[i++], buf[i++], buf[i++], buf[i++]); //52
      int sortBlockOffset = UnsignedByte.unsignedBytes2Int(buf[i++], buf[i++], buf[i++], buf[i++]); //56

      dbinfo.setType(UnsignedByte.unsignedBytes2Int(buf[i++], buf[i++], buf[i++], buf[i++])); //60
      dbinfo.setCreator(UnsignedByte.unsignedBytes2Int(buf[i++], buf[i++], buf[i++], buf[i++])); //64

      int uniqueIDSeed = UnsignedByte.unsignedBytes2Int(buf[i++], buf[i++], buf[i++], buf[i++]); //68
      int nextRecordList = UnsignedByte.unsignedBytes2Int(buf[i++], buf[i++], buf[i++], buf[i++]); //72
      char numEntries = UnsignedByte.unsignedBytes2Char(buf[i++], buf[i++]); //76

      db.setDatabaseInfo(dbinfo);
      db.setIsResourceDB(db.getDatabaseInfo().checkDatabaseFlag(DLPDatabase.RESOURCE_DATABASE));

      // Read the Application Block
      //
      if (appInfoOffset != 0) {
         int nextOffset = (sortBlockOffset == 0 ? (numEntries == 0 ? buf.length : UnsignedByte.unsignedBytes2Int(buf[78], buf[79], buf[80], buf[81])) : sortBlockOffset);
         byte tempAppInfo[] = new byte[nextOffset - appInfoOffset];
         System.arraycopy(buf, appInfoOffset, tempAppInfo, 0, nextOffset - appInfoOffset);
         db.setApplicationBlock(new DLPBlock(tempAppInfo, (char)tempAppInfo.length));
      }
      // Read the Sort Block
      if (sortBlockOffset != 0) {
         int nextOffset = (numEntries == 0 ? buf.length : UnsignedByte.unsignedBytes2Int(buf[78], buf[79], buf[80], buf[81]));
         byte tempSortBlock[] = new byte[nextOffset - sortBlockOffset];
         System.arraycopy(buf, sortBlockOffset, tempSortBlock, 0, nextOffset - sortBlockOffset);
         db.setSortBlock(new DLPBlock(tempSortBlock, (char)tempSortBlock.length));
      }

      // Read the records
      // First the record header, then the record itself
      i = 78;
      for (int j = 0; j < numEntries; j++) {
         if (db.getDatabaseInfo().checkDatabaseFlag(DLPDatabase.RESOURCE_DATABASE)) {
            DLPResource resource = new DLPResource();
            resource.setResourceType(UnsignedByte.unsignedBytes2Int(buf[i++], buf[i++], buf[i++], buf[i++]));
            resource.setResourceID(UnsignedByte.unsignedBytes2Char(buf[i++], buf[i++]));
            int recordOffset = UnsignedByte.unsignedBytes2Int(buf[i++], buf[i++], buf[i++], buf[i++]);
            int nextRecOffset;
            if (j != numEntries - 1)
               nextRecOffset = UnsignedByte.unsignedBytes2Int(buf[i + 6], buf[i + 7], buf[i + 8], buf[i + 9]);
            else
               nextRecOffset = buf.length;
               
            if (nextRecOffset <= recordOffset) {
               System.err.println("[DLPDatabase] - Next record offset <= current record offset!!!");
               System.err.println("[DLPDatabase] -   nextRecOffset = "+nextRecOffset);
               System.err.println("[DLPDatabase] -   recordOffset = "+recordOffset);
               System.err.println("[DLPDatabase] -   j = "+j);
               System.err.println("[DLPDatabase] -   numEntries = "+numEntries);
            } // end-method
            byte tempResource[] = new byte[nextRecOffset - recordOffset];
            resource.setResourceSize((char) tempResource.length);
            System.arraycopy(buf, recordOffset, tempResource, 0, nextRecOffset - recordOffset);
            resource.setData(tempResource);
            db.addElement(resource);
         } else {
            DLPRecord record = new DLPRecord();
            int recordOffset = UnsignedByte.unsignedBytes2Int(buf[i++], buf[i++], buf[i++], buf[i++]);
            // Set the attributes and category, they're packed into one byte
            byte attr = buf[i++];
            record.setCategory((byte) (attr & 0x0f));
            record.setRecordID(UnsignedByte.unsignedBytes2Int((byte) 0, buf[i++], buf[i++], buf[i++]));
            int nextRecOffset;

            // Query the next offset
            if (j != numEntries - 1) {
               nextRecOffset = UnsignedByte.unsignedBytes2Int(buf[i], buf[i + 1], buf[i + 2], buf[i + 3]);
            } else {
               nextRecOffset = buf.length;
            }
            byte tempRecord[] = new byte[nextRecOffset - recordOffset];

            // Now that we know the length of the record, we can test to see if it's non-0 length.
            // If it's flagged as deleted and is non-null, flag it as ARCHIVED.
            if ((attr & DLPRecord.DELETED) == DLPRecord.DELETED && tempRecord.length != 0) attr |=DLPRecord.ARCHIVED;
            record.setAttributes((byte) (attr & 0xf0));


            record.setRecordSize((char) tempRecord.length);
            System.arraycopy(buf, recordOffset, tempRecord, 0, nextRecOffset - recordOffset);
            record.setData(tempRecord);
            db.addElement(record);
         }
      }
      return db;
   } catch (Throwable throwable) {
      throwable.printStackTrace(System.err);
      throw new DatabaseFormatException(throwable.toString());
   }
} // end-method

// --------------------------------------------------------------------------

/** Import a database from the specified file.
  * @param filename filename to import from.
  * @exception DatabaseFormatException if the file isn't a serialized database.
  * @return DLPDatabase the database object contained in the specified file.
  */
public static DLPDatabase importFromFile(String filename) throws DatabaseFormatException {
   return importFromFile(new File(filename));
} // end-method

// --------------------------------------------------------------------------

/** Import a database from the specified File object.
  * @param filename the File object to import from.
  * @exception DatabaseFormatException thrown if the file doesn't contain a parseable database.
  * @return DLPDatabase the database read from the specified File object.
  */
public static DLPDatabase importFromFile(File filename) throws DatabaseFormatException {
   FileInputStream inputStream;
   DLPDatabase db;

   // Open the file
   try {
      inputStream = new FileInputStream(filename);
   } catch (IOException ioexception) {
      throw new DatabaseFormatException(ioexception.toString());
   } // end-catch

   // Import the database
   try {
      db = importDB(inputStream);
      inputStream.close();
      return db;
   } catch (Throwable throwable) {
      try {
         inputStream.close();
      } catch (Exception ex) {
         System.out.println("[DLPDatabase] - Exception closing the stream.  Ignoring...");
      }
      System.err.println("[DLPDatabase] -   Filename: "+filename);
      throw new DatabaseFormatException(throwable.toString());
   }
} // end-method

// --------------------------------------------------------------------------

/** Import a database from the specified URL.
  * @param url the URL object to read from.
  * @exception DatabaseFormatException thrown if the received data is not a parseable database.
  * @return DLPDatabase the database read and parsed from the requested URL.
  */
public static DLPDatabase importFromURL(URL url) throws DatabaseFormatException {
   DLPDatabase db;
   DataInputStream inputStream;

   // Open the stream
   try {
      inputStream = new DataInputStream(url.openStream());
   } catch (IOException ioe) {
      throw new DatabaseFormatException(ioe.toString());
   }
   // Import the database
   try {
      db = importDB(inputStream);
      inputStream.close();
      return db;
   } catch (Throwable throwable) {
      try {
         inputStream.close();
      } catch (Exception ex) {
      }
      throw new DatabaseFormatException(throwable.toString());
   }
} // end-method

// --------------------------------------------------------------------------

/** Return boolean denoting whether this is a Resource DB or not.
  * Database on PalmOS-bassed systems are one of two types -- either they are 
  * application databases, comprising an executable program or library made up of resources,
  * or they are information databases, containing data elements.
  * @return <b>true</b> if this is a resource database, <b>false</b> otherwise.
  */
public boolean isResourceDB() {
   return isResourceDB;
} // end-method

// --------------------------------------------------------------------------

/** Read a stream input and return a byte[] buffer for parsing.
  * @param stream the stream to be read.
  * @exception java.lang.Exception thrown if any exceptions are encountered.
  * @return byte[] the received buffer for parsing.
  */
private static byte[] readStream(InputStream stream) throws Exception {
   byte buffer[] = null;

   // if the stream is a file
   if (stream instanceof FileInputStream) {
      try {
         buffer = new byte[ ((FileInputStream) stream).available()];
         ((FileInputStream) stream).read(buffer);
      } catch (IOException ioe) {
         throw ioe;
      }
   } else {
      int len = 0x10000; // 64K
      buffer = new byte[len]; // 64K
      int n = 0;
      int count = 0;
      do {
         while (n < len) {
            count = stream.read(buffer, n, len - n);
            if (count < 0)
               break;
            n += count;
         }
         if (count < 0)
            break;
         // increase the buffer
         len = len + 0x10000;
         byte temp[] = new byte[len];
         System.arraycopy(buffer, 0, temp, 0, buffer.length);
         buffer = temp;
      } while (count > 0);

      // copy into the valid size
      byte temp[] = new byte[n];
      System.arraycopy(buffer, 0, temp, 0, n);
      buffer = temp;
   }
   return buffer;
} // end-method

// --------------------------------------------------------------------------

/** Set the ApplicationBlock for this database.
  * @param dlpblock the ApplicationBlock for this database.
  */
public void setApplicationBlock(DLPBlock dlpblock) {
   applicationBlock = dlpblock;
} // end-method

// --------------------------------------------------------------------------

/** Set the Database Info object for this database.
  * @param dlpdatabaseinfo the Database Info object for this database.
  */
protected void setDatabaseInfo(DLPDatabaseInfo dlpdatabaseinfo) {
   databaseInfo = dlpdatabaseinfo;
} // end-method

// --------------------------------------------------------------------------

/** Set the flag denoting whether this is a Resource DB or not.
  * @param flag <b>true</b> if this is a resource database, <b>false</b> otherwise.
  */
protected void setIsResourceDB(boolean flag) {
   isResourceDB = flag;
} // end-method

// --------------------------------------------------------------------------

/** Set the Sort Block for this database.
  * @param dlpblock the Sort Block for this database.
  */
public void setSortBlock(DLPBlock dlpblock) {
   sortBlock = dlpblock;
} // end-method

// --------------------------------------------------------------------------

/** Displays the contents of this DLPDatabase object as human-readable Strings.
  * Note that the results of this method may differ depending on the source of the data it contains.
  * A DLPDatabase object built in Java code using DLPBlock, DLPRecord, and/or DLPResource sub-classes
  * will display the results of those classes toString() methods.  Data read from the handheld 
  * or a PRC/PDB file in and of these generic types won't be up-casted to their parser types,
  * and thus will be displayed in human-readable hex dump format.
  * @return a human-readable String of the data contained within this DLPDatabase.
  */
public String toString() {
   StringBuffer sb = new StringBuffer();
   sb.append("==========================================================================\n");
   sb.append("DLPDatabase dump\n");

   if (applicationBlock!=null) {
      sb.append("==========================================================================\n");
      sb.append("Application Block:\n");
      sb.append("--------------------------------------------------------------------------\n");
      sb.append(applicationBlock.toString()).append('\n');
   } // end-if

   if (sortBlock!=null) {
      sb.append("==========================================================================\n");
      sb.append("Sort Block:\n");
      sb.append("--------------------------------------------------------------------------\n");
      sb.append(sortBlock.toString()).append('\n');
   } // end-if
   
   for(int i=0;i<data.size();i++) {
      sb.append("==========================================================================\n");
      sb.append(data.elementAt(i).getClass().getName()).append('\n');
      sb.append("--------------------------------------------------------------------------\n");
      sb.append(data.elementAt(i).toString()).append('\n');
   } // end-for
   
   sb.append("==========================================================================\n");
   
   return sb.toString();
   
} // end-method

// ==========================================================================

} // end-class

