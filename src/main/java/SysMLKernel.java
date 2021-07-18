import java.util.List;

public interface SysMLKernel {
    SysMLEvaluationResult eval(String content);
    String getSvg(List<String> names, List<String> views, List<String> styles, List<String> help);
}
