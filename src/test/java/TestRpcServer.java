import org.junit.Assert;
import org.junit.Test;
import org.omg.sysml.interactive.SysMLInteractive;

import java.io.File;

public class TestRpcServer {
    private static final String inputVehicle =  "package 'Part Definition Example' {" +
                                                "	import ScalarValues::*;" +
                                                "		part def Vehicle {" +
                                                "		    attribute mass : Real;" +
                                                "		    attribute status : VehicleStatus;" +
                                                "			part eng : Engine;" +
                                                "			ref part driver : Person;" +
                                                "	    }" +
                                                "" +
                                                "       attribute def VehicleStatus {" +
                                                "		    gearSetting : Integer;" +
                                                "		    acceleratorPosition : Real;" +
                                                "	    }" +
                                                "" +
                                                "       part def Engine;" +
                                                "		part def Person;" +
                                                "}";

    private SysMLRpcServer bootstrapSysMLRpcServer() {
        var rpcServer = new SysMLRpcServer();

        File file = new File("resources/sysml.library");
        String absolutePath = file.getAbsolutePath();
        SysMLInteractive.getInstance().loadLibrary(absolutePath);

        return rpcServer;
    }

    @Test
    public void TestEval() {
        var rpcServer = bootstrapSysMLRpcServer();
        var input = inputVehicle;

        var result = rpcServer.sysMlKernel.eval(input);

//        Assert.assertEquals();
    }

    @Test
    public void TestEvalWithRpc() {

//        var input = String.format("{\"jsonrpc\": \"2.0\", \"method\": \"eval\", \"params\": [\"%s\"], \"id\": 0}", inputData);

        //            sysMLRpcServer.respond(new ByteArrayInputStream(input.getBytes()), System.out);

//        {"jsonrpc": "2.0", "method": "eval", "params": ["package Sys { import ScalarValues::*; }"], "id": 0}
    }

}
