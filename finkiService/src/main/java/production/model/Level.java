package production.model;

public enum Level {
    SUBMITTED("Submitted", 1),APPROVED_BY_PRODEKAN("ApprovedByProdekan", 2),
    APPROVED_BY_MENTOR("ApprovedByMentor", 3),APPROVED_BY_COMMISSION("ApprovedByCommission", 4),
    SCHEDULED("Scheduled", 5), ARCHIVE("Archive", 6);

    private final String key;
    private final Integer value;


    Level(String key, Integer value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }
    public Integer getValue() {
        return value;
    }

}
