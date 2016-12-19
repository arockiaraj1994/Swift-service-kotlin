
package com.example.template;

import com.example.model.Transaction;
import com.prowidesoftware.swift.model.SwiftBlock4;
import com.prowidesoftware.swift.model.field.Field;
import com.prowidesoftware.swift.model.field.Field32A;
import com.prowidesoftware.swift.model.field.Field53B;
import com.prowidesoftware.swift.model.field.Field57A;
import com.prowidesoftware.swift.model.field.Field58A;
import com.prowidesoftware.swift.model.mt.AbstractMT;


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
        // Add Field32A
        block4 .append(getField32A(transaction));
        // Add Field58A
        block4 .append(getField58A(transaction));
        // Add Field53B
        block4 .append(getField53B(transaction));
        // Add Field57A
        block4 .append(getField57A(transaction));
        AbstractMT abstractMT = AbstractMT.create(Integer.valueOf("202"));
        abstractMT.getSwiftMessage().setBlock4(block4);
        return abstractMT.message();
    }

    /**
     * This auto generate field methods
     * 
     */
    private Field getField32A(Transaction transaction) {
        Field32A field32A = new Field32A();
        field32A.setCurrency(transaction.CurrencyCode());
        field32A.setAmount(transaction.Amount());
        field32A.setDate(transaction.Date());
        return field32A;
    }

    /**
     * This auto generate field methods
     * 
     */
    private Field getField58A(Transaction transaction) {
        Field58A field58A = new Field58A();
        field58A.setAccount(transaction.ReceiverAccountNumber());
        field58A.setBIC(transaction.ReceiverInstitutionBIC());
        return field58A;
    }

    /**
     * This auto generate field methods
     * 
     */
    private Field getField53B(Transaction transaction) {
        Field53B field53B = new Field53B();
        field53B.setAccount(transaction.SenderAccountNumber());
        field53B.setLocation(transaction.SenderInstitutionName());
        return field53B;
    }

    /**
     * This auto generate field methods
     * 
     */
    private Field getField57A(Transaction transaction) {
        Field57A field57A = new Field57A();
        field57A.setBIC(transaction.ReceiverBankBICReceiverBankBranch());
        return field57A;
    }

}
