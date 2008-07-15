/* ====================================================================
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
==================================================================== */
package org.apache.poi.xwpf.usermodel;

import org.apache.poi.xwpf.model.XMLParagraph;
import org.apache.poi.xwpf.XWPFDocument;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;

/**
 * Sketch of XWPF paragraph class
 */
public class XWPFParagraph extends XMLParagraph
{
    protected XWPFDocument docRef; // XXX: we'd like to have access to document's hyperlink, comments and other tables
    /**
     * TODO - replace with RichText String
     */
    private StringBuffer text = new StringBuffer();
    
    public XWPFParagraph(CTP prgrph, XWPFDocument docRef)
    {
        super(prgrph);
        
        this.docRef = docRef; 
        CTR[] rs = paragraph.getRArray();
    
        // Get text
        for (int j = 0; j < rs.length; j++) {
            // Loop over text runs
            CTText[] texts = rs[j].getTArray();
            for (int k = 0; k < texts.length; k++) {
                text.append(
                        texts[k].getStringValue()
                );
            }
        }
    }
    
    public XWPFParagraph(CTP prgrph) {
        this(prgrph, null);
    }
    
    public XWPFParagraph(XMLParagraph paragraph) {
        this(paragraph.getCTP());
    }
    
    public XWPFDocument getDocRef() {
        return docRef;
    }
    
    public String getText() {
        return text.toString();
    }
}