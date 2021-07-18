import org.omg.sysml.interactive.SysMLInteractive;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        var sysMLRpcServer = new SysMLRpcServer();
        SysMLInteractive.getInstance().setVerbose(false);

        // load sysml libraries
        String lib;
        var runningFile = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        if(runningFile.isFile()) {  // Run with JAR file
            lib = (new File(runningFile.getParentFile().getAbsolutePath() + "/sysml.library")).getAbsolutePath();
        } else { // Run with IDE
            lib = (new File("sysml.library")).getAbsolutePath();
        }
        SysMLInteractive.getInstance().loadLibrary(lib);

        // jsonRpc server loop
        while(true) {
            try {
                sysMLRpcServer.respond(System.in, System.out);
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        }
    }
}
