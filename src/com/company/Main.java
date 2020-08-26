package com.company;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import sailpoint.api.SailPointContext;
import sailpoint.api.SailPointFactory;
import sailpoint.object.Bundle;
import sailpoint.object.Filter;
import sailpoint.object.QueryOptions;
import sailpoint.server.ExportVisitor;
import sailpoint.server.Exporter.Cleaner;
import sailpoint.spring.SpringStarter;
import sailpoint.tools.GeneralException;
import sailpoint.tools.Util;

public class Main {

    public static void main(String[] args) {
	// write your code here

        ist propertiesToClean = new ArrayList();
        propertiesToClean.add("id");
        propertiesToClean.add("created");
        propertiesToClean.add("modified");
        Cleaner cleaner = new Cleaner(propertiesToClean);


        try {
            List<Bundle> roles = context.getObjects(Bundle.class);
            System.out.println(roles);
            for (Bundle bun : roles){
                try{
                    if(null !=bun.getType() && bun.getType().equalsIgnoreCase("business")){
                        //new ExportVisitor(context).visit(bun);
                        String xml = bun.toXml();
                        System.out.println("raw xml" + xml);
                        if (propertiesToClean != null){
                            xml = cleaner.clean(xml);
                            System.out.println("clean xml" + xml);
                        }
                        Util.writeFile("C://Temp//"+bun.getName()+".xml", xml);
                    }
                }catch (GeneralException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } catch (GeneralException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            try {
                SailPointFactory.releaseContext(context);
            } catch (GeneralException e) {
                e.printStackTrace();
            }
        }

    }
    }
}