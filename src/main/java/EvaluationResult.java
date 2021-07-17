import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.eclipse.xtext.validation.Issue;
import org.omg.sysml.interactive.SysMLInteractiveResult;

import java.util.List;
import java.util.stream.Collectors;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class EvaluationResult {
    private final List<Issue> issues;
    private final List<Issue> syntaxErrors;
    private final List<Issue> semanticErrors;
    private final List<Issue> warnings;
    private final List<SysMLElement> content;

    public EvaluationResult(SysMLInteractiveResult result) {
        issues = result.getIssues();
        syntaxErrors = result.getSyntaxErrors();
        semanticErrors = result.getSemanticErrors();
        warnings = result.getWarnings();
        content = result.getRootElement().getOwnedElement().stream().map(SysMLElement::new).collect(Collectors.toList());
    }
}
