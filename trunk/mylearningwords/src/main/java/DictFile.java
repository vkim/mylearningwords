// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DictFile.java

package com.learnwords.android;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import java.io.*;

public class DictFile
{

    public DictFile()
    {
        isDemo = false;
        isReg = false;
        IsSoundPack = false;
        IsSoundPack2 = false;
        m_isFileOpen = false;
        m_nRecordCount = 0;
        m_nCurrentRecord = 0;
        isDemo = false;
    }

    private void GetHole(RandomAccessFile randomaccessfile, long nextRecOffset, int i)
    {
        if(i != 0) {
        	return;
        }
        long l3;
        
        char c;
        byte abyte0[];
        byte abyte1[];
        int k;
        long l6;
        long l8;
        long l1 = randomaccessfile.length();
        long l2 = i;
        l3 = l1 + l2;
        RandomAccessFile randomaccessfile1 = randomaccessfile;
        long l4 = l3;
        randomaccessfile1.setLength(l4);
        c = '\u0400';
        abyte0 = new byte[c];
        abyte1 = new byte[c];
        randomaccessfile.seek(nextRecOffset);
        RandomAccessFile randomaccessfile2 = randomaccessfile;
        byte abyte2[] = abyte1;
        int j = 0;
        char c1 = c;
        k = randomaccessfile2.read(abyte2, j, c1);
        long l5 = i;
        l6 = nextRecOffset + l5;
        long l7 = k;
        l8 = nextRecOffset + l7;
_L3:
        int i2;
        if(l6 >= l3)
            continue; /* Loop/switch isn't completed */
        byte abyte3[] = abyte1;
        int i1 = 0;
        byte abyte4[] = abyte0;
        int j1 = 0;
        int k1 = k;
        System.arraycopy(abyte3, i1, abyte4, j1, k1);
        i2 = k;
        RandomAccessFile randomaccessfile3 = randomaccessfile;
        long l9 = l8;
        randomaccessfile3.seek(l9);
        RandomAccessFile randomaccessfile4 = randomaccessfile;
        byte abyte5[] = abyte1;
        int j2 = 0;
        char c2 = c;
        k = randomaccessfile4.read(abyte5, j2, c2);
        long l10 = k;
        l8 += l10;
        RandomAccessFile randomaccessfile5 = randomaccessfile;
        long l11 = l6;
        randomaccessfile5.seek(l11);
        RandomAccessFile randomaccessfile6 = randomaccessfile;
        byte abyte6[] = abyte0;
        int k2 = 0;
        int i3 = i2;
        randomaccessfile6.write(abyte6, k2, i3);
        long l12 = i2;
        l6 += l12;
          goto _L3
        IOException ioexception;
        ioexception;
        if(true) goto _L1; else goto _L4
_L4:
    }

    private void MoveLeft(RandomAccessFile randomaccessfile, long l, int i)
    {
        if(i != 0)
            try
            {
                long l1 = randomaccessfile.length();
                char c = '\u0400';
                byte abyte0[] = new byte[c];
                long l2 = l;
                while(l2 < l1) 
                {
                    randomaccessfile.seek(l2);
                    int j = randomaccessfile.read(abyte0, 0, c);
                    long l3 = i;
                    long l4 = l2 - l3;
                    long l5 = j;
                    l2 += l5;
                    randomaccessfile.seek(l4);
                    randomaccessfile.write(abyte0, 0, j);
                }
            }
            catch(IOException ioexception) { }
    }

    private void ReadDatabaseInfo()
        throws IOException
    {
        byte abyte0[] = new byte[100];
        int i = m_file.read(abyte0, 0, 64);
        int j = m_file.read(abyte0, 0, 4);
        int k = abyte0[3] & 0xff;
        int l = abyte0[2] << 8 & 0xff00;
        int i1 = k | l;
        int j1 = abyte0[1] << 16 & 0xff0000;
        int k1 = i1 | j1;
        int l1 = abyte0[0] << 24 & 0xff000000;
        int i2;
        int j2;
        int k2;
        int l2;
        int i3;
        long l3;
        int j3;
        int k3;
        int i4;
        int j4;
        int k4;
        int l4;
        int i5;
        byte byte0;
        byte byte1;
        int j5;
        int k5;
        int l5;
        byte byte2;
        byte byte3;
        byte byte4;
        byte byte5;
        if((k1 | l1) == 0x4d44574c)
            isDemo = true;
        else
            isDemo = false;
        i2 = m_file.read(abyte0, 0, 8);
        j2 = m_file.read(abyte0, 0, 2);
        k2 = abyte0[1] & 0xff;
        l2 = abyte0[0] << 8 & 0xff00;
        i3 = k2 | l2;
        m_nRecordCount = i3;
        l3 = m_nRecordCount * 8 + 78 + 2;
        m_file.seek(l3);
        j3 = m_file.read(abyte0, 0, 12);
        k3 = abyte0[3] & 0xff;
        i4 = abyte0[2] << 8 & 0xff00;
        j4 = k3 | i4;
        m_nCurrentRecord = j4;
        k4 = abyte0[1] & 0xff;
        l4 = abyte0[0] << 8 & 0xff00;
        i5 = k4 | l4;
        m_nIndexBlock = i5;
        byte0 = abyte0[6];
        m_nTypeDict = byte0;
        byte1 = abyte0[7];
        m_nCompleteWords = byte1;
        j5 = abyte0[5] & 0xff;
        k5 = abyte0[4] << 8 & 0xff00;
        l5 = j5 | k5;
        m_nAllCompleteWords = l5;
        byte2 = abyte0[8];
        m_nShifr = byte2;
        byte3 = abyte0[9];
        mCodeLang1 = byte3;
        byte4 = abyte0[10];
        mCodeLang2 = byte4;
        byte5 = abyte0[11];
        mCodeLang3 = byte5;
        if((m_nTypeDict & 2) != 2)
            break MISSING_BLOCK_LABEL_409;
        isUnicode = true;
_L1:
        String s;
        int i6;
        IOException ioexception;
        return;
        boolean flag = false;
        try
        {
            isUnicode = flag;
        }
        // Misplaced declaration of an exception variable
        catch(IOException ioexception)
        {
            s = ioexception.getMessage();
            i6 = Log.i("error= ", s);
        }
          goto _L1
    }

    private void ResetRec()
    {
        m_wCat = 0;
        m_wBall = 0;
    }

    private long SWAP_LONG(long l)
    {
        long l1 = (255L & l) << 24;
        long l2 = (65280L & l) << 8;
        long l3 = l1 | l2;
        long l4 = (0xff0000L & l) >> 8;
        long l5 = l3 | l4;
        long l6 = (0xffffffffff000000L & l) >> 24;
        return l5 | l6;
    }

