import org.omg.sysml.interactive.SysMLInteractive;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        var sysMLRpcServer = new SysMLRpcServer();
        SysMLInteractive.getInstance().setVerbose(false);

        // load sysml libraries
        File file = new File("sysml/sysml.library");
        String absolutePath = file.getAbsolutePath();
        SysMLInteractive.getInstance().loadLibrary(absolutePath);

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
