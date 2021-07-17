import com.googlecode.jsonrpc4j.JsonRpcServer;
import com.googlecode.jsonrpc4j.ProxyUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SysMLRpcServer {
    SysMLKernel sysMlKernel = new SysMLKernelImpl();

    Object compositeService = ProxyUtil.createCompositeServiceProxy(
            this.getClass().getClassLoader(),
            new Object[] { sysMlKernel },
            new Class<?>[] { SysMLKernel.class },
            false);

    JsonRpcServer jsonRpcServer = new JsonRpcServer(compositeService);

    public int respond(InputStream is, OutputStream os) throws IOException {
        return jsonRpcServer.handleRequest(is, os);
    }
}
