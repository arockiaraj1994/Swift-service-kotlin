package com.example.build

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.sun.codemodel.JCodeModel
import com.sun.codemodel.JMod
import java.io.File
import com.sun.codemodel.JExpr
import com.sun.codemodel.JType
import com.example.model.Transaction
import com.prowidesoftware.swift.model.SwiftBlock1
import com.prowidesoftware.swift.model.SwiftBlock2
import com.prowidesoftware.swift.model.SwiftBlock3
import com.prowidesoftware.swift.model.SwiftBlock4
import com.beust.klaxon.JsonArray

class Template {
    fun build() {
        val templateObject = parse("src/main/template/MT202.json") as JsonObject
        val fileName: String = "MT202G"
        val codeModel = JCodeModel();
        
        
        // create package
        val javaPackage = codeModel._package("com.example.template");
        
        var definedClass = javaPackage._class(fileName);
        definedClass.javadoc().add("Auto Generated class.");
        var method = definedClass.method(JMod.PUBLIC, String::class.java, "getSwiftMessage");
        method.javadoc().add("This method returns swift message");
        method.param(Transaction::class.java, "transaction");
        var block = method.body();
        
        var methodParams = method.listParams();
        var swiftBlock4 = codeModel.ref(SwiftBlock4::class.java);

                
        block.directStatement("// construct block 4");
        var block4Var = block.decl(swiftBlock4, "block4", JExpr._new(swiftBlock4));
        
        for ((key,value) in templateObject.get("block4") as JsonObject) {
            var field = codeModel.ref(Class.forName("com.prowidesoftware.swift.model.field."+key));
            var fieldVar = block.decl(field, (value as JsonObject).get("name") as String, JExpr._new(field));
            
            for(i in value.get("params") as JsonArray<JsonObject>){
                for ((paramKey,paramValue) in i ) {
                    println("set"+paramKey)
                	block.add(fieldVar.invoke("set"+paramKey).arg(methodParams[0].invoke("get"+paramValue)))
                }
                block.add(block4Var.invoke("append").arg(fieldVar));
            }
            
        }
        var jVarMT = block.decl(definedClass,"myMT", JExpr._new(definedClass));
        
        if(block4Var != null){
            block.add(jVarMT.invoke("getSwiftMessage").invoke("setBlock4").arg(block4Var));
        }
        block._return(jVarMT.invoke("message"));
        codeModel.build(File("src/main/java/"));
    }

    fun parse(name: String): Any {
        val inputStream = File(name).inputStream()
        return Parser().parse(inputStream)!!
    }
}

fun main(args: Array<String>) {
    Template().build()
}

