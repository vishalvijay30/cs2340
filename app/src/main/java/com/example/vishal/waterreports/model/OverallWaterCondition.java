public enum OverallWaterCondition {

    SAFE("Safe"), TREATABLE("Treatable"), UNSAFE("Unsafe");

    private String condition;

    private OverallWaterCondition(String condition) {
        this.condition = condition;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}