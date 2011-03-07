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
// $Id: DLPRecord.java,v 3.6 2004/12/04 02:15:28 yaztromo Exp $
// --------------------------------------------------------------------------

package org.jSyncManager.API.Protocol.Util;

import org.jSyncManager.API.Protocol.DLPFunctionCallException;
import org.jSyncManager.API.Tools.UnsignedByte;
import java.io.Serializable;

// ==========================================================================

/** A class to hold a handheld record of information.
  * This class holds a single record of data.
  * @author Brad BARCLAY &lt;bbarclay@jsyncmanager.org&gt;
  * <br>Last modified by: $Author: yaztromo $ on $Date: 2004/12/04 02:15:28 $.
  * @version $Revision: 3.6 $
  */
public class DLPRecord implements Serializable {
   /** A byte array to hold the input data array.                       */
   protected byte inputData[];
    
   /** A field to store this records unique ID number.                  */
   protected int  recordID = (byte)0;

   /** A field to store this records index within its database.         */
   protected char index = (byte)0;

   /** A field to store the size of this record.                        */
   protected char recordSize = (byte)0;

   /** A field to store this records attributes bitmap.                 */
   protected byte attributes = (byte)0;

   /** A field to store this records category ID.                       */
   protected byte category = (byte)0;

   /** A field to store this records data array.                        */
   protected byte data[];

   /** A flag to signify that all records in the specified database should be 
     * deleted during a record delete operation.
     */
   public static final byte DELETE_ALL_RECORDS = -128;

   /** A flag to signify that all records in the specified category of the 
     * specified  database should be deleted during a record delete operation.
     */
   public static final byte DELETE_ALL_IN_CATEGORY = 64;

   /** A flag to denote that the database is sorted.                    */
   public static final byte SORT_DATABASE = -128;

   /** A flag to denote that this record has been deleted.              */
   public static final byte DELETED = -128;

   /** A flag to denote that this record is dirty.
     * Dirty records are those that have been added, modified, or deleted
     * since the last synchronization.  To test to see if it's been deleted,
     * check the deleted attribute.
     */
   public static final byte DIRTY = 64;

   /** A flag to denote that this record is busy.                       */
   public static final byte BUSY = 32;

   /** A flag to denote that this record is secret.                     */
   public static final byte SECRET = 16;

   /** A flag to denote that this record is flagged for archival.       */
   public static final byte ARCHIVED = 8;

   /** A flag to denote that this record includes data.
     * This flag is included for compatibility with old handhelds.
     * Typically you won't need to set this if your record includes data.
     */
   public static final byte DATA_INCLUDED = (byte)0x80;

