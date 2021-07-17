import java.util.List;

public interface SysMLKernel {
    EvaluationResult eval(String content);
    String getSvg(List<String> names, List<String> views, List<String> styles, List<String> help);
}
