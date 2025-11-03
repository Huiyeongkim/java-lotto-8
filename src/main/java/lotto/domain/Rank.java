package lotto.domain;

import java.util.Arrays;

public enum Rank {
    FIRST(6, 2_000_000_000, false, "6개 일치 (2,000,000,000원)"),
    SECOND(5, 30_000_000, true, "5개 일치, 보너스 볼 일치 (30,000,000원)"),
    THIRD(5, 1_500_000, false, "5개 일치 (1,500,000원)"),
    FOURTH(4, 50_000, false, "4개 일치 (50,000원)"),
    FIFTH(3, 5_000, false, "3개 일치 (5,000원)"),
    NONE(0, 0, false, "");

    private final int matchCount;
    private final long prize;
    private final boolean bonusMatch;
    private final String description;

    Rank(int matchCount, long prize, boolean requireBonus, String description) {
        this.matchCount = matchCount;
        this.prize = prize;
        this.bonusMatch = requireBonus;
        this.description = description;
    }

    public static Rank valueOf(int matchCount, boolean bonusMatch) {
        if (matchCount == 5 && bonusMatch) {
            return SECOND;
        }
        return Arrays.stream(values())
                .filter(rank -> rank.matchCount == matchCount)
                .filter(rank -> !rank.bonusMatch)
                .findFirst()
                .orElse(NONE);
    }

    public long getPrize() {
        return prize;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public String getDescription() {
        return description;
    }
}