   private static final long serialVersionUID = -4282181958516884584L;

// ==========================================================================

/** Empty constructor.  This is useful for creating a new record entry.
  */
public DLPRecord() {
} // end-constructor

// --------------------------------------------------------------------------

/** Create a new DLPRecord object from another DLPRecord type.
  * @param dlp the DLP record to make a copy object of.
  */
public DLPRecord(DLPRecord dlp) {
    setRecordID(dlp.getRecordID());
    setIndex(dlp.getIndex());
    setRecordSize(dlp.getRecordSize());
    setAttributes(dlp.getAttributes());
    setCategory(dlp.getCategory());
    
    try {
      setData(dlp.getData());
   } catch (ParseException e) {
      // Can't actually happen in this class, so we'll ignore it.
   } // end-catch
} // end-constructor

// --------------------------------------------------------------------------

/** Create a new DLPRecord object from an array of bytes.
  * @param inputData the array of bytes from the handheld representing a record to be parsed.
  */
public DLPRecord(byte inputData[]) throws DLPFunctionCallException {
   int i = 0;

   try {
      setRecordID(UnsignedByte.unsignedBytes2Int(inputData[i++], inputData[i++], inputData[i++], inputData[i++]));
      setIndex(UnsignedByte.unsignedBytes2Char(inputData[i++], inputData[i++]));
      setRecordSize(UnsignedByte.unsignedBytes2Char(inputData[i++], inputData[i++]));
      setAttributes(inputData[i++]);
      setCategory(inputData[i++]);
      data = new byte[inputData.length - 10];
      System.arraycopy(inputData, i, data, 0, data.length);
   } catch(Throwable throwable) {
      throw new DLPFunctionCallException(throwable.toString(), DLPFunctionCallException.NO_DLP_ERROR);
   } /* endcatch */
} // end-constructor

// ==========================================================================

/** Checks wether or not the specified attribute is present in this record.
  * @return <b>true</b> if the specified flag is present, <b>false</b> otherwise.
  * @param value the attribute to be tested.
  * @see DLPRecord#DELETED
  * @see DLPRecord#DIRTY
  * @see DLPRecord#BUSY
  * @see DLPRecord#SECRET
  * @see DLPRecord#ARCHIVED
  */
public boolean checkAttribute(byte value) {
   return (attributes&value)!=0;
} // end-method

// --------------------------------------------------------------------------

/** Gererates a data array from a set of object fields.
  * Subclasses that parse record data should implement this method so they can
  * reconstruct the data byte array from their object fields so it can be written back
  * to the handheld.  In this class, this method has a null implementation.
  */
protected void generateData() {
} // end-method

// --------------------------------------------------------------------------

/** Retreives the attributes for this record.
  * @return the attributes for this record.
  */
public byte getAttributes() {
   return attributes;
} // end-method

// --------------------------------------------------------------------------

/** Retreives the category ID for this record.
  * @return the category ID for this record.
  */
public byte getCategory() {
   return category;
} // end-method

// --------------------------------------------------------------------------

/** Retreives the data for this record.
  * @return the data for this record, as an array of bytes.
  */
public final byte[] getData() {
   generateData();
   return data;
} // end-method

// --------------------------------------------------------------------------

/** Retreives the index for this record.
  * @return the index for this record.
  */
public char getIndex() {
   return index;
} // end-method

// --------------------------------------------------------------------------

/** Retreives the record ID for this record.
  * @return the record ID for this record.
  */
public int getRecordID() {
   return recordID;
} // end-method

// --------------------------------------------------------------------------

/** Retreives the reported size for this record.
  * Due to the need for byte alignment, the value reported here and the number of bytes
  * in the data array may differ.
  * @return the reported size for this record.
  */
public char getRecordSize() {
   return recordSize;
} // end-method

// --------------------------------------------------------------------------

/** Parses the record data array into a set of fields.
  * This method should be implemented by subclasses.  For this class, it uses
  * a null implementation, as we don't have anything to parse.
  * @throws ParseException thrown if we are unable to parse the data correctly.
  */
protected void parseFields() throws ParseException {
} // end-method

// --------------------------------------------------------------------------

/** Set the attributes to the specified value.
  * @param flags the attribute bitmap to use.
  */
public void setAttributes(byte flags) {
   attributes = flags;
} // end-method

// --------------------------------------------------------------------------

/** Set the category ID for this record.
  * @param categoryID the category ID for this record.
  */
public void setCategory(byte categoryID) {
   category = categoryID;
} // end-method

// --------------------------------------------------------------------------

/** Set the data byte array for this record.
  * @param inputData the data byte array for this record.
  * @throws ParseException if the data cannot be parsed correctly.
  */
public void setData(byte inputData[]) throws ParseException {
   data = new byte[inputData.length];
   System.arraycopy(inputData, 0, data, 0, inputData.length);
   parseFields();
} // end-method

// --------------------------------------------------------------------------

/** Set the index for this record.
  * @param i the index for this record.
  */
public void setIndex(char i) {
   index = i;
} // end-method

// --------------------------------------------------------------------------

/** Set the unique record ID for this record.
  * @param id the unique record ID for this record.
  */
public void setRecordID(int id) {
   recordID = id;
} // end-method

// --------------------------------------------------------------------------

/** Set the record size for this record.
  * @param size the record size for this record.
  */
public void setRecordSize(char size) {
   recordSize = size;
} // end-method

// --------------------------------------------------------------------------

/** Converts this record to a human-readable hexdump String.
  * @return this record as a human-readable hexdump String.
  */
public String toString() {
   StringBuffer sb=new StringBuffer();

   sb.append("Record ID:      ");sb.append(recordID);sb.append('\n');
   sb.append("Record Index:   ");sb.append((int)index);sb.append('\n');
   sb.append("Record Size:    ");sb.append((int)recordSize);sb.append('\n');
   sb.append("Attributes:     ");

   if (checkAttribute(DELETED)) sb.append("DELETED ");
   if (checkAttribute(DIRTY)) sb.append("DIRTY ");
   if (checkAttribute(BUSY)) sb.append("BUSY ");
   if (checkAttribute(SECRET)) sb.append("SECRET ");
   if (checkAttribute(ARCHIVED)) sb.append("ARCHIVED ");
   sb.append('\n');

   sb.append("Category:       ");sb.append(category);sb.append("\n\n");
   generateData();
   sb.append(UnsignedByte.toString(data));

   return sb.toString();
} // end-method

// --------------------------------------------------------------------------

/** Adds an attribute to the attributes bitmap.
  * @param flag the flag to add.
  */
public void addAttribute(byte flag) {
   attributes|=flag;
} // end-method

// --------------------------------------------------------------------------

/** Clears the specified attribute.
  * @param flag the attribute flag to clear.
  */
public void clearAttribute(byte flag) {
   attributes &= ~flag;
} // end-method

// ==========================================================================

} // end-class
