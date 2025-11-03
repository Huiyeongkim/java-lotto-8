package lotto;

import java.util.Arrays;

public enum Rank {
    FIRST(6, 2_000_000_000, false),
    SECOND(5, 30_000_000, true),
    THIRD(5, 1_500_000, false),
    FOURTH(4, 50_000, false),
    FIFTH(3, 5_000, false),
    NONE(0, 0, false);

    private final int matchCount;
    private final long prize;
    private final boolean bonusMatch;

    Rank(int matchCount, long prize, boolean requireBonus) {
        this.matchCount = matchCount;
        this.prize = prize;
        this.bonusMatch = requireBonus;
    }

    public static Rank valueOf(int matchCount, boolean bonusMatch) {
        return Arrays.stream(values())
                .filter(rank -> rank.matchCount == matchCount)
                .filter(rank -> rank.bonusMatch == bonusMatch)
                .findFirst()
                .orElse(NONE);
    }

    public long getPrize() {
        return prize;
    }

    public int getMatchCount() {
        return matchCount;
    }
}