package pl.touk.sonar.gerrit;

import java.util.List;
import java.util.Map;

/**
 * {
 * "message": "Some nits need to be fixed.",
 * "labels": {
 * "Code-Review": -1
 * },
 * "comments": {
 * "gerrit-server/src/main/java/com/google/gerrit/server/project/RefControl.java": [
 * {
 * "line": 23,
 * "message": "[nit] trailing whitespace"
 * },
 * {
 * "line": 49,
 * "message": "[nit] s/conrtol/control"
 * }
 * ]
 * }
 * }
 */
public class ReviewInput {
    public static final String CODE_REVIEW = "Code-Review";
    public String message = "TODO";
    public Map<String, Integer> labels;
    public Map<String, List<ReviewComment>> comments;

    public void setLabelToPlusOne() {
        labels.put(CODE_REVIEW, 1);
    }

    public void setLabelToMinusOne() {
        labels.put(CODE_REVIEW, -1);
    }
}
