package com.example.fn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import oracle.nosql.driver.NoSQLHandle;
import oracle.nosql.driver.NoSQLHandleConfig;
import oracle.nosql.driver.NoSQLHandleFactory;
import oracle.nosql.driver.Region;
import oracle.nosql.driver.iam.SignatureProvider;
import oracle.nosql.driver.iam.SignatureProvider.ResourcePrincipalClaimKeys;
import oracle.nosql.driver.ops.DeleteRequest;
import oracle.nosql.driver.ops.DeleteResult;
import oracle.nosql.driver.ops.GetRequest;
import oracle.nosql.driver.ops.GetResult;
import oracle.nosql.driver.ops.GetTableRequest;
import oracle.nosql.driver.ops.ListTablesRequest;
import oracle.nosql.driver.ops.ListTablesResult;
import oracle.nosql.driver.ops.PrepareRequest;
import oracle.nosql.driver.ops.PrepareResult;
import oracle.nosql.driver.ops.PutRequest;
import oracle.nosql.driver.ops.QueryRequest;
import oracle.nosql.driver.ops.QueryResult;
import oracle.nosql.driver.ops.TableLimits;
import oracle.nosql.driver.ops.TableRequest;
import oracle.nosql.driver.ops.TableResult;
import oracle.nosql.driver.ops.WriteMultipleRequest;
import oracle.nosql.driver.ops.PutRequest.Option;
import oracle.nosql.driver.values.MapValue;

public class HelloFunction {
	/* Name of your table */
    String tableName = "HelloWorldTable";
		
    public String handleRequest(String input) {
        System.err.println(input);
        String tableName = "JavaFunction";

        System.err.println("OCI_RESOURCE_PRINCIPAL_VERSION " + System.getenv("OCI_RESOURCE_PRINCIPAL_VERSION"));
        System.err.println("OCI_RESOURCE_PRINCIPAL_REGION " + System.getenv("OCI_RESOURCE_PRINCIPAL_REGION"));
        System.err.println("OCI_RESOURCE_PRINCIPAL_RPST " + System.getenv("OCI_RESOURCE_PRINCIPAL_RPST"));
        System.err.println("OCI_RESOURCE_PRINCIPAL_PRIVATE_PEM " + System.getenv("OCI_RESOURCE_PRINCIPAL_PRIVATE_PEM"));         
        SignatureProvider sp = SignatureProvider.createWithResourcePrincipal();
        String compartmentId = sp.getResourcePrincipalClaim(
            ResourcePrincipalClaimKeys.COMPARTMENT_ID_CLAIM_KEY);
        System.err.println("Compartment id " + compartmentId);
        String tenantId = sp.getResourcePrincipalClaim(
            ResourcePrincipalClaimKeys.TENANT_ID_CLAIM_KEY);
        System.err.println("Tenant id " + tenantId);

        NoSQLHandleConfig config = new NoSQLHandleConfig(Region.US_PHOENIX_1,
                                                         sp);
        NoSQLHandle handle = NoSQLHandleFactory.createNoSQLHandle(config);
//        TableLimits limits = new TableLimits(1, 2, 3);
//        TableRequest treq = new TableRequest()
//            .setCompartment(compartmentId)
//            .setStatement("create table if not exists " + tableName +
//                          "(id integer, name string, primary key(id))")
//            .setTableLimits(limits);
//        TableResult tres = handle.tableRequest(treq);
//        System.err.println("Creating table " + tableName);
//
//        tres.waitForCompletion(handle,
//                               20000,
//                               1000);
//
//        System.err.println("Created table " + tableName);

        ListTablesRequest lreq = new ListTablesRequest()
            .setCompartment(compartmentId)
            .setLimit(0);
        ListTablesResult ltr = handle.listTables(lreq);

        System.err.println("ListTable results: " +
                           Arrays.toString(ltr.getTables()));
//        MapValue value = new MapValue().put("id", 1).put("name", "myname");
//        PutRequest putRequest = new PutRequest()
//            .setValue(value)
//            .setCompartment(compartmentId)
//            .setTableName(tableName);
//
//        handle.put(putRequest);
//        System.err.println("Executed put, value: " + value.toString());
//
//        MapValue key = new MapValue().put("id", 1);
//        GetRequest getRequest = new GetRequest()
//            .setKey(key)
//            .setCompartment(compartmentId)
//            .setTableName(tableName);
//
//        GetResult getRes = handle.get(getRequest);
//        System.err.println("Executed get, value: " + getRes.getValue());

        return "Java Function Example Succeed";
    }
		 

}