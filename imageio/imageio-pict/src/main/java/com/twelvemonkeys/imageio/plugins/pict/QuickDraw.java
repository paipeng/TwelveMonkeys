/*
 * Copyright (c) 2008, Harald Kuhr
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name "TwelveMonkeys" nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.twelvemonkeys.imageio.plugins.pict;

import java.awt.*;
import java.awt.image.DataBuffer;
import java.awt.image.IndexColorModel;

/**
 * QuickDraw constants.
 *
 * @author <a href="mailto:harald.kuhr@gmail.com">Harald Kuhr</a>
 * @author last modified by $Author: haraldk$
 * @version $Id: QuickDraw.java,v 1.0 Oct 9, 2007 1:15:09 AM haraldk Exp$
 */
interface QuickDraw {
    // Reversed from default BufferedImage.TYPE_BYTE_BINARY
    IndexColorModel MONOCHROME = new IndexColorModel(1, 2, new int[] {0xffffff, 0x00000000}, 0, false, -1, DataBuffer.TYPE_BYTE);

    /** QuickDraw {@code white} pattern */
    Pattern WHITE = new BitMapPattern(Color.WHITE);

    /** QuickDraw {@code black} pattern */
    Pattern BLACK = new BitMapPattern(Color.BLACK);

    /** QuickDraw {@code gray} pattern */
    Pattern GRAY = new BitMapPattern(0xAA55AA55);

    /** QuickDraw {@code ltGray} pattern */
    Pattern LIGT_GRAY = new BitMapPattern(0x88228822);

    /** QuickDraw {@code dkGray} pattern */
    Pattern DARK_GRAY = new BitMapPattern(0x77DD77DD);

    // Boolean Transfer modes.
    // http://developer.apple.com/documentation/mac/quickdraw/QuickDraw-196.html#HEADING196-2
    // http://developer.apple.com/documentation/mac/quickdraw/QuickDraw-269.html#HEADING269-2
    // See http://developer.apple.com/documentation/mac/quickdraw/QuickDraw-199.html#HEADING199-76 for color!
    /*
    Source mode	Action on destination pixel
 	If source pixel is black	If source pixel is white	If source pixel is any other color
    srcCopy	Apply foreground color	Apply background color	Apply weighted portions of foreground and background colors
    notSrcCopy	Apply background color	Apply foreground color	Apply weighted portions of background and foreground colors
    srcOr	Apply foreground color	Leave alone	Apply weighted portions of foreground color
    notSrcOr	Leave alone	Apply foreground color	Apply weighted portions of foreground color
    srcXor	Invert (undefined for colored destination pixel)	Leave alone	Leave alone
    notSrcXor	Leave alone	Invert (undefined for colored destination pixel)	Leave alone
    srcBic	Apply background color	Leave alone	Apply weighted portions of background color
    notSrcBic	Leave alone	Apply background color	Apply weighted portions of background color
     */
    int SRC_COPY = 0;
    int SRC_OR = 1;
    int SRC_XOR = 2;
    int SRC_BIC = 3;
    int NOT_SRC_COPY = 4;
    int NOT_SRC_OR = 5;
    int NOT_SRC_XOR = 6;
    int NOT_SRC_BIC = 7;

    int PAT_COPY = 8;
    int PAT_OR = 9;
    int PAT_XOR = 10;
    int PAT_BIC = 11;
    int NOT_PAT_COPY = 12;
    int NOT_PAT_OR = 13;
    int NOT_PAT_XOR = 14;
    int NOT_PAT_BIC = 15;

    int DITHER_COPY = 64; // Add to src mode for dither
    int HILITE = 50; // Add to src or pattern mode for highlight

    // Arithmetic Transfer Modes
    // http://developer.apple.com/documentation/mac/quickdraw/QuickDraw-199.html#HEADING199-112
    /*
    CONST
    blend       = 32; {replace destination pixel with a blend }
                      { of the source and destination pixel }
                      { colors; if the destination is a bitmap or }
                      { 1-bit pixel map, revert to srcCopy mode}
    addPin      = 33; {replace destination pixel with the sum of }
                      { the source and destination pixel colors-- }
                      { up to a maximum allowable value; if }
                      { the destination is a bitmap or }
                      { 1-bit pixel map, revert to srcBic mode}
    addOver     = 34; {replace destination pixel with the sum of }
                      { the source and destination pixel colors-- }
                      { but if the value of the red, green, or }
                      { blue component exceeds 65,536, then }
                      { subtract 65,536 from that value; if the }
                      { destination is a bitmap or 1-bit }
                      { pixel map, revert to srcXor mode}
    subPin      = 35; {replace destination pixel with the }
                      { difference of the source and destination }
                      { pixel colors--but not less than a minimum }
                      { allowable value; if the destination }
                      { is a bitmap or 1-bit pixel map, revert to }
                      { srcOr mode}
    transparent = 36; {replace the destination pixel with the }
                      { source pixel if the source pixel isn't }
                      { equal to the background color}
    addMax      = 37; {compare the source and destination pixels, }
                      { and replace the destination pixel with }
                      { the color containing the greater }
                      { saturation of each of the RGB components; }
                      { if the destination is a bitmap or }
                      { 1-bit pixel map, revert to srcBic mode}
    subOver     = 38; {replace destination pixel with the }
                      { difference of the source and destination }
                      { pixel colors--but if the value of a red, }
                      { green, or blue component is }
                      { less than 0, add the negative result to }
                      { 65,536; if the destination is a bitmap or }
                      { 1-bit pixel map, revert to srcXor mode}
    adMin       = 39; {compare the source and destination pixels, }
                      { and replace the destination pixel with }
                      { the color containing the lesser }
                      { saturation of each of the RGB components; }
                      { if the destination is a bitmap or }
                      { 1-bit pixel map, revert to srcOr mode}
   */
    int BLEND = 32; // dest = source weight/65,535 + destination (1 - weight/65,535)
    int ADD_PIN = 33;
    int ADD_OVER = 34;
    int SUB_PIN = 35;
    int TRANSPARENT = 36;
    int ADD_MAX = 37;
    int SUB_OVER = 38;
    int ADD_MIN = 39;

    // Transfer mode for text only
    /*
    If the destination device is color and grayishTextOr is the transfer mode,
    QuickDraw draws with a blend of the foreground and background colors. If
    the destination device is black and white, the grayishTextOr mode dithers
    black and white.

    Note that grayishTextOr is not considered a standard transfer mode because
    currently it is not stored in pictures, and printing with it is undefined.
     */
    int GRAYISH_TEXT_OR = 49;

    /*
     * Text face masks.
     */
    int TX_BOLD_MASK = 1;
    int TX_ITALIC_MASK = 2;
    int TX_UNDERLINE_MASK = 4;
    int TX_OUTLINE_MASK = 8;
    int TX_SHADOWED_MASK = 16;
    int TX_CONDENSED_MASK = 32;
    int TX_EXTENDED_MASK = 64;
}
