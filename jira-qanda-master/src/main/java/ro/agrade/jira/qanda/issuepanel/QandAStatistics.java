package ro.agrade.jira.qanda.issuepanel;

/**
 * The statistics
 *
 * @author Radu Dumitriu (rdumitriu@gmail.com)
 * @since 1.0
 */
public class QandAStatistics {
    private int total;
    private int unresolved;

    public QandAStatistics(int total, int unresolved) {
        this.total = total;
        this.unresolved = unresolved;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getUnresolved() {
        return unresolved;
    }

    public void setUnresolved(int unresolved) {
        this.unresolved = unresolved;
    }
}
