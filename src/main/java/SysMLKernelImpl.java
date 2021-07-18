import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.xtext.validation.Issue;
import org.omg.sysml.interactive.SysMLInteractive;

import java.util.List;

public class SysMLKernelImpl implements SysMLKernel {

    @JsonIgnoreProperties("uriToProblem")
    private interface IssueMixIn {}


    private ObjectMapper objectMapper;

    public SysMLEvaluationResult eval(String content) {
        try {
            var sysMlResult = SysMLInteractive.getInstance().eval(content);
            return new SysMLEvaluationResult(sysMlResult);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }

        return null;
    }

    public String getSvg(List<String> names, List<String> views, List<String> styles, List<String> help) {
        try {
            var vizResult = SysMLInteractive.getInstance().viz(names, views, styles, help);
            return vizResult.getSVG();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }

        return null;
    }

    public SysMLKernelImpl() {
        objectMapper = new ObjectMapper();
        objectMapper.addMixIn(Issue.class, IssueMixIn.class);
    }
}
