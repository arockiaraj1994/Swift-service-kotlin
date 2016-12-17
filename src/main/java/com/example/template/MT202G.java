
package com.example.template;

import com.example.model.Transaction;
import com.prowidesoftware.swift.model.SwiftBlock4;
import com.prowidesoftware.swift.model.field.Field32A;


/**
 * Auto Generated class.
 * 
 */
public class MT202G {


    /**
     * This method returns swift message
     * 
     */
    public String getSwiftMessage(Transaction transaction) {
        // construct block 4
        SwiftBlock4 block4 = new SwiftBlock4();
        Field32A field32A = new Field32A();
        field32A.setCurrency(transaction.getCurrencyCode());
        field32A.setAmount(transaction.getAmount());
        field32A.setDate(transaction.getDate());
        block4 .append(field32A);
        MT202G myMT = new MT202G();
        myMT.getSwiftMessage().setBlock4(block4);
        return myMT.message();
    }

}