    private long SeekPos(int i)
        throws IOException
    {
        long l;
        long l1;
        l = 65535L;
        l1 = i * 8 + 78;
        m_file.seek(l1);
        byte abyte0[] = new byte[8];
        if(m_file.read(abyte0, 0, 8) == 8)
        {
            int j = abyte0[3] & 0xff;
            int k = abyte0[2] << 8 & 0xff00;
            int i1 = j | k;
            int j1 = abyte0[1] << 16 & 0xff0000;
            int k1 = i1 | j1;
            int i2 = abyte0[0] << 24 & 0xff000000;
            l = k1 | i2;
            m_file.seek(l);
        }
        long l2 = l;
_L2:
        return l2;
        IOException ioexception;
        ioexception;
        l2 = 65535L;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private void doConvIPA()
    {
        String s = m_transc.replace('i', '\u0131');
        m_transc = s;
        String s1 = m_transc.replace('q', '\u0259');
        m_transc = s1;
        String s2 = m_transc.replace('x', '\346');
        m_transc = s2;
        String s3 = m_transc.replace('Q', '\u0251');
        m_transc = s3;
        String s4 = m_transc.replace("W", "\u0259:");
        m_transc = s4;
        String s5 = m_transc.replace('E', '\u025C');
        m_transc = s5;
        String s6 = m_transc.replace("R", "\u0251:");
        m_transc = s6;
        String s7 = m_transc.replace('T', '\u03B8');
        m_transc = s7;
        String s8 = m_transc.replace('Y', '\u025B');
        m_transc = s8;
        String s9 = m_transc.replace('U', '\u0251');
        m_transc = s9;
        String s10 = m_transc.replace('I', '\u0131');
        m_transc = s10;
        String s11 = m_transc.replace('O', 'o');
        m_transc = s11;
        String s12 = m_transc.replace('P', '\u0254');
        m_transc = s12;
        String s13 = m_transc.replace('A', '\u028C');
        m_transc = s13;
        String s14 = m_transc.replace('S', '\u0283');
        m_transc = s14;
        String s15 = m_transc.replace('D', '\360');
        m_transc = s15;
        String s16 = m_transc.replace('F', '\u025B');
        m_transc = s16;
        String s17 = m_transc.replace('G', '\u02A4');
        m_transc = s17;
        String s18 = m_transc.replace("H", "u:");
        m_transc = s18;
        String s19 = m_transc.replace("J", "\u0131:");
        m_transc = s19;
        String s20 = m_transc.replace('K', '\u0254');
        m_transc = s20;
        String s21 = m_transc.replace("L", "\u0254:");
        m_transc = s21;
        String s22 = m_transc.replace('Z', '\u0292');
        m_transc = s22;
        String s23 = m_transc.replace('X', '\u0153');
        m_transc = s23;
        String s24 = m_transc.replace('C', '\u02A7');
        m_transc = s24;
        String s25 = m_transc.replace('V', '\u03C5');
        m_transc = s25;
        String s26 = m_transc.replace('B', '\u0265');
        m_transc = s26;
        String s27 = m_transc.replace('N', '\u014B');
        m_transc = s27;
        String s28 = m_transc.replace('M', '\u0272');
        m_transc = s28;
    }

    private void fromIPAConv()
    {
        String s = m_transc.replace("\u0259:", "W");
        m_transc = s;
        String s1 = m_transc.replace('\u0259', 'q');
        m_transc = s1;
        String s2 = m_transc.replace('\346', 'x');
        m_transc = s2;
        String s3 = m_transc.replace("\u0251:", "R");
        m_transc = s3;
        String s4 = m_transc.replace('\u0251', 'Q');
        m_transc = s4;
        String s5 = m_transc.replace('\u025C', 'E');
        m_transc = s5;
        String s6 = m_transc.replace('\u03B8', 'T');
        m_transc = s6;
        String s7 = m_transc.replace('\u025B', 'Y');
        m_transc = s7;
        String s8 = m_transc.replace('\u0251', 'U');
        m_transc = s8;
        String s9 = m_transc.replace('o', 'O');
        m_transc = s9;
        String s10 = m_transc.replace('\u0254', 'P');
        m_transc = s10;
        String s11 = m_transc.replace('\u028C', 'A');
        m_transc = s11;
        String s12 = m_transc.replace('\u0283', 'S');
        m_transc = s12;
        String s13 = m_transc.replace('\360', 'D');
        m_transc = s13;
        String s14 = m_transc.replace('\u025B', 'F');
        m_transc = s14;
        String s15 = m_transc.replace('\u02A4', 'G');
        m_transc = s15;
        String s16 = m_transc.replace("u:", "H");
        m_transc = s16;
        String s17 = m_transc.replace("\u0131:", "J");
        m_transc = s17;
        String s18 = m_transc.replace('\u0131', 'I');
        m_transc = s18;
        String s19 = m_transc.replace('\u0131', 'i');
        m_transc = s19;
        String s20 = m_transc.replace("\u0254:", "L");
        m_transc = s20;
        String s21 = m_transc.replace('\u0254', 'K');
        m_transc = s21;
        String s22 = m_transc.replace('\u0292', 'Z');
        m_transc = s22;
        String s23 = m_transc.replace('\u0153', 'X');
        m_transc = s23;
        String s24 = m_transc.replace('\u02A7', 'C');
        m_transc = s24;
        String s25 = m_transc.replace('\u03C5', 'V');
        m_transc = s25;
        String s26 = m_transc.replace('\u0265', 'B');
        m_transc = s26;
        String s27 = m_transc.replace('\u014B', 'N');
        m_transc = s27;
        String s28 = m_transc.replace('\u0272', 'M');
        m_transc = s28;
    }

    public void CloseDict()
        throws IOException
    {
        if(!m_isFileOpen)
            break MISSING_BLOCK_LABEL_20;
        m_isFileOpen = false;
        m_file.close();
_L2:
        return;
        String s;
        int i;
        IOException ioexception;
        ioexception;
        s = ioexception.getMessage();
        i = Log.i("error= ", s);
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void CreatePDBFile(String s, byte byte0)
    {
        File file = new File(s);
        if(file.exists()) goto _L2; else goto _L1
_L1:
        RandomAccessFile randomaccessfile;
        byte abyte0[];
        int i;
        boolean flag = file.createNewFile();
        randomaccessfile = new RandomAccessFile(s, "rw");
        abyte0 = new byte[78];
        i = 0;
_L9:
        int j = abyte0.length;
        if(i < j) goto _L4; else goto _L3
_L3:
        byte abyte1[];
        int i1;
        String s1 = s;
        int k = s1.lastIndexOf('/');
        if(k >= 0)
        {
            int l = k + 1;
            s1 = s1.substring(l);
        }
        k = s1.indexOf('.');
        if(k >= 0)
            s1 = s1.substring(0, k);
        abyte1 = s1.getBytes();
        i1 = abyte1.length;
        if(i1 > 31)
            i1 = 31;
        i = 0;
_L10:
        if(i < i1) goto _L6; else goto _L5
_L5:
        abyte0[33] = 8;
        abyte0[36] = 77;
        abyte0[37] = 27;
        abyte0[38] = 39;
        abyte0[39] = 65411;
        abyte0[40] = 77;
        abyte0[41] = 27;
        abyte0[42] = 39;
        abyte0[43] = 65411;
        abyte0[44] = 77;
        abyte0[45] = 27;
        abyte0[46] = 39;
        abyte0[47] = 65411;
        abyte0[55] = 80;
        abyte0[60] = 68;
        abyte0[61] = 65;
        abyte0[62] = 84;
        abyte0[63] = 65;
        abyte0[64] = 76;
        abyte0[65] = 87;
        abyte0[66] = 48;
        abyte0[67] = 49;
        randomaccessfile.write(abyte0, 0, 78);
        i = 0;
_L11:
        int j1 = abyte0.length;
        if(i < j1) goto _L8; else goto _L7
_L7:
        abyte0[0] = 65408;
        abyte0[8] = 2;
        abyte0[10] = byte0;
        abyte0[12] = 3;
        abyte0[13] = 2;
        randomaccessfile.write(abyte0, 0, 14);
        randomaccessfile.close();
_L2:
        return;
_L4:
        abyte0[i] = 0;
        i++;
          goto _L9
_L6:
        byte byte1 = abyte1[i];
        abyte0[i] = byte1;
        i++;
          goto _L10
_L8:
        abyte0[i] = 0;
        i++;
          goto _L11
        String s2;
        int k1;
        IOException ioexception;
        ioexception;
        s2 = ioexception.getMessage();
        k1 = Log.i("error= ", s2);
          goto _L2
    }

    public void DeleteRecord(int i)
    {
        int k;
        int l;
        int j = m_nRecordCount;
        k = i;
        l = j;
        if(k < l) goto _L2; else goto _L1
_L1:
        return;
_L2:
        int i1 = i + 1;
        int k1;
        int l1;
        int j1 = m_nRecordCount;
        k1 = i1;
        l1 = j1;
        if(k1 < l1) goto _L4; else goto _L3
_L3:
        long l2 = m_file.length();
_L7:
        long l3;
        long l4;
        long l5;
        byte abyte0[];
        int i2;
        long l7;
        int j2;
        l3 = SeekPos(i);
        l4 = l2 - l3;
        l5 = m_file.length();
        abyte0 = new byte[8];
        RandomAccessFile randomaccessfile = m_file;
        long l6 = 78L;
        randomaccessfile.seek(l6);
        i2 = 8;
        l7 = 78L;
        j2 = 0;
_L8:
        int k2;
        int i3;
        k2 = j2;
        i3 = i;
        if(k2 < i3) goto _L6; else goto _L5
_L5:
        long l8 = (i + 1) * 8 + 78;
        RandomAccessFile randomaccessfile1 = m_file;
        long l9 = l8;
        randomaccessfile1.seek(l9);
        long l10 = i2;
        l7 = l8 - l10;
        j2 = i + 1;
_L9:
        int j3 = m_nRecordCount;
        int k3 = j2;
        int i4 = j3;
        if(k3 < i4)
            break MISSING_BLOCK_LABEL_721;
        long l11 = m_nRecordCount * 8 + 78;
        long l12 = l3 - l11;
        RandomAccessFile randomaccessfile2 = m_file;
        DictFile dictfile = this;
        RandomAccessFile randomaccessfile3 = randomaccessfile2;
        long l13 = l11;
        int j4 = i2;
        dictfile.MoveLeft(randomaccessfile3, l13, j4);
        RandomAccessFile randomaccessfile4 = m_file;
        long l14 = i2;
        long l15 = l2 - l14;
        int k4 = (int)l4;
        DictFile dictfile1 = this;
        RandomAccessFile randomaccessfile5 = randomaccessfile4;
        long l16 = l15;
        int i5 = k4;
        dictfile1.MoveLeft(randomaccessfile5, l16, i5);
        m_file.seek(76L);
        int j5 = m_nRecordCount - 1;
        m_nRecordCount = j5;
        RandomAccessFile randomaccessfile6 = m_file;
        byte byte0 = (byte)(m_nRecordCount >> 8 & 0xff);
        randomaccessfile6.writeByte(byte0);
        RandomAccessFile randomaccessfile7 = m_file;
        byte byte1 = (byte)(m_nRecordCount & 0xff);
        randomaccessfile7.writeByte(byte1);
        m_file.seek(74L);
        RandomAccessFile randomaccessfile8 = m_file;
        byte byte2 = (byte)(m_nRecordCount >> 8 & 0xff);
        randomaccessfile8.writeByte(byte2);
        RandomAccessFile randomaccessfile9 = m_file;
        byte byte3 = (byte)(m_nRecordCount & 0xff);
        randomaccessfile9.writeByte(byte3);
        RandomAccessFile randomaccessfile10 = m_file;
        long l17 = (long)i2 + l4;
        long l18 = l5 - l17;
        randomaccessfile10.setLength(l18);
          goto _L1
        IOException ioexception;
        ioexception;
          goto _L1
_L4:
        int k5 = i + 1;
        DictFile dictfile2 = this;
        int i6 = k5;
        l2 = dictfile2.SeekPos(i6);
          goto _L7
_L6:
        RandomAccessFile randomaccessfile11 = m_file;
        byte abyte1[] = abyte0;
        int j6 = 0;
        int k6 = i2;
        int i7 = randomaccessfile11.read(abyte1, j6, k6);
        int j7 = abyte0[3] & 0xff;
        int k7 = abyte0[2] << 8 & 0xff00;
        int i8 = j7 | k7;
        int j8 = abyte0[1] << 16 & 0xff0000;
        int k8 = i8 | j8;
        int i9 = abyte0[0] << 24 & 0xff000000;
        int j9 = (k8 | i9) - i2;
        byte byte4 = (byte)((0xff000000 & j9) >> 24);
        abyte0[0] = byte4;
        byte byte5 = (byte)((0xff0000 & j9) >> 16);
        abyte0[1] = byte5;
        byte byte6 = (byte)((0xff00 & j9) >> 8);
        abyte0[2] = byte6;
        byte byte7 = (byte)(j9 & 0xff);
        abyte0[3] = byte7;
        RandomAccessFile randomaccessfile12 = m_file;
        long l19 = l7;
        randomaccessfile12.seek(l19);
        RandomAccessFile randomaccessfile13 = m_file;
        byte abyte2[] = abyte0;
        int k9 = 0;
        int i10 = i2;
        randomaccessfile13.write(abyte2, k9, i10);
        long l20 = i2;
        l7 += l20;
        j2++;
          goto _L8
        RandomAccessFile randomaccessfile14 = m_file;
        byte abyte3[] = abyte0;
        int j10 = 0;
        int k10 = i2;
        int i11 = randomaccessfile14.read(abyte3, j10, k10);
        int j11 = abyte0[3] & 0xff;
        int k11 = abyte0[2] << 8 & 0xff00;
        int i12 = j11 | k11;
        int j12 = abyte0[1] << 16 & 0xff0000;
        int k12 = i12 | j12;
        int i13 = abyte0[0] << 24 & 0xff000000;
        long l21 = k12 | i13;
        long l22 = (long)i2 + l4;
        int j13 = (int)(l21 - l22);
        byte byte8 = (byte)((0xff000000 & j13) >> 24);
        abyte0[0] = byte8;
        byte byte9 = (byte)((0xff0000 & j13) >> 16);
        abyte0[1] = byte9;
        byte byte10 = (byte)((0xff00 & j13) >> 8);
        abyte0[2] = byte10;
        byte byte11 = (byte)(j13 & 0xff);
        abyte0[3] = byte11;
        RandomAccessFile randomaccessfile15 = m_file;
        long l23 = l7;
        randomaccessfile15.seek(l23);
        RandomAccessFile randomaccessfile16 = m_file;
        byte abyte4[] = abyte0;
        int k13 = 0;
        int i14 = i2;
        randomaccessfile16.write(abyte4, k13, i14);
        RandomAccessFile randomaccessfile17 = m_file;
        byte abyte5[] = abyte0;
        int j14 = 0;
        int k14 = i2;
        int i15 = randomaccessfile17.read(abyte5, j14, k14);
        long l24 = i2;
        l7 += l24;
        j2++;
          goto _L9
    }

    public void DeleteSoundData()
    {
        File file;
        byte abyte0[];
        String s = mFileName;
        int i = mFileName.length() - 3;
        String s1 = String.valueOf(s.substring(0, i));
        String s2 = (new StringBuilder(s1)).append("snd").toString();
        file = new File(s2);
        abyte0 = new byte[4];
        RandomAccessFile randomaccessfile;
        int j6;
        int k8;
        int i9;
        randomaccessfile = JVM INSTR new #80  <Class RandomAccessFile>;
        RandomAccessFile randomaccessfile1 = randomaccessfile;
        File file1 = file;
        String s3 = "rw";
        randomaccessfile1.RandomAccessFile(file1, s3);
        RandomAccessFile randomaccessfile2 = randomaccessfile;
        byte abyte1[] = abyte0;
        int j = 0;
        byte byte0 = 4;
        int k = randomaccessfile2.read(abyte1, j, byte0);
        int l = abyte0[0] & 0xff;
        int i1 = abyte0[1] << 8 & 0xff00;
        int j1 = l | i1;
        int k1 = abyte0[2] << 16 & 0xff0000;
        int l1 = j1 | k1;
        int i2 = abyte0[3] << 24 & 0xff000000;
        int j2 = l1 | i2;
        RandomAccessFile randomaccessfile3 = randomaccessfile;
        byte abyte2[] = abyte0;
        int k2 = 0;
        byte byte1 = 4;
        int l2 = randomaccessfile3.read(abyte2, k2, byte1);
        int i3 = abyte0[0] & 0xff;
        int j3 = abyte0[1] << 8 & 0xff00;
        int k3 = i3 | j3;
        int l3 = abyte0[2] << 16 & 0xff0000;
        int i4 = k3 | l3;
        int j4 = abyte0[3] << 24 & 0xff000000;
        int k4 = i4 | j4;
        long l4 = pos_word_segment;
        RandomAccessFile randomaccessfile4 = randomaccessfile;
        long l5 = l4;
        randomaccessfile4.seek(l5);
        byte byte2 = randomaccessfile.readByte();
        long l6 = pos_word_segment;
        long l7 = byte2 + 1 + 8;
        long l8 = l6 + l7;
        int i5 = byte2 + 1 + 8;
        DictFile dictfile = this;
        RandomAccessFile randomaccessfile5 = randomaccessfile;
        long l9 = l8;
        int j5 = i5;
        dictfile.MoveLeft(randomaccessfile5, l9, j5);
        long l10 = pos_snd_segment;
        long l11 = byte2 + 1 + 8;
        long l12 = l10 - l11;
        long l13 = pos_snd_wav;
        long l14 = l12 + l13;
        int k5 = pos_snd_wav;
        DictFile dictfile1 = this;
        RandomAccessFile randomaccessfile6 = randomaccessfile;
        long l15 = l14;
        int i6 = k5;
        dictfile1.MoveLeft(randomaccessfile6, l15, i6);
        RandomAccessFile randomaccessfile7 = randomaccessfile;
        long l16 = 0L;
        randomaccessfile7.seek(l16);
        j6 = k4 + -1;
        int k6 = byte2 + 1 + 8;
        int i7 = j2 - k6;
        byte byte3 = (byte)((0xff000000 & i7) >> 24);
        abyte0[3] = byte3;
        byte byte4 = (byte)((0xff0000 & i7) >> 16);
        abyte0[2] = byte4;
        byte byte5 = (byte)((0xff00 & i7) >> 8);
        abyte0[1] = byte5;
        byte byte6 = (byte)(i7 & 0xff);
        abyte0[0] = byte6;
        RandomAccessFile randomaccessfile8 = randomaccessfile;
        byte abyte3[] = abyte0;
        int j7 = 0;
        byte byte7 = 4;
        randomaccessfile8.write(abyte3, j7, byte7);
        byte byte8 = (byte)((0xff000000 & j6) >> 24);
        abyte0[3] = byte8;
        byte byte9 = (byte)((0xff0000 & j6) >> 16);
        abyte0[2] = byte9;
        byte byte10 = (byte)((0xff00 & j6) >> 8);
        abyte0[1] = byte10;
        byte byte11 = (byte)(j6 & 0xff);
        abyte0[0] = byte11;
        RandomAccessFile randomaccessfile9 = randomaccessfile;
        byte abyte4[] = abyte0;
        int k7 = 0;
        byte byte12 = 4;
        randomaccessfile9.write(abyte4, k7, byte12);
        long l17 = randomaccessfile.length();
        int i8 = byte2 + 1 + 8;
        int j8 = pos_snd_wav;
        long l18 = i8 + j8;
        long l19 = l17 - l18;
        RandomAccessFile randomaccessfile10 = randomaccessfile;
        long l20 = l19;
        randomaccessfile10.setLength(l20);
        RandomAccessFile randomaccessfile11 = randomaccessfile;
        long l21 = 16L;
        randomaccessfile11.seek(l21);
        k8 = 0;
        i9 = 0;
_L3:
        if(i9 < j6) goto _L2; else goto _L1
_L1:
        randomaccessfile.close();
_L4:
        return;
_L2:
        int k12;
        byte byte13 = randomaccessfile.readByte();
        byte abyte5[] = new byte[byte13];
        RandomAccessFile randomaccessfile12 = randomaccessfile;
        byte abyte6[] = abyte5;
        int j9 = 0;
        byte byte14 = byte13;
        int k9 = randomaccessfile12.read(abyte6, j9, byte14);
        RandomAccessFile randomaccessfile13 = randomaccessfile;
        byte abyte7[] = abyte0;
        int i10 = 0;
        byte byte15 = 4;
        int j10 = randomaccessfile13.read(abyte7, i10, byte15);
        int k10 = abyte0[0] & 0xff;
        int i11 = abyte0[1] << 8 & 0xff00;
        int j11 = k10 | i11;
        int k11 = abyte0[2] << 16 & 0xff0000;
        int i12 = j11 | k11;
        int j12 = abyte0[3] << 24 & 0xff000000;
        k12 = i12 | j12;
        byte byte16 = (byte)((0xff000000 & k8) >> 24);
        abyte0[3] = byte16;
        byte byte17 = (byte)((0xff0000 & k8) >> 16);
        abyte0[2] = byte17;
        byte byte18 = (byte)((0xff00 & k8) >> 8);
        abyte0[1] = byte18;
        byte byte19 = (byte)(k8 & 0xff);
        abyte0[0] = byte19;
        RandomAccessFile randomaccessfile14 = randomaccessfile;
        byte abyte8[] = abyte0;
        int i13 = 0;
        byte byte20 = 4;
        randomaccessfile14.write(abyte8, i13, byte20);
        k8 += k12;
        i9++;
          goto _L3
        String s4;
        int j13;
        IOException ioexception;
        ioexception;
        s4 = ioexception.getMessage();
        j13 = Log.i("error deleting from sound file", s4);
          goto _L4
    }

    public boolean Find_Sound(String s)
    {
        boolean flag = false;
        if(IsSoundPack) goto _L2; else goto _L1
_L1:
        boolean flag1 = false;
_L5:
        return flag1;
_L2:
        String s1;
        File file;
        byte abyte0[];
        s1 = PrepareForWriting(s);
        String s2 = mFileName;
        int i = mFileName.length() - 3;
        String s3 = String.valueOf(s2.substring(0, i));
        String s4 = (new StringBuilder(s3)).append("snd").toString();
        file = JVM INSTR new #246 <Class File>;
        File file1 = file;
        String s5 = s4;
        file1.File(s5);
        abyte0 = new byte[8];
        RandomAccessFile randomaccessfile;
        int j2;
        int k4;
        int j5;
        int k5;
        randomaccessfile = JVM INSTR new #80  <Class RandomAccessFile>;
        RandomAccessFile randomaccessfile1 = randomaccessfile;
        File file2 = file;
        String s6 = "r";
        randomaccessfile1.RandomAccessFile(file2, s6);
        RandomAccessFile randomaccessfile2 = randomaccessfile;
        byte abyte1[] = abyte0;
        int j = 0;
        byte byte0 = 4;
        int k = randomaccessfile2.read(abyte1, j, byte0);
        int l = abyte0[0] & 0xff;
        int i1 = abyte0[1] << 8 & 0xff00;
        int j1 = l | i1;
        int k1 = abyte0[2] << 16 & 0xff0000;
        int l1 = j1 | k1;
        int i2 = abyte0[3] << 24 & 0xff000000;
        j2 = l1 | i2;
        RandomAccessFile randomaccessfile3 = randomaccessfile;
        byte abyte2[] = abyte0;
        int k2 = 0;
        byte byte1 = 4;
        int l2 = randomaccessfile3.read(abyte2, k2, byte1);
        int i3 = abyte0[0] & 0xff;
        int j3 = abyte0[1] << 8 & 0xff00;
        int k3 = i3 | j3;
        int l3 = abyte0[2] << 16 & 0xff0000;
        int i4 = k3 | l3;
        int j4 = abyte0[3] << 24 & 0xff000000;
        k4 = i4 | j4;
        RandomAccessFile randomaccessfile4 = randomaccessfile;
        byte abyte3[] = abyte0;
        int l4 = 0;
        byte byte2 = 8;
        int i5 = randomaccessfile4.read(abyte3, l4, byte2);
        j5 = 0;
        k5 = 16;
_L6:
        if(!flag && j5 < k4) goto _L4; else goto _L3
_L3:
        randomaccessfile.close();
_L7:
        flag1 = flag;
          goto _L5
_L4:
        byte byte3;
        long l5 = k5;
        pos_word_segment = l5;
        byte3 = randomaccessfile.readByte();
        byte abyte4[] = new byte[byte3];
        RandomAccessFile randomaccessfile5 = randomaccessfile;
        byte abyte5[] = abyte4;
        int i6 = 0;
        byte byte4 = byte3;
        int j6 = randomaccessfile5.read(abyte5, i6, byte4);
        String s7 = JVM INSTR new #170 <Class String>;
        DictFile dictfile = this;
        int k6 = 0;
        String s8 = dictfile.getEncoding(k6);
        String s9 = s7;
        byte abyte6[] = abyte4;
        int l6 = 0;
        byte byte5 = byte3;
        String s10 = s8;
        s9.String(abyte6, l6, byte5, s10);
        String s11 = s7;
        String s12 = s1;
        if(s11.equals(s12))
        {
            RandomAccessFile randomaccessfile6 = randomaccessfile;
            byte abyte7[] = abyte0;
            int i7 = 0;
            byte byte6 = 4;
            int j7 = randomaccessfile6.read(abyte7, i7, byte6);
            int k7 = abyte0[0] & 0xff;
            int l7 = abyte0[1] << 8 & 0xff00;
            int i8 = k7 | l7;
            int j8 = abyte0[2] << 16 & 0xff0000;
            int k8 = i8 | j8;
            int l8 = abyte0[3] << 24 & 0xff000000;
            int i9 = k8 | l8;
            pos_snd_wav = i9;
            RandomAccessFile randomaccessfile7 = randomaccessfile;
            byte abyte8[] = abyte0;
            int j9 = 0;
            byte byte7 = 4;
            int k9 = randomaccessfile7.read(abyte8, j9, byte7);
            int l9 = abyte0[0] & 0xff;
            int i10 = abyte0[1] << 8 & 0xff00;
            int j10 = l9 | i10;
            int k10 = abyte0[2] << 16 & 0xff0000;
            int l10 = j10 | k10;
            int i11 = abyte0[3] << 24 & 0xff000000;
            int j11 = l10 | i11;
            flag = true;
            long l11 = j2 + j11;
            pos_snd_segment = l11;
        }
        if(!flag)
        {
            RandomAccessFile randomaccessfile8 = randomaccessfile;
            byte abyte9[] = abyte0;
            int k11 = 0;
            byte byte8 = 8;
            int i12 = randomaccessfile8.read(abyte9, k11, byte8);
        }
        j5++;
        int j12 = byte3 + 1 + 8;
        k5 += j12;
          goto _L6
        String s13;
        int k12;
        IOException ioexception;
        ioexception;
        s13 = ioexception.getMessage();
        k12 = Log.i("error read sound file", s13);
          goto _L7
    }

    public byte GetBall()
    {
        return m_wBall;
    }

    public int GetCurrentRecord()
    {
        return m_nCurrentRecord;
    }

    public int GetNumRecords()
    {
        return m_nRecordCount;
    }

    public boolean GetRecord(int recordId)
        throws IOException
    {
        int j;
        int k;
        int l;
        j = 0;
        k = 0;
        l = 0;
        if(m_isFileOpen) goto _L2; else goto _L1
_L1:
        int nextRecordId = 0;
_L7:
        return nextRecordId;
_L2:
        nextRecordId = recordId + 1;
        int recordLength;
        byte abyte0[];
        char ac[];
        int j3;
        int j1 = m_nRecordCount;
        int k1 = nextRecordId;
        int l1 = j1;
        long nextRecOffset;
        long recordOffset;
        RandomAccessFile randomaccessfile;
        byte abyte1[];
        int j2;
        int k2;
        int i3;
        int k3;
        int i4;
        int j4;
        int k4;
        byte byte0;
        int l4;
        byte byte1;
        byte byte2;
        int i5;
        byte byte3;
        String s;
        StringBuilder stringbuilder;
        String s1;
        String s2;
        int j5;
        long l5;
        int k5;
        if(k1 >= l1)
        {
            nextRecOffset = m_file.length();
        } else
        {
            int i6 = recordId + 1;
            DictFile dictfile = this;
            int j6 = i6;
            nextRecOffset = dictfile.SeekPos(j6);
        }
        recordOffset = SeekPos(recordId);
        recordLength = (int)(nextRecOffset - recordOffset);
        abyte0 = new byte[recordLength];
        randomaccessfile = m_file;
        abyte1 = abyte0;
        j2 = 0;
        k2 = recordLength;
        i3 = randomaccessfile.read(abyte1, j2, k2);
        if(!isUnicode) goto _L4; else goto _L3
_L3:
        ac = new char[recordLength];
        j3 = 0;
_L15:
        k3 = recordLength - 3;
        i4 = j3;
        j4 = k3;
        if(i4 < j4) goto _L6; else goto _L5
_L5:
        k4 = recordLength - 2;
        byte0 = abyte0[k4];
        m_wCat = byte0;
        l4 = recordLength - 1;
        byte1 = abyte0[l4];
        m_wBall = byte1;
        byte2 = m_wBall;
        m_nCurBall = byte2;
        if(!isDemo && !isReg)
        {
            i5 = m_transl.length();
            byte3 = 4;
            if(i5 <= byte3)
                break MISSING_BLOCK_LABEL_920;
            s = String.valueOf(m_transl.substring(0, 3));
            stringbuilder = new StringBuilder(s);
            s1 = strDEMO;
            s2 = stringbuilder.append(s1).toString();
            m_transl = s2;
        }
_L23:
        j5 = recordLength;
        m_nRecordLength = j5;
        l5 = recordOffset;
        m_nRecordOffset = l5;
        k5 = recordId;
        m_nCurrentRecord = k5;
        nextRecordId = 1;
          goto _L7
_L6:
        if(abyte0[j3] != 0) goto _L9; else goto _L8
_L8:
        int k6 = j3 + 1;
        if(abyte0[k6] != 0) goto _L9; else goto _L10
_L10:
        int l6 = 0;
_L16:
        if(l6 < j) goto _L12; else goto _L11
_L11:
        if(l != 0) goto _L14; else goto _L13
_L13:
        String s3 = JVM INSTR new #170 <Class String>;
        String s4 = s3;
        char ac1[] = ac;
        int i7 = 0;
        int j7 = j;
        s4.String(ac1, i7, j7);
        String s5 = s3;
        m_word = s5;
_L19:
        l++;
        k = j3 + 2;
        j = 0;
_L20:
        j3 = j3 + 1 + 1;
          goto _L15
_L12:
        int k7 = l6 * 2 + k;
        int l7 = abyte0[k7] & 0xff;
        int i8 = l6 * 2 + k + 1;
        int j8 = abyte0[i8] << 8 & 0xff00;
        char c = (char)(l7 | j8);
        ac[l6] = c;
        l6++;
          goto _L16
_L14:
        int k8;
        int l8;
        k8 = l;
        l8 = 1;
        if(k8 != l8) goto _L18; else goto _L17
_L17:
        String s6 = JVM INSTR new #170 <Class String>;
        String s7 = s6;
        char ac2[] = ac;
        int i9 = 0;
        int j9 = j;
        s7.String(ac2, i9, j9);
        String s8 = s6;
        m_transc = s8;
        byte byte4 = mCodeLang2;
        int k9 = 3;
        if(byte4 == k9)
            doConvIPA();
          goto _L19
        String s9;
        int l9;
        int l10;
        int i11;
        IOException ioexception;
        ioexception;
        s9 = ioexception.getMessage();
        l9 = Log.i("error= ", s9);
        i1 = 0;
          goto _L7
_L18:
        String s10 = JVM INSTR new #170 <Class String>;
        String s11 = s10;
        char ac3[] = ac;
        int i10 = 0;
        int j10 = j;
        s11.String(ac3, i10, j10);
        String s12 = s10;
        m_transl = s12;
          goto _L19
_L9:
        j++;
          goto _L20
_L4:
        j3 = 0;
_L22:
        int k10 = i2 - 2;
        l10 = j3;
        i11 = k10;
        if(l10 >= i11) goto _L5; else goto _L21
_L21:
        if(abyte0[j3] == 0)
        {
            int i12;
            int j12;
            if(true)
            {
                String s13 = JVM INSTR new #170 <Class String>;
                DictFile dictfile1 = this;
                int j11 = 0;
                String s14 = dictfile1.getEncoding(j11);
                String s15 = s13;
                byte abyte2[] = abyte0;
                int k11 = 0;
                int l11 = j;
                String s16 = s14;
                s15.String(abyte2, k11, l11, s16);
                String s17 = s13;
                m_word = s17;
            } else
            {
                boolean flag = false;
                boolean flag1 = true;
                if(flag == flag1)
                {
                    String s18 = JVM INSTR new #170 <Class String>;
                    DictFile dictfile2 = this;
                    int k12 = 1;
                    String s19 = dictfile2.getEncoding(k12);
                    String s20 = s18;
                    byte abyte3[] = abyte0;
                    int l12 = 0;
                    int i13 = j;
                    String s21 = s19;
                    s20.String(abyte3, l12, i13, s21);
                    String s22 = s18;
                    m_transc = s22;
                    doConvIPA();
                } else
                {
                    String s23 = JVM INSTR new #170 <Class String>;
                    DictFile dictfile3 = this;
                    byte byte5 = 2;
                    String s24 = dictfile3.getEncoding(byte5);
                    String s25 = s23;
                    byte abyte4[] = abyte0;
                    int j13 = 0;
                    int k13 = j;
                    String s26 = s24;
                    s25.String(abyte4, j13, k13, s26);
                    String s27 = s23;
                    m_transl = s27;
                }
            }
            i12 = 0 + 1;
            j12 = j3 + 1;
            j = 0;
        } else
        {
            j++;
        }
        j3++;
          goto _L22
          goto _L5
        String s28 = m_transl;
        StringBuilder stringbuilder1 = JVM INSTR new #308 <Class StringBuilder>;
        String s29 = String.valueOf(s28);
        StringBuilder stringbuilder2 = stringbuilder1;
        String s30 = s29;
        stringbuilder2.StringBuilder(s30);
        String s31 = strDEMO;
        StringBuilder stringbuilder3 = stringbuilder1;
        String s32 = s31;
        String s33 = stringbuilder3.append(s32).toString();
        m_transl = s33;
          goto _L23
    }

    public int GetRecordCount()
    {
        return m_nRecordCount;
    }

    public int GetRecordLength()
    {
        return m_nRecordLength;
    }

    public boolean IsFileOpen()
    {
        return m_isFileOpen;
    }

    public void NewRecord()
    {
        long l;
        int i;
        l = m_file.length();
        i = m_word.length();
        if(!isUnicode) goto _L2; else goto _L1
_L1:
        char ac[];
        byte abyte0[];
        int j;
        i *= 2;
        ac = m_word.toCharArray();
        abyte0 = new byte[i];
        j = 0;
_L17:
        int i1;
        int j1;
        int k = m_word.length();
        i1 = j;
        j1 = k;
        if(i1 < j1) goto _L4; else goto _L3
_L3:
        int wordLength;
        wordLength = i;
        i = m_transc.length();
        if(!isUnicode) goto _L6; else goto _L5
_L5:
        byte abyte1[];
        i *= 2;
        ac = m_transc.toCharArray();
        abyte1 = new byte[i];
        j = 0;
_L18:
        int i2;
        int j2;
        int l1 = m_transc.length();
        i2 = j;
        j2 = l1;
        if(i2 < j2) goto _L8; else goto _L7
_L7:
        int k2;
        k2 = i;
        i = m_transl.length();
        if(!isUnicode) goto _L10; else goto _L9
_L9:
        byte abyte2[];
        i *= 2;
        ac = m_transl.toCharArray();
        abyte2 = new byte[i];
        j = 0;
_L19:
        int i3;
        int j3;
        int l2 = m_transl.length();
        i3 = j;
        j3 = l2;
        if(i3 < j3) goto _L12; else goto _L11
_L11:
        int recHeaderLength;
        byte abyte3[];
        long l6;
        int k3 = i;
        int recLength;
        long mFileLength;
        RandomAccessFile randomaccessfile;
        long l5;
        int j4;
        int k4;
        int i5;
        long l7;
        long l8;
        RandomAccessFile randomaccessfile1;
        DictFile dictfile;
        RandomAccessFile randomaccessfile2;
        long firstRecHeaderOffset;
        int j5;
        RandomAccessFile randomaccessfile3;
        long l10;
        RandomAccessFile randomaccessfile4;
        long l11;
        long l12;
        long l13;
        byte byte0;
        byte byte1;
        byte byte2;
        byte byte3;
        byte byte4;
        byte byte5;
        byte byte6;
        RandomAccessFile randomaccessfile5;
        byte abyte4[];
        int k5;
        int i6;
        RandomAccessFile randomaccessfile6;
        long l14;
        RandomAccessFile randomaccessfile7;
        byte abyte5[];
        int j6;
        int k6;
        RandomAccessFile randomaccessfile8;
        byte abyte6[];
        int i7;
        int j7;
        RandomAccessFile randomaccessfile9;
        byte abyte7[];
        int k7;
        int i8;
        int j8;
        RandomAccessFile randomaccessfile10;
        byte byte7;
        RandomAccessFile randomaccessfile11;
        byte byte8;
        int k8;
        RandomAccessFile randomaccessfile12;
        byte byte9;
        RandomAccessFile randomaccessfile13;
        byte byte10;
        RandomAccessFile randomaccessfile14;
        byte byte11;
        RandomAccessFile randomaccessfile15;
        byte byte12;
        RandomAccessFile randomaccessfile16;
        byte abyte8[];
        int i9;
        byte byte13;
        int j9;
        int k9;
        int i10;
        int j10;
        int k10;
        int i11;
        int j11;
        long l15;
        byte byte14;
        byte byte15;
        byte byte16;
        byte byte17;
        RandomAccessFile randomaccessfile17;
        byte abyte9[];
        int k11;
        byte byte18;
        int i12;
        byte byte19;
        int j12;
        byte byte20;
        String s;
        DictFile dictfile1;
        int k12;
        String s1;
        int i13;
        byte byte21;
        int j13;
        byte byte22;
        byte byte23;
        int k13;
        String s2;
        DictFile dictfile2;
        int i14;
        String s3;
        int j14;
        byte byte24;
        int k14;
        byte byte25;
        String s4;
        DictFile dictfile3;
        byte byte26;
        String s5;
        if(isUnicode)
            recLength = wordLength + 2 + k2 + 2 + k3 + 2 + 2;
        else
            recLength = wordLength + 1 + k2 + 1 + k3 + 1 + 2;
        recHeaderLength = 8; // record header
        mFileLength = m_file.length();
        abyte3 = new byte[8];
        randomaccessfile = m_file;
        l5 = 78L;
        randomaccessfile.seek(l5);
        l6 = 78L;
        j = 0;
_L20:
        j4 = m_nRecordCount;
        k4 = j;
        i5 = j4;
        if(k4 < i5) goto _L14; else goto _L13
_L13:
        l7 = l6;
        l8 = l - l7;
        randomaccessfile1 = m_file;
        dictfile = this;
        randomaccessfile2 = randomaccessfile1;
        firstRecHeaderOffset = l7;
        j5 = recHeaderLength;
        
        //insert one record header in to the record table
        dictfile.GetHole(randomaccessfile2, firstRecHeaderOffset, j5);
        randomaccessfile3 = m_file;
        l10 = (long)(recHeaderLength + recLength) + mFileLength;
        randomaccessfile3.setLength(l10);
        
        
        randomaccessfile4 = m_file;
        randomaccessfile4.seek(firstRecHeaderOffset);
        l12 = recHeaderLength;
        l13 = l + l12;
        
        
        byte0 = (byte)(int)((0xffffffffff000000L & l13) >> 24);
        abyte3[0] = byte0;
        
        byte1 = (byte)(int)((0xff0000L & l13) >> 16);
        abyte3[1] = byte1;
        byte2 = (byte)(int)((65280L & l13) >> 8);
        abyte3[2] = byte2;
        byte3 = (byte)(int)(255L & l13);
        abyte3[3] = byte3;
        abyte3[4] = 0;
        byte4 = (byte)(m_nRecordCount >> 16 & 0xff);
        abyte3[5] = byte4;
        byte5 = (byte)(m_nRecordCount >> 8 & 0xff);
        abyte3[6] = byte5;
        byte6 = (byte)(m_nRecordCount & 0xff);
        abyte3[7] = byte6;
        randomaccessfile5 = m_file;
        abyte4 = abyte3;
        k5 = 0;
        i6 = recHeaderLength;
        //writing recordHeader
        randomaccessfile5.write(abyte4, k5, i6);
        
        
        randomaccessfile6 = m_file;
        l14 = (long)recHeaderLength + l;
        //positioning at next recordHeader offset
        randomaccessfile6.seek(l14);
        randomaccessfile7 = m_file;
        abyte5 = abyte0;
        j6 = 0;
        k6 = wordLength;
        randomaccessfile7.write(abyte5, j6, k6);
        RandomAccessFile randomaccessfile18;
        byte abyte10[];
        int i15;
        int j15;
        int k15;
        int i16;
        int j16;
        int k16;
        int l16;
        int i17;
        int j17;
        long l17;
        long l18;
        long l19;
        byte byte27;
        byte byte28;
        byte byte29;
        byte byte30;
        RandomAccessFile randomaccessfile19;
        long l20;
        RandomAccessFile randomaccessfile20;
        byte abyte11[];
        int k17;
        int i18;
        long l21;
        if(isUnicode)
        {
            m_file.writeByte(0);
            m_file.writeByte(0);
        } else
        {
            m_file.writeByte(0);
        }
        randomaccessfile8 = m_file;
        abyte6 = abyte1;
        i7 = 0;
        j7 = k2;
        randomaccessfile8.write(abyte6, i7, j7);
        if(!isUnicode) goto _L16; else goto _L15
_L15:
        m_file.writeByte(0);
        m_file.writeByte(0);
_L22:
        randomaccessfile9 = m_file;
        abyte7 = abyte2;
        k7 = 0;
        i8 = k3;
        randomaccessfile9.write(abyte7, k7, i8);
        if(!isUnicode)
            break MISSING_BLOCK_LABEL_1744;
        m_file.writeByte(0);
        m_file.writeByte(0);
_L23:
        j8 = 0;
        m_wBall = j8;
        randomaccessfile10 = m_file;
        byte7 = m_wCat;
        randomaccessfile10.writeByte(byte7);
        randomaccessfile11 = m_file;
        byte8 = m_wBall;
        randomaccessfile11.writeByte(byte8);
        m_file.seek(76L);
        k8 = m_nRecordCount + 1;
        m_nRecordCount = k8;
        randomaccessfile12 = m_file;
        byte9 = (byte)(m_nRecordCount >> 8 & 0xff);
        randomaccessfile12.writeByte(byte9);
        randomaccessfile13 = m_file;
        byte10 = (byte)(m_nRecordCount & 0xff);
        randomaccessfile13.writeByte(byte10);
        m_file.seek(74L);
        randomaccessfile14 = m_file;
        byte11 = (byte)(m_nRecordCount >> 8 & 0xff);
        randomaccessfile14.writeByte(byte11);
        randomaccessfile15 = m_file;
        byte12 = (byte)(m_nRecordCount & 0xff);
        randomaccessfile15.writeByte(byte12);
        m_file.seek(52L);
        randomaccessfile16 = m_file;
        abyte8 = abyte3;
        i9 = 0;
        byte13 = 4;
        j9 = randomaccessfile16.read(abyte8, i9, byte13);
        k9 = abyte3[3] & 0xff;
        i10 = abyte3[2] << 8 & 0xff00;
        j10 = k9 | i10;
        k10 = abyte3[1] << 16 & 0xff0000;
        i11 = j10 | k10;
        j11 = abyte3[0] << 24 & 0xff000000;
        l15 = (long)(i11 | j11) + 8L;
        m_file.seek(52L);
        byte14 = (byte)(int)((0xffffffffff000000L & l15) >> 24);
        abyte3[0] = byte14;
        byte15 = (byte)(int)((0xff0000L & l15) >> 16);
        abyte3[1] = byte15;
        byte16 = (byte)(int)((65280L & l15) >> 8);
        abyte3[2] = byte16;
        byte17 = (byte)(int)(255L & l15);
        abyte3[3] = byte17;
        randomaccessfile17 = m_file;
        abyte9 = abyte3;
        k11 = 0;
        byte18 = 4;
        randomaccessfile17.write(abyte9, k11, byte18);
_L21:
        return;
_L4:
        i12 = j * 2 + 1;
        byte19 = (byte)(ac[j] >> 8 & 0xff);
        abyte0[i12] = byte19;
        j12 = j * 2;
        byte20 = (byte)(ac[j] & 0xff);
        abyte0[j12] = byte20;
        j++;
          goto _L17
_L2:
        s = m_word;
        dictfile1 = this;
        k12 = 0;
        s1 = dictfile1.getEncoding(k12);
        abyte0 = s.getBytes(s1);
          goto _L3
_L8:
        i13 = j * 2 + 1;
        byte21 = (byte)(ac[j] >> 8 & 0xff);
        abyte1[i13] = byte21;
        j13 = j * 2;
        byte22 = (byte)(ac[j] & 0xff);
        abyte1[j13] = byte22;
        j++;
          goto _L18
_L6:
        byte23 = mCodeLang2;
        k13 = 3;
        if(byte23 == k13)
        {
            fromIPAConv();
            i = m_transc.length();
        }
        s2 = m_transc;
        dictfile2 = this;
        i14 = 1;
        s3 = dictfile2.getEncoding(i14);
        abyte1 = s2.getBytes(s3);
          goto _L7
_L12:
        j14 = j * 2 + 1;
        byte24 = (byte)(ac[j] >> 8 & 0xff);
        abyte2[j14] = byte24;
        k14 = j * 2;
        byte25 = (byte)(ac[j] & 0xff);
        abyte2[k14] = byte25;
        j++;
          goto _L19
_L10:
        s4 = m_transl;
        dictfile3 = this;
        byte26 = 2;
        s5 = dictfile3.getEncoding(byte26);
        abyte2 = s4.getBytes(s5);
          goto _L11
_L14:
        randomaccessfile18 = m_file;
        abyte10 = abyte3;
        i15 = 0;
        j15 = i4;
        k15 = randomaccessfile18.read(abyte10, i15, j15);
        i16 = abyte3[3] & 0xff;
        j16 = abyte3[2] << 8 & 0xff00;
        k16 = i16 | j16;
        l16 = abyte3[1] << 16 & 0xff0000;
        i17 = k16 | l16;
        j17 = abyte3[0] << 24 & 0xff000000;
        l17 = i17 | j17;
        l18 = i4;
        l19 = l17 + l18;
        byte27 = (byte)(int)((0xffffffffff000000L & l19) >> 24);
        abyte3[0] = byte27;
        byte28 = (byte)(int)((0xff0000L & l19) >> 16);
        abyte3[1] = byte28;
        byte29 = (byte)(int)((65280L & l19) >> 8);
        abyte3[2] = byte29;
        byte30 = (byte)(int)(255L & l19);
        abyte3[3] = byte30;
        randomaccessfile19 = m_file;
        l20 = l6;
        randomaccessfile19.seek(l20);
        randomaccessfile20 = m_file;
        abyte11 = abyte3;
        k17 = 0;
        i18 = i4;
        randomaccessfile20.write(abyte11, k17, i18);
        l21 = i4;
        l6 += l21;
        j++;
          goto _L20
        IOException ioexception;
        ioexception;
          goto _L21
_L16:
        m_file.writeByte(0);
          goto _L22
        m_file.writeByte(0);
          goto _L23
    }

    public void NewSoundData(String s, int i)
    {
        String s3;
        String s1 = mFileName;
        int j = mFileName.length() - 3;
        String s2 = String.valueOf(s1.substring(0, j));
        s3 = (new StringBuilder(s2)).append("snd").toString();
        if(i != 0 || IsSoundPack) goto _L2; else goto _L1
_L1:
        return;
_L2:
        File file;
        file = JVM INSTR new #246 <Class File>;
        File file1 = file;
        String s4 = s3;
        file1.File(s4);
        if(!file.exists()) goto _L1; else goto _L3
_L3:
        File file2;
        byte abyte0[];
        file2 = JVM INSTR new #246 <Class File>;
        File file3 = file2;
        String s5 = s;
        file3.File(s5);
        abyte0 = new byte[4];
        if(file2.exists()) goto _L5; else goto _L4
_L4:
        RandomAccessFile randomaccessfile;
        int l;
        boolean flag = file2.createNewFile();
        randomaccessfile = JVM INSTR new #80  <Class RandomAccessFile>;
        RandomAccessFile randomaccessfile1 = randomaccessfile;
        File file4 = file2;
        String s6 = "rw";
        randomaccessfile1.RandomAccessFile(file4, s6);
        byte byte0 = (byte)((0xff000000 & 0x10) >> 24);
        abyte0[3] = byte0;
        byte byte1 = (byte)((0xff0000 & 0x10) >> 16);
        abyte0[2] = byte1;
        byte byte2 = (byte)((0xff00 & 0x10) >> 8);
        abyte0[1] = byte2;
        byte byte3 = (byte)(0x10 & 0xff);
        abyte0[0] = byte3;
        RandomAccessFile randomaccessfile2 = randomaccessfile;
        byte abyte1[] = abyte0;
        int k = 0;
        byte byte4 = 4;
        randomaccessfile2.write(abyte1, k, byte4);
        l = 0;
_L10:
        int i1;
        byte byte5;
        i1 = l;
        byte5 = 4;
        if(i1 < byte5) goto _L7; else goto _L6
_L6:
        RandomAccessFile randomaccessfile3 = randomaccessfile;
        byte abyte2[] = abyte0;
        int j1 = 0;
        byte byte6 = 4;
        randomaccessfile3.write(abyte2, j1, byte6);
        RandomAccessFile randomaccessfile4 = randomaccessfile;
        byte abyte3[] = abyte0;
        int k1 = 0;
        byte byte7 = 4;
        randomaccessfile4.write(abyte3, k1, byte7);
        RandomAccessFile randomaccessfile5 = randomaccessfile;
        byte abyte4[] = abyte0;
        int l1 = 0;
        byte byte8 = 4;
        randomaccessfile5.write(abyte4, l1, byte8);
        randomaccessfile.close();
_L5:
        RandomAccessFile randomaccessfile6;
        int j6;
        RandomAccessFile randomaccessfile10;
        long l8;
        int j7;
        int k7;
        int j8;
        int i9;
        byte abyte12[];
        int j10;
        randomaccessfile6 = JVM INSTR new #80  <Class RandomAccessFile>;
        RandomAccessFile randomaccessfile7 = randomaccessfile6;
        File file5 = file2;
        String s7 = "rw";
        randomaccessfile7.RandomAccessFile(file5, s7);
        RandomAccessFile randomaccessfile8 = randomaccessfile6;
        byte abyte5[] = abyte0;
        int i2 = 0;
        byte byte9 = 4;
        int j2 = randomaccessfile8.read(abyte5, i2, byte9);
        int k2 = abyte0[0] & 0xff;
        int l2 = abyte0[1] << 8 & 0xff00;
        int i3 = k2 | l2;
        int j3 = abyte0[2] << 16 & 0xff0000;
        int k3 = i3 | j3;
        int l3 = abyte0[3] << 24 & 0xff000000;
        int i4 = k3 | l3;
        RandomAccessFile randomaccessfile9 = randomaccessfile6;
        byte abyte6[] = abyte0;
        int j4 = 0;
        byte byte10 = 4;
        int k4 = randomaccessfile9.read(abyte6, j4, byte10);
        int l4 = abyte0[0] & 0xff;
        int i5 = abyte0[1] << 8 & 0xff00;
        int j5 = l4 | i5;
        int k5 = abyte0[2] << 16 & 0xff0000;
        int l5 = j5 | k5;
        int i6 = abyte0[3] << 24 & 0xff000000;
        j6 = l5 | i6;
        randomaccessfile10 = JVM INSTR new #80  <Class RandomAccessFile>;
        RandomAccessFile randomaccessfile11 = randomaccessfile10;
        File file6 = file;
        String s8 = "r";
        randomaccessfile11.RandomAccessFile(file6, s8);
        long l6 = pos_word_segment;
        RandomAccessFile randomaccessfile12 = randomaccessfile10;
        long l7 = l6;
        randomaccessfile12.seek(l7);
        byte byte11 = randomaccessfile10.readByte();
        byte abyte7[] = new byte[byte11];
        RandomAccessFile randomaccessfile13 = randomaccessfile10;
        byte abyte8[] = abyte7;
        int k6 = 0;
        byte byte12 = byte11;
        int i7 = randomaccessfile13.read(abyte8, k6, byte12);
        l8 = randomaccessfile6.length();
        j7 = pos_snd_wav;
        k7 = byte11 + 1 + 8;
        long l9 = i4;
        RandomAccessFile randomaccessfile14 = randomaccessfile6;
        long l10 = l9;
        randomaccessfile14.seek(l10);
        long l11 = l8 - l9;
        DictFile dictfile = this;
        RandomAccessFile randomaccessfile15 = randomaccessfile6;
        long l12 = l9;
        int i8 = k7;
        dictfile.GetHole(randomaccessfile15, l12, i8);
        j8 = i4 + k7;
        RandomAccessFile randomaccessfile16 = randomaccessfile6;
        long l13 = l9;
        randomaccessfile16.seek(l13);
        int k8 = ((int)l8 + k7) - j8;
        i9 = j7;
        RandomAccessFile randomaccessfile17 = randomaccessfile6;
        byte byte13 = byte11;
        randomaccessfile17.writeByte(byte13);
        RandomAccessFile randomaccessfile18 = randomaccessfile6;
        byte abyte9[] = abyte7;
        int j9 = 0;
        byte byte14 = byte11;
        randomaccessfile18.write(abyte9, j9, byte14);
        byte byte15 = (byte)((0xff000000 & i9) >> 24);
        abyte0[3] = byte15;
        byte byte16 = (byte)((0xff0000 & i9) >> 16);
        abyte0[2] = byte16;
        byte byte17 = (byte)((0xff00 & i9) >> 8);
        abyte0[1] = byte17;
        byte byte18 = (byte)(i9 & 0xff);
        abyte0[0] = byte18;
        RandomAccessFile randomaccessfile19 = randomaccessfile6;
        byte abyte10[] = abyte0;
        int k9 = 0;
        byte byte19 = 4;
        randomaccessfile19.write(abyte10, k9, byte19);
        byte byte20 = (byte)((0xff000000 & k8) >> 24);
        abyte0[3] = byte20;
        byte byte21 = (byte)((0xff0000 & k8) >> 16);
        abyte0[2] = byte21;
        byte byte22 = (byte)((0xff00 & k8) >> 8);
        abyte0[1] = byte22;
        byte byte23 = (byte)(k8 & 0xff);
        abyte0[0] = byte23;
        RandomAccessFile randomaccessfile20 = randomaccessfile6;
        byte abyte11[] = abyte0;
        int i10 = 0;
        byte byte24 = 4;
        randomaccessfile20.write(abyte11, i10, byte24);
        long l14 = (long)k7 + l8;
        RandomAccessFile randomaccessfile21 = randomaccessfile6;
        long l15 = l14;
        randomaccessfile21.seek(l15);
        long l16 = pos_snd_segment;
        RandomAccessFile randomaccessfile22 = randomaccessfile10;
        long l17 = l16;
        randomaccessfile22.seek(l17);
        abyte12 = new byte[1024];
        j10 = 0;
_L11:
        int k10;
        int i11;
        k10 = j10;
        i11 = i9;
        if(k10 < i11) goto _L9; else goto _L8
_L8:
        RandomAccessFile randomaccessfile23 = randomaccessfile6;
        long l18 = 0L;
        randomaccessfile23.seek(l18);
        int j11 = j6 + 1;
        byte byte25 = (byte)((0xff000000 & j8) >> 24);
        abyte0[3] = byte25;
        byte byte26 = (byte)((0xff0000 & j8) >> 16);
        abyte0[2] = byte26;
        byte byte27 = (byte)((0xff00 & j8) >> 8);
        abyte0[1] = byte27;
        byte byte28 = (byte)(j8 & 0xff);
        abyte0[0] = byte28;
        RandomAccessFile randomaccessfile24 = randomaccessfile6;
        byte abyte13[] = abyte0;
        int k11 = 0;
        byte byte29 = 4;
        randomaccessfile24.write(abyte13, k11, byte29);
        byte byte30 = (byte)((0xff000000 & j11) >> 24);
        abyte0[3] = byte30;
        byte byte31 = (byte)((0xff0000 & j11) >> 16);
        abyte0[2] = byte31;
        byte byte32 = (byte)((0xff00 & j11) >> 8);
        abyte0[1] = byte32;
        byte byte33 = (byte)(j11 & 0xff);
        abyte0[0] = byte33;
        RandomAccessFile randomaccessfile25 = randomaccessfile6;
        byte abyte14[] = abyte0;
        int i12 = 0;
        byte byte34 = 4;
        randomaccessfile25.write(abyte14, i12, byte34);
        long l19 = (long)(j7 + k7) + l8;
        RandomAccessFile randomaccessfile26 = randomaccessfile6;
        long l20 = l19;
        randomaccessfile26.setLength(l20);
        randomaccessfile10.close();
        randomaccessfile6.close();
          goto _L1
        String s9;
        int j12;
        byte byte35;
        String s10;
        int k12;
        char c;
        IOException ioexception;
        IOException ioexception1;
        ioexception;
        s9 = ioexception.getMessage();
        j12 = Log.i("error read sound file", s9);
          goto _L1
_L7:
        byte35 = 0;
        abyte0[l] = byte35;
        l++;
          goto _L10
        ioexception1;
        s10 = ioexception1.getMessage();
        k12 = Log.i("error write sound file", s10);
          goto _L5
_L9:
        byte35 = 0;
        c = '\u0400';
        RandomAccessFile randomaccessfile27 = randomaccessfile10;
        byte abyte15[] = abyte12;
        int i13 = byte35;
        char c1 = c;
        int j13 = randomaccessfile27.read(abyte15, i13, c1);
        j10 += j13;
        int k13 = j10;
        int i14 = i9;
        if(k13 > i14)
        {
            int j14 = j10 - i9;
            j13 -= j14;
        }
        if(j13 > 0)
        {
            RandomAccessFile randomaccessfile28 = randomaccessfile6;
            byte abyte16[] = abyte12;
            int k14 = 0;
            int i15 = j13;
            randomaccessfile28.write(abyte16, k14, i15);
        }
          goto _L11
    }

    public boolean OpenFile(String s)
        throws IOException
    {
        CloseDict();
        String s1 = new String(s);
        mFileName = s1;
        IsSoundPack = false;
        IsSoundPack2 = false;
        String s2 = mFileName;
        File file = new File(s2);
        ffile = file;
        if(!ffile.exists())
            break MISSING_BLOCK_LABEL_318;
        String s3;
        File file1 = ffile;
        RandomAccessFile randomaccessfile = new RandomAccessFile(file1, "rw");
        m_file = randomaccessfile;
        m_isFileOpen = true;
        ResetRec();
        ReadDatabaseInfo();
        s3 = "/data/data/com.learnwords.android/files/demo-italian.pdb";
        if(!mFileName.equals("/data/data/com.learnwords.android/files/demo.pdb") && !mFileName.equals(s3)) goto _L2; else goto _L1
_L1:
        isDemo = true;
_L3:
        String s4 = myContext.getResources().getString(0x7f070029);
        String s5 = new String(s4);
        strDEMO = s5;
        boolean flag;
        int i = s.length() - 3;
        String s6 = String.valueOf(s.substring(0, i));
        String s7 = (new StringBuilder(s6)).append("snd").toString();
        if((new File(s7)).exists())
            IsSoundPack = true;
        int j = s.length() - 3;
        String s8 = String.valueOf(s.substring(0, j));
        String s9 = (new StringBuilder(s8)).append("sn2").toString();
        if((new File(s9)).exists())
            IsSoundPack2 = true;
        flag = true;
_L4:
        return flag;
_L2:
        flag = false;
        isDemo = flag;
          goto _L3
        IOException ioexception;
        ioexception;
        flag = false;
          goto _L4
        flag = false;
          goto _L4
    }

    public String PrepareForWriting(String s)
    {
        String s1 = s;
        do
        {
            int i = s1.indexOf('(');
            if(i != -1)
            {
                int j = s1.indexOf(')');
                String s4;
                StringBuilder stringbuilder1;
                int l;
                String s5;
                if(j != -1)
                {
                    String s2 = String.valueOf(s1.substring(0, i));
                    StringBuilder stringbuilder = new StringBuilder(s2);
                    int k = j + 1;
                    String s3 = s1.substring(k);
                    s1 = stringbuilder.append(s3).toString();
                } else
                {
                    s1 = s1.substring(0, i);
                }
            }
        } while(i != -1);
        s1 = s1.trim().replace(' ', '_').replace('/', '_').replace('\\', '_').toUpperCase();
        do
        {
            i = s1.indexOf("__");
            if(i != -1)
            {
                s4 = String.valueOf(s1.substring(0, i));
                stringbuilder1 = new StringBuilder(s4);
                l = i + 1;
                s5 = s1.substring(l);
                s1 = stringbuilder1.append(s5).toString();
            }
        } while(i != -1);
        return s1;
    }

    public byte QuickGetBall(int i)
        throws IOException
    {
        byte byte1;
        if(i < 0)
            i = 0;
        int j = i + 1;
        int k = m_nRecordCount;
        long l;
        long l1;
        long l2;
        byte byte0;
        if(j >= k)
        {
            l = m_file.length();
        } else
        {
            int i1 = i + 1;
            l = SeekPos(i1);
        }
        l1 = SeekPos(i);
        l2 = l - l1;
        l = (l1 + l2) - 1L;
        m_file.seek(l);
        byte0 = m_file.readByte();
        byte1 = byte0;
_L2:
        return byte1;
        IOException ioexception;
        ioexception;
        byte1 = 0;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void QuickSaveBall(int i, byte byte0)
        throws IOException
    {
        int j = i + 1;
        int k = m_nRecordCount;
        long l;
        long l1;
        long l2;
        if(j >= k)
        {
            l = m_file.length();
        } else
        {
            int i1 = i + 1;
            l = SeekPos(i1);
        }
        l1 = SeekPos(i);
        l2 = l - l1;
        l = (l1 + l2) - 1L;
        m_file.seek(l);
        m_file.writeByte(byte0);
_L2:
        return;
        IOException ioexception;
        ioexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public boolean SaveBall()
        throws IOException
    {
        long l2;
        long l = m_nRecordOffset;
        long l1 = m_nRecordLength;
        l2 = (l + l1) - 1L;
        m_file.seek(l2);
        RandomAccessFile randomaccessfile = m_file;
        byte byte0 = m_wBall;
        randomaccessfile.writeByte(byte0);
        boolean flag = true;
_L2:
        return flag;
        IOException ioexception;
        ioexception;
        flag = false;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void SaveRecord(int i)
    {
        int j = i + 1;
        long nextRecOffset;
        int j1;
        char ac[];
        byte abyte0[];
        int k1;
        int i3;
        byte abyte1[];
        int j4;
        byte abyte2[];
        long l7;
        byte abyte3[];
        long l11;
        int k = m_nRecordCount;
        int l = j;
        int i1 = k;
        long l2;
        long recordLength;
        int i2;
        int j2;
        int k2;
        int j3;
        int k3;
        int i4;
        int k4;
        int l4;
        int i5;
        long l6;
        int k5;
        int i6;
        int j6;
        int k6;
        long l8;
        long l9;
        RandomAccessFile randomaccessfile;
        long l10;
        int i7;
        int j7;
        int k7;
        RandomAccessFile randomaccessfile1;
        int i8;
        DictFile dictfile;
        RandomAccessFile randomaccessfile2;
        long l12;
        int j8;
        RandomAccessFile randomaccessfile3;
        long l13;
        RandomAccessFile randomaccessfile4;
        byte abyte4[];
        int k8;
        int i9;
        RandomAccessFile randomaccessfile5;
        byte abyte5[];
        int j9;
        int k9;
        RandomAccessFile randomaccessfile6;
        byte abyte6[];
        int i10;
        int j10;
        RandomAccessFile randomaccessfile7;
        byte byte0;
        RandomAccessFile randomaccessfile8;
        byte byte1;
        RandomAccessFile randomaccessfile9;
        long l14;
        if(l >= i1)
        {
            nextRecOffset = m_file.length();
        } else
        {
            int k10 = i + 1;
            DictFile dictfile1 = this;
            int i11 = k10;
            nextRecOffset = dictfile1.SeekPos(i11);
        }
        l2 = SeekPos(i);
        recordLength = nextRecOffset - l2;
        j1 = m_word.length();
        if(!isUnicode) {
//        	goto _L2; else goto _L1
        } else {
        	
        }
_L1:
        j1 *= 2;
        ac = m_word.toCharArray();
        abyte0 = new byte[j1];
        k1 = 0;
_L21:
        i2 = m_word.length();
        j2 = k1;
        k2 = i2;
        if(j2 < k2) goto _L4; else goto _L3
_L3:
        i3 = j1;
        j1 = m_transc.length();
        if(!isUnicode) goto _L6; else goto _L5
_L5:
        j1 *= 2;
        ac = m_transc.toCharArray();
        abyte1 = new byte[j1];
        k1 = 0;
_L22:
        j3 = m_transc.length();
        k3 = k1;
        i4 = j3;
        if(k3 < i4) goto _L8; else goto _L7
_L7:
        j4 = j1;
        j1 = m_transl.length();
        if(!isUnicode) goto _L10; else goto _L9
_L9:
        j1 *= 2;
        ac = m_transl.toCharArray();
        abyte2 = new byte[j1];
        k1 = 0;
_L23:
        k4 = m_transl.length();
        l4 = k1;
        i5 = k4;
        if(l4 < i5) goto _L12; else goto _L11
_L11:
        int j5 = j1;
        long l5;
        int j11;
        byte byte2;
        int k11;
        byte byte3;
        String s;
        DictFile dictfile2;
        int i12;
        String s1;
        int j12;
        byte byte4;
        int k12;
        byte byte5;
        byte byte6;
        int i13;
        String s2;
        DictFile dictfile3;
        int j13;
        String s3;
        int k13;
        byte byte7;
        int i14;
        byte byte8;
        String s4;
        DictFile dictfile4;
        byte byte9;
        String s5;
        if(isUnicode)
            l5 = i3 + 2 + j4 + 2 + j5 + 2 + 2;
        else
            l5 = i3 + 1 + j4 + 1 + j5 + 1 + 2;
        l6 = m_file.length();
        l7 = l5 - recordLength;
        k5 = i + 1;
        i6 = m_nRecordCount;
        j6 = k5;
        k6 = i6;
        if(j6 >= k6) goto _L14; else goto _L13
_L13:
        l8 = m_file.length() - nextRecOffset;
        abyte3 = new byte[8];
        l9 = (i + 1) * 8 + 78;
        randomaccessfile = m_file;
        l10 = l9;
        randomaccessfile.seek(l10);
        l11 = l9;
        k1 = i + 1;
_L24:
        i7 = m_nRecordCount;
        j7 = k1;
        k7 = i7;
        if(j7 < k7) goto _L16; else goto _L15
_L15:
        if(l7 > 0L)
        {
            randomaccessfile1 = m_file;
            i8 = (int)l7;
            dictfile = this;
            randomaccessfile2 = randomaccessfile1;
            l12 = nextRecOffset;
            j8 = i8;
            dictfile.GetHole(randomaccessfile2, nextRecOffset, j8);
        } else
        {
            RandomAccessFile randomaccessfile13 = m_file;
            int j17 = (int)(65535L * l7);
            DictFile dictfile5 = this;
            RandomAccessFile randomaccessfile14 = randomaccessfile13;
            long l17 = nextRecOffset;
            int k17 = j17;
            dictfile5.MoveLeft(randomaccessfile14, nextRecOffset, k17);
        }
_L14:
        randomaccessfile3 = m_file;
        l13 = l2;
        randomaccessfile3.seek(l13);
        randomaccessfile4 = m_file;
        abyte4 = abyte0;
        k8 = 0;
        i9 = i3;
        randomaccessfile4.write(abyte4, k8, i9);
        if(!isUnicode) goto _L18; else goto _L17
_L17:
        m_file.writeByte(0);
        m_file.writeByte(0);
_L26:
        randomaccessfile5 = m_file;
        abyte5 = abyte1;
        j9 = 0;
        k9 = j4;
        randomaccessfile5.write(abyte5, j9, k9);
        if(!isUnicode) goto _L20; else goto _L19
_L19:
        m_file.writeByte(0);
        m_file.writeByte(0);
_L27:
        randomaccessfile6 = m_file;
        abyte6 = abyte2;
        i10 = 0;
        j10 = j5;
        randomaccessfile6.write(abyte6, i10, j10);
        if(!isUnicode)
            break MISSING_BLOCK_LABEL_1338;
        m_file.writeByte(0);
        m_file.writeByte(0);
_L28:
        randomaccessfile7 = m_file;
        byte0 = m_wCat;
        randomaccessfile7.writeByte(byte0);
        randomaccessfile8 = m_file;
        byte1 = m_wBall;
        randomaccessfile8.writeByte(byte1);
        randomaccessfile9 = m_file;
        l14 = l6 + l7;
        randomaccessfile9.setLength(l14);
_L25:
        return;
_L4:
        j11 = k1 * 2 + 1;
        byte2 = (byte)(ac[k1] >> 8 & 0xff);
        abyte0[j11] = byte2;
        k11 = k1 * 2;
        byte3 = (byte)(ac[k1] & 0xff);
        abyte0[k11] = byte3;
        k1++;
          goto _L21
_L2:
        s = m_word;
        dictfile2 = this;
        i12 = 0;
        s1 = dictfile2.getEncoding(i12);
        abyte0 = s.getBytes(s1);
          goto _L3
_L8:
        j12 = k1 * 2 + 1;
        byte4 = (byte)(ac[k1] >> 8 & 0xff);
        abyte1[j12] = byte4;
        k12 = k1 * 2;
        byte5 = (byte)(ac[k1] & 0xff);
        abyte1[k12] = byte5;
        k1++;
          goto _L22
_L6:
        byte6 = mCodeLang2;
        i13 = 3;
        if(byte6 == i13)
        {
            fromIPAConv();
            j1 = m_transc.length();
        }
        s2 = m_transc;
        dictfile3 = this;
        j13 = 1;
        s3 = dictfile3.getEncoding(j13);
        abyte1 = s2.getBytes(s3);
          goto _L7
_L12:
        k13 = k1 * 2 + 1;
        byte7 = (byte)(ac[k1] >> 8 & 0xff);
        abyte2[k13] = byte7;
        i14 = k1 * 2;
        byte8 = (byte)(ac[k1] & 0xff);
        abyte2[i14] = byte8;
        k1++;
          goto _L23
_L10:
        s4 = m_transl;
        dictfile4 = this;
        byte9 = 2;
        s5 = dictfile4.getEncoding(byte9);
        abyte2 = s4.getBytes(s5);
          goto _L11
_L16:
        RandomAccessFile randomaccessfile10 = m_file;
        byte abyte7[] = abyte3;
        int j14 = 0;
        byte byte10 = 8;
        int k14 = randomaccessfile10.read(abyte7, j14, byte10);
        int i15 = abyte3[3] & 0xff;
        int j15 = abyte3[2] << 8 & 0xff00;
        int k15 = i15 | j15;
        int l15 = abyte3[1] << 16 & 0xff0000;
        int i16 = k15 | l15;
        int j16 = abyte3[0] << 24 & 0xff000000;
        int k16 = (int)((long)(i16 | j16) + l7);
        byte byte11 = (byte)((0xff000000 & k16) >> 24);
        abyte3[0] = byte11;
        byte byte12 = (byte)((0xff0000 & k16) >> 16);
        abyte3[1] = byte12;
        byte byte13 = (byte)((0xff00 & k16) >> 8);
        abyte3[2] = byte13;
        byte byte14 = (byte)(k16 & 0xff);
        abyte3[3] = byte14;
        RandomAccessFile randomaccessfile11 = m_file;
        long l16 = l11;
        randomaccessfile11.seek(l16);
        RandomAccessFile randomaccessfile12 = m_file;
        byte abyte8[] = abyte3;
        int i17 = 0;
        byte byte15 = 8;
        randomaccessfile12.write(abyte8, i17, byte15);
        l11 += 8L;
        k1++;
          goto _L24
        IOException ioexception;
        ioexception;
          goto _L25
_L18:
        m_file.writeByte(0);
          goto _L26
_L20:
        m_file.writeByte(0);
          goto _L27
        m_file.writeByte(0);
          goto _L28
    }

    public void SetBall(byte byte0)
    {
        if(byte0 < 0)
            byte0 = 0;
        m_wBall = byte0;
    }

    public void WritePDBInfo()
        throws IOException
    {
        long l;
        byte abyte0[] = new byte[100];
        l = m_nRecordCount * 8 + 78 + 2;
        byte byte0 = (byte)((m_nIndexBlock & 0xff00) >> 8);
        abyte0[0] = byte0;
        byte byte1 = (byte)(m_nIndexBlock & 0xff);
        abyte0[1] = byte1;
        byte byte2 = (byte)((m_nCurrentRecord & 0xff00) >> 8);
        abyte0[2] = byte2;
        byte byte3 = (byte)(m_nCurrentRecord & 0xff);
        abyte0[3] = byte3;
        byte byte4 = (byte)((m_nAllCompleteWords & 0xff00) >> 8);
        abyte0[4] = byte4;
        byte byte5 = (byte)(m_nAllCompleteWords & 0xff);
        abyte0[5] = byte5;
        byte byte6 = m_nTypeDict;
        abyte0[6] = byte6;
        byte byte7 = m_nCompleteWords;
        abyte0[7] = byte7;
        byte byte8 = m_nShifr;
        abyte0[8] = byte8;
        byte byte9 = mCodeLang1;
        abyte0[9] = byte9;
        byte byte10 = mCodeLang2;
        abyte0[10] = byte10;
        byte byte11 = mCodeLang3;
        abyte0[11] = byte11;
        m_file.seek(l);
        m_file.write(abyte0, 0, 12);
_L2:
        return;
        IOException ioexception;
        ioexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public String getEncoding(int i)
    {
        byte byte0;
        byte0 = 0;
        if(i == 0)
            byte0 = mCodeLang1;
        if(i == 1)
            byte0 = mCodeLang2;
        if(i == 2)
            byte0 = mCodeLang3;
        byte0;
        JVM INSTR tableswitch 2 27: default 152
    //                   2 158
    //                   3 165
    //                   4 152
    //                   5 152
    //                   6 152
    //                   7 152
    //                   8 152
    //                   9 172
    //                   10 179
    //                   11 152
    //                   12 152
    //                   13 152
    //                   14 152
    //                   15 186
    //                   16 152
    //                   17 193
    //                   18 152
    //                   19 152
    //                   20 200
    //                   21 152
    //                   22 152
    //                   23 207
    //                   24 214
    //                   25 207
    //                   26 152
    //                   27 221;
           goto _L1 _L2 _L3 _L1 _L1 _L1 _L1 _L1 _L4 _L5 _L1 _L1 _L1 _L1 _L6 _L1 _L7 _L1 _L1 _L8 _L1 _L1 _L9 _L10 _L9 _L1 _L11
_L1:
        String s = "CP1252";
_L13:
        return s;
_L2:
        s = "CP1251";
        continue; /* Loop/switch isn't completed */
_L3:
        s = "UTF-8";
        continue; /* Loop/switch isn't completed */
_L4:
        s = "CP1251";
        continue; /* Loop/switch isn't completed */
_L5:
        s = "CP1250";
        continue; /* Loop/switch isn't completed */
_L6:
        s = "CP1253";
        continue; /* Loop/switch isn't completed */
_L7:
        s = "CP1250";
        continue; /* Loop/switch isn't completed */
_L8:
        s = "CP1250";
        continue; /* Loop/switch isn't completed */
_L9:
        s = "CP1251";
        continue; /* Loop/switch isn't completed */
_L10:
        s = "CP1254";
        continue; /* Loop/switch isn't completed */
_L11:
        s = "CP1250";
        if(true) goto _L13; else goto _L12
_L12:
    }

    void saveLevels(byte byte0)
    {
        RandomAccessFile randomaccessfile = m_file;
        long l = m_nRecordCount * 8 + 78 + 10;
        randomaccessfile.seek(l);
        m_file.writeByte(byte0);
_L2:
        return;
        IOException ioexception;
        ioexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static final int dcBrick = 1024;
    private static final int sizeDatabaseHeader = 78;
    private static final int sizeListAppInfo = 12;
    private static final int sizeRecordEntry = 8;
    boolean IsSoundPack;
    boolean IsSoundPack2;
    File ffile;
    boolean gnSwap;
    boolean isCat;
    public boolean isDemo;
    public boolean isReg;
    boolean isUnicode;
    byte mCodeLang1;
    byte mCodeLang2;
    byte mCodeLang3;
    String mFileName;
    RandomAccessFile m_file;
    boolean m_isFileOpen;
    int m_nAllCompleteWords;
    byte m_nCompleteWords;
    byte m_nCurBall;
    int m_nCurrentRecord;
    int m_nIndexBlock;
    int m_nRecordCount;
    int m_nRecordLength;
    long m_nRecordOffset;
    byte m_nShifr;
    byte m_nTypeDict;
    String m_transc;
    String m_transl;
    byte m_wBall;
    byte m_wCat;
    String m_word;
    public Context myContext;
    public long pos_snd_segment;
    private int pos_snd_wav;
    public long pos_word_segment;
    public String strDEMO;
}
