package entity;

public class EmployeePRJ {
    private long employeeId;
    private long projectId;

    public EmployeePRJ() {
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeePRJ that = (EmployeePRJ) o;

        if (getEmployeeId() != that.getEmployeeId()) return false;
        return getProjectId() == that.getProjectId();
    }

    @Override
    public int hashCode() {
        int result = (int) (getEmployeeId() ^ (getEmployeeId() >>> 32));
        result = 31 * result + (int) (getProjectId() ^ (getProjectId() >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "EmployeePRJ{" +
                "employeeId=" + employeeId +
                ", projectId=" + projectId +
                '}';
    }
}
